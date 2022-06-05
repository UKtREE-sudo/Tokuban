package jp.co.bungeejump.tokuban.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.bungeejump.tokuban.dao.real.TokubanJdbcDaoImpl;

/**
 * 認証用
 * @author 周東
 * @version 0.2.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	// ログイン情報をDBから持ってくる用
	@Autowired
	private DataSource dataSource;


	// ログイン情報をDBから持ってくる用。
	//private static final String USER_QUERY = "SELECT mail_address, password, TRUE FROM member WHERE mail_address = ?";
	private static final String USER_QUERY = "SELECT mail_address, password, user_id FROM m_user WHERE mail_address = ? AND is_active = true";
	private static final String ROLES_QUERY = "SELECT mail_address, role FROM m_user WHERE mail_address = ?";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/dontSubmit").permitAll()
				.antMatchers("/wip").permitAll()
				.antMatchers("/top").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/registerCheck").permitAll()
				.antMatchers("/loginFail").permitAll()
				.antMatchers("/premium/**").hasRole("PAID")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/processLogin")
				.defaultSuccessUrl("/loginSuccess", true)
				.failureUrl("/loginFail")
				.usernameParameter("inputMail")
				.passwordParameter("inputPass")
				.and()
			.logout()
				.logoutUrl("/processLogout")
				.logoutSuccessUrl("/logoutSuccess")
				.and()
			.csrf()
				.disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		/*auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(USER_QUERY)
			.authoritiesByUsernameQuery(ROLES_QUERY)
			.passwordEncoder(new BCryptPasswordEncoder());*/
		TokubanJdbcDaoImpl userService = new TokubanJdbcDaoImpl();
		userService.setDataSource(dataSource);
		userService.setUsersByUsernameQuery(USER_QUERY);
		userService.setAuthoritiesByUsernameQuery(ROLES_QUERY);

		auth.userDetailsService(userService);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

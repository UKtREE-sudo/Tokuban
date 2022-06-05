package jp.co.bungeejump.tokuban.dao.real;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import jp.co.bungeejump.tokuban.entity.real.MemberDetails;

/**
 * 認証用
 * @author 周東
 *
 */
public class TokubanJdbcDaoImpl extends JdbcDaoImpl{
	@SuppressWarnings("deprecation")
	@Override
	protected List<UserDetails> loadUsersByUsername(String mailAddress){
		return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] {mailAddress}, new RowMapper<UserDetails>() {
			public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

				String mailAddress = rs.getString("mail_address");
				String password = rs.getString("password");

				Integer userId = rs.getInt("user_id");

				MemberDetails user = new MemberDetails(mailAddress, password, AuthorityUtils.NO_AUTHORITIES);

				user.setUserId(userId);
				return user;
			}
		});
	}

	protected UserDetails createUserDetails(
		String mailAddress,
		UserDetails userFromUserQuery,
		List<GrantedAuthority> combinedAuthorities
	) {
		MemberDetails origin = (MemberDetails) userFromUserQuery;
		String loginAddress = origin.getUsername();
		String password = origin.getPassword();

		Integer userId = origin.getUserId();
		MemberDetails user = new MemberDetails(loginAddress, password, combinedAuthorities);
		user.setUserId(userId);
		return user;
	}

}

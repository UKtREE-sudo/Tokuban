
package jp.co.bungeejump.tokuban.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	public SecurityWebApplicationInitializer () {
		super(SecurityConfig.class);
	}


}

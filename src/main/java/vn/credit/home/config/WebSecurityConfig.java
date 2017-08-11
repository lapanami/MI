package vn.credit.home.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import vn.credit.home.config.ext.MyUserDetailsContextMapper;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${ldap.domain}")
	private String DOMAIN;

	@Value("${ldap.url}")
	private String URL;

	@Value("${server.port}")
	private int httpPort;

	@Autowired
	MyUserDetailsContextMapper myUserDetailsContextMapper;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(new CSRFTokenGeneratorFilter(), CsrfFilter.class).authorizeRequests()
				.antMatchers("/static/**", "/", "/logout", "/images/**", "/error").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/").defaultSuccessUrl("/Information/COUNTRY")
				.failureUrl("/?error=errorLoginFail").and().exceptionHandling()
				.accessDeniedPage("/?error=errorAccessDenied").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.invalidateHttpSession(true).and().csrf().ignoringAntMatchers("/**")
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.authenticationProvider(activeDirectoryLdapAuthenticationProvider())
				.userDetailsService(userDetailsService());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Arrays.asList(activeDirectoryLdapAuthenticationProvider()));
	}

	@Bean
	public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(DOMAIN, URL);
		provider.setConvertSubErrorCodesToExceptions(true);
		provider.setUseAuthenticationRequestCredentials(true);
		provider.setUserDetailsContextMapper(myUserDetailsContextMapper);
		return provider;
	}
}

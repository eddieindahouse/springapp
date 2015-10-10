package springapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
			
			auth
				.jdbcAuthentication()
					.dataSource(dataSource)
					.usersByUsernameQuery("select username, password, enabled from users where username=?")
					.authoritiesByUsernameQuery("select username, authority from users where username=?");
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		
		http
			.formLogin()
				.loginPage("/login.html")
				.defaultSuccessUrl("/loginSuccess.html")
				.failureUrl("/loginFail.html")
			.and()
				.authorizeRequests()
					
					// login requests
					.antMatchers("/login.html").permitAll()
					.antMatchers("/checkUniqueUsername.html").permitAll()
					.antMatchers("/checkUniqueEmail.html").permitAll()
					.antMatchers("/submitRegistration.html").permitAll()
					.antMatchers("/css/login.css").permitAll()
					.antMatchers("/js/login.js").permitAll()
					.antMatchers("/images/background.jpg").permitAll()
					.antMatchers("/loginFail.html").permitAll()
					.antMatchers("/loginSuccess.html").permitAll()
					
					// all other requests
					.anyRequest().hasRole("USER")
			.and()
				.httpBasic()
			.and()
				.csrf()
					.disable();
	}

}

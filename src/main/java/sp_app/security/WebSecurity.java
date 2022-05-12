package sp_app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sp_app.Application;
import sp_app.entities.User;
import sp_app.roles.Role;
import sp_app.service.UserDetailsServiceImpl;

import java.util.Arrays;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter
{
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder(10);
	}

	public WebSecurity()
	{
		User admin = new User("Petar", "Petrovic", "admin@mail.com", new BCryptPasswordEncoder(10).encode("admin123"),
				Role.ADMIN);
		Application.users.put(admin.getEmail(), admin);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/register").permitAll()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/user/**").access("hasRole('ROLE_USER')")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.successHandler(loginSuccessHandler)
				.permitAll()
				.and()
				.logout()
				.permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)
			throws Exception
	{
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource()
	{
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "PATCH"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.addExposedHeader("Authorization");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
package sp_app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sp_app.Application;
import sp_app.entities.User;


@Component
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		if (username == null || username.isEmpty())
		{
			throw new UsernameNotFoundException("username is empty");
		}

		User foundUser = Application.users.get(username);
		if (foundUser != null)
		{
			return foundUser;
		}
		throw new UsernameNotFoundException(username + "is not found");
	}
}

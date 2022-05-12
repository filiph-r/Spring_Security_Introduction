package sp_app.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sp_app.roles.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class User implements UserDetails
{
	private String ROLE_PREFIX = "ROLE_";

	private String ime;
	private String prezime;
	private String email;
	private String password;
	private Role role;

	public User(String ime, String prezime, String email, String password, Role role)
	{
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		List<GrantedAuthority> list = new ArrayList<>();

		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

		return list;
	}

	@Override
	public String getUsername()
	{
		return email;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	public String getIme()
	{
		return ime;
	}

	public void setIme(String ime)
	{
		this.ime = ime;
	}

	public String getPrezime()
	{
		return prezime;
	}

	public void setPrezime(String prezime)
	{
		this.prezime = prezime;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}
}

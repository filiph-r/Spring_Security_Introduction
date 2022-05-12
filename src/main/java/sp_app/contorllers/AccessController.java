package sp_app.contorllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sp_app.Application;
import sp_app.entities.User;
import sp_app.roles.Role;


@RestController
public class AccessController
{

	@Autowired
	private PasswordEncoder pwEncoder;

	@GetMapping("/login")
	public ModelAndView login()
	{
		return new ModelAndView("login");
	}

	@PostMapping("/register")
	@ResponseBody
	public ModelAndView register(String name, String surname, String email, String registrationPassword)
	{
		try
		{
			// Proveravamo da li korisnik sa datim email-om vec postoji
			if (Application.users.containsKey(email))
			{
				return new ModelAndView("login");
			}

			// Kreiramo i pamtimo novog korisnika
			final User newUser = new User(name, surname, email,
					pwEncoder.encode(registrationPassword), Role.USER);
			Application.users.put(newUser.getEmail(), newUser);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return new ModelAndView("login");
	}
}

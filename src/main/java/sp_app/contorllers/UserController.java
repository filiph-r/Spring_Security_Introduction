package sp_app.contorllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sp_app.Application;
import sp_app.entities.User;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController
{

	@GetMapping("/home")
	public ModelAndView homeForUser(Principal principal)
	{
		final User user = Application.users.get(principal.getName());
		final String name = user.getIme() + " " + user.getPrezime();

		return (new ModelAndView("home")).addObject("name", name);
	}
}

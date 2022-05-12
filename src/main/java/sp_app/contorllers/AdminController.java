package sp_app.contorllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sp_app.Application;
import sp_app.entities.User;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController
{
	@GetMapping("/home")
	public ModelAndView homeForAdmin(Principal principal)
	{
		final User user = Application.users.get(principal.getName());
		final String name = user.getIme() + " " + user.getPrezime();

		List<User> userList = new ArrayList<>(Application.users.values());
		return (new ModelAndView("adminHome")).addObject("name", name)
				.addObject("userList", userList);
	}
}

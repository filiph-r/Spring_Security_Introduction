package sp_app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import sp_app.entities.User;
import sp_app.roles.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException
	{

		User user = (User) authentication.getPrincipal();

		String redirectURL = request.getContextPath();

		if (user.getRole().equals(Role.USER))
		{
			redirectURL = "user/home";
		}
		else if (user.getRole().equals(Role.ADMIN))
		{
			redirectURL = "admin/home";
		}

		response.sendRedirect(redirectURL);
	}

}

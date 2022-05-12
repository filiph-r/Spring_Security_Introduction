package sp_app;


import sp_app.entities.User;

import java.util.HashMap;
import java.util.Map;


@org.springframework.boot.autoconfigure.SpringBootApplication
public class Application
{
	// Ova mapa nam zamenjuje DB [Key -> Email] [Value -> User]
	public static Map<String, User> users = new HashMap<>();

	public static void main(String[] args)
	{
		org.springframework.boot.SpringApplication.run(Application.class, args);
	}
}

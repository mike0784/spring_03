package proba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ProbaApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//database db = new database("users.sqlite");
		SpringApplication.run(ProbaApplication.class, args);
	}

}

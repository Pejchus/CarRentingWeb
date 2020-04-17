package tygri.pujcovna;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import tygri.pujcovna.model.AuthorityType;
import tygri.pujcovna.services.UserService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ApplicationRunner init(UserService service) {
        return (ApplicationArguments args) -> dataSetup(service);
    }

    //init db on launch, works only when db is empty
    public void dataSetup(UserService service) {
        service.createAuthority(AuthorityType.ROLE_ADMIN);
        service.createAuthority(AuthorityType.ROLE_CUSTOMER);
        service.createAuthority(AuthorityType.ROLE_EMPLOYEE);
        service.createUser("admin", "admin", "admin@admin.com", "true", "777777777", "25101", "admin", "vonAdmin", "Praha", "Parizska", "4", "ADMIN");
    }
}

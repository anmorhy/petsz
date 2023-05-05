package petsis.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages="petsis.pet.model")
@ComponentScan(basePackages={"petsis.*"})
@EnableJpaRepositories(basePackages = {"petsis.pet.repository"})
@EnableTransactionManagement
// @EnableWebMvc
public class PetApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(PetApplication.class, args);

		// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// String result = encoder.encode("123");
		// System.out.println(result);
	}

	// @Override
	// public void addViewControllers(ViewControllerRegistry registry) {
	// 	registry.addViewController("/login").setViewName("/login");
	// 	registry.setOrder(Ordered.LOWEST_PRECEDENCE);
	// }

}

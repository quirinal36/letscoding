package kr.coding.lets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories("kr.coding.lets.repository")
@EntityScan("kr.coding.lets.model")
@SpringBootApplication
public class LetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetsApplication.class, args);
	}

}

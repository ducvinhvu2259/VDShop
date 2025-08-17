package fu.hanoi.swp.VDShop.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "fu.hanoi.swp.VDShop")
@EntityScan("fu.hanoi.swp.VDShop.entity")
@EnableJpaRepositories("fu.hanoi.swp.VDShop.repository")
public class JavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaApplication.class, args);
	}

}

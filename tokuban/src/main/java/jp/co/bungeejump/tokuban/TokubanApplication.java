package jp.co.bungeejump.tokuban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"jp.co.bungeejump.tokuban.entity.real", "jp.co.bungeejump.tokuban.entity.virtual"})
@EnableJpaRepositories({"jp.co.bungeejump.tokuban.dao.real", "jp.co.bungeejump.tokuban.dao.virtual"})
public class TokubanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokubanApplication.class, args);
	}

}

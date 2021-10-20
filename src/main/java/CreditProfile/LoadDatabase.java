package CreditProfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new User("user1", "test1-email", "123-456" , 100)));
      log.info("Preloading " + repository.save(new User("user2", "test2-email","679-124", 24)));
    };
  }
}
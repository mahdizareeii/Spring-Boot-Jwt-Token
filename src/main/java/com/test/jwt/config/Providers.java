package com.test.jwt.config;

import com.test.jwt.entities.Role;
import com.test.jwt.entities.User;
import com.test.jwt.repository.UserRepository;
import com.test.jwt.util.TokenUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Providers {

    @Bean
    public ApplicationRunner dataBaseInitializer(
            UserRepository repository,
            PasswordEncoder encoder
    ) {
        ApplicationRunner runner = new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                if (repository.findByEmail("admin@admin.com").isEmpty()) {
                    User user = new User();
                    user.setName("admin");
                    user.setEmail("admin@admin.com");
                    user.setPassword("admin");
                    user.setRole(Role.ADMIN);
                    user.setPassword(encoder.encode("admin"));
                    repository.save(user);
                }
            }
        };
        return runner;
    }

    @Bean
    public TokenUtil provideTokenUtil() {
        return new TokenUtil();
    }
}

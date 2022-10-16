package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.repository.UserRepository;
import edu.school21.cinema.repository.UserRepositoryJdbcImpl;
import edu.school21.cinema.service.UserService;
import edu.school21.cinema.service.UserServiceImpl;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    private final Environment environment;

    public ApplicationConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig("/hikari.properties");
//        HikariDataSource dataSource = new HikariDataSource(config);
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(environment.getProperty("driver"));
        dataSource.setJdbcUrl(environment.getProperty("url"));
        dataSource.setSchema(environment.getProperty("schema"));
        dataSource.setUsername(environment.getProperty("username"));
        dataSource.setPassword(environment.getProperty("password"));
        return dataSource;
    }

    @Bean
    UserService userService() {
        return new UserServiceImpl(userRepository(), passwordEncoder());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryJdbcImpl(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}

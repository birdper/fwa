package edu.school21.cinema.repository;

import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM cinema.user WHERE email=?",
                        new BeanPropertyRowMapper<>(User.class),
                        email)
                .stream().findAny().orElse(null);
    }

    @Override
    public void save(User user) {

        jdbcTemplate.update("INSERT INTO cinema.user (first_name, last_name, email, phone_number, password) " +
                        "VALUES(?, ?, ?, ?, ?)",
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword());
    }
}

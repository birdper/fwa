package edu.school21.cinema.repository;

import edu.school21.cinema.models.User;

public interface UserRepository {

    User findByEmail(String email);

    void save(User user);
}

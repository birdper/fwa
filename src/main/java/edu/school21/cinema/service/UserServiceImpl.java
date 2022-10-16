package edu.school21.cinema.service;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

//@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null) {
            String encodedPassword = passwordEncoder
                    .encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
    }
}

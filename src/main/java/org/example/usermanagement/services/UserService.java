package org.example.usermanagement.services;

import lombok.AllArgsConstructor;
import org.example.usermanagement.data.User;
import org.example.usermanagement.repositories.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public User addUser(String name, String lastname, int age, String login, String password) {
        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println("Original password: " + password);
        System.out.println("Hashed password: " + hashedPassword);*/

/*        User user = new User();
        user.setName(name);
        user.setLastname(lastname);
        user.setAge(age);
        user.setLogin(login);
        user.setPassword(hashedPassword);*/
        User user = User.builder().name(name)
                .lastname(lastname)
                .age(age)
                .login(login)
                .password(password)
                .build();
        userRepository.save(user);
        return user;
    }
}

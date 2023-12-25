package org.example.usermanagement.services;

import lombok.AllArgsConstructor;
import org.example.usermanagement.data.User;
import org.example.usermanagement.exeption.UserNotFoundException;
import org.example.usermanagement.repositories.UserRepository;
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
        /*String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);*/

        User user = User.builder().name(name)
                .lastname(lastname)
                .age(age)
                .login(login)
                .password(password)
                .build();
        userRepository.save(user);
        return user;
    }

    public User deleteUser(int id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            userOptional.get().setDelete(true);
            return userOptional.get();
        } else {
            throw new UserNotFoundException("User with id: " + id + " did`nt found");
        }

    }

    public String changeUser(int id, String login, String password) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            userOptional.get().setLogin(login);
            userOptional.get().setPassword(password);
            return userOptional.get().toString();
        }  else {
            throw new UserNotFoundException("User with id: " + id + " did`nt found");
        }
    }
}
/*if (BCrypt.checkpw(password, hashedPassword)) {
            System.out.println("Пароль верный");
        } else {
            System.out.println("Пароль неверный");
        }*/
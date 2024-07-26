package me.dio.service.implementation;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) throws IllegalAccessException {
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalAccessException("Este usuário já existe.");
        }
        return userRepository.save(userToCreate);
    }
}

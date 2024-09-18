package br.com.gs3.tecnico.desafio.services;

import br.com.gs3.tecnico.desafio.domain.entities.User;
import br.com.gs3.tecnico.desafio.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> listAllUsers() {
        return repository.findAll();
    }

    public Optional<User> readById(Integer id) {
        return repository.findById(id);
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public User updateUser(User user) {
        return repository.save(user);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

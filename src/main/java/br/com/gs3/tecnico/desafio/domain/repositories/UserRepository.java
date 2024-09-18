package br.com.gs3.tecnico.desafio.domain.repositories;

import br.com.gs3.tecnico.desafio.domain.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
}

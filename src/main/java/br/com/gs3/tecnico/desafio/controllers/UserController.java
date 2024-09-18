package br.com.gs3.tecnico.desafio.controllers;

import br.com.gs3.tecnico.desafio.adapters.requests.UserRequest;
import br.com.gs3.tecnico.desafio.services.UserService;
import br.com.gs3.tecnico.desafio.domain.entities.User;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    /* Criar um usuário (Retorna 201 para sucesso / 400 para erro) */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest userRequest) {

        User user = new User();
        user.setNome(userRequest.getNome());
        user.setEmail(userRequest.getEmail());
        user.setEndereco(userRequest.getEndereco());
        user.setTelefone(userRequest.getTelefone());

        service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /* Listar todos os usuários (Retorna 200 sempre) */
    @GetMapping("/list")
    public ResponseEntity<List<User>> listAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAllUsers());
    }

    /* Listar usuário por id (Retorna 200 para sucesso / 404 para id inexistente) */
    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<User>> readById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readById(id));
    }

    /* Atualizar um usuário (Retorna 200 para sucesso / 404 para usuário inexistente) */
    @PutMapping("/put/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
        // Verifica se o usuário com o ID fornecido existe
        Optional<User> optUser = service.readById(id);

        // Se o usuário não for encontrado, retorna 404 Not Found
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Atualiza os campos do usuário existente com os valores recebidos no request body
        User existingUser = optUser.get();
        existingUser.setNome(user.getNome());
        existingUser.setEmail(user.getEmail());
        existingUser.setEndereco(user.getEndereco());
        existingUser.setTelefone(user.getTelefone());

        // Chama o serviço para atualizar o usuário
        service.updateUser(existingUser);

        // Retorna 200 OK e o usuário atualizado
        return ResponseEntity.ok(existingUser);
    }

    /* Deletar um usuário (Retorna 204 para sucesso / 400 para id inexistente) */
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        ResponseEntity.status(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = "Erro ao processar a requisição: formato de JSON inválido.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}

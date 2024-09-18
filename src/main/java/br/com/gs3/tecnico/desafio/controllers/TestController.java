package br.com.gs3.tecnico.desafio.controllers;

import br.com.gs3.tecnico.desafio.domain.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/testBusinessException")
    public void throwBusinessException() {
        throw new BusinessException("Mensagem de erro de negocio esperada");
    }

    @PostMapping("/testHttpMessageNotReadable")
    public void throwHttpMessageNotReadableException(@RequestBody String body) {
        throw new org.springframework.http.converter.HttpMessageNotReadableException("Erro de leitura");
    }
}


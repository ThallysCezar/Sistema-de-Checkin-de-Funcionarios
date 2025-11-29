package br.com.thallysprojetos.backenddesafio1.exceptions.workRecord;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ModelNotFoundException extends ResponseStatusException {

    public ModelNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Usuario não encontrado com esse id");
    }

    public ModelNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }

}
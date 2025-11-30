package br.com.thallysprojetos.backenddesafio1.exceptions.employeers;

public class EmployeersAlreadyExistException extends RuntimeException {

    public EmployeersAlreadyExistException() {
        super("Já existe um usuário com esse email");
    }

    public EmployeersAlreadyExistException(String message) {
        super(message);
    }

}
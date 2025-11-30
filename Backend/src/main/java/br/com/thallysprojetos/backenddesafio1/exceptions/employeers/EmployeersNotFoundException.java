package br.com.thallysprojetos.backenddesafio1.exceptions.employeers;

public class EmployeersNotFoundException extends RuntimeException {

    public EmployeersNotFoundException() {
        super("Employee not found with this id");
    }

    public EmployeersNotFoundException(String message) {
        super(message);
    }

}
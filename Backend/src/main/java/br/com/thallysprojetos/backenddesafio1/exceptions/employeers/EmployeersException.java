package br.com.thallysprojetos.backenddesafio1.exceptions.employeers;

public class EmployeersException extends RuntimeException {

    public EmployeersException() {
        super("General error");
    }

    public EmployeersException(String message) {
        super(message);
    }

}

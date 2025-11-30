package br.com.thallysprojetos.backenddesafio1.exceptions.workRecord;

public class WorkRecordsAlreadyExistException extends RuntimeException {

    public WorkRecordsAlreadyExistException() {
        super("Employee(id) already checked-in and did not check-out yet.");
    }

    public WorkRecordsAlreadyExistException(String message) {
        super(message);
    }

}
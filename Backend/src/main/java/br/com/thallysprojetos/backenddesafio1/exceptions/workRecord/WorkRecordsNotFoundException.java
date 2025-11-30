package br.com.thallysprojetos.backenddesafio1.exceptions.workRecord;

public class WorkRecordsNotFoundException extends RuntimeException {

    public WorkRecordsNotFoundException() {
        super("Work record not found with this id");
    }

    public WorkRecordsNotFoundException(String message) {
        super(message);
    }

}
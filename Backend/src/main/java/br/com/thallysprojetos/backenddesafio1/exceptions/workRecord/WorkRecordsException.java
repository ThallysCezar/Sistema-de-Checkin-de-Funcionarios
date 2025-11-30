package br.com.thallysprojetos.backenddesafio1.exceptions.workRecord;

public class WorkRecordsException extends RuntimeException {

    public WorkRecordsException() {
        super("General error");
    }

    public WorkRecordsException(String message) {
        super(message);
    }

}

package br.com.thallysprojetos.backenddesafio1.exceptions;

import br.com.thallysprojetos.backenddesafio1.exceptions.employeers.EmployeersAlreadyExistException;
import br.com.thallysprojetos.backenddesafio1.exceptions.employeers.EmployeersException;
import br.com.thallysprojetos.backenddesafio1.exceptions.employeers.EmployeersNotFoundException;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.WorkRecordsAlreadyExistException;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.WorkRecordsException;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.WorkRecordsNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({WorkRecordsNotFoundException.class, EmployeersNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception ex, HttpServletRequest request) {
        log.warn("Work Records(Id) or Employee(id) Not found: {}", ex.getMessage());
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({WorkRecordsAlreadyExistException.class, EmployeersAlreadyExistException.class})
    public ResponseEntity<ErrorMessage> handleAlreadyExistException(Exception ex, HttpServletRequest request) {
        log.warn("Work Records(Id) or Employee(id) already exists: {}", ex.getMessage());
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({WorkRecordsException.class, EmployeersException.class})
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex, HttpServletRequest request) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        String message = "An internal server error has occurred. Please try again later.";
        return buildErrorResponse(new Exception(message), request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(Exception ex, HttpServletRequest request) {
        log.warn("Illegal argument: {}", ex.getMessage());
        return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorMessage> buildErrorResponse(Exception ex, HttpServletRequest request, HttpStatus status) {
        ErrorMessage errorMessage = new ErrorMessage(request, status, ex.getMessage());
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> buildValidationErrorResponse(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(request, HttpStatus.BAD_REQUEST,
                "Erro de validação nos campos", ex.getBindingResult());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorMessage);
    }

}
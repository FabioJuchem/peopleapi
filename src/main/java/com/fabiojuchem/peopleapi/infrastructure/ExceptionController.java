package com.fabiojuchem.peopleapi.infrastructure;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handle(Exception exception) {
        if (exception instanceof TransactionSystemException
                && exception.getCause() instanceof RollbackException
                && exception.getCause().getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException violationException = (ConstraintViolationException) exception.getCause().getCause();
            Object[] violations = violationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).sorted().toArray();
            BadRequestResponse response = new BadRequestResponse(violations);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.status(400).body(exception.getLocalizedMessage());
    }

    public class BadRequestResponse {

        private final int status = 400;

        private final Object[] violations;

        public BadRequestResponse(Object[] violations) {
            this.violations = violations;
        }

        public int getStatus() {
            return status;
        }

        public Object[] getViolations() {
            return violations;
        }
    }

}

package ua.nure.arkpz.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.nure.arkpz.exception.ValidateException;

import java.util.Map;

@ControllerAdvice
@RequestMapping(produces = "application/json")
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({ValidateException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Map<String, String> handleConflict(ValidateException ex) {
        return ex.getErrorsMap();
    }
}

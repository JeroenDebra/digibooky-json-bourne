package com.switchfully.jsonbourne.api.controllers.controllerexceptions;

import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public void bookNotFound(BookNotFoundException bookNotFoundException, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, bookNotFoundException.getMessage());
    }

}

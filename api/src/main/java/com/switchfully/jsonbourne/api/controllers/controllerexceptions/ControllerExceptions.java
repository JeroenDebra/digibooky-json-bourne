package com.switchfully.jsonbourne.api.controllers.controllerexceptions;

import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.infrastructure.exceptions.DuplicateMemberException;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptions extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptions.class);

    @ExceptionHandler(BookNotFoundException.class)
    public void bookNotFound(BookNotFoundException bookNotFoundException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("someone search for a book that could not be found in the database", bookNotFoundException);

        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, bookNotFoundException.getMessage());
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public void memberAlreadyExists(DuplicateMemberException duplicateMemberException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("some tried to add a memeber that already exists", duplicateMemberException);

        httpServletResponse.sendError(HttpServletResponse.SC_CONFLICT, duplicateMemberException.getMessage());
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public void actionNotAuthorized(NotAuthorizedException notAuthorizedException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("someone tried to do an unauthorized action", notAuthorizedException);
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, notAuthorizedException.getMessage());
    }

}

package com.switchfully.jsonbourne.api.controllers.controllerexceptions;

import com.switchfully.jsonbourne.infrastructure.exceptions.BookNotFoundException;
import com.switchfully.jsonbourne.infrastructure.exceptions.DuplicateMemberException;
import com.switchfully.jsonbourne.infrastructure.exceptions.LoanNotFoundException;
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

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptions.class);

    @ExceptionHandler(BookNotFoundException.class)
    public void bookNotFound(BookNotFoundException bookNotFoundException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("someone searched for a book that could not be found in the database", bookNotFoundException);

        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, bookNotFoundException.getMessage());
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public void memberAlreadyExists(DuplicateMemberException duplicateMemberException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("someone tried to add a member that already exists", duplicateMemberException);

        httpServletResponse.sendError(HttpServletResponse.SC_CONFLICT, duplicateMemberException.getMessage());
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public void actionNotAuthorized(NotAuthorizedException notAuthorizedException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("someone tried to do an unauthorized action", notAuthorizedException);
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, notAuthorizedException.getMessage());
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public void loanCouldNotBeFound(LoanNotFoundException loanNotFoundException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("loan of book you wanter to return could not be found", loanNotFoundException);
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, loanNotFoundException.getMessage());
    }



}

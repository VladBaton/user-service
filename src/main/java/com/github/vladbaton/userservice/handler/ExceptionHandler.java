package com.github.vladbaton.userservice.handler;

import com.github.vladbaton.userservice.exception.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;
import java.util.Set;

@ApplicationScoped
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        if (e instanceof UserNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format(
                            "Пользователь с id %d не найден!", ((UserNotFoundException) e).getUserId()
                    ))
                    .build();
        } else if (e instanceof PersistenceException) {
            return Response.status(Response.Status.CONFLICT).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Неизвестная ошибка сервера " + e.getClass().getSimpleName())
                    .build();
        }
    }
}
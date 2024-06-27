package com.github.vladbaton.userservice.resource;

import com.github.vladbaton.userservice.exception.UserNotFoundException;
import com.github.vladbaton.userservice.resource.pojo.*;
import com.github.vladbaton.userservice.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.ValidationException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
@Tag(name = "Мультиюзер", description = "Предоставляет возможности для манипуляции сразу несколькими записями")
public class UsersResource {
    @Inject
    UserService userService;

    @POST
    @Operation(description = "Позволяет создать сразу несколько пользователей")
    @APIResponse(responseCode = "201", description = "Пользователь создан")
    public Response create(CreateUsersRequest createUsersRequest) throws ConstraintViolationException {
        userService.createUsers(createUsersRequest.getUsers());
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Operation(description = "Позволяет обновить сразу несколько пользователей")
    @APIResponse(responseCode = "204", description = "Данные пользователей изменены")
    public Response update(UpdateUsersRequest updateUsersRequest) throws ConstraintViolationException, UserNotFoundException {

        userService.updateUsers(updateUsersRequest.getUsers());
        return Response.noContent().build();
    }

    @DELETE
    @Operation(description = "Позволяет удалить сразу несколько пользователей")
    @APIResponse(responseCode = "410", description = "Записи о пользователях удалены")
    public Response delete(DeleteUsersRequest deleteUsersRequest) throws UserNotFoundException {
        userService.deleteUsers(deleteUsersRequest.getUserIds());
        return Response.status(Response.Status.GONE).build();
    }
}

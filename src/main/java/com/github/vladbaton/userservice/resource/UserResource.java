package com.github.vladbaton.userservice.resource;

import com.github.vladbaton.userservice.entity.User;
import com.github.vladbaton.userservice.exception.UserNotFoundException;
import com.github.vladbaton.userservice.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.hibernate.engine.spi.Status;

import java.util.List;


@ApplicationScoped
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Морда юзерская", description = "Позволяет просматривать пользователей, создавать новых, удалять старых, изменять всех")
public class UserResource {
    @Inject
    UserService userService;

    @GET
    @Operation(summary = "Получить список пользователей")
    @APIResponse(responseCode = "200")
    public Response getList() {
        return Response.ok(userService.getUserList()).build();
    }

    @GET
    @Path("{userId}")
    @Operation(summary = "Получить пользователя по Id")
    @APIResponse(responseCode = "200")
    public Response getById(@PathParam("userId") Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @POST
    @Operation(summary = "Позволяет создать пользователя")
    @APIResponse(responseCode = "201", description = "Пользователь успешно создан")
    public Response create(User user) throws ConstraintViolationException {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{userId}")
    @Operation(summary = "Позволяет изменить пользователя с данным Id")
    @APIResponse(responseCode = "204", description = "Данные пользователя изменены")
    public Response update(@PathParam("userId") Long userId, User user) throws ConstraintViolationException, UserNotFoundException {
        userService.updateUser(userId, user);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{userId}")
    @Operation(summary = "Позволяет удалить пользователя с данным Id")
    @APIResponse(responseCode = "410", description = "Пользователь удалён")
    public Response delete(@PathParam("userId") Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return Response.status(Response.Status.GONE).build();
    }


}

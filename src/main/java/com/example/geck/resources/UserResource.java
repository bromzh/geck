package com.example.geck.resources;

import com.example.geck.dao.UserDao;
import com.example.geck.entities.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("users")
@Consumes({"application/json"})
@Produces({"application/json"})
public class UserResource {

    @EJB
    UserDao dao;

    @GET
    public List<User> getAll() {
        return dao.findAll();
    }

    @POST
    public User create(User item) {
        dao.create(item);
        return item;
    }

    @Path("{id}")
    @GET
    public User getOne(@PathParam("id") Integer id) {
        return dao.find(id);
    }

    @Path("{id}")
    @PUT
    public User update(@PathParam("id") Integer id, User item) {
        item.setId(id);
        dao.edit(item);
        return item;
    }

    @Path("{id}")
    @DELETE
    public void delete(@PathParam("id") Integer id) {
        dao.remove(dao.find(id));
    }
}

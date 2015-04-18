package com.example.geck.resources;

import com.example.geck.beans.UserBean;
import com.example.geck.entities.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("users")
@Consumes({"application/json"})
@Produces({"application/json"})
public class UserResource {

    @EJB
    UserBean bean;

    @GET
    public List<User> getAll() {
        return bean.findAll();
    }

    @POST
    public User create(User item) {
        bean.create(item);
        return item;
    }

    @Path("{id}")
    @GET
    public User getOne(@PathParam("id") Integer id) {
        return bean.find(id);
    }

    @Path("{id}")
    @PUT
    public User update(@PathParam("id") Integer id, User item) {
        item.setId(id);
        bean.edit(item);
        return item;
    }

    @Path("{id}")
    @DELETE
    public void delete(@PathParam("id") Integer id) {
        bean.remove(bean.find(id));
    }
}

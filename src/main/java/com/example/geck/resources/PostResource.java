package com.example.geck.resources;

import com.example.geck.dao.PostDao;
import com.example.geck.entities.Post;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("posts")
@Consumes({"application/json"})
@Produces({"application/json"})
public class PostResource {

    @EJB
    PostDao dao;

    @GET
    public List<Post> getAll() {
        return dao.findAllWithLazy("owner");
    }

    @POST
    public Post create(Post item) {
        dao.create(item);
        return dao.findWithLazy(item.getId(), "owner");
    }

    @Path("{id}")
    @GET
    public Post getOne(@PathParam("id") Integer id) {
        return dao.findWithLazy(id, "owner");
    }

    @Path("{id}")
    @PUT
    public Post update(@PathParam("id") Integer id, Post item) {
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

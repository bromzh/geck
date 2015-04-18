package com.example.geck.resources;

import com.example.geck.beans.PostBean;
import com.example.geck.entities.Post;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("posts")
@Consumes({"application/json"})
@Produces({"application/json"})
public class PostResource {

    @EJB
    PostBean bean;

    @GET
    public List<Post> getAll() {
        return bean.findAllWithLazy("owner");
    }

    @POST
    public Post create(Post item) {
        bean.create(item);
        return bean.findWithLazy(item.getId(), "owner");
    }

    @Path("{id}")
    @GET
    public Post getOne(@PathParam("id") Integer id) {
        return bean.findWithLazy(id, "owner");
    }

    @Path("{id}")
    @PUT
    public Post update(@PathParam("id") Integer id, Post item) {
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

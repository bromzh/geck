package com.example.geck.beans;

import com.example.geck.entities.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PostBean extends AbstractBean<Post> {

    @PersistenceContext(unitName = "GeckPU")
    EntityManager em;

    public PostBean() {
        super(Post.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

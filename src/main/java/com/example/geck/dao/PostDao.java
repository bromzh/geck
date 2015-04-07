package com.example.geck.dao;

import com.example.geck.entities.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PostDao extends AbstractDao<Post> {

    @PersistenceContext(unitName = "GeckPU")
    EntityManager em;

    public PostDao() {
        super(Post.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

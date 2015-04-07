package com.example.geck.dao;

import com.example.geck.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao extends AbstractDao<User> {

    @PersistenceContext(unitName = "GeckPU")
    EntityManager em;

    public UserDao() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

package com.example.geck.beans;

import com.example.geck.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserBean extends AbstractBean<User> {

    @PersistenceContext(unitName = "GeckPU")
    EntityManager em;

    public UserBean() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

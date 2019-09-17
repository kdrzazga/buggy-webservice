package org.kd.buggyservice.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class Repo {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        Session session;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new NullPointerException();
        }
        return entityManager.unwrap(Session.class);
    }
}

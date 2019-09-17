package org.kd.buggyservice.dao;

import org.hibernate.query.Query;
import org.kd.buggyservice.entities.Author;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AuthorRepo extends Repo {

    @Transactional
    public Long create(String newName, String newLastName) {
        var entity = new Author(newName, newLastName);
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Transactional
    @Modifying
    public boolean delete(long id) {
        var session = getSession();
        // TODO: Functional error on purpose - instead of single record, the whole table is dropped
        var query2 = session.createSQLQuery("drop table Author");
        return (0 == query2.executeUpdate());
    }

    @Transactional
    public Author updateAuthorName(long id, String newName, String newLastName) {

        var entity = read(id);
        entity.setName(newName);
        entity.setLastname(newLastName);
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Transactional(readOnly = true)
    public List<Author> readAll() {
        var session = getSession();
        var criteria = session.getCriteriaBuilder().createQuery(Author.class);
        criteria.from(Author.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional(readOnly = true)
    public Author read(long id) {

        String hql = "from Author where id=:id";
        Query query = getSession().createQuery(hql);
        query.setParameter("id", id);
        return (Author) query.getSingleResult();
    }
}

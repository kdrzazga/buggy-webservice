package org.kd.buggyservice.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import org.kd.buggyservice.entities.Book;

@Repository
public class BookRepo extends Repo {

    @Transactional
    public long create(Book book) {
        return createBook(book);
    }

    @Transactional(readOnly = true)
    public Book read(long id) {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        query.select(root).where(builder.equal(root.get("id"), id));//SELECT from book WHERE id=id
        Query<Book> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<Book> readAll() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Book.class);
        criteria.from(Book.class);

        var books = session.createQuery(criteria).getResultList();
        session.close();
        return books;
    }

    @Transactional
    public Book update(long id, String newTitle, int newPublishYear) {
        // TODO: Functional error on purpose - instead of update, a duplicated record is created,
        //  and authorId cannot be changed - it's hardcoded
        var entity = new Book(id + 300 + (long)(40 *Math.random()), newTitle, newPublishYear, (long)(1000 *Math.random()));
        createBook(entity);
        return entity;
    }

    @Transactional
    public boolean delete(long id) {
        //TODO: Functional error on purpose - nothing  deleted here
        return true;
    }

    private long createBook(Book book) {
        entityManager.persist(book);
        getSession().save(book);
        return book.getId();
    }
}

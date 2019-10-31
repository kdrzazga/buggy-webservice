package org.kd.buggyservice.dao;

import org.hibernate.query.Query;
import org.kd.buggyservice.entities.ExternalLibrary;
import org.kd.buggyservice.entities.Library;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class LibraryRepo extends Repo{

    @Transactional
    public Long create(String address) {
        var session = getSession();
        var lib = new Library(address);
        session.save(lib);
        return lib.getId();
    }

    @Transactional
    public Library read(long id) {
        return readLibrary(id);
    }

    @Transactional
    public List<Library> readAll() {
        //libraries.addAll(readExternalLibraries());
        return readInternalLibraries();
    }

    @Transactional
    public Library update(long id, String newAddress) {
        return updateLibraryAddress(id, newAddress);
    }

    @Transactional
    public boolean delete(long id) {
        updateLibraryAddress(id, null);
        //TODO: Functional error on purpose - deleting is only resetting fields!
        return true;
    }

    private Library updateLibraryAddress(long id, String newAddress) {
        var entity = readLibrary(id);
        entity.setAddress(newAddress);
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    private Library readLibrary(long id) {
        var session = getSession();
        var builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Library> query = builder.createQuery(Library.class);
        Root<Library> root = query.from(Library.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Library> q = session.createQuery(query);
        return q.getSingleResult();
    }

    private List<Library> readInternalLibraries() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Library.class);
        criteria.from(Library.class);

        return session.createQuery(criteria).getResultList();
    }

    private List<ExternalLibrary> readExternalLibraries() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(ExternalLibrary.class);
        criteria.from(ExternalLibrary.class);

        return session.createQuery(criteria).getResultList();
    }


}

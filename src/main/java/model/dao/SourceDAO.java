package model.dao;

import model.Source;
import org.hibernate.SessionFactory;
import model.User;


import java.util.List;

class SourceDAO extends BaseDAO {

    public SourceDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Source get(Long id) {
        return super.produceInTransaction(session -> session.get(Source.class, id));
    }

    public void save(Source source) {
        super.executeInTransaction(session -> session.save(source));
    }

    public void update(Source source) {
        super.executeInTransaction(session -> session.update(source));
    }

    public void delete(Source source) {
        super.executeInTransaction(session -> session.delete(source));
    }

    public Boolean isSourceAvailable(String source) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT count(s) FROM Sources s WHERE s.description = :source", Long.class)
                        .setParameter("source", source)
                        .getSingleResult() <= 0
        );
    }
    public List<Source> getAll() {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT '*' FROM Sources", Source.class)
                        .getResultList());
    }


}
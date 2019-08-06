package model.dao;

import model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends BaseDAO {

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User get(Long id) {
        return super.produceInTransaction(session -> session.get(User.class, id));
    }

    public void save(User user) {
        super.executeInTransaction(session -> session.save(user));
    }

    public void update(User user) {
        super.executeInTransaction(session -> session.update(user));
    }

    public void delete(User user) {
        super.executeInTransaction(session -> session.delete(user));
    }

    public Boolean isUsernameAvailable(String username) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT count(u) FROM Users u WHERE u.username = :username", Long.class)
                        .setParameter("username", username)
                        .getSingleResult() <= 0
        );
    }

    public List<User> getAll() {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM Users u", User.class)
                        .getResultList());
    }

    public List<User> getAllByUsername(String username) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM Users u WHERE u.username = :username", User.class)
                        .setParameter("username", username)
                        .getResultList());
    }

    public List<User> getAllByUsernameAndPassword(String username, String password) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM Users u WHERE u.username = :username AND u.password = :password", User.class)
                        .setParameter("username", username)
                        .setParameter("password", password)
                        .getResultList());
    }
}

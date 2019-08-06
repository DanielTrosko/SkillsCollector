package model.dao;

import model.Skill;
import org.hibernate.SessionFactory;

import java.util.List;

public class SkillDAO extends BaseDAO {
    public SkillDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Skill get(Long id) {
        return super.produceInTransaction(session -> session.get(Skill.class, id));
    }

    public void save(Skill skill) {
        super.executeInTransaction(session -> session.save(skill));
    }

    public void update(Skill skill) {
        super.executeInTransaction(session -> session.update(skill));
    }

    public void delete(Skill skill) {
        super.executeInTransaction(session -> session.delete(skill));
    }

    public List<Skill> getAllByName(String name) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM Skills u WHERE u.name = :name", Skill.class)
                        .setParameter("skills", name)
                        .getResultList()
        );
    }
    public List<Skill> getAll(){
        return super.produceInTransaction(
                session -> session.createQuery("SELECT '*' FROM Skills", Skill.class)
                .getResultList()
        );
    }


}

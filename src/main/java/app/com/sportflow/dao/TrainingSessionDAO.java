package app.com.sportflow.dao;

import app.com.sportflow.config.HibernateConfig;
import app.com.sportflow.dto.TrainingSessionDTO;
import app.com.sportflow.entity.TrainingSession;
import app.com.sportflow.mapper.TrainingSessionMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class TrainingSessionDAO {

    public TrainingSession getSession (long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(TrainingSession.class, id);
        }
    }

    public void saveSession (TrainingSession session) {
        try(Session session1 = HibernateConfig.getSessionFactory().openSession()) {
            Transaction tx = session1.beginTransaction();
            session1.persist(session);
            tx.commit();
        }
    }

    public void updateSession (TrainingSession session) {
        try(Session session1 = HibernateConfig.getSessionFactory().openSession()){
            Transaction tx = session1.beginTransaction();
            session1.merge(session);
            tx.commit();
        }
    }

    public void deleteSession (TrainingSession session) {
        try(Session session1 = HibernateConfig.getSessionFactory().openSession()){
            Transaction tx = session1.beginTransaction();
            session1.remove(session);
            tx.commit();
        }
    }

    public Set<TrainingSessionDTO> getAllSessions() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("From TrainingSession ", TrainingSession.class)
                    .getResultStream()
                    .map(TrainingSessionMapper::toTrainingSessionDTO)
                    .collect(Collectors.toSet());
        }
    }

    public long getAllSessionsCount() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("SELECT count(s) from TrainingSession s", Long.class)
                    .uniqueResult();
        }
    }

}

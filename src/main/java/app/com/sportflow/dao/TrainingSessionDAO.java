package app.com.sportflow.dao;

import app.com.sportflow.config.HibernateConfig;
import app.com.sportflow.dto.TrainingSessionDTO;
import app.com.sportflow.entity.Trainer;
import app.com.sportflow.entity.TrainingSession;
import app.com.sportflow.enums.EnrollmentStatus;
import app.com.sportflow.mapper.TrainingSessionMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class TrainingSessionDAO {

    public TrainingSessionDTO getSession (long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return TrainingSessionMapper.toTrainingSessionDTO(session.get(TrainingSession.class, id));
        }
    }

     public TrainingSession getSessionById (long id) {
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

    public long getSessionsCountByTrainer(long trainerId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("SELECT count(id) from TrainingSession where trainer.userId = :trainerID", Long.class)
                    .setParameter("trainerID", trainerId)
                    .uniqueResult();
        }
    }

    public Set<TrainingSessionDTO> getSessionsByTrainerId(long trainerID) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from TrainingSession where trainer.userId = :trainerId", TrainingSession.class)
                    .setParameter("trainerId", trainerID)
                    .getResultStream()
                    .map(TrainingSessionMapper::toTrainingSessionDTO)
                    .collect(Collectors.toSet());
        }
    }

    public Set<TrainingSessionDTO> getAllUnregisteredSessionsByMember(long memberId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from TrainingSession s where not exists (from Enrollment e where  e.member.userId = :memberId and e.session.id = s.id)", TrainingSession.class)
                    .setParameter("memberId", memberId)
                    .getResultStream()
                    .map(TrainingSessionMapper::toTrainingSessionDTO)
                    .collect(Collectors.toSet());
        }
    }

    public Set<TrainingSessionDTO> getAllRegisteredSessionsByMember(long memberId, EnrollmentStatus status) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from TrainingSession s where exists (from Enrollment e where  e.member.userId = :memberId and e.session.id = s.id and e.status != :status)", TrainingSession.class)
                    .setParameter("memberId", memberId)
                    .setParameter("status", status)
                    .getResultStream()
                    .map(TrainingSessionMapper::toTrainingSessionDTO)
                    .collect(Collectors.toSet());
        }
    }
}

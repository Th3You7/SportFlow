package app.com.sportflow.dao;

import app.com.sportflow.config.HibernateConfig;
import app.com.sportflow.dto.EnrollmentDTO;
import app.com.sportflow.entity.Enrollment;
import app.com.sportflow.enums.EnrollmentStatus;
import app.com.sportflow.mapper.EnrollmentMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class EnrollmentDAO {
    public Enrollment getEnrollment(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.get(Enrollment.class, id);
        }
    }
    public void saveEnrollment(Enrollment enrollment) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(enrollment);
            tx.commit();
        }
    }
    public void updateEnrollment(Enrollment enrollment) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.merge(enrollment);
            tx.commit();
        }
    }
    public Set<EnrollmentDTO> getAllEnrollments() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("FROM Enrollment", Enrollment.class)
                    .getResultStream()
                    .map(EnrollmentMapper::toEnrollmentDTO)
                    .collect(Collectors.toSet());
        }
    }

    public Set<EnrollmentDTO> getEnrollmentsByTrainer(long trainerId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("from Enrollment where session.trainer.userId = :trainerId", Enrollment.class)
                    .setParameter("trainerId", trainerId)
                    .getResultStream()
                    .map(EnrollmentMapper::toEnrollmentDTO)
                    .collect(Collectors.toSet());
        }
    }

    public long getEnrollmentsCountByTrainer(long trainerId) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("select count(*) from Enrollment where session.trainer.userId = :trainerId", Long.class)
                    .setParameter("trainerId", trainerId)
                    .uniqueResult();
        }
    }

    public Set<EnrollmentDTO> getEnrollmentsByMember(long memberId, EnrollmentStatus status) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("from Enrollment where member.id = :memberId and status != :status", Enrollment.class)
                    .setParameter("memberId", memberId)
                    .setParameter("status", status)
                    .getResultStream()
                    .map(EnrollmentMapper::toEnrollmentDTO)
                    .collect(Collectors.toSet());
        }
    }


    public long getAllEnrollmentsCount() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("select count(e) from Enrollment e", Long.class)
                    .uniqueResult();
        }
    }

    public Set<EnrollmentDTO> getEnrollmentsByStatus(EnrollmentStatus status) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("from Enrollment where status = :status", Enrollment.class)
                    .setParameter("status", status)
                    .getResultStream()
                    .map(EnrollmentMapper::toEnrollmentDTO)
                    .collect(Collectors.toSet());
        }
    }
}

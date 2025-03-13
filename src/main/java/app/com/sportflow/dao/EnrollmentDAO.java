package app.com.sportflow.dao;

import app.com.sportflow.config.HibernateConfig;
import app.com.sportflow.entity.Enrollment;
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

    public Set<Enrollment> getAllEnrollments() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("FROM Enrollment", Enrollment.class)
                    .getResultStream().collect(Collectors.toSet());
        }
    }

    public long getAllEnrollmentsCount() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.createQuery("select count(e) from Enrollment e", Long.class)
                    .uniqueResult();
        }
    }


}

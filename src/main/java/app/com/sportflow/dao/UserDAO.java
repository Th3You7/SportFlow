package app.com.sportflow.dao;

import app.com.sportflow.mapper.UserMapper;
import app.com.sportflow.config.HibernateConfig;
import app.com.sportflow.dto.UserDTO;
import app.com.sportflow.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDAO {

    public UserDTO getUser(String email, String password) {
       try(Session session = HibernateConfig.getSessionFactory().openSession()){
           User user = session.createQuery("FROM User WHERE email = :email AND password = :password", User.class)
                   .setParameter("email", email)
                   .setParameter("password", password)
                   .uniqueResult();
           return UserMapper.toUserDTO(user);
       }
    }



    public User getUserById(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.get(User.class, id);
        }
    }

    public void saveUser(User user) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }
    }

    public void updateUser(User user) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
    }

    public void deleteUser(User user) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
    }

    public Set<UserDTO> getAllUsersByName(String name) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM User u where u.firstName like ':name%' OR u.lastName like ':name%'", User.class)
                    .setParameter("name", name)
                    .getResultList()
                    .stream()
                    .map(UserMapper::toUserDTO)
                    .collect(Collectors.toSet());
        }
    }

    public Set<UserDTO> getUsersByRole(Class<? extends User> role) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM User u WHERE type(u) = :role ", User.class)
                    .setParameter("role", role)
                    .getResultList()
                    .stream()
                    .map(UserMapper::toUserDTO)
                    .collect(Collectors.toSet());

        }
    }

    public long getUserCountByRole(Class<? extends User> role) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select count(u) from User u where type(u) = :role", Long.class)
                    .setParameter("role", role)
                    .uniqueResult();
        }
    }


}

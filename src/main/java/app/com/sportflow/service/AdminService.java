package app.com.sportflow.service;

import app.com.sportflow.dao.EnrollmentDAO;
import app.com.sportflow.dao.TrainingSessionDAO;
import app.com.sportflow.dao.UserDAO;
import app.com.sportflow.dto.UserDTO;
import app.com.sportflow.entity.Member;
import app.com.sportflow.entity.Trainer;
import app.com.sportflow.entity.User;
import app.com.sportflow.exception.DuplicateEmailException;
import app.com.sportflow.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdminService {
    UserDAO userDAO = new UserDAO();
    TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO();
    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public void getDashboard(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            req.setAttribute("trainersCount", userDAO.getUserCountByRole(Trainer.class));
            req.setAttribute("membersCount", userDAO.getUserCountByRole(Member.class));
            req.setAttribute("sessionsCount", trainingSessionDAO.getAllSessionsCount());
            req.setAttribute("enrollmentsCount", enrollmentDAO.getAllEnrollmentsCount());
            req.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(req, res);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            req.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(req, res);
        }
    }
    public void getProfile(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       long id = Long.parseLong(req.getParameter("id"));
        UserDTO user = UserMapper.toUserDTO(userDAO.getUserById(id));
        req.setAttribute("user", user);
        req.getRequestDispatcher("profile.jsp").forward(req, res);
    }

    // Trainer funcs
    public void getAllTrainers(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("users", userDAO.getUsersByRole(Trainer.class));
        userDAO.getUsersByRole(Trainer.class).forEach(user -> System.out.println(user.getUserId() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail()));
        req.getRequestDispatcher("/WEB-INF/views/admin/trainers.jsp").forward(req, res);
    }
    public void editTrainerForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        try {
            UserDTO user = UserMapper.toUserDTO(userDAO.getUserById(id));
            req.setAttribute("user", user);
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            req.getRequestDispatcher("editTrainer.jsp").forward(req, res);
        }
    }

    public void addUserForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = req.getParameter("type");
        req.setAttribute("type", type);
        req.getRequestDispatcher("/WEB-INF/views/admin/form.jsp").forward(req, res);
    }
    public void editUserForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        UserDTO user = UserMapper.toUserDTO(userDAO.getUserById(id));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/admin/form.jsp").forward(req, res);
    }
    public void addUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = req.getParameter("type");
        System.out.println(type);
        User user = type.equals("TRAINER") ? new Trainer() : new Member();

        HttpSession session = req.getSession(false);
        user.setFirstName(req.getParameter("fname"));
        user.setLastName(req.getParameter("lname"));
        user.setEmail(req.getParameter("email"));
        user.setPassword("12345"); // default password
        user.setBirthDate(LocalDate.parse(req.getParameter("bdate")));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        try {
            userDAO.saveUser(user);
            session.setAttribute("message", "User created successfully");
            session.setAttribute("type", "success");

        }catch (DuplicateEmailException e){
            session.setAttribute("message", e.getMessage());
            session.setAttribute("type", "error");
        }catch (Exception e){
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            if(user instanceof Trainer){
                res.sendRedirect("/admin/trainers.jsp");
            }
            if(user instanceof Member){
                res.sendRedirect("/admin/members.jsp");
            }
        }
    }
    public void editUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("userId"));
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String email = req.getParameter("email");
        String bdate = req.getParameter("bdate");
        HttpSession session = req.getSession(false);
        User user = userDAO.getUserById(id);
        try {
            boolean isTheSameEmail = user.getEmail().equals(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setBirthDate(LocalDate.parse(bdate));
            user.setUpdatedAt(LocalDateTime.now());
            userDAO.updateUser(user, isTheSameEmail);
            session.setAttribute("message", "User updated successfully");
            session.setAttribute("type", "success");
        }catch (DuplicateEmailException e){
            session.setAttribute("message", e.getMessage());
            session.setAttribute("type", "error");
        }catch (Exception e){
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            if(user instanceof Trainer){
                res.sendRedirect("/admin/trainers.jsp");
            }
            if(user instanceof Member){
                res.sendRedirect("/admin/members.jsp");
            }

        }
    }
    public void deleteUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        HttpSession session = req.getSession(false);

        User user = userDAO.getUserById(id);
        try {
            userDAO.deleteUser(user);
            session.setAttribute("message", "User deleted successfully");
            session.setAttribute("type", "success");
        }catch (Exception e){
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            if(user instanceof Trainer){
                res.sendRedirect("/admin/trainers.jsp");
            }
            if(user instanceof Member){
                res.sendRedirect("/admin/members.jsp");
            }
        }
    }
    // Members func
    public void getAllMembers(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Set<UserDTO> users  = userDAO.getUsersByRole(Member.class);
        List<UserDTO> usersList = new ArrayList<>(users);
        req.setAttribute("users", usersList);
        req.getRequestDispatcher("/WEB-INF/views/admin/members.jsp").forward(req, res);
    }

    // Sessions func
    public void getAllTrainingSessions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("trainingSessions", trainingSessionDAO.getAllSessionsCount());
        req.getRequestDispatcher("/WEB-INF/views/admin/sessions.jsp").forward(req, res);
    }

    // Enrollments funcs
    public void getAllEnrollments(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("enrollments", enrollmentDAO.getAllEnrollments());
        req.getRequestDispatcher("/WEB-INF/views/admin/enrollments.jsp").forward(req, res);
    }
}


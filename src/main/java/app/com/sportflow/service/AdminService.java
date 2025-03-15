package app.com.sportflow.service;

import app.com.sportflow.dao.EnrollmentDAO;
import app.com.sportflow.dao.TrainingSessionDAO;
import app.com.sportflow.dao.UserDAO;
import app.com.sportflow.dto.UserDTO;
import app.com.sportflow.entity.Enrollment;
import app.com.sportflow.entity.Member;
import app.com.sportflow.entity.Trainer;
import app.com.sportflow.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminService {
    UserDAO userDAO = new UserDAO();
    TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO();
    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public void getDashboard(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            req.setAttribute("trainersCount", userDAO.getUserCountByRole(Trainer.class));
            req.setAttribute("membersCount", userDAO.getUserCountByRole(Member.class));
            req.setAttribute("sessionsCount", trainingSessionDAO.getAllSessionsCount());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            req.getRequestDispatcher("dashboard.jsp").forward(req, res);
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
        req.setAttribute("trainers", userDAO.getUsersByRole(Trainer.class));
        req.getRequestDispatcher("allTrainers.jsp").forward(req, res);
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
    public void addTrainerForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("addTrainer.jsp").forward(req, res);
    }

    // Members func
    public void getAllMembers(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("trainers", userDAO.getUsersByRole(Member.class));
        req.getRequestDispatcher("allMembers.jsp").forward(req, res);
    }

    // Sessions func
    public void getAllTrainingSessions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("trainingSessions", trainingSessionDAO.getAllSessionsCount());
        req.getRequestDispatcher("allTrainingSessions.jsp").forward(req, res);
    }

    // Enrollments funcs
    public void getAllEnrollments(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("enrollments", enrollmentDAO.getAllEnrollmentsCount());
        req.getRequestDispatcher("allEnrollments.jsp").forward(req, res);
    }
}


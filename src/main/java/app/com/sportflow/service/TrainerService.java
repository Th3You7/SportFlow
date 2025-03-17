package app.com.sportflow.service;

import app.com.sportflow.dao.EnrollmentDAO;
import app.com.sportflow.dao.TrainingSessionDAO;
import app.com.sportflow.dto.TrainingSessionDTO;
import app.com.sportflow.dto.UserDTO;
import app.com.sportflow.entity.Trainer;
import app.com.sportflow.entity.TrainingSession;
import app.com.sportflow.enums.ClubDomain;
import app.com.sportflow.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TrainerService {
    TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO();
    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public void getDashboard(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            UserDTO user = (UserDTO) req.getSession(false).getAttribute("user");
            req.setAttribute("sessionsCount", trainingSessionDAO.getSessionsCountByTrainer(user.getUserId()));
            req.setAttribute("enrollmentsCount", enrollmentDAO.getEnrollmentsCountByTrainer(user.getUserId()));
            req.getRequestDispatcher("/WEB-INF/views/trainer/dashboard.jsp").forward(req, res);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            req.getRequestDispatcher("/WEB-INF/views/trainer/dashboard.jsp").forward(req, res);
        }
    }

    public void listSessions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            UserDTO user = (UserDTO) req.getSession(false).getAttribute("user");
            req.setAttribute("sessions", trainingSessionDAO.getSessionsByTrainerId(user.getUserId()));
            trainingSessionDAO.getSessionsByTrainerId(user.getUserId()).forEach(session -> System.out.println(session.getTrainer().getFirstName()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            req.getRequestDispatcher("/WEB-INF/views/common/sessions.jsp").forward(req, res);
        }
    }

    public void listEnrollments(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            UserDTO user = (UserDTO) req.getSession(false).getAttribute("user");
            req.setAttribute("sessions", trainingSessionDAO.getSessionsByTrainerId(user.getUserId()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            req.getRequestDispatcher("/WEB-INF/views/common/enrollments.jsp").forward(req, res);
        }
    }

    public void addSessionForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("domains", Arrays.asList(ClubDomain.class.getEnumConstants()));
        req.getRequestDispatcher("/WEB-INF/views/trainer/form.jsp").forward(req, res);
    }

    public void editSessionForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long sessionId = Long.parseLong(req.getParameter("sessionId"));

        try {
            TrainingSessionDTO trainingSessionDTO = trainingSessionDAO.getSession(sessionId);
            req.setAttribute("session", trainingSessionDTO);

        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally {
            req.setAttribute("domains", Arrays.asList(ClubDomain.class.getEnumConstants()));
            req.getRequestDispatcher("/WEB-INF/views/trainer/form.jsp").forward(req, res);
        }
    }

    public void addSession(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        ClubDomain domain = ClubDomain.valueOf(req.getParameter("domain"));
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        HttpSession session = req.getSession(false);
        UserDTO user = (UserDTO) session.getAttribute("user");

        TrainingSession trainingSession = new TrainingSession();

        trainingSession.setSessionName(name);
        trainingSession.setSessionDescription(description);
        trainingSession.setDomain(domain);
        trainingSession.setCreatedAt(createdAt);
        trainingSession.setUpdatedAt(updatedAt);

        try {
            Trainer trainer = (Trainer) UserMapper.toUserEntity(user);
            trainingSession.setTrainer(trainer);
            trainingSessionDAO.saveSession(trainingSession);

            session.setAttribute("message", "Session created successfully");
            session.setAttribute("type", "success");

        }catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("/trainer/sessions.jsp");
        }

    }

    public void editSession(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long sessionId = Long.parseLong(req.getParameter("sessionId"));
        HttpSession session = req.getSession(false);

        try {
            TrainingSession trainingSession = trainingSessionDAO.getSessionById(sessionId);
            trainingSession.setSessionName( req.getParameter("name"));
            trainingSession.setSessionDescription(req.getParameter("description"));
            trainingSession.setDomain(ClubDomain.valueOf(req.getParameter("domain")));
            trainingSession.setUpdatedAt(LocalDateTime.now());

            trainingSessionDAO.updateSession(trainingSession);
            session.setAttribute("message", "Session updated successfully");
            session.setAttribute("type", "success");
        }catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("/trainer/sessions.jsp");
        }
    }

    public void deleteSession(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long sessionId = Long.parseLong(req.getParameter("sessionId"));

        try {
            TrainingSession session = trainingSessionDAO.getSessionById(sessionId);
            trainingSessionDAO.deleteSession(session);
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

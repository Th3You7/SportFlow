package app.com.sportflow.service;

import app.com.sportflow.dao.TrainingSessionDAO;
import app.com.sportflow.dto.TrainingSessionDTO;
import app.com.sportflow.entity.Trainer;
import app.com.sportflow.entity.TrainingSession;
import app.com.sportflow.enums.ClubDomain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

public class TrainingSessionService {
    TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO();

    public void listTrainingSessions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            Set<TrainingSessionDTO> trainingSessions = trainingSessionDAO.getAllSessions();
            req.setAttribute("trainingSessions", trainingSessions);
        }catch(Exception e) {
            session.setAttribute("message", "Training Sessions could not be listed");
            session.setAttribute("type", "error");
        }finally {
            req.getRequestDispatcher("trainingSessions.jsp").forward(req, resp);
        }
    }
    public void createTrainingSession(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
        Trainer trainer = new Trainer();
        HttpSession session = req.getSession(false);


        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String domain = req.getParameter("domain");
        long trainerId = Long.parseLong(req.getParameter("trainerId"));
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setSessionName(name);
        trainingSession.setSessionDescription(description);
        trainingSession.setDomain(ClubDomain.valueOf(domain));

        trainer.setUserId(trainerId);
        trainingSession.setTrainer(trainer);
        trainingSession.setCreatedAt(createdAt);
        trainingSession.setUpdatedAt(updatedAt);

        try {
            trainingSessionDAO.saveSession(trainingSession);
            session.setAttribute("message", "Training Session created successfully");
            session.setAttribute("type", "success");
        }catch(Exception e) {
            session.setAttribute("message", "Training Session could not be created");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect( "/trainingsessions" );
        }

    }
    public void deleteTrainingSession(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
        long trainingSessionId = Long.parseLong(req.getParameter("trainingSessionId"));
        HttpSession session = req.getSession(false);
        try {
            TrainingSession trainingSession = trainingSessionDAO.getSession(trainingSessionId);
            trainingSessionDAO.deleteSession(trainingSession);
            session.setAttribute("message", "Training Session deleted successfully");
            session.setAttribute("type", "success");
        }catch(Exception e) {
            session.setAttribute("message", "Training Session could not be deleted");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("/trainingsessions");
        }
    }
    public void updateTrainingSession(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
        long trainingSessionId = Long.parseLong(req.getParameter("trainingSessionId"));
        HttpSession session = req.getSession(false);

        try {
            TrainingSession trainingSession = trainingSessionDAO.getSession(trainingSessionId);
            trainingSession.setSessionName(req.getParameter("sessionName"));
            trainingSession.setSessionDescription(req.getParameter("sessionDescription"));
            trainingSession.setDomain(ClubDomain.valueOf(req.getParameter("domain")));
            trainingSession.setUpdatedAt(LocalDateTime.now());
            trainingSessionDAO.updateSession(trainingSession);
            session.setAttribute("message", "Training Session updated successfully");
            session.setAttribute("type", "success");

        }catch(Exception e) {
            session.setAttribute("message", "Training Session could not be updated");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect( "/trainingsessions" );
        }
    }
}

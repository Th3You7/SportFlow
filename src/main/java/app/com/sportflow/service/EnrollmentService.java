package app.com.sportflow.service;

import app.com.sportflow.dao.EnrollmentDAO;
import app.com.sportflow.dao.TrainingSessionDAO;
import app.com.sportflow.dto.TrainingSessionDTO;
import app.com.sportflow.entity.Enrollment;
import app.com.sportflow.entity.Member;
import app.com.sportflow.entity.TrainingSession;
import app.com.sportflow.enums.EnrollmentStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

public class EnrollmentService {
    TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO();
    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public void enroll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long trainingSessionId = Long.parseLong(req.getParameter("trainingSessionId"));
        HttpSession session = req.getSession();
        Member user = (Member) req.getSession().getAttribute("user");

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.PENDING);
        enrollment.setMember(user);

        try {
            TrainingSession trainingSession = trainingSessionDAO.getSessionById(trainingSessionId);
            if (trainingSession != null) {
                enrollment.setSession(trainingSession);
                enrollmentDAO.saveEnrollment(enrollment);
                session.setAttribute("message", "Your request has been registered successfully");
                session.setAttribute("type", "success");
            }
        }catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("/mysessions");
        }
    }
    public void cancel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long trainingSessionId = Long.parseLong(req.getParameter("trainingSessionId"));
        HttpSession session = req.getSession();
        try {
            Enrollment enrollment = enrollmentDAO.getEnrollment(trainingSessionId);
            if (enrollment != null) {
                enrollment.setStatus(EnrollmentStatus.CANCELLED);
                enrollmentDAO.updateEnrollment(enrollment);
                session.setAttribute("message", "You have cancelled successfully");
                session.setAttribute("type", "success");
            }
        }catch(Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("/mysessions");
        }

    }
    public void accept(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long trainingSessionId = Long.parseLong(req.getParameter("trainingSessionId"));
        HttpSession session = req.getSession();
        try {
            Enrollment enrollment = enrollmentDAO.getEnrollment(trainingSessionId);
            if (enrollment != null) {
                enrollment.setStatus(EnrollmentStatus.ACTIVE);
                enrollmentDAO.updateEnrollment(enrollment);
                session.setAttribute("message", "You have accepted an enrollment successfully");
                session.setAttribute("type", "success");
            }
        }catch(Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("/mysessions");
        }
    }

}

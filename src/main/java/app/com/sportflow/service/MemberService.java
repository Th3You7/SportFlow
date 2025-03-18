package app.com.sportflow.service;

import app.com.sportflow.dao.EnrollmentDAO;
import app.com.sportflow.dao.TrainingSessionDAO;
import app.com.sportflow.dto.UserDTO;
import app.com.sportflow.entity.Enrollment;
import app.com.sportflow.entity.Member;
import app.com.sportflow.entity.TrainingSession;
import app.com.sportflow.entity.User;
import app.com.sportflow.enums.EnrollmentStatus;
import app.com.sportflow.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

public class MemberService {
    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO();

    public void getHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession(false).getAttribute("user");
        try {
            req.setAttribute("sessions", trainingSessionDAO.getAllUnregisteredSessionsByMember(user.getUserId()));
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }finally {
            req.getRequestDispatcher("/WEB-INF/views/member/home.jsp").forward(req, resp);
        }
    }
    public void enroll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long trainingSessionId = Long.parseLong(req.getParameter("sessionId"));
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) req.getSession(false).getAttribute("user");

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.PENDING);
        enrollment.setMember((Member)UserMapper.toUserEntity(user));

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
            res.sendRedirect("enrollments.jsp");
        }
    }
    public void cancel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long enrollmentId = Long.parseLong(req.getParameter("enrollmentId"));
        HttpSession session = req.getSession();
        try {
            Enrollment enrollment = enrollmentDAO.getEnrollment(enrollmentId);
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
            res.sendRedirect("enrollments.jsp");
        }

    }
    public void getEnrollments(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession(false).getAttribute("user");
        try {
            req.setAttribute("enrollments", enrollmentDAO.getEnrollmentsByMember(user.getUserId(), EnrollmentStatus.CANCELLED));
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }finally {
            req.getRequestDispatcher("/WEB-INF/views/common/enrollments.jsp").forward(req, res);
        }
    }
}

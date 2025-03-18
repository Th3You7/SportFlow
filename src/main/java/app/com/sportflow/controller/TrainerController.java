package app.com.sportflow.controller;

import app.com.sportflow.service.TrainerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/trainer/*")
public class TrainerController extends HttpServlet {
    TrainerService trainerService = new TrainerService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        switch(action){
            case "/dashboard.jsp":
                trainerService.getDashboard(req, resp);
                break;

            case "/sessions.jsp":
                trainerService.listSessions(req, resp);
                break;

            case "/enrollments.jsp":
                trainerService.listEnrollments(req, resp);
                break;

            case "/add-form.jsp":
                trainerService.addSessionForm(req, resp);
                break;

            case "/edit-form.jsp":
                trainerService.editSessionForm(req, resp);
                break;

            case "/add-session":
                trainerService.addSession(req, resp);
                break;

            case "/edit-session":
                trainerService.editSession(req, resp);
                break;

            case "/delete-session":
                trainerService.deleteSession(req, resp);
                break;

            case "/accept":
                trainerService.accept(req, resp);
                break;

            case "/cancel":
                trainerService.cancel(req, resp);
                break;

            default:
                resp.sendRedirect("/trainer/dashboard.jsp");

        }
    }
}

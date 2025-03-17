package app.com.sportflow.controller;

import app.com.sportflow.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
    AdminService adminService = new AdminService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        switch (action) {
            case "/dashboard.jsp":
                adminService.getDashboard(req, resp);
                break;

            case "/trainers.jsp":
                adminService.getAllTrainers(req, resp);
                break;

            case "/enrollments.jsp":
                adminService.getAllEnrollments(req, resp);
                break;

            case "/members.jsp":
                adminService.getAllMembers(req, resp);
                break;

            case "/sessions.jsp":
                adminService.getAllTrainingSessions(req, resp);
                break;

            case "/add-form.jsp":
                adminService.addUserForm(req, resp);
                break;

            case "/edit-form.jsp":
                adminService.editUserForm(req, resp);
                break;

            case "/add-user":
                adminService.addUser(req, resp);
                break;

            case "/edit-user":
                adminService.editUser(req, resp);
                break;

            case "/delete":
                adminService.deleteUser(req, resp);
                break;

            default:
                resp.sendRedirect("/admin/dashboard.jsp");
        }


    }

}

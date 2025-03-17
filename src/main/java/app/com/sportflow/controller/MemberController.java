package app.com.sportflow.controller;

import app.com.sportflow.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/member/*")
public class MemberController extends HttpServlet {
    MemberService memberService = new MemberService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        switch (action) {
            case "/home.jsp":
                memberService.getHome(req, resp);
                break;

            case "/enrollments.jsp":
                memberService.getEnrollments(req, resp);
                break;

            case "/enroll":
                memberService.enroll(req, resp);
                break;

            case "/cancel":
                memberService.cancel(req, resp);
                break;


        }
    }
}

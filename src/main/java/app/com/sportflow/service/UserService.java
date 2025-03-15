package app.com.sportflow.service;

import app.com.sportflow.dao.UserDAO;
import app.com.sportflow.dto.UserDTO;
import app.com.sportflow.entity.Member;
import app.com.sportflow.entity.Trainer;
import app.com.sportflow.entity.User;
import app.com.sportflow.exception.DuplicateEmailException;
import app.com.sportflow.exception.UserEmailNoExistException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserService {
    UserDAO userDAO = new UserDAO();
    public void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthdate = req.getParameter("birthdate");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new Member();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDate(LocalDate.parse(birthdate));

        try {
            userDAO.saveUser(user);
            session.setAttribute("message", "you have registered successfully! Try to log in");
            session.setAttribute("type", "success");
        }catch (DuplicateEmailException e){
            session.setAttribute("message", e.getMessage());
            session.setAttribute("type", "error");
        }catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("/login");

        }

    }
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        try {
            UserDTO userDTO = userDAO.getUser(email, password);
            if (userDTO != null) {
                session.setAttribute("message", "Email or password is incorrect");
                session.setAttribute("type", "error");
                request.getRequestDispatcher("WEB-INF/views/login.jsp");
            }
            response.sendRedirect( "/");
        }catch (UserEmailNoExistException e){
            session.setAttribute("message", e.getMessage());
            session.setAttribute("type", "error");
        }catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
            request.getRequestDispatcher("WEB-INF/views/login.jsp");
        }
    }
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/");
        } catch (Exception e) {
            System.out.println(e);
        }
   }
    public void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        HttpSession session = req.getSession();
        try {
            User user = userDAO.getUserById(id);
            userDAO.deleteUser(user);
            session.setAttribute("message", "User has been deleted successfully");
            session.setAttribute("type", "success");
        } catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("/admin/trainers");
        }
    }
    public void edit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        long id = Long.parseLong(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthdate = req.getParameter("birthdate");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        LocalDateTime updatedAt = LocalDateTime.now();

        try {
            session.setAttribute("message", "User edited successfully");
            session.setAttribute("type", "success");
        } catch (DuplicateEmailException e){
            session.setAttribute("message", e.getMessage());
            session.setAttribute("type", "error");
        }catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("/admin/trainers"); // !! set redirection
        }
    }
    public void addTrainer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Trainer trainer = new Trainer();
        HttpSession session = req.getSession();
        trainer.setFirstName(req.getParameter("firstName"));
        trainer.setLastName(req.getParameter("lastName"));
        trainer.setEmail(req.getParameter("email"));
        trainer.setPassword("12345"); // default password
        trainer.setBirthDate(LocalDate.parse(req.getParameter("birthDate")));
        trainer.setCreatedAt(LocalDate.now());
        trainer.setUpdatedAt(LocalDateTime.now());
        try {
            userDAO.saveUser(trainer);
            session.setAttribute("message", "Trainer created successfully");
            session.setAttribute("type", "success");
        }catch (DuplicateEmailException e){
            session.setAttribute("message", e.getMessage());
            session.setAttribute("type", "error");
        }catch (Exception e){
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "success");
        }finally {
            res.sendRedirect("/admin/trainers");
        }
    }

}

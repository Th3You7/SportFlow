package app.com.sportflow.filters;

import app.com.sportflow.dto.UserDTO;
import app.com.sportflow.enums.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter({"/trainer/*"})
public class TrainerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        UserDTO userDTO = (UserDTO) request.getSession(false).getAttribute("user");

        if (userDTO == null) {
            response.sendRedirect("/auth/login.jsp");
        }

        switch (userDTO.getRole()) {
            case ADMIN:
                response.sendRedirect("/admin/dashboard.jsp");
                break;

            case TRAINER:
                chain.doFilter(request, response);
                break;

            case MEMBER:
                response.sendRedirect("/member/home.jsp");
                break;
        }
    }
}

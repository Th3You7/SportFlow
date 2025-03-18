package app.com.sportflow.filters;

import app.com.sportflow.dto.UserDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/auth/login.jsp", "/auth/register.jsp"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            UserDTO user = (UserDTO) session.getAttribute("user");

            switch (user.getRole()) {
                case ADMIN:
                    res.sendRedirect( "/admin/dashboard.jsp" );
                    break;

                case TRAINER:
                    res.sendRedirect( "/trainer/dashboard.jsp" );
                    break;

                case MEMBER:
                    res.sendRedirect( "/member/home.jsp" );
                    break;
            }

            return;
        }

        chain.doFilter(request, response);
    }
}

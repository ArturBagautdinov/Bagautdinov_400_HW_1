package com.bagautdinov.servlet;

import com.bagautdinov.service.UserService;
import com.bagautdinov.service.UserServiceImpl;
import com.bagautdinov.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Main", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.ftl");
            return;
        }

        String username = (String) session.getAttribute("user");
        String sessionId = session.getId();

        String cookieUser = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("user".equals(c.getName())) {
                    cookieUser = c.getValue();
                }
            }
        }

        User user = userService.loginUser(username, "");
        String userImage = user != null && user.getImage() != null ? user.getImage() : "default-avatar.png";

        req.setAttribute("username", username);
        req.setAttribute("sessionId", sessionId);
        req.setAttribute("cookieUser", cookieUser);
        req.setAttribute("userImage", userImage);
        req.getRequestDispatcher("main.ftl").forward(req, resp);
    }
}
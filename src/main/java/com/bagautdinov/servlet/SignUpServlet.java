package com.bagautdinov.servlet;

import com.bagautdinov.entity.User;
import com.bagautdinov.service.UserService;
import com.bagautdinov.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("sign_up.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            resp.sendRedirect("sign_up.ftl");
            return;
        }

        if (name == null) name = "";
        if (lastname == null) lastname = "";

        User newUser = new User(0, name, lastname, login, password);

        boolean isRegistered = userService.registerUser(newUser);

        resp.sendRedirect("login.ftl");
    }
}
package com.bagautdinov.servlet;

import com.bagautdinov.entity.User;
import com.bagautdinov.service.UserService;
import com.bagautdinov.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
@MultipartConfig(
        maxFileSize = 2 * 1024 * 1024,
        maxRequestSize = 5 * 1024 * 1024
)
public class SignUpServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private static final String UPLOAD_DIR = "uploads";
    private static final String DEFAULT_IMAGE = "default-avatar.png";

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

        String imagePath = handleFileUpload(req, login);

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            resp.sendRedirect("sign_up.ftl");
            return;
        }

        if (name == null) name = "";
        if (lastname == null) lastname = "";

        User newUser = new User(0, name, lastname, login, password, imagePath);
        boolean isRegistered = userService.registerUser(newUser);

        resp.sendRedirect("login.ftl");
    }

    private String handleFileUpload(HttpServletRequest request, String login) throws IOException, ServletException {
        Part filePart = request.getPart("image");
        if (filePart == null || filePart.getSize() == 0) {
            return DEFAULT_IMAGE;
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        if (fileName == null || fileName.isEmpty()) {
            return DEFAULT_IMAGE;
        }
        String uploadPath = request.getServletContext().getRealPath("") + UPLOAD_DIR;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String fileExtension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            fileExtension = fileName.substring(i);
        }
        String uniqueFileName = System.currentTimeMillis() + "_" + login + fileExtension;

        String filePath = uploadPath + File.separator + uniqueFileName;
        filePart.write(filePath);
        return UPLOAD_DIR + "/" + uniqueFileName;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.main;

import dao.AuthenticationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import model.User;
import utils.CodeGenerator;
import utils.EmailSender;

/**
 *
 * @author tranh
 */
@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotPasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/main/password-forgot.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AuthenticationDAO authenticationDAO = new AuthenticationDAO();
        User user = authenticationDAO.getUserByEmail(request.getParameter("email"));
        if (user == null) {
            request.setAttribute("notExistEmailError", "The email address doesn’t exist. Please try again.");
        } else {
            String token = CodeGenerator.generateToken();
            authenticationDAO.saveResetPasswordToken(user, token);
            EmailSender sender = new EmailSender();
            sender.setTo(user.getEmail());
            sender.setSubject("Password Reset");
            sender.setContent("<p>We have just received a password reset request for <strong>" + user.getEmail() + "</strong>.</p>\n"
                    + "<p>Please click <a href=\"http://localhost:9999/ISM/reset-password?token=" + token + "\">here</a> to reset your password.</p>\n"
                    + "<p>For your security, the link will expire in 24 hours or immediately after you reset your password.</p>\n"
                    + "<p>Thanks &amp; Regards!</p>\n"
                    + "<p>IMS Team.</p>");
            sender.start();
            request.setAttribute("enteredEmail", user.getEmail());
            request.setAttribute("sendLinkSuccess", "We've sent an email with the link to reset your password.");
        }
        request.getRequestDispatcher("view/main/password-forgot.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

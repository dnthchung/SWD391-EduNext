/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.offer;

import dao.OfferDAO;
import dto.OfferInformationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.InterviewSchedule;
import model.User;

/**
 *
 * @author tranh
 */
@WebServlet(name = "OfferDetailsController", urlPatterns = {"/offer-details"})
public class OfferDetailsController extends HttpServlet {

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
            out.println("<title>Servlet OfferDetailsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OfferDetailsController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            if (loggedInUser.getUserRoleId() != 3) {
                try {
                    Long offerId = Long.parseLong(request.getParameter("offerId"));
                    OfferDAO offerDAO = new OfferDAO();
                    OfferInformationDTO offerInformation = offerDAO.getOfferDetailsById(offerId);
                    if (offerInformation == null) {
                        response.sendRedirect("offer-list");
                    } else {
                        request.setAttribute("offerInf", offerInformation);
                        InterviewSchedule interviewSchedule = offerDAO.getInterviewScheduleInfByOfferId(offerId);
                        request.setAttribute("interviewSchedule", interviewSchedule);
                        String interviewers = offerDAO.getInterviewersByScheduleId(interviewSchedule.getInterviewScheduleId());
                        request.setAttribute("interviewers", interviewers);
                        request.setAttribute("URL", "Offer");
                        request.getRequestDispatcher("view/offer/offer-details.jsp").forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect("offer-list");
                }
            } else {
                response.sendRedirect("home");
            }
        } else {
            String path = request.getServletPath() + "?" + request.getQueryString();
            response.sendRedirect("login?continueUrl=" + path.substring(1));
        }

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
        processRequest(request, response);
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

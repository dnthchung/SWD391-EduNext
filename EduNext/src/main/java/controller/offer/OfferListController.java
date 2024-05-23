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
import model.User;

/**
 *
 * @author tranh
 */
@WebServlet(name = "OfferListServlet", urlPatterns = {"/offer-list"})
public class OfferListController extends HttpServlet {

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
            out.println("<title>Servlet OfferListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OfferListServlet at " + request.getContextPath() + "</h1>");
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
                OfferDAO offerDAO = new OfferDAO();
                List<OfferInformationDTO> offers = offerDAO.getAllOfferInformations();
                request.setAttribute("offers", offers);
                request.setAttribute("offerStatuses", offerDAO.getAllOfferStatuses());
                request.setAttribute("departments", offerDAO.getAllDepartments());
                request.setAttribute("URL", "Offer");
                request.setAttribute("currentPage", 1);
                request.setAttribute("nextPage", 2);
                request.setAttribute("totalPage", getTotalPage(offerDAO));
                request.getRequestDispatcher("view/offer/offer-list.jsp").forward(request, response);
            } else {
                response.sendRedirect("home");
            }
        } else {
            String path = request.getServletPath();
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
        String searchValue = request.getParameter("searchValue");
        String dept = request.getParameter("dept");
        String status = request.getParameter("status");
        int pageNum = 1;
        try {
            pageNum = Integer.parseInt(request.getParameter("currentPage"));
            if (request.getParameter("btnPage").equals("prev")) {
                pageNum--;
            } else {
                pageNum++;
            }
        } catch (Exception e) {
        }
        if (searchValue.isEmpty() && dept.equals("Department") && status.equals("Status")) {
            doGet(request, response);
        } else {
            OfferDAO offerDAO = new OfferDAO();
            System.out.println("Dept: " + dept);
            System.out.println("Status: " + status);
            List<OfferInformationDTO> offers = offerDAO.searchOffers(searchValue, dept, status, pageNum);
            if (offers.isEmpty()) {
                request.setAttribute("isEmptySearch", true);
            } else {
                request.setAttribute("isEmptySearch", false);
                request.setAttribute("offers", offers);
            }

            
            if (!dept.equals("Department")) {
                request.setAttribute("deptSelected", dept);
            }

            
            if (!status.equals("Status")) {
                request.setAttribute("statusSelected", status);
            }
            request.setAttribute("searchedValue", searchValue);
            request.setAttribute("offerStatuses", offerDAO.getAllOfferStatuses());
            request.setAttribute("departments", offerDAO.getAllDepartments());
            request.setAttribute("currentPage", pageNum);
            request.setAttribute("totalPage", getTotalPage(offerDAO));
            request.setAttribute("URL", "Offer");
            request.getRequestDispatcher("view/offer/offer-list.jsp").forward(request, response);
        }
    }

    private int getTotalPage(OfferDAO offerDAO) {
        int numRows = offerDAO.countAllOffers();
        int totalPage = numRows / 2;
        if (numRows % 2 != 0) {
            totalPage++;
        }
        return totalPage;
    }

    private int getTotalPageWithSearch(List<OfferInformationDTO> offers) {
        int totalPage = offers.size() / 2;
        if (offers.size() % 2 != 0) {
            totalPage++;
        }
        return totalPage;
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

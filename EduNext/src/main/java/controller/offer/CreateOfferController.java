/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.offer;

import dao.OfferDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Candidate;
import model.ContractType;
import model.Department;
import model.Level;
import model.Offer;
import model.Position;
import model.User;

/**
 *
 * @author tranh
 */
@WebServlet(name = "CreateOfferServlet", urlPatterns = {"/create-offer"})
public class CreateOfferController extends HttpServlet {

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
            out.println("<title>Servlet CreateOfferServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateOfferServlet at " + request.getContextPath() + "</h1>");
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
                List<Candidate> offerableCandidate = offerDAO.getOfferableCandidates();
                request.setAttribute("offerableCandidate", offerableCandidate);
                List<User> activeManagers = offerDAO.getAllActiveManager();
                request.setAttribute("activeManagers", activeManagers);
                List<User> activeRecuiters = offerDAO.getAllActiveRecuiter();
                request.setAttribute("activeRecuiters", activeRecuiters);
                getSystemOfferValues(request);
                request.setAttribute("URL", "Offer");
                request.getRequestDispatcher("view/offer/offer-create.jsp").forward(request, response);
            } else {
                response.sendRedirect("home");
            }
        } else {
            String path = request.getServletPath();
            response.sendRedirect("login?continueUrl=" + path.substring(1));
        }
    }

    private void getSystemOfferValues(HttpServletRequest request) {
        HttpSession session = request.getSession();
        OfferDAO offerDAO = new OfferDAO();
        
        List<ContractType> contractTypes = (List<ContractType>) session.getAttribute("offerableCandidate");
        if (contractTypes == null) {
            contractTypes = offerDAO.getAllContractTypes();
            session.setAttribute("contractTypes", contractTypes);
        }
        
        List<Position> positions = (List<Position>) session.getAttribute("positions");
        if (positions == null) {
            positions = offerDAO.getAllPositions();
            session.setAttribute("positions", positions);
        }
        
        List<Level> levels = (List<Level>) session.getAttribute("levels");
        if (levels == null) {
            levels = offerDAO.getAllLevels();
            session.setAttribute("levels", levels);
        }
        
        List<Department> departments = (List<Department>) session.getAttribute("departments");
        if (departments == null) {
            departments = offerDAO.getAllDepartments();
            session.setAttribute("departments", departments);
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
        try {
            Long candidateID = Long.parseLong(request.getParameter("candidateId"));
            Long contractTypeID = Long.parseLong(request.getParameter("contractTypeID"));
            Long positionID = Long.parseLong(request.getParameter("positionId"));
            Long levelID = Long.parseLong(request.getParameter("levelId"));
            Long approverID = Long.parseLong(request.getParameter("approverId"));
            Long departmentID = Long.parseLong(request.getParameter("departmentId"));
            Long interviewID = Long.parseLong(request.getParameter("interviewScheduleId"));
            Long recruiterID = Long.parseLong(request.getParameter("recruiterId"));
            String contractFrom = request.getParameter("startDate");
            String contractTo = request.getParameter("endDate");
            String dueDate = request.getParameter("dueDate");
            Double salary = Double.parseDouble(request.getParameter("salary"));
            String note = request.getParameter("note");

            HttpSession session = request.getSession();
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Offer offer = Offer.builder()
                    .candidateId(candidateID)
                    .contractTypeId(contractTypeID)
                    .positionId(positionID)
                    .levelId(levelID)
                    .appover(approverID)
                    .departmentId(departmentID)
                    .interviewScheduleId(interviewID)
                    .recuiterOwner(recruiterID)
                    .contractFrom(LocalDate.parse(contractFrom, formatter))
                    .contractTo(LocalDate.parse(contractTo, formatter))
                    .dueDate(LocalDate.parse(dueDate, formatter))
                    .basicSalary(salary)
                    .note(note)
                    .offerStatusId(1L)
                    .createdAt(LocalDateTime.now())
                    .modifiedBy(loggedInUser.getUserId())
                    .modifiedAt(LocalDateTime.now())
                    .build();
            OfferDAO offerDAO = new OfferDAO();
            offerDAO.saveOffer(offer);
            response.sendRedirect("offer-list");
        } catch (NumberFormatException e) {
            doGet(request, response);
        }
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

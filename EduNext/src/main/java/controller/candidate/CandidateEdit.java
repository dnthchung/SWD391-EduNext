/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.candidate;

import dao.CandidateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import model.Candidate;
import model.User;
import org.apache.commons.io.FilenameUtils;
import utils.CloudinaryService;
import utils.DBFileUtils;
import utils.expirationTimer;

/**
 *
 * @author Vanhle
 */
@WebServlet(name = "CandidateEdit", urlPatterns = {"/candidate-edit"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 20,
        maxRequestSize = 1024 * 1024 * 5 * 10)
public class CandidateEdit extends HttpServlet {

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
            out.println("<title>Servlet CandidateEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CandidateEdit at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("loggedInUser");
        if(u == null || u.getUserRoleId() == 2){
            response.sendRedirect("candidate-list");
            return;
        }
        String id = request.getParameter("id");
        if (id == null) {
            response.sendRedirect("candidate-list");
            return;
        }
        CandidateDAO dao = new CandidateDAO();
        request.setAttribute("c", dao.getCandidateDetail(id));
        request.setAttribute("listPosition", dao.getAllPositon());
        request.setAttribute("status", dao.getAllStatus());
        request.setAttribute("listSkill", dao.getAllSkill());
        request.setAttribute("listLevel", dao.getAllLevel());
        request.setAttribute("listRecruiter", dao.getAllRecruiter());
        request.getRequestDispatcher("view/candidate/candidate-edit-info.jsp").forward(request, response);
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
        CandidateDAO dao = new CandidateDAO();
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String note = request.getParameter("note") == null ? "" : request.getParameter("note");
        String positionId = request.getParameter("position");
        String statusId = request.getParameter("status");
        String[] skill = request.getParameterValues("skill");
        String yoe = request.getParameter("yoe");
        String recruiterId = request.getParameter("recruiter");
        String levelId = request.getParameter("level");
        String oldFile = request.getParameter("oldFile");
        String candidateId = request.getParameter("candidateId");
        //file
        String uploadDir = "CV/";
        String cvFile = DBFileUtils.uploadFile(uploadDir, request);
        String newCV = "";
        if (cvFile != null) {
            String fullPath = request.getServletContext().getRealPath("") + uploadDir + cvFile;
            CloudinaryService cl = new CloudinaryService();
            cl.uploadImage(fullPath, FilenameUtils.removeExtension(cvFile), "CV");
            cl.deleteImage(oldFile);
            newCV = cl.getImageUrl("CV", cvFile);
        } else {
            newCV = oldFile;
        }
        Candidate can = Candidate.builder()
                .candidateId(Long.parseLong(candidateId))
                .fullName(fullName)
                .address(address)
                .email(email)
                .dob(LocalDate.parse(dob))
                .phoneNumber(phone)
                .gender(Integer.parseInt(gender))
                .note(note)
                .positionId(Long.parseLong(positionId))
                .candidateStatusId(Long.parseLong(statusId))
                .yearOfExperience(Integer.parseInt(yoe))
                .recruiterId(Long.parseLong(recruiterId))
                .highestLevel(Long.parseLong(levelId))
                .cvAttachment(newCV)
                //                .createBy(8L)
                .build();
        dao.updateCandidate(can, skill);
        request.setAttribute("candidateId", candidateId);
        HttpSession session = request.getSession();
        session.setAttribute("mess", "Update successfully");
        System.out.println(session.getAttribute("mess"));
        expirationTimer.timerOTP(20, request, "mess");
        response.sendRedirect("candidate-edit?id="+candidateId);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.user;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author chun
 */
@WebServlet(name="UserStatusControl", urlPatterns={"/user-status-control"})
public class UserStatusControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet UserStatusControl</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserStatusControl at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Không tạo session mới nếu không tồn tại
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null && loggedInUser.getUserRoleId() == 1) {
            // Nếu người dùng đã đăng nhập và là quản trị viên
            int flag = Integer.parseInt(request.getParameter("flag"));
            Long userId = Long.parseLong(request.getParameter("userId"));
            UserDAO userDAO = new UserDAO();
            switch (flag) {
                case 1:
                    // code block to active
                    int newStatusId = 2;
                    boolean success1 = userDAO.updateUserStatusById(userId, newStatusId);
                    if (success1) {
                        System.out.println("User status updated successfully!");
                    } else {
                        System.out.println("Active failed.");
                    }
                    break;
                case 2:
                    // code block to de-activate
                    newStatusId = 1;
                    boolean success2 = userDAO.updateUserStatusById(userId, newStatusId);
                    if (success2) {
                        System.out.println("User status updated successfully!");
                    } else {
                        System.out.println("De-activate failed.");
                    }
                    break;
                default:
                // code block
            }
        } else {
            // Nếu không có người dùng đăng nhập hoặc không phải là quản trị viên, chuyển hướng về trang chính hoặc trang đăng nhập
            response.sendRedirect("home"); // hoặc chuyển hướng đến trang đăng nhập tùy thuộc vào yêu cầu
            return;
        }
    }


    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
        if (session == null || session.getAttribute("loggedInUser") == null) {
            // Nếu session không tồn tại hoặc không có thông tin người dùng đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login"); // Điều hướng đến trang đăng nhập của bạn
            return; // Kết thúc xử lý
        }
        // Tiếp tục xử lý yêu cầu nếu người dùng đã đăng nhập
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

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
import java.time.LocalDate;
import java.util.ArrayList;
import model.Department;
import model.User;
import model.UserRole;
import model.UserStatus;
import utils.EmailSender;
import utils.VietnameseConverter;

/**
 *
 * @author chun
 */
@WebServlet(name = "UserEdit", urlPatterns = {"/user-edit"})
public class UserEdit extends HttpServlet {

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
            out.println("<title>Servlet UserEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserEdit at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null && loggedInUser.getUserRoleId() == 1) {
            int flag = Integer.parseInt(request.getParameter("flag"));
            if (flag == 1) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                UserDAO userDAO = new UserDAO();
                User user = userDAO.getUserDetails(userId);
                ArrayList<UserStatus> userStatus = userDAO.getAllUserStatus();
                ArrayList<UserRole> userRole = userDAO.getAllUserRole();
                ArrayList<Department> departmentList = userDAO.getAllDeparmentForUser();
                request.setAttribute("userStatus", userStatus);
                request.setAttribute("userRole", userRole);
                request.setAttribute("user", user);
                request.setAttribute("departmentList", departmentList);
                request.setAttribute("URL", "Edit User");
                request.getRequestDispatcher("view/user/user-edit.jsp").forward(request, response);
            } else if (flag == 2) {
                long userId = Long.parseLong(request.getParameter("userId"));
                String email = request.getParameter("email");
                boolean emailExists = checkEmailExistForEditUser(email, userId);
                try (PrintWriter out = response.getWriter()) {
                    out.print(emailExists);
                }
            }
        } else {
            response.sendRedirect("home"); 
            return;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null && loggedInUser.getUserRoleId() == 1) {
            Long userId = Long.valueOf(request.getParameter("user-Id"));
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email").trim();
            String phone = request.getParameter("phone").trim();
            LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dob"));
            String address = request.getParameter("address").trim();
            int gender = Integer.parseInt(request.getParameter("gender"));
            int userRoleId = Integer.parseInt(request.getParameter("role"));
            Long departmentId = Long.valueOf(request.getParameter("department"));
            int userStatusId = Integer.parseInt(request.getParameter("status"));
            String note = request.getParameter("note").trim();
            // Gen Use name from full name, check user exist = gen usename + check exist
            String useName = checkUserExistForEditUser(fullName, userId);
            //add to db & send mail if add done
            UserDAO userDAO = new UserDAO();
            //check email
            boolean check = checkEmailExistForEditUser(email, userId);
            if (check) {
                request.setAttribute("EMAIL", "Email had existed in system!");
                request.setAttribute("URL", "Edit User");
                request.getRequestDispatcher("view/user/user-edit.jsp").forward(request, response);
            } else {
                User newUser = User.builder()
                        .fullName(fullName)
                        .useName(useName)
                        .dob(dateOfBirth)
                        .phoneNumber(phone)
                        .userRoleId(userRoleId)
                        .userStatusId(userStatusId)
                        .email(email)
                        .address(address)
                        .gender(gender)
                        .departmentId(departmentId)
                        .note(note)
                        .userId(userId)
                        .build();
                boolean userUpdated = userDAO.updateUser(newUser);
                if (userUpdated) {
                    String emailContent = "<h3>This email is from IMS system</h3>\n"
                            + "<p>Your information has been updated. Please use the following credentials to login:</p>\n"
                            + "<ul>\n"
                            + " <li>\n"
                            + "     New User name: <i><strong>" + useName + "</strong></i>\n"
                            + " </li>\n"
                            + "</ul>\n"
                            + "<p>If anything wrong, please reach out to the recruiter <i>offer recruiter owner account</i>. We are so sorry for this inconvenience.</p>\n"
                            + "<p style=\"margin-bottom: 0;\">Thanks & Regards!</p>\n"
                            + "<strong><i>IMS Team.</i></strong>";
                    // Send email
                    EmailSender sender = new EmailSender();
                    sender.setTo(email);
                    sender.setSubject("no-reply-email-IMS-system <Account created>");
                    sender.setContent(emailContent);
                    sender.start();
                    System.out.println(emailContent);
                    response.sendRedirect("user-details?userId="+userId);
                } else {
                    System.out.println("Failed to update user");
                }
            }
        } else {
            response.sendRedirect("home");
            return;
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
    // ========================== GEN USENAME FUNCTION ex: chungdt1 ===========================
    //4 steps

    public static String[] splitFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return null;
        }
        String[] nameParts = fullName.split("\\s+");

        if (nameParts.length == 2) {
            String ho = nameParts[0];
            String ten = nameParts[1];
            return new String[]{ho, "", ten};
        } else if (nameParts.length == 3) {
            String ho = nameParts[0];
            String tenDem = nameParts[1];
            String ten = nameParts[2];
            return new String[]{ho, tenDem, ten};
        } else if (nameParts.length > 3) {
            String ho = nameParts[0];
            String ten = nameParts[nameParts.length - 1];

            StringBuilder tenDemBuilder = new StringBuilder();
            for (int i = 1; i < nameParts.length - 1; i++) {
                tenDemBuilder.append(nameParts[i]).append(" ");
            }
            String tenDem = tenDemBuilder.toString().trim();
            return new String[]{ho, tenDem, ten};
        }

        return null;
    }

    public static String extractFirstCharacter(String str) {
        StringBuilder result = new StringBuilder();
        for (String word : str.split("\\s+")) {
            // Lấy kí tự đầu tiên của từ
            result.append(word.charAt(0));
        }
        return result.toString().toLowerCase();
    }

    public static String generateUseName(String fullName) {
        VietnameseConverter cn = new VietnameseConverter();

        String origin = fullName;
        String nonAcc = cn.toNonAccentVietnamese(origin);
        String lowerNonAcc = cn.toLowerCaseNonAccentVietnamese(origin);

        String[] nameParts = splitFullName(lowerNonAcc);
        String ho1 = extractFirstCharacter(nameParts[0]);
        String dem1 = extractFirstCharacter(nameParts[1]);
        String ten0 = nameParts[2];

        String result = ten0 + ho1 + dem1;
        return result;
    }

    public static String checkUserExistForEditUser(String fullName, Long userId) {
        String useName = generateUseName(fullName);
        String finalUseName = "";
        String fullNameInput = fullName.trim().toLowerCase();
        int count = 0;

        UserDAO userDAO = new UserDAO();
        ArrayList<User> userList = userDAO.getAllUser();
        for (User u : userList) {
            String nameDB = u.getFullName().trim().toLowerCase();
            long userIdDB = u.getUserId();
            if (nameDB.equals(fullNameInput) && userIdDB != userId) {
                count++;
            }
        }
        if (count > 0) {
            finalUseName = useName + (count + 1);
        } else {
            finalUseName = useName;
        }
        return finalUseName;
    }

    // ========================== CHECK MAIL DUPLICATE ===========================
    public boolean checkEmailExistForEditUser(String mail, Long userId) {
        UserDAO userDAO = new UserDAO();
        ArrayList<User> listUser = userDAO.getAllUser();
        for (User u : listUser) {
            if (u.getEmail().equalsIgnoreCase(mail) && u.getUserId() != userId) {
                return true;
            } else if (u.getEmail().equalsIgnoreCase(mail) && u.getUserId() == userId) {
                return false;
            }
        }
        return false;
    }

}

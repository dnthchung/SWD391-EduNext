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
import java.util.Random;
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
@WebServlet(name = "UserCreate", urlPatterns = {"/user-create"})
public class UserCreate extends HttpServlet {

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
            out.println("<title>Servlet UserCreate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserCreate at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser.getUserRoleId() != 1) { 
            response.sendRedirect("home"); 
            return;
        }

        int flag = Integer.parseInt(request.getParameter("flag"));
        if (flag == 1) {
            UserDAO userDAO = new UserDAO();
            ArrayList<User> userList = userDAO.getAllUser();
            ArrayList<UserStatus> userStatus = userDAO.getAllUserStatus();
            ArrayList<UserRole> userRole = userDAO.getAllUserRole();
            ArrayList<Department> departmentList = userDAO.getAllDeparmentForUser();

            request.setAttribute("userList", userList);
            request.setAttribute("userStatus", userStatus);
            request.setAttribute("userRole", userRole);
            request.setAttribute("departmentList", departmentList);
            request.setAttribute("URL", "Create User");

            request.getRequestDispatcher("view/user/user-create.jsp").forward(request, response);
        } else if (flag == 2) {
            // Handle checking email existence request
            String email = request.getParameter("email");
            boolean emailExists = checkEmailExist(email);

            try (PrintWriter out = response.getWriter()) {
                out.print(emailExists);
            }
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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser.getUserRoleId() != 1) { 
            response.sendRedirect("home"); 
            return;
        }
        
        String fullName = request.getParameter("fullNameSelected").trim();
        String email = request.getParameter("emailSelected").trim();
        String phone = request.getParameter("phoneSelected").trim();
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateSelected").trim());
        String address = request.getParameter("addressSelected").trim();
        int gender = Integer.parseInt(request.getParameter("genderSelected"));
        int userRoleId = Integer.parseInt(request.getParameter("userRoleSelected"));
        Long departmentId = Long.valueOf(request.getParameter("departmentSelected"));
        int userStatusId = Integer.parseInt(request.getParameter("statusSelected"));
        String note = request.getParameter("noteSelected").trim();

        // Gen usename
        String useName = checkUserExist(fullName);

        // Gen password
        String password = generatePassword();
        User newUser = User.builder()
                .fullName(fullName)
                .useName(useName)
                .password(password)
                .dob(dateOfBirth)
                .phoneNumber(phone)
                .userRoleId(userRoleId)
                .userStatusId(userStatusId)
                .email(email)
                .address(address)
                .gender(gender)
                .departmentId(departmentId)
                .note(note)
                .build();

        // Add user 
        UserDAO userDAO = new UserDAO();
        boolean check = checkEmailExist(email);
        if (check) {
            request.setAttribute("EMAIL", "Email already exists in the system!");
            request.setAttribute("URL", "Create User");
            request.getRequestDispatcher("view/user/user-create.jsp").forward(request, response);
        } else {
            boolean userAdded = userDAO.addUser(newUser);
            if (userAdded) {
                String emailContent = "<h3>This email is from IMS system</h3>\n"
                        + "<p>Your account has been created. Please use the following credentials to login:</p>\n"
                        + "<ul>\n"
                        + " <li>\n"
                        + "     User name: <i><strong>" + useName + "</strong></i>\n"
                        + " </li>\n"
                        + " <li>\n"
                        + "     Password : <i><strong>" + password + "</strong></i>\n"
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

                response.sendRedirect("user-list");
            } else {
                System.out.println("Failed to add user");
            }
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
    }

    // ========================== GEN PASSWORD FUNCTION ===========================
    public static String generatePassword() {
        // Define the character sets for each type of character
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*-_+=~.<>?";

        StringBuilder password = new StringBuilder();
        Random random = new Random();
        password.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        password.append(lowercaseLetters.charAt(random.nextInt(lowercaseLetters.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        for (int i = 4; i < 10; i++) {
            String randomSet = uppercaseLetters + lowercaseLetters + digits + specialCharacters;
            password.append(randomSet.charAt(random.nextInt(randomSet.length())));
        }
        char[] passwordChars = password.toString().toCharArray();
        for (int i = 0; i < passwordChars.length; i++) {
            int randomIndex = random.nextInt(passwordChars.length);
            char temp = passwordChars[i];
            passwordChars[i] = passwordChars[randomIndex];
            passwordChars[randomIndex] = temp;
        }

        return new String(passwordChars);
    }
    
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

        System.out.println("===== TEST GEN USENAME =====");
        System.out.println("nonAcc name: " + nonAcc);
        System.out.println("lowerNonAcc: " + lowerNonAcc);

        String[] nameParts = splitFullName(lowerNonAcc);
        if (nameParts[1].isEmpty()) {
            String ho1 = extractFirstCharacter(nameParts[0]);
            String ten0 = nameParts[2];
            String result = ten0 + ho1;
            return result;
        } else {
            String ho1 = extractFirstCharacter(nameParts[0]);
            String dem1 = extractFirstCharacter(nameParts[1]);
            String ten0 = nameParts[2];

            String result = ten0 + ho1 + dem1;
            return result;
        }
    }

    public static String checkUserExist(String fullName) {
        String useName = generateUseName(fullName);
        String finalUseName = "";
        String fullNameInput = fullName.trim().toLowerCase();
        int count = 0;

        UserDAO userDAO = new UserDAO();
        ArrayList<User> userList = userDAO.getAllUser();
        for (User u : userList) {
            String nameDB = u.getFullName().trim().toLowerCase();
            if (nameDB.equals(fullNameInput)) {
                count++; 
            }
        }
        if (count > 0) {
            System.out.println("Exist name like that in db oy oy!");
            finalUseName = useName + (count + 1);
        } else {
            finalUseName = useName; 
        }
        System.out.println("finalUseName: " + finalUseName);
        return finalUseName;
    }
 
     // ========================== CHECK MAIL DUPLICATE ===========================
    public boolean checkEmailExist(String email) {
        UserDAO userDAO = new UserDAO();
        ArrayList<User> listUser = userDAO.getAllUser();
        for(User u : listUser){
            if(u.getEmail().trim().equalsIgnoreCase(email.trim())){
                System.out.println("===== CHECK MAIL FUNC USER CREATE SVL =====");
                System.out.println("mail db: " + u.getEmail());
                System.out.println("mail input: " + email);
                return true;
            }
        }
        System.out.println("===== CHECK MAIL FUNC =====");
        System.out.println("Mail ok ko van de gi");
        return false;
    }
    
}

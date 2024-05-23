/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CandidateDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import model.Candidate;
import model.CandidateStatus;
import model.Level;
import model.Position;
import model.Skill;
import model.User;
import utils.DBContext;

/**
 *
 * @author Vanhle
 */
public class CandidateDAO {

    //Mapping funtion
    private Position resultPosition(ResultSet rs) throws SQLException {
        Position p = Position.builder()
                .positionId(rs.getLong("PositionID"))
                .positionName(rs.getString("PositionName")).build();
        return p;
    }

    private CandidateStatus resultCandidateStatus(ResultSet rs) throws SQLException {
        CandidateStatus cs = CandidateStatus.builder()
                .candidateStatusId(rs.getLong("CandidateStatusID"))
                .statusName(rs.getString("StatusName")).build();
        return cs;
    }

    private Skill resultSkill(ResultSet rs) throws SQLException {
        Skill s = Skill.builder()
                .skillId(rs.getLong("SkillID"))
                .skillName(rs.getString("SkillName")).build();
        return s;
    }

    private Level resultLevel(ResultSet rs) throws SQLException {
        Level l = Level.builder()
                .levelId(rs.getLong("LevelID"))
                .levelName(rs.getString("LevelName")).build();
        return l;
    }
    // End mapping

    // 1.Create candidate
    // 1.1 Get all Position
    public List<Position> getAllPositon() {
        List<Position> l = new ArrayList<>();
        String sql = "SELECT * FROM [Position]";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Position p = resultPosition(rs);
                l.add(p);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // 1.2 Get all Candidate Status
    public List<CandidateStatus> getAllStatus() {
        List<CandidateStatus> l = new ArrayList<>();
        String sql = "SELECT * FROM [CandidateStatus]";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CandidateStatus p = resultCandidateStatus(rs);
                l.add(p);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // 1.3 Get all Skill
    public List<Skill> getAllSkill() {
        List<Skill> l = new ArrayList<>();
        String sql = "SELECT * FROM [Skill]";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Skill p = resultSkill(rs);
                l.add(p);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // 1.4 Get all Level
    public List<Level> getAllLevel() {
        List<Level> l = new ArrayList<>();
        String sql = "SELECT * FROM [Level]";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Level p = resultLevel(rs);
                l.add(p);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // 1.5
    public List<User> getAllRecruiter() {
        List<User> l = new ArrayList<>();
        String sql = "SELECT * FROM [User] WHERE UserRoleID = 2";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User u = User.builder()
                        .userId(rs.getLong("UserID"))
                        .useName(rs.getString("Usename"))
                        .build();
                l.add(u);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // 1.6 Add Candidate
    private long addCandidate(Candidate candidate) {
        String sql = "INSERT INTO Candidate (FullName, DOB, PhoneNumber, Email, Address, Gender, CVAttachment, PositionID, Note, CandidateStatusID, YearOfExperience, HighestLevel, CreateBy, LastUpdateAt, Recruiter) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, candidate.getFullName());
            preparedStatement.setDate(2, Date.valueOf(candidate.getDob()));
            preparedStatement.setString(3, candidate.getPhoneNumber());
            preparedStatement.setString(4, candidate.getEmail());
            preparedStatement.setString(5, candidate.getAddress());
            preparedStatement.setInt(6, candidate.getGender());
            preparedStatement.setString(7, candidate.getCvAttachment());
            preparedStatement.setLong(8, candidate.getPositionId());
            preparedStatement.setString(9, candidate.getNote());
            preparedStatement.setLong(10, candidate.getCandidateStatusId());
            preparedStatement.setInt(11, candidate.getYearOfExperience());
            preparedStatement.setLong(12, candidate.getHighestLevel());
            preparedStatement.setLong(13, candidate.getCreateBy());
            preparedStatement.setDate(14, Date.valueOf(LocalDate.now()));
            preparedStatement.setLong(15, candidate.getRecruiterId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating candidate failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1); // Lấy ID của candidate vừa được thêm vào
                } else {
                    throw new SQLException("Creating candidate failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // 1.7 Add skil for candidate
    private void addSkillsForCandidate(long candidateId, String[] skills) {
        String sql = "INSERT INTO CandidateSkills (Candidate_CandidateID, Skills_SkillID) VALUES (?, ?)";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (String skill : skills) {
                preparedStatement.setLong(1, candidateId);
                preparedStatement.setLong(2, Long.parseLong(skill));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 1.8 Add Candidate And Skills
    public void addCandidate(Candidate candidate, String[] skills) {
        long candidateId = addCandidate(candidate);
        if (candidateId != -1) {
            addSkillsForCandidate(candidateId, skills);
        } else {
            System.out.println("Failed to add candidate.");
        }
    }

    // 2 View candidate detail
    // 2.1 Get all skills
    public List<Skill> getAllSkillByCandidateId(String candidateId) {
        List<Skill> l = new ArrayList<>();
        String sql = "SELECT * FROM Skill s\n"
                + "JOIN CandidateSkills cs\n"
                + "ON s.SkillID = cs.Skills_SkillID\n"
                + "WHERE cs.Candidate_CandidateID = ?";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, candidateId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Skill p = resultSkill(rs);
                l.add(p);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    //2.2 Get Position
    public Position getPositionById(String positionId) {
        String sql = "SELECT * FROM [Position] WHERE PositionID = ?";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, positionId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Position p = resultPosition(rs);
                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 2.3 Get CandidateStatus
    public CandidateStatus getCandidateStatusById(String candidateStatusId) {
        String sql = "SELECT * FROM [CandidateStatus] WHERE CandidateStatusID = ?";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, candidateStatusId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CandidateStatus cs = resultCandidateStatus(rs);
                return cs;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 2.4 Get Level
    public Level getLevelById(String levelId) {
        String sql = "SELECT * FROM [Level] WHERE LevelID = ?";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, levelId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Level p = resultLevel(rs);
                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    // 2.5 Get User
    public User getUserDetails(int userID) {
        String sql = "SELECT * FROM [User] WHERE UserID = ?";
        try (
                Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return User.builder()
                        .userId(rs.getLong("UserID"))
                        .useName(rs.getString("Usename"))
                        .fullName(rs.getString("FullName"))
                        .build();
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return null;
    }

    // 2.6 Get Candidate Detail
    public CandidateDTO getCandidateDetail(String candidateId) {
        String sql = "SELECT * FROM Candidate WHERE CandidateID = ?";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, candidateId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                List<Skill> listSkill = getAllSkillByCandidateId(candidateId);
                Position p = getPositionById(rs.getString("PositionID"));
                CandidateStatus cs = getCandidateStatusById(rs.getString("CandidateStatusID"));
                Level l = getLevelById(rs.getString("HighestLevel"));
                User u = getUserDetails(rs.getInt("CreateBy"));
                User recruiter = getUserDetails(rs.getInt("Recruiter"));
                
                CandidateDTO c = CandidateDTO.builder()
                        .candidateId(rs.getLong("CandidateID"))
                        .fullName(rs.getString("FullName"))
                        .phoneNumber(rs.getString("PhoneNumber"))
                        .email(rs.getString("Email"))
                        .address(rs.getString("Address"))
                        .gender(rs.getInt("Gender"))
                        .cvAttachment(rs.getString("CVAttachment"))
                        .position(p)
                        .note(rs.getString("Note"))
                        .candidateStatus(cs)
                        .yearOfExperience(rs.getInt("YearOfExperience"))
                        .highestLevel(l)
                        .createBy(u)
                        .lastUpdateAt(rs.getDate("LastUpdateAt").toLocalDate())
                        .skill(listSkill)
                        .dob(LocalDate.parse(rs.getString("DOB")))
                        .recruiter(recruiter).build();
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3 Update Status
    public boolean updateCandidateStatus(String candidateId, String status) {
        String sql = "UPDATE [dbo].[Candidate]\n"
                + "   SET [CandidateStatusID] = ?\n"
                + " WHERE CandidateID = ?";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, candidateId);
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4 Update Candidate
    // 4.1 Update Candidate
    public void updateCandidate(Candidate candidate) {
        String sql = "UPDATE Candidate SET FullName = ?, DOB = ?, PhoneNumber = ?,"
                + " Email = ?, Address = ?, Gender = ?, CVAttachment = ?, PositionID = ?,"
                + " Note = ?, YearOfExperience = ?, HighestLevel = ?, LastUpdateAt = ?, CandidateStatusID = ?"
                + " WHERE CandidateID = ?";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, candidate.getFullName());
            preparedStatement.setDate(2, Date.valueOf(candidate.getDob()));
            preparedStatement.setString(3, candidate.getPhoneNumber());
            preparedStatement.setString(4, candidate.getEmail());
            preparedStatement.setString(5, candidate.getAddress());
            preparedStatement.setInt(6, candidate.getGender());
            preparedStatement.setString(7, candidate.getCvAttachment());
            preparedStatement.setLong(8, candidate.getPositionId());
            preparedStatement.setString(9, candidate.getNote());
            preparedStatement.setInt(10, candidate.getYearOfExperience());
            preparedStatement.setLong(11, candidate.getHighestLevel());
            preparedStatement.setDate(12, Date.valueOf(LocalDate.now()));
            preparedStatement.setLong(13, candidate.getCandidateStatusId());
            preparedStatement.setLong(14, candidate.getCandidateId()); // CandidateID is used in WHERE clause
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4.2 Delete All Skill
    public void deleteSkillByCandidateId(Long candidateId) {
        String sql = "DELETE FROM [dbo].[CandidateSkills]\n"
                + "      WHERE Candidate_CandidateID = ?";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, candidateId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4.3 Update Candidate and Skill
    public void updateCandidate(Candidate candidate, String[] skill) {
        deleteSkillByCandidateId(candidate.getCandidateId());
        addSkillsForCandidate(candidate.getCandidateId(), skill);
        updateCandidate(candidate);
    }

    // 5 Candidate List
    // 5.1 get list with condition
    public List<CandidateDTO> getAll(String search, String status, int amount, int pageNumber) {
        List<CandidateDTO> l = new ArrayList<>();
        String sql = "";
        if (status.isBlank()) {
            sql += "SELECT c.CandidateID, c.FullName, c.Email,c.PhoneNumber, p.PositionName, r.Usename, cs.StatusName FROM Candidate c\n"
                    + "JOIN Position p\n"
                    + "ON p.PositionID = c.PositionID\n"
                    + "JOIN [User] r\n"
                    + "ON r.UserID = c.Recruiter\n"
                    + "JOIN CandidateStatus cs \n"
                    + "ON cs.CandidateStatusID = c.CandidateStatusID\n"
                    + "WHERE c.FullName LIKE ? OR c.PhoneNumber LIKE ? OR c.Email LIKE ? OR p.PositionName LIKE ? OR r.Usename LIKE ?\n"
                    + "ORDER BY c.CandidateID DESC\n"
                    + "OFFSET (?-1)*? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";

        } else {
            sql += "SELECT c.CandidateID, c.FullName, c.Email,c.PhoneNumber, p.PositionName, r.Usename, cs.StatusName FROM Candidate c\n"
                    + "JOIN Position p\n"
                    + "ON p.PositionID = c.PositionID\n"
                    + "JOIN [User] r\n"
                    + "ON r.UserID = c.Recruiter\n"
                    + "JOIN CandidateStatus cs \n"
                    + "ON cs.CandidateStatusID = c.CandidateStatusID\n"
                    + "WHERE (c.FullName LIKE ? OR c.PhoneNumber LIKE ? OR c.Email LIKE ? OR p.PositionName LIKE ? OR r.Usename LIKE ?) AND cs.CandidateStatusID = " + status + "\n"
                    + "ORDER BY c.CandidateID DESC\n"
                    + "OFFSET (?-1)*? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
        }
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");
            preparedStatement.setString(3, "%" + search + "%");
            preparedStatement.setString(4, "%" + search + "%");
            preparedStatement.setString(5, "%" + search + "%");
            preparedStatement.setInt(6, pageNumber);
            preparedStatement.setInt(7, amount);
            preparedStatement.setInt(8, amount);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CandidateDTO c = CandidateDTO.builder()
                        .candidateId(rs.getLong("CandidateID"))
                        .fullName(rs.getString("FullName"))
                        .email(rs.getString("Email"))
                        .phoneNumber(rs.getString("PhoneNumber"))
                        .position(Position.builder().positionName(rs.getString("PositionName")).build())
                        .recruiter(User.builder().useName(rs.getString("Usename")).build())
                        .candidateStatus(CandidateStatus.builder().statusName(rs.getString("StatusName")).build())
                        .build();
                l.add(c);
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    // 5.2 Count total row
    public int getTotalPage(int amount, String search, String status) {
        String sql = "";
        if (status.isBlank()) {
            sql += "SELECT COUNT(*) FROM Candidate c\n"
                    + "JOIN Position p\n"
                    + "ON p.PositionID = c.PositionID\n"
                    + "JOIN [User] r\n"
                    + "ON r.UserID = c.Recruiter\n"
                    + "JOIN CandidateStatus cs \n"
                    + "ON cs.CandidateStatusID = c.CandidateStatusID\n"
                    + "WHERE (c.FullName LIKE ? OR c.PhoneNumber LIKE ? OR c.Email LIKE ? OR p.PositionName LIKE ? OR r.Usename LIKE ?)";
        } else {
            sql += "SELECT COUNT(*) FROM Candidate c\n"
                    + "JOIN Position p\n"
                    + "ON p.PositionID = c.PositionID\n"
                    + "JOIN [User] r\n"
                    + "ON r.UserID = c.Recruiter\n"
                    + "JOIN CandidateStatus cs \n"
                    + "ON cs.CandidateStatusID = c.CandidateStatusID\n"
                    + "WHERE (c.FullName LIKE ? OR c.PhoneNumber LIKE ? OR c.Email LIKE ? OR p.PositionName LIKE ? OR r.Usename LIKE ?)\n"
                    + "AND cs.CandidateStatusID = " + status;
        }
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");
            preparedStatement.setString(3, "%" + search + "%");
            preparedStatement.setString(4, "%" + search + "%");
            preparedStatement.setString(5, "%" + search + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int totalRow = rs.getInt(1);
                System.out.println("Total Row: " + totalRow);
                if (totalRow % amount == 0) {
                    return totalRow / amount;
                } else {
                    return totalRow / amount + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Ending
    //Test code
    public static void main(String[] args) {
        CandidateDAO dao = new CandidateDAO();
//        for (Skill p : dao.getAllSkillByCandidateId("4")) {
//            System.out.println(p);
//        }
//        System.out.println(dao.getPositionById("1"));
//        System.out.println(dao.getCandidateDetail("4"));
//        Candidate candidate = Candidate.builder()
//                .candidateId(18L)
//                .fullName("John Doeeeee")
//                .dob(LocalDate.of(1990, 6, 15))
//                .phoneNumber("199999")
//                .email("john.doe@example.com")
//                .address("444 Main Street")
//                .gender(2)
//                .cvAttachment("cv.pdf")
//                .positionId(2L)
//                .note("Note for candidate")
//                //                .candidateStatusId(2L)
//                .yearOfExperience(4)
//                .highestLevel(3L)
//                //                .createBy(1L)
//                //                .lastUpdateAt(LocalDate.now())
//                .build();
//        String[] skill = {"2", "3"};
////        System.out.println(dao.addCandidate(candidate));
//        dao.addCandidate(candidate, skill);
//        dao.updateCandidateStatus("100", "1");
//        dao.updateCandidate(candidate, skill);
//        for (CandidateDTO c : dao.getAll("01", "", 5, 3)) {
//            System.out.println(c);
//        }
        System.out.println(dao.getTotalPage(5, "", ""));
    }
}

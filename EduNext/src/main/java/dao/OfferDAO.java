/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.OfferInformationDTO;
import dto.OfferRemindDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Candidate;
import model.ContractType;
import model.Department;
import model.InterviewSchedule;
import model.Level;
import model.Offer;
import model.OfferStatus;
import model.Position;
import model.User;
import utils.DBContext;

/**
 *
 * @author tranh
 */
public class OfferDAO {

    public static void main(String[] args) {
        OfferDAO offerDAO = new OfferDAO();
        System.out.println(offerDAO.getInterviewersByScheduleId(16L));
    }

    public List<OfferInformationDTO> getAllOfferInformations() {
        List<OfferInformationDTO> offerInformationDTOs = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT o.OfferID\n"
                        + "      ,c.FullName AS [CandidateName]\n"
                        + "	  ,c.Email\n"
                        + "      ,u.FullName AS [ApproverName]\n"
                        + "      ,d.DepartmentName\n"
                        + "      ,o.Note\n"
                        + "      ,os.StatusName\n"
                        + "  FROM [Offer] o\n"
                        + "  JOIN [Candidate] c on o.CandidateID = c.CandidateID\n"
                        + "  JOIN [User] u ON o.Approver = u.UserID\n"
                        + "  JOIN [Department] d ON o.DepartmentID = d.DepartmentID\n"
                        + "  JOIN [OfferStatus] os ON o.OfferStatusID = os.OfferStatusID\n"
                        + " ORDER BY o.LastModified DESC\n"
                        + " OFFSET 0 ROWS \n"
                        + " FETCH NEXT 2 ROWS ONLY")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OfferInformationDTO offer = OfferInformationDTO.builder()
                        .offerId(rs.getLong("OfferID"))
                        .candidateName(rs.getString("CandidateName"))
                        .email(rs.getString("Email"))
                        .approverName(rs.getString("ApproverName"))
                        .departmentName(rs.getString("DepartmentName"))
                        .note(rs.getString("Note"))
                        .statusName(rs.getString("StatusName"))
                        .build();
                offerInformationDTOs.add(offer);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerInformationDTOs;
    }

    public Offer getOfferByOfferId(Long offerId) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [Offer] WHERE OfferID = ?")) {
            preparedStatement.setLong(1, offerId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Offer.builder()
                        .offerId(rs.getLong("OfferID"))
                        .candidateId(rs.getLong("CandidateID"))
                        .contractTypeId(rs.getLong("ContractTypeID"))
                        .positionId(rs.getLong("PositionID"))
                        .levelId(rs.getLong("LevelID"))
                        .appover(rs.getLong("Approver"))
                        .departmentId(rs.getLong("DepartmentID"))
                        .interviewScheduleId(rs.getLong("InterviewScheduleID"))
                        .recuiterOwner(rs.getLong("RecuiterOwner"))
                        .contractFrom(rs.getDate("ContractFrom").toLocalDate())
                        .contractTo(rs.getDate("ContractTo").toLocalDate())
                        .dueDate(rs.getDate("DueDate").toLocalDate())
                        .basicSalary(rs.getDouble("BasicSalary"))
                        .note(rs.getString("Note"))
                        .offerStatusId(rs.getLong("OfferStatusID"))
                        .build();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countAllOffers() {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT COUNT(OfferID) FROM [Offer]")) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public OfferInformationDTO getOfferDetailsById(Long offerId) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT o.OfferID\n"
                        + ",c.FullName AS [CandidateName]\n"
                        + ",ct.TypeName\n"
                        + ",p.PositionName\n"
                        + ",l.LevelName\n"
                        + ",u.FullName AS [ApproverName]\n"
                        + ",u.Usename AS [ApproverUsename]\n"
                        + ",d.DepartmentName\n"
                        + ",u2.useName AS [Recruiter]\n"
                        + ",FORMAT(o.ContractFrom,'dd/MM/yyyy') AS ContractFrom\n"
                        + ",FORMAT(o.ContractTo,'dd/MM/yyyy') AS ContractTo\n"
                        + ",FORMAT(o.DueDate,'dd/MM/yyyy') AS DueDate\n"
                        + ",o.BasicSalary\n"
                        + ",o.Note\n"
                        + ",os.StatusName\n"
                        + ",u3.Usename AS ModifiedBy\n"
                        + ",FORMAT(o.CreatedAt,'dd/MM/yyyy') AS CreatedAt\n"
                        + ",FORMAT(o.LastModified,'dd/MM/yyyy') AS LastModified\n"
                        + "FROM [Offer] o\n"
                        + "JOIN [Candidate] c on o.CandidateID = c.CandidateID\n"
                        + "JOIN [User] u ON o.Approver = u.UserID\n"
                        + "JOIN [Department] d ON o.DepartmentID = d.DepartmentID\n"
                        + "JOIN [OfferStatus] os ON o.OfferStatusID = os.OfferStatusID\n"
                        + "JOIN [ContractType] ct ON o.ContractTypeID = ct.ContractTypeID\n"
                        + "JOIN [Position] p ON o.PositionID = p.PositionID\n"
                        + "JOIN [Level] l ON o.LevelID = l.LevelID\n"
                        + "JOIN [User] u2 ON o.RecuiterOwner = u2.UserID\n"
                        + "JOIN [User] u3 ON o.ModifiedBy = u3.UserID\n"
                        + "WHERE o.OfferID = ?")) {
            preparedStatement.setLong(1, offerId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return OfferInformationDTO.builder()
                        .offerId(rs.getLong("OfferID"))
                        .candidateName(rs.getString("CandidateName"))
                        .contractTypeName(rs.getString("TypeName"))
                        .positionName(rs.getString("PositionName"))
                        .levelName(rs.getString("LevelName"))
                        .approverName(rs.getString("ApproverName"))
                        .approverUsename(rs.getString("ApproverUsename"))
                        .departmentName(rs.getString("DepartmentName"))
                        .recruiter(rs.getString("Recruiter"))
                        .contractFrom(rs.getString("ContractFrom"))
                        .contractTo(rs.getString("ContractTo"))
                        .dueDate(rs.getString("DueDate"))
                        .basicSalary(rs.getString("BasicSalary"))
                        .note(rs.getString("Note"))
                        .statusName(rs.getString("StatusName"))
                        .createdAt(rs.getString("CreatedAt"))
                        .modifiedBy(rs.getString("ModifiedBy"))
                        .lastModified(rs.getString("LastModified"))
                        .build();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InterviewSchedule getInterviewScheduleInfByOfferId(Long offerId) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT isc.InterviewScheduleID, isc.ScheduleTitle, isc.Notes FROM InterviewSchedule isc\n"
                        + "JOIN Offer o ON o.InterviewScheduleID = isc.InterviewScheduleID\n"
                        + "WHERE o.OfferID = ?")) {
            preparedStatement.setLong(1, offerId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return InterviewSchedule.builder()
                        .interviewScheduleId(rs.getLong("InterviewScheduleID"))
                        .scheduleTitle(rs.getString("ScheduleTitle"))
                        .notes(rs.getString("Notes"))
                        .build();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getInterviewersByScheduleId(Long scheduleId) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT STRING_AGG(u.Usename, ',') AS InterviewersName FROM Interviewer i\n"
                        + "JOIN [User] u ON i.UserID = u.UserID\n"
                        + "WHERE InterviewScheduleID = ?")) {
            preparedStatement.setLong(1, scheduleId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("InterviewersName");
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OfferStatus> getAllOfferStatuses() {
        List<OfferStatus> offerStatuses = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [OfferStatus]")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OfferStatus offerStatus = OfferStatus.builder()
                        .offerStatusId(rs.getLong("OfferStatusID"))
                        .statusName(rs.getString("StatusName"))
                        .build();
                offerStatuses.add(offerStatus);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerStatuses;
    }

    public List<Offer> getAllOffers() {
        List<Offer> offers = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [Offer]")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Offer offer = Offer.builder()
                        .offerId(rs.getLong("OfferID"))
                        .candidateId(rs.getLong("CandidateID"))
                        .contractTypeId(rs.getLong("ContractTypeID"))
                        .positionId(rs.getLong("PositionID"))
                        .levelId(rs.getLong("LevelID"))
                        .appover(rs.getLong("Approver"))
                        .departmentId(rs.getLong("DepartmentID"))
                        .interviewScheduleId(rs.getLong("InterviewScheduleID"))
                        .recuiterOwner(rs.getLong("RecuiterOwner"))
                        .contractFrom(rs.getDate("ContractFrom").toLocalDate())
                        .contractTo(rs.getDate("ContractTo").toLocalDate())
                        .dueDate(rs.getDate("DueDate").toLocalDate())
                        .basicSalary(rs.getDouble("BasicSalary"))
                        .note(rs.getString("Note"))
                        .offerStatusId(rs.getLong("OfferStatusID"))
                        .build();
                offers.add(offer);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

    public List<OfferInformationDTO> searchOffers(String searchValue, String departmentId, String statusId, int pageNum) {
        List<OfferInformationDTO> offers = new ArrayList<>();
        String SQL = "SELECT o.OfferID\n"
                + "      ,c.FullName AS [CandidateName]\n"
                + "	  ,c.Email\n"
                + "      ,u.FullName AS [ApproverName]\n"
                + "      ,d.DepartmentName\n"
                + "      ,o.Note\n"
                + "      ,os.StatusName\n"
                + "  FROM [Offer] o\n"
                + "  JOIN [Candidate] c on o.CandidateID = c.CandidateID\n"
                + "  JOIN [User] u ON o.Approver = u.UserID\n"
                + "  JOIN [Department] d ON o.DepartmentID = d.DepartmentID\n"
                + "  JOIN [OfferStatus] os ON o.OfferStatusID = os.OfferStatusID WHERE (c.FullName LIKE '%" + searchValue + "%'"
                + " OR c.Email LIKE '%" + searchValue + "%') ";
        if (!departmentId.equals("Department") && !departmentId.equals("")) {
            SQL += " AND o.DepartmentID = " + departmentId + " ";
        }

        if (!statusId.equals("Status") && !statusId.equals("")) {
            SQL += "  AND o.OfferStatusID = " + statusId + " ";
        }

        int numRows = 2;

        SQL += " ORDER BY o.LastModified DESC\n"
                + "  OFFSET " + (pageNum - 1) * numRows + " ROWS \n"
                + "FETCH NEXT " + numRows + " ROWS ONLY";
        System.out.println(SQL);
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement(SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OfferInformationDTO offer = OfferInformationDTO.builder()
                        .offerId(rs.getLong("OfferID"))
                        .candidateName(rs.getString("CandidateName"))
                        .email(rs.getString("Email"))
                        .approverName(rs.getString("ApproverName"))
                        .departmentName(rs.getString("DepartmentName"))
                        .note(rs.getString("Note"))
                        .statusName(rs.getString("StatusName"))
                        .build();
                offers.add(offer);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

    public List<ContractType> getAllContractTypes() {
        List<ContractType> contractTypes = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [ContractType]")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ContractType contractType = ContractType.builder()
                        .contractTypeID(rs.getLong(1))
                        .typeName(rs.getString(2))
                        .build();
                contractTypes.add(contractType);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractTypes;
    }

    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [Position]")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Position position = Position.builder()
                        .positionId(rs.getLong("PositionID"))
                        .positionName(rs.getString("PositionName"))
                        .build();
                positions.add(position);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
    }

    public List<Level> getAllLevels() {
        List<Level> levels = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [Level]")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Level level = Level.builder()
                        .levelId(rs.getLong("LevelID"))
                        .levelName(rs.getString("LevelName"))
                        .build();
                levels.add(level);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [Department]")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Department department = Department.builder()
                        .departmentId(rs.getLong("DepartmentID"))
                        .departmentName(rs.getString("DepartmentName"))
                        .build();
                departments.add(department);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public List<User> getAllActiveManager() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [User]\n"
                        + "WHERE UserRoleID = 4 AND UserStatusID = 1")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = User.builder()
                        .userId(rs.getLong("UserID"))
                        .fullName(rs.getString("FullName"))
                        .useName(rs.getString("UseName"))
                        .build();
                users.add(user);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getAllActiveRecuiter() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM [User]\n"
                        + "WHERE UserRoleID = 2 AND UserStatusID = 1")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = User.builder()
                        .userId(rs.getLong("UserID"))
                        .fullName(rs.getString("FullName"))
                        .useName(rs.getString("UseName"))
                        .build();
                users.add(user);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Candidate> getOfferableCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT c.* \n"
                        + "FROM Candidate c\n"
                        + "INNER JOIN InterviewSchedule i ON c.CandidateID = i.CandidateID\n"
                        + "LEFT JOIN Offer o ON c.CandidateID = o.CandidateID\n"
                        + "WHERE i.Result = 'Passed'\n"
                        + "AND (o.CandidateID IS NULL OR o.OfferStatusID IN (3, 6, 7))\n"
                        + "AND NOT EXISTS (\n"
                        + "    SELECT 1\n"
                        + "    FROM Offer o2\n"
                        + "    WHERE o2.CandidateID = c.CandidateID\n"
                        + "    AND o2.OfferStatusID IN (1, 2, 4, 5)\n"
                        + ")\n"
                        + "AND c.CandidateStatusID = 1;")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Candidate candidate = Candidate.builder()
                        .candidateId(rs.getLong("CandidateID"))
                        .fullName(rs.getString("FullName"))
                        .build();
                candidates.add(candidate);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    public Candidate getCandidateByOfferId(Long offerId) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT c.* FROM Candidate c\n"
                        + "LEFT JOIN Offer o ON c.CandidateID = o.CandidateID\n"
                        + "WHERE o.OfferID = ?;")) {
            preparedStatement.setLong(1, offerId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return Candidate.builder()
                        .candidateId(rs.getLong("CandidateID"))
                        .fullName(rs.getString("FullName"))
                        .build();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<InterviewSchedule> getInterviewSchedule() {
        List<InterviewSchedule> interviewSchedules = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM InterviewSchedule ins JOIN Candidate c ON ins.CandidateID = c.CandidateID\n"
                        + "WHERE ins.CandidateID IN (SELECT c.CandidateID FROM Candidate c\n"
                        + "		INNER JOIN InterviewSchedule i ON c.CandidateID = i.CandidateID\n"
                        + "		LEFT JOIN Offer o ON c.CandidateID = o.CandidateID\n"
                        + "		WHERE i.Result = 'Passed'\n"
                        + "		AND (o.CandidateID IS NULL OR o.OfferStatusID IN (3, 6, 7))\n"
                        + "		AND c.CandidateStatusID = 1)")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                InterviewSchedule interviewSchedule = InterviewSchedule.builder()
                        .interviewScheduleId(rs.getLong("InterviewScheduleID"))
                        .scheduleTitle(rs.getString("ScheduleTitle"))
                        .notes(rs.getString("Note"))
                        .candidateId(rs.getLong("CandidateID"))
                        .build();
                interviewSchedules.add(interviewSchedule);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interviewSchedules;
    }

    public boolean saveOffer(Offer offer) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("INSERT INTO Offer ([CandidateID]\n"
                        + "      ,[ContractTypeID]\n"
                        + "      ,[PositionID]\n"
                        + "      ,[LevelID]\n"
                        + "      ,[Approver]\n"
                        + "      ,[DepartmentID]\n"
                        + "      ,[InterviewScheduleID]\n"
                        + "      ,[RecuiterOwner]\n"
                        + "      ,[ContractFrom]\n"
                        + "      ,[ContractTo]\n"
                        + "      ,[DueDate]\n"
                        + "      ,[BasicSalary]\n"
                        + "      ,[Note]\n"
                        + "      ,[OfferStatusID]\n"
                        + "      ,[ModifiedBy]\n"
                        + "      ,[LastModified]\n"
                        + "      ,[CreatedAt])\n"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setLong(1, offer.getCandidateId());
            preparedStatement.setLong(2, offer.getContractTypeId());
            preparedStatement.setLong(3, offer.getPositionId());
            preparedStatement.setLong(4, offer.getLevelId());
            preparedStatement.setLong(5, offer.getAppover());
            preparedStatement.setLong(6, offer.getDepartmentId());
            preparedStatement.setLong(7, offer.getInterviewScheduleId());
            preparedStatement.setLong(8, offer.getRecuiterOwner());
            preparedStatement.setDate(9, Date.valueOf(offer.getContractFrom()));
            preparedStatement.setDate(10, Date.valueOf(offer.getContractTo()));
            preparedStatement.setDate(11, Date.valueOf(offer.getDueDate()));
            preparedStatement.setDouble(12, offer.getBasicSalary());
            preparedStatement.setString(13, offer.getNote());
            preparedStatement.setLong(14, offer.getOfferStatusId());
            preparedStatement.setLong(15, offer.getModifiedBy());
            preparedStatement.setTimestamp(16, Timestamp.valueOf(offer.getModifiedAt()));
            preparedStatement.setTimestamp(17, Timestamp.valueOf(offer.getCreatedAt()));
            preparedStatement.executeUpdate();
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateOffer(Offer offer) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("UPDATE [Offer] \n"
                        + "SET [CandidateID] = ?, \n"
                        + "    [ContractTypeID] = ?, \n"
                        + "    [PositionID] = ?, \n"
                        + "    [LevelID] = ?, \n"
                        + "    [Approver] = ?, \n"
                        + "    [DepartmentID] = ?, \n"
                        + "    [InterviewScheduleID] = ?, \n"
                        + "    [RecuiterOwner] = ?, \n"
                        + "    [ContractFrom] = ?, \n"
                        + "    [ContractTo] = ?, \n"
                        + "    [DueDate] = ?, \n"
                        + "    [BasicSalary] = ?, \n"
                        + "    [Note] = ?, \n"
                        + "    [OfferStatusID] = ?, \n"
                        + "    [ModifiedBy] = ?, \n"
                        + "    [LastModified] = ? \n"
                        + "WHERE OfferID = ?")) {
            preparedStatement.setLong(1, offer.getCandidateId());
            preparedStatement.setLong(2, offer.getContractTypeId());
            preparedStatement.setLong(3, offer.getPositionId());
            preparedStatement.setLong(4, offer.getLevelId());
            preparedStatement.setLong(5, offer.getAppover());
            preparedStatement.setLong(6, offer.getDepartmentId());
            preparedStatement.setLong(7, offer.getInterviewScheduleId());
            preparedStatement.setLong(8, offer.getRecuiterOwner());
            preparedStatement.setDate(9, Date.valueOf(offer.getContractFrom()));
            preparedStatement.setDate(10, Date.valueOf(offer.getContractTo()));
            preparedStatement.setDate(11, Date.valueOf(offer.getDueDate()));
            preparedStatement.setDouble(12, offer.getBasicSalary());
            preparedStatement.setString(13, offer.getNote());
            preparedStatement.setLong(14, offer.getOfferStatusId());
            preparedStatement.setLong(15, offer.getModifiedBy());
            preparedStatement.setTimestamp(16, Timestamp.valueOf(offer.getModifiedAt()));
            preparedStatement.setLong(17, offer.getOfferId());
            preparedStatement.executeUpdate();
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateNewOfferStatus(String offerId, int newStatus, Long modifierId) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("UPDATE [Offer]\n"
                        + "SET OfferStatusID = ?,"
                        + "LastModified = ?,"
                        + "ModifiedBy = ?\n"
                        + "WHERE OfferID = ?;")) {
            preparedStatement.setInt(1, newStatus);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(3, modifierId);
            preparedStatement.setString(4, offerId);
            int result = preparedStatement.executeUpdate();
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCandidateStatus(Long candidateId, int newStatus) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("UPDATE [Candidate]\n"
                        + "SET CandidateStatusID = ?,"
                        + "LastUpdateAt = ?"
                        + "WHERE CandidateID = ?;")) {
            preparedStatement.setInt(1, newStatus);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setLong(3, candidateId);
            int result = preparedStatement.executeUpdate();
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<OfferRemindDTO> getOfferWithDueDateToday() {
        List<OfferRemindDTO> offers = new ArrayList<>();
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT \n"
                        + "o.OfferID,\n"
                        + "appr.Email AS ApproverMail,\n"
                        + "c.FullName AS CandidateName,\n"
                        + "p.PositionName,\n"
                        + "c.CVAttachment,\n"
                        + "FORMAT(o.DueDate,'dd/MM/yyyy') AS DueDate,\n"
                        + "recr.Usename AS RecuiterOwner\n"
                        + "FROM [Offer] o\n"
                        + "JOIN [User] appr ON o.Approver = appr.UserID\n"
                        + "JOIN [Candidate] c ON o.CandidateID = c.CandidateID\n"
                        + "JOIN [Position] p ON o.PositionID = p.PositionID\n"
                        + "JOIN [User] recr ON o.Approver = recr.UserID\n"
                        + "WHERE CONVERT(DATE, o.DueDate) = CONVERT(DATE, GETDATE()) AND o.OfferStatusID = 1")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OfferRemindDTO offerRemindDTO = OfferRemindDTO.builder()
                        .offerId(rs.getLong("OfferID"))
                        .approverMail(rs.getString("ApproverMail"))
                        .candidateName(rs.getString("CandidateName"))
                        .candidatePosition(rs.getString("PositionName"))
                        .cvAttachment(rs.getString("CVAttachment"))
                        .dueDate(rs.getString("DueDate"))
                        .recruiterAccount(rs.getString("RecuiterOwner"))
                        .build();
                offers.add(offerRemindDTO);
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

    public InterviewSchedule getInterviewScheduleByCandidateId(Long candidateId) {
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT * FROM InterviewSchedule WHERE CandidateID = ?")) {
            preparedStatement.setLong(1, candidateId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return InterviewSchedule.builder()
                        .interviewScheduleId(rs.getLong("InterviewScheduleID"))
                        .scheduleTitle(rs.getString("ScheduleTitle"))
                        .notes(rs.getString("Notes"))
                        .build();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

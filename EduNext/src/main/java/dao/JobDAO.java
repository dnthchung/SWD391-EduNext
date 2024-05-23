/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Logger;
import model.Benefit;
import model.Job;
import model.Level;

import model.Skill;
import utils.DBContext;

/**
 *
 * @author acer
 */
public class JobDAO extends DBContext{

    public ArrayList<Job> getAllJob() {
        ArrayList<Job> jobList = new ArrayList<>();
        String sql = "SELECT * FROM [Job]";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Job job = Job.builder()
                        .jobId(rs.getInt("JobID"))
                        .jobTitle(rs.getString("jobTitle"))
                        .startDate(rs.getDate("StartDate").toLocalDate())
                        .endDate(rs.getDate("EndDate").toLocalDate())
                        .salaryFrom(rs.getInt("SalarayFrom"))
                        .salaryTo(rs.getInt("SalaryTo"))
                        .workAddress(rs.getString("WorkingAddress"))
                        .description(rs.getString("Description"))
                        .status(rs.getBoolean("status"))
                        .build();
                jobList.add(job);
            }
            return jobList;
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Job> getAllJobsWithDetails(String jobId) {
        ArrayList<Job> jobList = new ArrayList<>();
        String sql = "SELECT j.*, s.skillName, b.benefitName, l.levelName\n"
                + "FROM Job j\n"
                + "LEFT JOIN JobSkills js ON j.jobId = js.jobId\n"
                + "LEFT JOIN Skill s ON js.skillId = s.skillId\n"
                + "LEFT JOIN JobBenefits jb ON j.jobId = jb.jobId\n"
                + "LEFT JOIN Benefit b ON jb.benefitId = b.benefitId\n"
                + "LEFT JOIN JobLevel jl ON j.jobId = jl.jobId\n"
                + "LEFT JOIN Level l ON jl.levelId = l.levelId\n"
                + "where j.JobID = ?";
        try (Connection connection = DBContext.makeConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, jobId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill();
                
                skill.setSkillName(rs.getString("skillName"));

                Benefit benefit = new Benefit();
                
                benefit.setBenefitName(rs.getString("benefitName"));

                Level level = new Level();
                
                level.setLevelName(rs.getString("levelName"));

                Job job = Job.builder()
                        .jobId(rs.getInt("JobId"))
                        .jobTitle(rs.getString("JobTitle"))
                        .startDate(rs.getDate("StartDate").toLocalDate())
                        .endDate(rs.getDate("EndDate").toLocalDate())
                        .salaryFrom(rs.getDouble("SalarayFrom"))
                        .salaryTo(rs.getDouble("SalaryTo"))
                        .workAddress(rs.getString("WorkingAddress"))
                        .description(rs.getString("Description"))
                        .status(rs.getBoolean("Status"))
                        .level(level)
                        .skill(skill)
                        .benefit(benefit)
                        .build();
                jobList.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public static void main(String[] args) {
        JobDAO dao = new JobDAO();
        ArrayList<Job> j = dao.getAllJobsWithDetails("1");
        System.out.println(j);
    }

}

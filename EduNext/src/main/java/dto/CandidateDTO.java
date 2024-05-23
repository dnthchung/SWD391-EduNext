/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.CandidateStatus;
import model.Level;
import model.Position;
import model.Skill;
import model.User;

/**
 *
 * @author chun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CandidateDTO {
    private Long candidateId;
    private String fullName;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String address;
    private int gender;
    private String cvAttachment;
    private Position position;
    private String note;
    private CandidateStatus candidateStatus;
    private int yearOfExperience;
    private Level highestLevel;
    private List<Skill> skill;
    private User createBy;
    private LocalDate lastUpdateAt;
    private User recruiter;
    
    
}

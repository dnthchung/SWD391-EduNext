/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.List;
import lombok.*;

/**
 *
 * @author Vanhle
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    private Long candidateId;
    private String fullName;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String address;
    private int gender;
    private String cvAttachment;
    private Long positionId;
    private String note;
    private Long candidateStatusId;
    private int yearOfExperience;
    private Long highestLevel;
    private Long createBy;
    private LocalDate lastUpdateAt;
    private Long recruiterId;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;



import lombok.*;

@Builder
@Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Job  {
    private int jobId;
    private String jobTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private double salaryFrom;
    private double salaryTo;
    private String workAddress;
    private String description;
    private Boolean status;
    private Benefit benefit;
    
    private Skill skill;
    private Level level;

  
    
}

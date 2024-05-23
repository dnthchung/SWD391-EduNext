/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tranh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {
    private Long offerId;
    private Long candidateId;
    private Long contractTypeId;
    private Long positionId;
    private Long levelId;
    private Long appover;
    private Long departmentId;
    private Long interviewScheduleId;
    private Long recuiterOwner;
    private LocalDate contractFrom;
    private LocalDate contractTo;
    private LocalDate dueDate;
    private Double basicSalary;
    private String note;
    private Long offerStatusId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long modifiedBy;
    
    public String getFormatedSalary() {
        String val = String.format("%.0f", basicSalary);
        return val.replace(".", "");
    }
    
    public String getFormatedDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
}

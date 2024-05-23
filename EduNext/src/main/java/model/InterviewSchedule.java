/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Tuan
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewSchedule {
    private Long interviewScheduleId;
    private String scheduleTitle;
    private Long jobId;
    private Long candidateId;
    private LocalDate scheduleDateFrom;
    private LocalDate scheduleDateTo;
    private String location;
    private Long userId;
    private String notes;
    private String meetingId;
    private String result;
    private Long interviewStatusId;
    
}

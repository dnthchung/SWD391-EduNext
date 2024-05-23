/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author chun
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewStatus {
    private Long interviewStatusId;
    private Long statusName;
}

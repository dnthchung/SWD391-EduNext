/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.*;

/**
 *
 * @author Vanhle
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CandidateStatus {
    private Long candidateStatusId;
    private String statusName;
}

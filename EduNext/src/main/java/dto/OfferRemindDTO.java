/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tranh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferRemindDTO {
    private Long offerId;
    private String approverMail;
    private String candidateName;
    private String candidatePosition;
    private String cvAttachment;
    private String dueDate;
    private String recruiterAccount;
}

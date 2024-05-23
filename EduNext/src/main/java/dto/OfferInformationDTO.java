/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
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
public class OfferInformationDTO {

    private Long offerId;
    private String candidateName;
    private String email;
    private String approverName;
    private String approverUsename;
    private String departmentName;
    private String note;
    private String statusName;
    private String contractTypeName;
    private String positionName;
    private String levelName;
    private String recruiter;
    private String contractFrom;
    private String contractTo;
    private String dueDate;
    private String basicSalary;
    private String createdAt;
    private String modifiedBy;
    private String lastModified;

    public String getCalculatedLastModified() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateFromString = LocalDate.parse(this.lastModified, formatter);
        if (dateFromString.isEqual(LocalDate.now())) {
            return "today";
        } else {
            return this.lastModified;
        }
    }

    public String getFormattedSalary() {
        String amount = this.basicSalary;
        StringBuilder formattedAmount = new StringBuilder();
        int endPoint = amount.indexOf(".");
        if (endPoint < 0) {
            endPoint = amount.length();
        }
        int count = 0;
        for (int i = endPoint - 1; i >= 0; i--) {
            formattedAmount.insert(0, amount.charAt(i));
            count++;

            if (count % 3 == 0 && i > 0) {
                formattedAmount.insert(0, ",");
            }
        }
        return formattedAmount.toString();
    }
}

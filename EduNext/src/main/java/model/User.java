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
@Getter
@Setter
public class User {
    private Long userId;
    private String useName;
    private String fullName;
    private String password;
    private LocalDate dob;
    private String phoneNumber;
    private int userRoleId;
    private int userStatusId;
    private String email;
    private String address;
    private int gender;
    private Long departmentId;
    private String note;

    public String getGenderAsString() {
        switch (gender) {
            case 0:
                return "Female";
            case 1:
                return "Male";
            default:
                return "Other";
        }
    }


}

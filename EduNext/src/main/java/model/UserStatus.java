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
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserStatus {
    private Long userStatusId;
    private String statusName;
}

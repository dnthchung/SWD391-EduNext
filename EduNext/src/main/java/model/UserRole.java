/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
/**
 *
 * @author chun
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private Long userRoleId;
    private String roleName;
}

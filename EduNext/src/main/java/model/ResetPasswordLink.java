/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDateTime;
import lombok.*;
/**
 *
 * @author chun
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordLink {
    private String resetPasswordId;
    private Long userId;
    private LocalDateTime linkGeneratedTime;
    private boolean isUsed;
}

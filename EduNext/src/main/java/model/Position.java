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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Position {
    private Long positionId;
    private String positionName;
    
}

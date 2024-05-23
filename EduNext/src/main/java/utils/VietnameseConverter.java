/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.Normalizer;

/**
 *
 * @author chun
 */
public class VietnameseConverter {

    public static String toLowerCaseNonAccentVietnamese(String str) {
        str = str.toLowerCase();
        str = str.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
        str = str.replaceAll("[èéẹẻẽêềếệểễ]", "e");
        str = str.replaceAll("[ìíịỉĩ]", "i");
        str = str.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
        str = str.replaceAll("[ùúụủũưừứựửữ]", "u");
        str = str.replaceAll("[ỳýỵỷỹ]", "y");
        str = str.replaceAll("đ", "d");

        str = Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return str;
    }

    public static String toNonAccentVietnamese(String str) {
        str = str.replaceAll("[AÁÀÃẠÂẤẦẪẬĂẮẰẴẶ]", "A");
        str = str.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
        str = str.replaceAll("[EÉÈẼẸÊẾỀỄỆ]", "E");
        str = str.replaceAll("[èéẹẻẽêềếệểễ]", "e");
        str = str.replaceAll("[IÍÌĨỊ]", "I");
        str = str.replaceAll("[ìíịỉĩ]", "i");
        str = str.replaceAll("[OÓÒÕỌÔỐỒỖỘƠỚỜỠỢ]", "O");
        str = str.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
        str = str.replaceAll("[UÚÙŨỤƯỨỪỮỰ]", "U");
        str = str.replaceAll("[ùúụủũưứừữự]", "u");
        str = str.replaceAll("[YÝỲỸỴ]", "Y");
        str = str.replaceAll("[ỳýỵỷỹ]", "y");
        str = str.replaceAll("Đ", "D");
        str = str.replaceAll("đ", "d");
        // Some system encode Vietnamese combining accent as individual utf-8 characters
        str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return str;
    }

    public static void main(String[] args) {
        String vietnameseText = "Đoàn Chung";
        System.out.println("Original: " + vietnameseText);
        System.out.println("Lowercase Non-Accent: " + toLowerCaseNonAccentVietnamese(vietnameseText));
        System.out.println("Non-Accent: " + toNonAccentVietnamese(vietnameseText));
    }
}

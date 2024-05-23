/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

public class DBFileUtils {

    public static String uploadFile(String UPLOAD_DIR, HttpServletRequest request) throws IOException {
        String fileName = null;
        try {
            List<Part> parts = (List<Part>) request.getParts();
            for (Part part : parts) {
                if (part.getName().equalsIgnoreCase("file")) {
                    fileName = getPredefinedUserImageName(part);
                    String applicationPath = request.getServletContext().getRealPath("");
                    String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
                    Files.createDirectories(Paths.get(basePath));
                    FileUtils.cleanDirectory(new File(basePath));
                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    try {
                        File outputFilePath = new File(basePath + fileName);
                        inputStream = part.getInputStream();
                        outputStream = new FileOutputStream(outputFilePath);
                        int read = 0;
                        final byte[] bytes = new byte[1024];
                        while ((read = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                        }
                    } catch (IOException ex) {
                        fileName = null;
                    } finally {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
            }
        } catch (ServletException | IOException | StringIndexOutOfBoundsException e) {
            return null;
        }
        return fileName;
    }

    private static String getPredefinedUserImageName(Part part) {
        UUID uuid = UUID.randomUUID();
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return uuid + content.substring(content.indexOf('.')).trim().replace("\"", "");
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

    }
}

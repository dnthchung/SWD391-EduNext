package utils;

import com.cloudinary.*;

//import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.*;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloudinaryService {

    public Cloudinary cloudinary() {
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dml7bkacn",
                "api_key", "232191739676339",
                "api_secret", "DzoJwKBAtA2QYRoH_eMEp9C-Rgk",
                "secure", true
        ));
        return c;
    }

    public void uploadImage(String image_path, String image_name, String folder) {
        try {
            String public_id = folder + "/" + image_name;
            cloudinary().uploader().upload(image_path,
                    ObjectUtils.asMap("public_id", public_id));
            System.out.println("Uploaded");
        } catch (IOException ex) {
            Logger.getLogger(CloudinaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getImageUrl(String folder, String image_name) {
        String image_path = folder + "/" + image_name;
        try {
            String image_url = cloudinary().url().generate(image_path);
//            String image_url = cloudinary().url().toString();
            return image_url;
        } catch (Exception e) {

        }
        return null;
    }

    public void deleteImage(String imageName) {
        try {
            // Thực hiện xóa ảnh
            Map result = cloudinary().uploader().destroy(extractPublicIdFromUrl(imageName), ObjectUtils.emptyMap());

            // Kiểm tra kết quả
            if (result.get("result").equals("ok")) {
                System.out.println("Đã xóa ảnh thành công.");
            } else {
                System.out.println("Không thể xóa ảnh.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String extractPublicIdFromUrl(String imageUrl) {
        // Chia URL thành các phần bằng dấu /
        String[] parts = imageUrl.split("/");
        String concat = parts[parts.length - 2] + "/" + parts[parts.length - 1];
        // Lấy chuỗi vừa ghép phân tách tiếp dấu chấm
        String[] parts2 = concat.split("\\.");
        return parts2[0];

    }

    public static void main(String[] args) throws IOException {
        CloudinaryService c = new CloudinaryService();
//        c.uploadImage("C:\\Users\\kingo\\OneDrive\\Pictures\\test test.jpg","another","CV");
//        String s = "C:\\Users\\kingo\\OneDrive\\Pictures\\Screenshots\\test.png";
//        s = s.replaceAll("\\\\", "/");
//        System.out.println(s);
//        System.out.println(c.cloudinary().url().generate("test.png"));
//        System.out.println(c.cloudinary().));
//          c.deleteImage("");
        System.out.println(c.getImageUrl("CV", "another"));
//        c.deleteImage("blog/9bbf2212-d2c4-48ee-b0bd-c68ed7042c69");
//        c.deleteImage("https://res.cloudinary.com/dorayifqz/image/upload/v1/blog/another.png");
//        System.out.println(c.extractPublicIdFromUrl("https://res.cloudinary.com/dorayifqz/image/upload/v1/blog/9bbf2212-d2c4-48ee-b0bd-c68ed7042c69.png"));
//        c.deleteImage("blog/9bbf2212-d2c4-48ee-b0bd-c68ed7042c69");
//        System.out.println(c.cloudinary().url());
    }

}

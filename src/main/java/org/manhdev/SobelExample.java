package org.manhdev;

import boofcv.abst.filter.derivative.ImageGradient;
import boofcv.factory.filter.derivative.FactoryDerivative;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayF32;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SobelExample {
    public static void main(String[] args) throws IOException {
        // Đọc ảnh
        BufferedImage input = ImageIO.read(new File("/Users/nguyenthemanh/Downloads/hinh-anh-nguoi-thanh-cong-inkythuatso-06-15-04-06.jpg"));

        // Chuyển đổi ảnh sang định dạng GrayF32
        GrayF32 gray = ConvertBufferedImage.convertFromSingle(input, null, GrayF32.class);

        // Khởi tạo ảnh gradient cho x và y
        GrayF32 gradientX = new GrayF32(gray.width, gray.height);
        GrayF32 gradientY = new GrayF32(gray.width, gray.height);

        // Áp dụng toán tử Sobel
        ImageGradient<GrayF32, GrayF32> sobel = FactoryDerivative.sobel(GrayF32.class, null);
        sobel.process(gray, gradientX, gradientY);

        // Hiển thị gradient kết quả (gradientX và gradientY chứa thông tin gradient theo hai chiều)
        BufferedImage outputX = ConvertBufferedImage.convertTo(gradientX, null);
        BufferedImage outputY = ConvertBufferedImage.convertTo(gradientY, null);

        // Lưu ảnh kết quả
        ImageIO.write(outputX, "png", new File("sobel_gradient_x.png"));
        ImageIO.write(outputY, "png", new File("sobel_gradient_y.png"));
    }
}

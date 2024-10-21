package org.manhdev;

import boofcv.alg.filter.derivative.GImageDerivativeOps;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.border.BorderType;
import boofcv.struct.image.GrayF32;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LaplacianExample {
    public static void main(String[] args) throws IOException {
        // Đọc ảnh
        BufferedImage input = ImageIO.read(new File("/Users/nguyenthemanh/Downloads/hinh-anh-nguoi-thanh-cong-inkythuatso-06-15-04-06.jpg"));

        // Chuyển đổi sang ảnh grayscale
        GrayF32 gray = ConvertBufferedImage.convertFromSingle(input, null, GrayF32.class);

        // Áp dụng Laplacian Gaussian với BorderType
        GrayF32 laplacian = new GrayF32(gray.width, gray.height);
        GImageDerivativeOps.laplace(gray, laplacian, BorderType.EXTENDED);  // Thêm BorderType.EXTENDED

        // Lưu ảnh kết quả
        BufferedImage output = ConvertBufferedImage.convertTo(laplacian, null);
        ImageIO.write(output, "png", new File("laplacian_output.png"));
    }
}

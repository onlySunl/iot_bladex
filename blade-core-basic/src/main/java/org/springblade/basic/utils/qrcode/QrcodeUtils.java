package org.springblade.basic.utils.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.EnumMap;
import java.util.Map;

/**
 * 二维码生成和处理的工具类。
 * 提供了生成带有Logo的二维码以及解析二维码内容的功能。
 *
 * @author xiaonannet
 */
@Slf4j
@UtilityClass
public class QrcodeUtils {

    /**
     * 生成二维码的默认边长，因为是正方形的，所以高度和宽度一致。
     */
    private static final int DEFAULT_LENGTH = 400;

    /**
     * 生成二维码的格式。
     */
    private static final String FORMAT = "png";

    /**
     * 根据内容生成二维码数据。
     *
     * @param content 二维码文字内容，为了信息安全性，通常需要先进行数据加密。
     * @param length  二维码图片的宽度和高度。
     * @return 生成的二维码的 BitMatrix 对象。
     */
    public static BitMatrix createQrcodeMatrix(String content, int length) {
        return createQrcodeMatrix(content, length, ErrorCorrectionLevel.H);
    }

    /**
     * 根据内容生成二维码数据，允许指定纠错级别。
     *
     * @param content 二维码文字内容。
     * @param length  二维码图片的宽度和高度。
     * @param level   二维码的纠错级别。
     * @return 生成的二维码的 BitMatrix 对象。
     */
    public static BitMatrix createQrcodeMatrix(String content, int length, ErrorCorrectionLevel level) {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        hints.put(EncodeHintType.ERROR_CORRECTION, level);

        try {
            // 先尝试默认尺寸
            return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, length, length, hints);
        } catch (WriterException e) {
            log.warn("默认尺寸二维码生成失败，尝试增大二维码尺寸", e);

            // 如果失败，尝试增大二维码尺寸
            int largerLength = length + 200;  // 增加二维码的边长
            try {
                return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, largerLength, largerLength, hints);
            } catch (WriterException ex) {
                log.error("内容为：【{}】的二维码生成失败！数据可能过大。", content, ex);
                throw new RuntimeException("二维码生成失败，数据过大", ex);
            }
        }
    }


    /**
     * 根据指定边长创建生成的二维码，允许配置 Logo 属性。
     *
     * @param content    二维码内容。
     * @param length     二维码的高度和宽度。
     * @param logoStream Logo 文件流，可以为空。
     * @param logoConfig Logo 配置，可以设置 Logo 展示的长宽和边框颜色。
     * @return 生成的二维码图片的字节数组。
     * @throws Exception 如果生成过程中出现错误。
     */
    public static byte[] createQrcode(String content, int length, InputStream logoStream, MatrixToLogoImageConfig logoConfig)
            throws Exception {
        BufferedImage img = generateQRCodeImage(content, length, logoStream, logoConfig);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(img, FORMAT, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            log.error("生成二维码时发生I/O错误", e);
            throw e;
        }
    }

    /**
     * 根据指定边长创建生成的二维码。
     *
     * @param content  二维码内容。
     * @param length   二维码的高度和宽度。
     * @param logoFile Logo 文件对象，可以为空。
     * @return 生成的二维码图片的字节数组。
     * @throws Exception 如果生成过程中出现错误。
     */
    public static byte[] createQrcode(String content, int length, File logoFile) throws Exception {
        if (logoFile != null && !logoFile.exists()) {
            throw new IllegalArgumentException("请提供正确的Logo文件！");
        }
        try (InputStream logoStream = logoFile == null ? null : new FileInputStream(logoFile)) {
            return createQrcode(content, length, logoStream, new MatrixToLogoImageConfig());
        }
    }


    /**
     * 创建默认大小的二维码图片，可以指定是否带 Logo。
     *
     * @param content  二维码内容。
     * @param logoFile Logo 文件对象，可以为空。
     * @return 生成的二维码图片的字节数组。
     * @throws Exception 如果生成过程中出现错误。
     */
    public static byte[] createQrcode(String content, File logoFile) throws Exception {
        return createQrcode(content, DEFAULT_LENGTH, logoFile);
    }

    /**
     * 根据指定边长创建生成的二维码，使用默认的 Logo 配置。
     *
     * @param content    二维码内容。
     * @param length     二维码的高度和宽度。
     * @param logoStream Logo 文件流，可以为空。
     * @return 生成的二维码图片的字节数组。
     * @throws Exception 如果生成过程中出现错误。
     */
    public static byte[] createQrcode(String content, int length, InputStream logoStream) throws Exception {
        // 使用默认的 MatrixToLogoImageConfig 生成二维码
        return createQrcode(content, length, logoStream, new MatrixToLogoImageConfig());
    }

    /**
     * 生成二维码图像，可以选择是否带 Logo。
     *
     * @param content    二维码内容。
     * @param length     二维码的高度和宽度。
     * @param logoStream Logo 文件流，可以为空。
     * @param logoConfig Logo 配置，可以设置 Logo 展示的长宽和边框颜色。
     * @return 生成的二维码图片。
     * @throws Exception 如果生成过程中出现错误。
     */
    public static BufferedImage generateQRCodeImage(String content, int length, InputStream logoStream, MatrixToLogoImageConfig logoConfig) throws Exception {
        BitMatrix qrCodeMatrix = createQrcodeMatrix(content, length);
        BufferedImage img = MatrixToImageWriter.toBufferedImage(qrCodeMatrix);
        if (logoStream != null) {
            overlapImage(img, FORMAT, logoStream, logoConfig);
        }
        return img;
    }

    /**
     * 根据指定边长创建生成的二维码图像。
     *
     * @param content  二维码内容。
     * @param length   二维码的高度和宽度。
     * @param logoFile Logo 文件对象，可以为空。
     * @return 生成的二维码图片。
     * @throws Exception 如果生成过程中出现错误。
     */
    public static BufferedImage generateQRCodeImage(String content, int length, File logoFile) throws Exception {
        if (logoFile != null && !logoFile.exists()) {
            throw new IllegalArgumentException("请提供正确的Logo文件！");
        }
        try (InputStream logoStream = logoFile == null ? null : Files.newInputStream(logoFile.toPath())) {
            return generateQRCodeImage(content, length, logoStream, new MatrixToLogoImageConfig());
        }
    }

    /**
     * 生成默认大小的二维码图像，可以选择是否带 Logo。
     *
     * @param content    二维码内容。
     * @param logoStream Logo 文件流，可以为空。
     * @return 生成的二维码图片。
     * @throws Exception 如果生成过程中出现错误。
     */
    public static BufferedImage generateQRCodeImage(String content, InputStream logoStream) throws Exception {
        return generateQRCodeImage(content, DEFAULT_LENGTH, logoStream, new MatrixToLogoImageConfig());
    }

    /**
     * 生成默认大小的二维码图像，可以选择是否带 Logo。
     *
     * @param content  二维码内容。
     * @param logoFile Logo 文件对象，可以为空。
     * @return 生成的二维码图片。
     * @throws Exception 如果生成过程中出现错误。
     */
    public static BufferedImage generateQRCodeImage(String content, File logoFile) throws Exception {
        return generateQRCodeImage(content, DEFAULT_LENGTH, logoFile);
    }

    /**
     * 将 Logo 添加到二维码中间。
     *
     * @param image         生成的二维码图片对象。
     * @param logoStream    Logo 文件流。
     * @param ignoredFormat 图片格式（在此方法中被忽略）。
     * @param logoConfig    Logo 配置，可以设置 Logo 展示的长宽和边框颜色。
     * @throws IOException 如果读取 Logo 文件时发生 I/O 错误。
     */
    private static void overlapImage(final BufferedImage image, String ignoredFormat, final InputStream logoStream,
                                     MatrixToLogoImageConfig logoConfig) throws IOException {
        BufferedImage logoImg = ImageIO.read(logoStream);
        logoImg = clipRound(logoImg);
        Graphics2D g = logoImg.createGraphics();
        // 考虑到logo图片贴到二维码中，建议大小不要超过二维码的1/5;
        int width = image.getWidth() / logoConfig.getLogoPart();
        int height = image.getHeight() / logoConfig.getLogoPart();
        int radius = width / 10;
        // logo起始位置，此目的是为logo居中显示
        int x = (image.getWidth() - width) / 2;
        int y = (image.getHeight() - height) / 2;

        // 创建一个支持有透明度的图像缓冲区
        BufferedImage buffer = g.getDeviceConfiguration().createCompatibleImage(image.getWidth(), image.getHeight(), Transparency.TRANSLUCENT);
        g.dispose();

        // 绘制阴影
        g = buffer.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.XOR, 0.1f));
        g.setColor(Color.BLACK);
        g.fillRoundRect(x + 10, y + 10, width - 20, height, radius, radius);
        g.dispose();

        // 绘制LOGO到缓冲区
        g = buffer.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1));
        g.drawImage(logoImg, x, y, width, height, null);

        // 给logo画边框
        g.setStroke(new BasicStroke(logoConfig.getBorder()));
        g.setColor(logoConfig.getBorderColor());
        g.drawRoundRect(x, y, width, height, radius, radius);
        g.setStroke(new BasicStroke(1));
        g.setColor(Color.GRAY);
        g.drawRoundRect(x + logoConfig.getBorder() / 2, y + logoConfig.getBorder() / 2, width - logoConfig.getBorder(),
                height - logoConfig.getBorder(), radius, radius);
        g.dispose();

        // 将带阴影的图像绘制到二维码上
        g = image.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
        g.drawImage(buffer, 0, 0, image.getWidth(), image.getHeight(), null);
        g.dispose();
    }

    /**
     * 为 Logo 剪出圆角。
     *
     * @param srcImage Logo 图像。
     * @return 带有圆角的 Logo 图像。
     */
    private static BufferedImage clipRound(BufferedImage srcImage) {
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        int radius = width / 10;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setClip(new RoundRectangle2D.Double(0, 0, width, height, radius, radius));
        g.drawImage(srcImage, 0, 0, null);
        g.dispose();
        return image;
    }

    /**
     * 解析二维码。
     *
     * @param file 二维码文件。
     * @return 二维码的内容。
     * @throws IOException       如果读取文件时发生 I/O 错误。
     * @throws NotFoundException 如果未找到二维码。
     */
    public static String decodeQrcode(File file) throws IOException, NotFoundException {
        // 读取图片文件
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            throw new IOException("无法读取图像文件，可能文件格式不支持或文件已损坏。");
        }

        // 创建 LuminanceSource
        LuminanceSource source = new BufferedImageLuminanceSource(image);

        // 使用 HybridBinarizer 二值化
        Binarizer binarizer = new HybridBinarizer(source);

        // 创建 BinaryBitmap
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

        // 设置解码提示
        Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());

        // 尝试解码二维码
        return new MultiFormatReader().decode(binaryBitmap, hints).getText();
    }
}

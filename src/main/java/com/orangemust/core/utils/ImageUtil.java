package com.orangemust.core.utils;

import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ImageUtil {

  /**
   * 调整bufferedimage大小
   *
   * @param source  BufferedImage 原始image
   * @param targetW int 目标宽
   * @param targetH int 目标高
   * @param flag    boolean 是否同比例调整
   * @return BufferedImage 返回新image
   */
  public static BufferedImage resizeBufferedImage(BufferedImage source, int targetW, int targetH, boolean flag) {
    int type = source.getType();
    BufferedImage target = null;
    double sx = (double) targetW / source.getWidth();
    double sy = (double) targetH / source.getHeight();
    if (flag && sx > sy) {
      sx = sy;
      targetW = (int) (sx * source.getWidth());
    } else if (flag && sx <= sy) {
      sy = sx;
      targetH = (int) (sy * source.getHeight());
    }
    if (type == BufferedImage.TYPE_CUSTOM) { // handmade
      ColorModel cm = source.getColorModel();
      WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
      boolean alphaPremultiplied = cm.isAlphaPremultiplied();
      target = new BufferedImage(cm, raster, alphaPremultiplied, null);
    } else {
      target = new BufferedImage(targetW, targetH, type);
    }
    Graphics2D g = target.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
    g.dispose();
    return target;
  }

  /**
   * 图像格式后缀
   */
  private static List<String> imageExt = Arrays.asList(".png", ".jpg", ".jpeg", ".bmp", ".webp", ".exif");

  /**
   * 判断是否为图片格式后缀
   */
  public static boolean isImageExt(String ext) {
    return imageExt.contains(ext);
  }

  /**
   * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片，这是一种非常简单的方式。
   *
   * @param imageFile
   * @return
   */
  public static boolean isImage(File imageFile) {
    if (!imageFile.exists()) {
      return false;
    }
    Image img;
    try {
      img = ImageIO.read(imageFile);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
      return false;
    }
    return true;
  }

  /**
   * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
   */
  public static String imageToBase64(String path) {
    byte[] data = null;
    // 读取图片字节数组
    try {
      InputStream in = new FileInputStream(path);
      data = new byte[in.available()];
      in.read(data);
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 对字节数组Base64编码
    return Base64.encodeBase64String(data); // 返回Base64编码过的字节数组字符串
  }

  /**
   * 对字节数组字符串进行Base64解码并生成图片
   */
  public static boolean base64ToImage(String base64, String path) {
    if (base64 == null) { // 图像数据为空
      return false;
    }
    try {
      // Base64解码
      byte[] bytes = Base64.decodeBase64(base64);
      for (int i = 0; i < bytes.length; ++i) {
        if (bytes[i] < 0) {// 调整异常数据
          bytes[i] += 256;
        }
      }
      // 生成jpeg图片
      OutputStream out = new FileOutputStream(path);
      out.write(bytes);
      out.flush();
      out.close();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 填充透明像素点
   */
  @SneakyThrows
  public static BufferedImage fillTransparentColor(BufferedImage bImage, int color) {
    // 获取图片的长宽高
    int width = bImage.getWidth();
    int height = bImage.getHeight();
    int minx = bImage.getMinTileX();
    int miny = bImage.getMinTileY();
    // 遍历图片的所有像素点，并对各个像素点进行判断，是否修改
    for (int i = minx; i < width; i++) {
      for (int j = miny; j < height; j++) {
        int pixel = bImage.getRGB(i, j);
        // 判断透明并设置指定颜色值
        if (pixel >> 24 > -1) {
          bImage.setRGB(i, j, color);
        }
      }
    }
    return bImage;
  }

  /**
   * 将图片流保存到文件
   */
  @SneakyThrows
  public static void bufferedImageToFile(BufferedImage bImage, String outFilePath) {
    // 输出照片保存在本地
    FileOutputStream ops = new FileOutputStream(outFilePath);
    ImageIO.write(bImage, getExt(outFilePath), ops);
    ops.flush();
    ops.close();
  }

  /**
   * BufferedImage 编码转换为 base64
   *
   * @param bufferedImage
   * @return
   */
  @SneakyThrows
  public static String bufferedImageToBase64(BufferedImage bufferedImage) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();// io流
    ImageIO.write(bufferedImage, "png", stream);// 写入流中
    byte[] bytes = stream.toByteArray();// 转换成字节
    stream.close();
    String png_base64 = Base64.encodeBase64String(bytes).trim();// 转换成base64串
    png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");// 删除 \r\n
    return "data:image/png;base64," + png_base64;
  }

  /**
   * base64 编码转换为 BufferedImage
   *
   * @param base64
   * @return
   */
  private static BufferedImage base64ToBufferedImage(String base64) {
    try {
      byte[] bytes1 = Base64.decodeBase64(base64);
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
      return ImageIO.read(bais);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 指定大小进行缩放
   */
  @SneakyThrows
  public static void changeSize(String filePath, Integer width, Integer height, String outFilePath) {
    Thumbnails.of(filePath).size(width, height).toFile(outFilePath);
  }

  /**
   * 按照比例进行缩放
   */
  @SneakyThrows
  public static void scale(String filePath, Double scale, String outFilePath) {
    Thumbnails.of(filePath).scale(scale).toFile(outFilePath);
  }

  /**
   * 不按照比例，指定大小进行缩放
   */
  @SneakyThrows
  public static void scaleKeepAspectRatioFalse(String filePath, Integer width, Integer height, String outFilePath) {
    Thumbnails.of(filePath).size(width, height).keepAspectRatio(false).toFile(outFilePath);
  }

  /**
   * 旋转
   */
  @SneakyThrows
  public static void rotate(String filePath, Integer rotate, String outFilePath) {
    Thumbnails.of(filePath).rotate(rotate).toFile(outFilePath);
  }

  /**
   * 转化图像格式
   */
  @SneakyThrows
  public static void convertFormat(String filePath, String outFilePath) {
    Thumbnails.of(filePath).outputFormat(getExt(outFilePath)).toFile(outFilePath);
  }

  /**
   * 输出到OutputStream
   */
  @SneakyThrows
  public static void outputStream(String filePath, String outFilePath) {
    OutputStream os = new FileOutputStream(outFilePath);
    Thumbnails.of(filePath).toOutputStream(os);
  }

  /**
   * 输出到BufferedImage
   */
  @SneakyThrows
  public static void outputBufferedImage(String filePath, String outFilePath) {
    BufferedImage thumbnail = Thumbnails.of(filePath).asBufferedImage();
    ImageIO.write(thumbnail, getExt(outFilePath), new File(outFilePath));
  }

  public static String getExt(String filePath) {
    String[] split = filePath.split("\\.");
    return split[split.length - 1];
  }

}

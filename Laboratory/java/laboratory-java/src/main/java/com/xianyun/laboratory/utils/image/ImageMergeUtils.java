package com.xianyun.laboratory.utils.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 图片合成
 *
 * @Author He Bingxing
 * @Date 2024-12-10
 */
public class ImageMergeUtils {
	
	/**
	 * 是否为空：null|""|" "
	 *
	 * @param str 字符串
	 *
	 * @return boolean
	 * @author He Bingxing
	 * @date 2024-12-11
	 */
	public static boolean isEmpty(String str) {
		return Objects.isNull(str) || str.trim().isEmpty();
	}
	
	/**
	 * 图片合并
	 *
	 * @param imgFileList 带合并图片
	 * @param imgType     图片类型
	 * @param imgName     图片名不带后缀
	 * @param imgOutPath  输出目录
	 *
	 * @return void
	 * @author He Bingxing
	 * @date 2024-05-29
	 */
	public static void imageMerge(List<File> imgFileList, String imgType, String imgName, String imgOutPath) throws IOException {
		if (isEmpty(imgName)) {
			imgName = "result";
		}
		if (isEmpty(imgType)) {
			imgType = "JPG";
		}
		if (isEmpty(imgOutPath)) {
			throw new IllegalArgumentException("输出目录不能为空");
		}
		// 获取待合并图片以及合并后图片高度和宽度
		List<BufferedImage> imgsList = new ArrayList<>();
		int totalHeight = 0;
		int maxWidth = 0;
		for (File file : imgFileList) {
			BufferedImage image1 = ImageIO.read(file);
			imgsList.add(image1);
			totalHeight = totalHeight + 10 + image1.getHeight();
			if (maxWidth < image1.getWidth()) {
				maxWidth = image1.getWidth();
			}
		}
		// 创建一个新的BufferedImage，用于存储合并后的图像
		BufferedImage mergedImage = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
		// 画布
		Graphics2D g2d = mergedImage.createGraphics();
		// 画布颜色
		g2d.setColor(Color.WHITE);
		// 画布多大
		g2d.fillRect(0, 0, maxWidth, totalHeight);
		int startHigth = 0;
		for (BufferedImage itemImg : imgsList) {
			g2d.drawImage(itemImg, 0, startHigth, null);
			startHigth = startHigth + 10 + itemImg.getHeight();
		}
		// 销毁画布
		g2d.dispose();
		// 组装输出路径
		imgOutPath = imgOutPath.lastIndexOf(File.separator) > -1 ? imgOutPath + imgName + "." + imgType.toLowerCase() : imgOutPath + File.separator + imgName + "." + imgType.toLowerCase();
		// 保存合并后的图像
		ImageIO.write(mergedImage, imgType, new File(imgOutPath));
	}
}

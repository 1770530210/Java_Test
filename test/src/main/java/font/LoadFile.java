package font;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;


public class LoadFile {
	/**
	 * 预编译的正则表达式
	 */
	private static final Pattern PATTERN = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
	private static final Pattern PATTERN_16 = Pattern.compile("^[0-9A-F]+$");

	public static String codeString(String fileName) throws Exception {
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
		int p = (bin.read() << 8) + bin.read();
		bin.close();
		String code = null;

		switch (p) {
			case 0xefbb:
				code = "UTF-8";
				break;
			case 0xfffe:
				code = "Unicode";
				break;
			case 0xfeff:
				code = "UTF-16BE";
				break;
			default:
				code = "GBK";
		}

		return code;
	}
	/**
	 * 判断文本文件的字符集，文件开头三个字节表明编码格式。
	 * @param path
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static String charset(String path) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
			bis.mark(0); // 读者注： bis.mark(0);修改为 bis.mark(100);我用过这段代码，需要修改上面标出的地方。
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1) {
				bis.close();
				return charset; // 文件编码为 ANSI
			} else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE"; // 文件编码为 Unicode
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE"; // 文件编码为 Unicode big endian
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8"; // 文件编码为 UTF-8
				checked = true;
			}
			bis.reset();
			if (!checked) {
				while ((read = bis.read()) != -1) {
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
							// (0x80 - 0xBF),也可能在GB编码内
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) { // 也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--文件-> [" + path + "] 采用的字符集为: [" + charset + "]");
		return charset;
	}
		public static void main(String[] args) throws Exception {


		// 本地字体的路径
		String fontFileUrl = "C:\\Windows\\Fonts\\simkai.ttf";
		System.out.println(codeString(fontFileUrl));
		System.out.println(charset(fontFileUrl));

		// 生成的图片存放路径
		String dirUrl = "D:/图片路径";
		// 读取本地文件
		try (FileInputStream fis = new FileInputStream(fontFileUrl);
			 // 指定字符编码读取文件
			 InputStreamReader isr = new InputStreamReader(fis,"GBK"); // gb2312 gbk utf-8 unicode
			 BufferedReader br = new BufferedReader(isr)) {
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String str = sb.toString();
			System.out.println(str);
			String[] split = str.split("uni");//uni
			List<String> tempList = Arrays.asList(split);
			List<String> arrList = new ArrayList<>(tempList);
			arrList.remove(0);
			System.out.println(arrList.size());
			Font font = loadFont(fontFileUrl, 340);

			for (String character : arrList) {
				if (null != character && character.length() > 4) {
					character = character.substring(0, 4);
					// 字符串处理转为汉字
					character = convertToCn(character);
					String characterUrl = dirUrl + "/" + character + ".png";

					if (isChineseChar(character)) {
						try {
							createImage(character, font, new File(characterUrl), 400, 400, 0, 0);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} else {
					character = convertToCn(character);
					String characterUrl = dirUrl + "/" + character + ".png";
					if (isChineseChar(character)) {
						try {
							createImage(character, font, new File(characterUrl), 400, 400, 0, 0);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将字符串转换为汉字输出
	 *
	 * @param s unicode编码的字符串
	 * @return 转换后的汉字
	 */
	private static String convertToCn(String s) {
		// 进行unicode 编码转换
		s = strToUnicode(s);
		// 进行unicode 编码转换成汉字
		s = unicodeToCn(s);
		return s;
	}

	/**
	 * 将16进制的字符串转换为unicode编码
	 *
	 * @param str 16进制字符串
	 * @return unicode编码
	 */
	public static String strToUnicode(String str) {
		// 判断字符串是否属于16进制
		if (isHexStrValid(str)) {
			BigInteger saint = new BigInteger(str, 16);
			int strNum = saint.intValue();
			int num = 255;
			if (strNum > num) {
				str = "\\u" + str;
			} else {
				str = "\\u00" + str;
			}
			return str;
		} else {
			return "一";
		}
	}

	/**
	 * 对字符串进行判断，是否属于16进制
	 *
	 * @param str
	 * @return
	 */
	public static boolean isHexStrValid(String str) {

		return PATTERN_16.matcher(str).matches();
	}
	/**
	 * unicode编码转汉字
	 *
	 * @param str 字符串
	 * @return 转换的汉字
	 */
	public static String unicodeToCn(String str) {
		Matcher matcher = PATTERN.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	/**
	 * 读取本地字体文件供系统使用
	 *
	 * @param fontFileName 本地字体所在路径
	 * @param fontSize     字体字号大小
	 * @return font
	 */
	public static Font loadFont(String fontFileName, float fontSize) {
		try {
			return getFont(fontFileName, fontSize);
		} catch (Exception e) {
			e.printStackTrace();
			// 异常处理，如果出现异常久返回宋体类型的字体
			return new java.awt.Font("宋体", Font.PLAIN, 100);
		}
	}

	private static Font getFont(String fontFileName, float fontSize) throws FontFormatException, IOException {
		File file = new File(fontFileName);
		FileInputStream fis = new FileInputStream(file);
		Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, fis);
		Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
		fis.close();
		return dynamicFontPt;
	}
	// 根据str,font的样式以及输出文件目录

	/**
	 * 根据输入的参数在对应路径生成对应的png格式图片字体，背景为透明
	 *
	 * @param str     文字
	 * @param font    字体类型
	 * @param outFile 图片输出路径
	 * @param width   图片宽度
	 * @param height  图片高度
	 * @param x       文字的横坐标（为0代表需要程序自动计算）
	 * @param y       文字的纵坐标（为0代表需要程序自动计算）
	 * @throws Exception Exception
	 */
	public static void createImage(String str, Font font, File outFile, Integer width, Integer height, int x, int y)
			throws Exception {
		// 创建BufferedImage对象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取Graphics2D
		Graphics2D g2d = image.createGraphics();

		// ---------- 增加下面的代码使得背景透明 -----------------
		image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = image.createGraphics();
		// ---------- 背景透明代码结束 -----------------

		// 设置字体颜色
		g2d.setColor(Color.black);
		// 设置字体类型
		g2d.setFont(font);

		// 用于获得垂直居中
		FontMetrics fm = g2d.getFontMetrics(font);
		int ascent = fm.getAscent();
		int descent = fm.getDescent();
		if (x == 0) {
			x = (width - fm.stringWidth(str)) / 2;
		}
		if (y == 0) {
			y = (height - (ascent + descent)) / 2 + ascent;
		}
		// 将字符写入到图片内
		g2d.drawString(str, x, y);
		// 释放对象
		g2d.dispose();

// 保存文件
		ImageIO.write(image, "png", outFile);

	}

	/**
	 * 判断字符是否为汉字
	 *
	 * @param c 字符串
	 * @return 判断结果
	 */
	public static boolean isChineseChar(String c) {

		return c.matches("[\u4e00-\u9fa5]");

	}

}
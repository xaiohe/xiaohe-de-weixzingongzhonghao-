package com.xiaohe.util;

import java.io.InputStream;
import java.util.Scanner;

public class StringUtil {
	/**
	 * 功能：从一个流里读取它的所有内容
	 * @param is 输入流
	 * @return 返回内容
	 */
	public static String getContentFromInputStream(InputStream is) {
		Scanner s = new Scanner(is);
		StringBuffer sb = new StringBuffer();
		while (s.hasNextLine()) {
			sb.append(s.nextLine());
		}
		s.close();
		return sb.toString();
	}
}

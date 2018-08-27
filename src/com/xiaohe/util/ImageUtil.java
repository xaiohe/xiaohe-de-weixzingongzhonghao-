package com.xiaohe.util;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUtil {
	public static void downloadPic(String picUrl) {
		URL url;
		try {
			url = new URL(picUrl);
			BufferedInputStream is = new BufferedInputStream(url.openStream());
			
			byte cache[] = new byte[1024*1024];
			int len = 0;
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
			String fileName = sd.format(new Date());
			FileOutputStream os = new FileOutputStream("D:\\WeiXin\\"+fileName+".jpg");
			while((len=is.read(cache))!=-1) {
				os.write(cache,0,len);
			}
			os.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ImageUtil.downloadPic("http://mmbiz.qpic.cn/mmbiz_jpg/6FdGCtuyXsZVANAUpcfJbXPZtV96y8ssibp0dYQltNYPPd59iaWEj7nwwhHtuUuLsUUNNiaschblalqxIo0DzN9rQ/0");
	}
}

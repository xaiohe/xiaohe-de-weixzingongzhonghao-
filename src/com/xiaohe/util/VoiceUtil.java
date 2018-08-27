package com.xiaohe.util;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VoiceUtil {
	public static void downloadVoice(String accessToken,String mediaId) {
		String voiceUrl="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+accessToken+"&media_id="+mediaId;
		System.out.println(voiceUrl);
		URL url;
		try {
			url=new URL(voiceUrl);
			BufferedInputStream is=new BufferedInputStream(url.openStream());
			byte[]cache=new byte[1024*1024];
			int len=0;
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
			String fileName=sdf.format(new Date());
			FileOutputStream os=new FileOutputStream("D:\\weixin\\"+fileName+".amr");
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
}

package com.taotao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService{
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap(); 
		try {
			// 取原始文件名
			String originalFilename = uploadFile.getOriginalFilename();
			// 新文件名
			String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
			String imagepath = new DateTime().toString("yyyy/MM/dd");
			// 转存文件，上传到ftp服务器
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
					FTP_BASEPATH, imagepath, newFileName, uploadFile.getInputStream());
			
			//返回结果
			if(!result) {
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
			} 
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL+imagepath+"/"+newFileName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return resultMap;	
	}

}

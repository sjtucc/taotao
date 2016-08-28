package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {
	@Test
	public  void testFtpClient() throws SocketException, IOException {
		/*FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.254.128", 21);
		ftpClient.login("ftpuser", "111111");
		
		FileInputStream inputStream = new FileInputStream(new File("F:\\IPMAC.txt"));
		ftpClient.changeWorkingDirectory("/data");  
		//"/data/IPMAC.txt"默认是home路径，即/data，而不是/home/ftp/data,估计是配置文件中local_root=/home/ftp的原因
	
		System.out.println(ftpClient.storeFile("IPMAC.txt", inputStream));  
		
		inputStream.close();
		ftpClient.logout();*/

	}
	
	
}

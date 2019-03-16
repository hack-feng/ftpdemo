
/**
 *  @Project       : ftpdemo
  
 *  @Program Name  : com.maple.ftpdemo.util.FtpUtil.java
 *  @Class Name    : FtpUtil
 *  @Description   : 类描述
 *  @Author        : Maple
 *  @Creation Date : 2019年3月15日 上午10:47:48 
 *  @ModificationHistory  
 *  Who        When          What 
 *  --------   ----------    -----------------------------------
 *  username   2019年3月15日       TODO
 */

package com.maple.ftpdemo.util;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.maple.ftpdemo.bean.FtpBean;

/**  
 *  @Project       : ftpdemo
 *  @Program Name  : com.maple.ftpdemo.util.FtpUtil.java
 *  @Class Name    : FtpUtil
 *  @Description   : 类描述
 *  @Author        : Maple
 *  @Creation Date : 2019年3月15日 上午10:47:48 
 *  @ModificationHistory  
 *  Who        When          What 
 *  --------   ----------    -----------------------------------
 *  Maple   2019年3月15日       上传FTP服务器工具类
 */

public class FtpUtil {
	
	public static boolean uploadFile(FtpBean ftpBean) {
		
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(ftpBean.getAddress(), Integer.valueOf(ftpBean.getPort()));// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(ftpBean.getUsername(), ftpBean.getPassword());// 登录
			reply = ftp.getReplyCode();
			
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			
			String tempPath = ftpBean.getBasepath();
			String fileName = ftpBean.getFileName();
			if (!ftp.changeWorkingDirectory(ftpBean.getBasepath())) {
				//判断目录是否存在，如果目录不存在创建目录，目录存在则跳转到此目录下
				String []tempPathList = tempPath.split("/");
				for (String dir : tempPathList) {
					if(dir != null && dir != ""){
						if (!ftp.changeWorkingDirectory(dir)) {
							if (!ftp.makeDirectory(dir)) {
								return result;
							} else {
								ftp.changeWorkingDirectory(dir);
							}
						}
					}
				}
			}
			
			//保存文件方式  默认：1-覆盖；2-文件名称后面+(递增数据) 
			if(ftpBean.getSaveFileType() == 2){
				FTPFile[]file = ftp.listFiles();
				Integer i = 1;
				//采用递归，文件名重复自动加(i)
				fileName = aaa(file, i, fileName, fileName);
			}
			//设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//设置模式很重要
			ftp.enterLocalActiveMode();
			//上传文件
			result = ftp.storeFile(fileName, ftpBean.getInputStream());
			if(!result){
				return result;
			}

			ftpBean.getInputStream().close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
	
	
	//递归重命名去重复
	public static String aaa(FTPFile[] file, int i, String fileName, String nameFlag){
		boolean isOk = true;
		for (FTPFile file2 : file) {
			String flag = file2.getName();
			if(nameFlag.equals(flag)){
				String []fileNames = fileName.split("\\.");
				if(fileNames.length > 0){
					nameFlag = "";
				}
				for (int j = 0; j < fileNames.length; j++) {
					if(j == fileNames.length-1){
						nameFlag = nameFlag+"("+i+")."+fileNames[j];
					}else if(j == fileNames.length-2){
						nameFlag = nameFlag + fileNames[j];
					}else{
						nameFlag = nameFlag + fileNames[j] + ".";
					}
				}
				i += 1;
				isOk = false;
			}else{
				isOk = true;
			}
		}
		
		if(isOk == false){
			nameFlag = aaa(file, i, fileName, nameFlag);
		}
		return nameFlag;
	}
}

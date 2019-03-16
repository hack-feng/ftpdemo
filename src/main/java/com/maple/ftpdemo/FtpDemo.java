
/**
 *  @Project       : ftpdemo
  
 *  @Program Name  : com.maple.ftpdemo.FtpDemo.java
 *  @Class Name    : FtpDemo
 *  @Description   : 类描述
 *  @Author        : Maple
 *  @Creation Date : 2019年3月15日 上午10:50:31 
 *  @ModificationHistory  
 *  Who        When          What 
 *  --------   ----------    -----------------------------------
 *  username   2019年3月15日       TODO
 */

package com.maple.ftpdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import com.maple.ftpdemo.bean.FtpBean;
import com.maple.ftpdemo.util.FtpUtil;
import com.maple.ftpdemo.util.GenerateDirUtil;

/**  
 *  @Project       : ftpdemo
 *  @Program Name  : com.maple.ftpdemo.FtpDemo.java
 *  @Class Name    : FtpDemo
 *  @Description   : 类描述
 *  @Author        : Maple
 *  @Creation Date : 2019年3月15日 上午10:50:31 
 *  @ModificationHistory  
 *  Who        When          What 
 *  --------   ----------    -----------------------------------
 *  username   2019年3月15日       文件上传测试用例
 */

public class FtpDemo {
	
	public static void main(String[] args){
		
		//获取本地需要上传的文件，此处只用作测试
		File file = new File("D:\\test\\ftp.txt");
		
		if(file.isFile() && file.exists()){
			
			//设置FTP上传的基本信息，可直接初始化数据
			FtpBean ftp = new FtpBean();
			
			//自动生成路径，默认取源文件路径，为FILETYPE时，取文件类型做路径
			//如使用自己的路径，直接ftp.setBasepath赋值即可
			String basepath = GenerateDirUtil.fileDirectory(file, GenerateDirUtil.Type.FILETYPE);
			ftp.setBasepath(basepath);
			
			/**
			 * 文件名生成方式
			 * 1、自己命名			ftp.setBasepath("aaaa.txt");
			 * 2、取文件原名字		ftp.setBasepath(file.getName());
			 * 3、根据时间戳自动生成	
			 */
			SimpleDateFormat sdfms = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String date = sdfms.format(System.currentTimeMillis());
			//添加三位自动生成的数字，防止重复
			int i=(int)(Math.random()*900)+100; 
			String fileName = date + i +".txt";
			ftp.setFileName(fileName);
			
			//测试命名重复递增
			fileName = "aaa.txt";
			
			try{
				//将文件转换成一个输入流
				InputStream in = new FileInputStream(file);
				ftp.setInputStream(in);
				//传入文件名称，和文件输入流，上传至FTP服务器
				boolean isOk = FtpUtil.uploadFile(ftp);
				if(isOk){
					System.out.println("文件上传成功");
				}else{
					System.out.println("文件上传失败");
				}
				in.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("文件不存在");
		}
	}
}

package com.maple.ftpdemo.bean;

import java.io.InputStream;

/**  
 *  @Project       : ftpdemo
 *  @Program Name  : com.maple.ftpdemo.bean.FtpBean.java
 *  @Class Name    : FtpBean
 *  @Description   : 类描述
 *  @Author        : Maple
 *  @Creation Date : 2019年3月15日 下午1:26:12 
 *  @ModificationHistory  
 *  Who        When          What 
 *  --------   ----------    -----------------------------------
 *  username   2019年3月15日       FTP实体类信息，封装FTP服务器信息，上传文件基本信息
 */

public class FtpBean {
	
	//获取ip地址
	private String address;
	//端口号
	private String port;
    //用户名
	private String username;
	//密码
	private String password;
	//文件名称
	private String fileName;
	//基本路
	private String basepath;
	//文件输入流
	private InputStream inputStream;
	//保存文件方式  默认：1-覆盖；2-文件名称后面+(递增数据) 
	private Integer saveFileType;
	
	public String getAddress() {
		return address == null ? "127.0.0.1":address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPort() {
		return port == null ? "21":port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUsername() {
		return username == null ? "maple":username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password == null ?"xxxxxxx":password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFileName() {
		return fileName == null ?"未命名":fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBasepath() {
		return basepath == null ?"/local":basepath;
	}
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public Integer getSaveFileType() {
		return saveFileType != 1 ? 2:saveFileType;
	}
	public void setSaveFileType(Integer saveFileType) {
		this.saveFileType = saveFileType;
	}
}

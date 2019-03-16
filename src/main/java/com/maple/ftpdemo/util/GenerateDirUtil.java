
/**
 *  @Project       : ftpdemo
  
 *  @Program Name  : com.maple.ftpdemo.util.GenerateDirUtil.java
 *  @Class Name    : GenerateDirUtil
 *  @Description   : 类描述
 *  @Author        : Maple
 *  @Creation Date : 2019年3月15日 下午1:42:16 
 *  @ModificationHistory  
 *  Who        When          What 
 *  --------   ----------    -----------------------------------
 *  username   2019年3月15日       TODO
 */

package com.maple.ftpdemo.util;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

/**  
 *  @Project       : ftpdemo
 *  @Program Name  : com.maple.ftpdemo.util.GenerateDirUtil.java
 *  @Class Name    : GenerateDirUtil
 *  @Author        : Maple
 *  @Creation Date : 2019年3月15日 下午1:42:16 
 *  @ModificationHistory  
 *  Who        When          What 
 *  --------   ----------    -----------------------------------
 *  Maple   2019年3月15日       根据文件按照文件目录类型，自动生成FTP上传至服务器的目录
 */

public class GenerateDirUtil {
	
	public static enum Type{
		DEFAULT,
		FILETYPE
	}

	/**
	 *  <p>
	 *  type为DEFAULT或者type为null时：获取原文件所在路径，直接在FTP创建此路径保存
	 *  type为FILETYPE时：则根据文件类型创建目录
	 *  </p>
	 *  @method_Name    : fileDirectory
	 *  @author         : Maple
	 *  @creation       : 2019年3月15日 下午1:59:31 
	 *  @param file
	 *  @return
	 */
	public static String fileDirectory(File file, Type type){
		if(Type.FILETYPE.equals(type)){
			String resDir = "/";
			resDir = new MimetypesFileTypeMap().getContentType(file);
			resDir.replace('\\', '/');
			return "/"+resDir;
		}else{
			String resDir = "/";
			resDir = file.getParent();
			return resDir;
		}
	}
}

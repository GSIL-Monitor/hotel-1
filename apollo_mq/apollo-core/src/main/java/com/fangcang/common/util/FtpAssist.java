package com.fangcang.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
/**
* @Description : 上传FTP文件工具类
* @author : leping.fang@fangcang.com
* @date : 2017年3月7日 上午10:48:14
*/
public class FtpAssist {

	private static final Log log = LogFactory.getLog(FtpAssist.class);
	
	/**
	 * 判断图片时候存在
	 * @param filePath
	 * @param pictureName
	 * @param server
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public static boolean isExitPictureFile(String filePath,String pictureName,String server, String userName, String userPassword){
		boolean flag =false;
		 InputStream inputStream = null;
		FTPClient ftpClient = new FTPClient();
		try {
			 ftpClient.connect(server);
			 ftpClient.login(userName, userPassword);//FTP服务器用户名和密码
			 inputStream =  ftpClient.retrieveFileStream(filePath+"/"+pictureName);
			 if(null != inputStream){
				 flag =true;
			 }
		}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("连接ftp服务器异常!", e);
		}finally{
			if(inputStream !=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		return flag;
	}
	

	/**
	 * 上传文件到FTP服务器
	 */
	public static boolean uploadFile(StringBuffer fileContent,
			String server, String userName, String userPassword, String path,
			String fileName) {
		FTPClient ftpClient = new FTPClient();
		try {
			InputStream is = null;
			is = new ByteArrayInputStream(fileContent.toString().getBytes("utf-8"));//文件内容
			ftpClient.connect(server);//FTP服务器ip地址(不需要端口号)
			ftpClient.login(userName, userPassword);//FTP服务器用户名和密码
			ftpClient.changeWorkingDirectory(path);//文件上传到FTP服务器的目录
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
			ftpClient.storeFile(fileName, is);//文件名及编码格式
			is.close();
		} catch (Exception e) {
			log.error("Write file to FTP server Failing!",e);
			return false;
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		return true;
	}
	
	/**
	 * 上传文件到FTP服务器
	 */
	public static boolean uploadFile(InputStream is,
			String server, String userName, String userPassword, String path,
			String fileName) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server);//FTP服务器ip地址(不需要端口号)
			ftpClient.login(userName, userPassword);//FTP服务器用户名和密码
			ftpClient.changeWorkingDirectory(path);//文件上传到FTP服务器的目录
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
			ftpClient.storeFile(fileName, is);//文件名及编码格式
			is.close();
		} catch (Exception e) {
			log.error("Write file to FTP server Failing!",e);
			return false;
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		return true;
	}
	
	/**
	 * 删除ftp文件
	 */
	public static boolean deleteFile(String server, String userName, String userPassword, String path,String fileName){
		FTPClient ftpClient = new FTPClient();
		boolean issuccess=false;
		try {
			ftpClient.connect(server);//FTP服务器ip地址(不需要端口号)
			ftpClient.login(userName, userPassword);//FTP服务器用户名和密码
			ftpClient.changeWorkingDirectory(path);//文件上传到FTP服务器的目录
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
			issuccess=ftpClient.deleteFile(fileName);
		} catch (Exception e) {
			log.error("Delete file on FTP server Failing!",e);
			return false;
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		return issuccess;
	}
	
	/**
	 * 读取文件
	 */
	public static InputStream readFile(String server, String userName, String userPassword, String path,
			String fileName){
		
		FTPClient ftpClient = new FTPClient();
		InputStream inputStream = null;
		try {
			ftpClient.connect(server);//FTP服务器ip地址(不需要端口号)
			ftpClient.login(userName, userPassword);//FTP服务器用户名和密码
			ftpClient.changeWorkingDirectory(path);//文件上传到FTP服务器的目录
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			inputStream=ftpClient.retrieveFileStream(fileName);
		} catch (Exception e) {
			log.error("Read file on FTP server Failing!",e);
			return inputStream;
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					log.error(e);
					return inputStream;
				}
			}
		}
		return inputStream;
	}
}

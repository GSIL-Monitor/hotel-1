package com.travel.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.travel.common.exception.ServiceException;

import net.sf.jxls.transformer.XLSTransformer;

public class ExcelHelper {
	
	private static final Log logger = LogFactory.getLog(ExcelHelper.class);
 	
	/**
	 * 从本地模板导出Excel
	 * @param data
	 * @param templateLocation
	 * @return
	 * @throws Exception
	 */
	public static void exportFromLocal(HttpServletResponse response, Map<String, Object> data,String templateLocation) {
		XSSFWorkbook workbook;
		try {
			ServletOutputStream out = response.getOutputStream();
			workbook = new XSSFWorkbook(new FileInputStream(templateLocation));
			XLSTransformer transformer = new XLSTransformer();
			transformer.transformWorkbook(workbook, data);
			workbook.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			logger.error("获取导出模板异常",e);
			throw new ServiceException("获取导出模板异常");
		} catch (IOException e) {
			logger.error("数据写入异常",e);
			throw new ServiceException("导出Excel异常");
		}
	}
	
	/**
	 * 从远程模板导出Excel
	 * @param data
	 * @param templateUrl
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayInputStream exportFromRemote(Map<String, Object> data,String templateUrl)throws Exception {
		InputStream is=getTemplateStreamRemote(templateUrl);
		if(is==null)throw new ServiceException("找不到模板文件");
		XSSFWorkbook workbook = new XSSFWorkbook(is);
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformWorkbook(workbook, data);
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
        workbook.write(output);
        output.flush();
		byte[] byteArray = output.toByteArray();
		output.close();
		return new ByteArrayInputStream(byteArray, 0, byteArray.length);
	}
	
	/**
	 * 远程读取模板数据流
	 * @param url
	 * @return
	 */
	private static InputStream getTemplateStreamRemote(String url){
		URL remoteUrl = null;
        InputStream is =null;
        try{
        	remoteUrl = new URL(url);
        }catch(MalformedURLException e){
        	logger.error("getTemplateStreamRemote",e);
        }
        try{
            HttpURLConnection conn = (HttpURLConnection) remoteUrl.openConnection();//利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream(); //得到网络返回的输入流
        }catch(IOException e){
        	logger.error("getTemplateStreamRemote",e);
        }
        return is;
	}
	
	/**
	 * 导出图片
	 * @param workbook
	 * @param logoUrl
	 * @param signUrl
	 */
	private static void exportImage(Workbook workbook, String logoUrl, String signUrl) {
		ByteArrayOutputStream logoByteArrayOut = new ByteArrayOutputStream();
		ByteArrayOutputStream signByteArrayOut = new ByteArrayOutputStream();
		try {
			int count = workbook.getNumberOfSheets();
			if(count <= 0){
				logger.error("获取模板excel信息失败...");
				return;
			}
			Sheet sheet = null;
			for (int i = 0; i < count; i++) {
				sheet = workbook.getSheetAt(i);
				if (null == sheet) {
					continue;
				}
				Drawing drawing = sheet.createDrawingPatriarch();
				ClientAnchor logoAnchor = null;
				ClientAnchor signAnchor = null;
				String logoPictureType = "";
				String signPictureType = "";
				if (StringUtils.isNotBlank(logoUrl)) {
					logger.info("export bill logo url := " + logoUrl);
					logoPictureType = logoUrl.substring( logoUrl.lastIndexOf(".") + 1, logoUrl.length());
					BufferedImage logoBufferImg = ImageIO.read(new URL(logoUrl));
					if (null == logoBufferImg) {
						logger.error("logo read buffer is null");
					} else {
						ImageIO.write(logoBufferImg, logoPictureType, logoByteArrayOut);
						CreationHelper helper = workbook.getCreationHelper();
						logoAnchor = helper.createClientAnchor();
						logoAnchor.setDx1(0);
						logoAnchor.setDy1(0);
						logoAnchor.setDx2(255);
						logoAnchor.setDy2(255);
						logoAnchor.setCol1(0);
						logoAnchor.setRow1(0);
						logoAnchor.setCol2(3);
						logoAnchor.setRow2(4);
						logoAnchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
						//插入图片
						if (logoPictureType.equalsIgnoreCase("png")) {
							drawing.createPicture(logoAnchor, workbook.addPicture( logoByteArrayOut.toByteArray(), Workbook.PICTURE_TYPE_PNG));
						} else {
							drawing.createPicture(logoAnchor, workbook.addPicture( logoByteArrayOut.toByteArray(), Workbook.PICTURE_TYPE_JPEG));
						}
					}
				} else {
					logger.info("export bill logo := {}");
				}
				
				if (StringUtils.isNotBlank(signUrl)) {
					logger.info("export bill sign url := " + signUrl);
					signPictureType = signUrl.substring( signUrl.lastIndexOf(".") + 1, signUrl.length());
					BufferedImage signBufferImg = ImageIO.read(new URL(signUrl));
					if (null == signBufferImg) {
						logger.error("sign read buffer is null");
					} else {
						ImageIO.write(signBufferImg, signPictureType, signByteArrayOut);
						CreationHelper helper = workbook.getCreationHelper();
						signAnchor = helper.createClientAnchor();
//						signAnchor = new XSSFClientAnchor();
						signAnchor.setDx1(0);
						signAnchor.setDy1(0);
						signAnchor.setDx2(255);
						signAnchor.setDy2(255);
						signAnchor.setCol1(12);
						signAnchor.setRow1(5);
						signAnchor.setCol2(14);
						signAnchor.setRow2(10);
						signAnchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
						//插入图片
						if (signPictureType.equalsIgnoreCase("png")) {
							drawing.createPicture(signAnchor, workbook.addPicture( signByteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
						} else {
							drawing.createPicture(signAnchor, workbook.addPicture( signByteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));
						}
					}
				} else {
					logger.info("export bill sign := {}");
				}
			}
		} catch (Exception e) {
			logger.error("插入图片信息异常", e);
		} finally {
			try {
				if (null != logoByteArrayOut) {
					logoByteArrayOut.close();
				}
				if (null != signByteArrayOut) {
					signByteArrayOut.close();
				}
			} catch (IOException e) {
				logger.error("插入图片信息异常", e);
			}
		}
	}
	
}

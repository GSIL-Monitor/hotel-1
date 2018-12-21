package com.travel.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.travel.common.exception.ServiceException;

/**
 * @Description : PDF工具类
 * @author : Zhiping Sun
 * @date : 2018年3月23日下午3:03:11
 */
public class PDFUtil {

	private static Logger logger = LoggerFactory.getLogger(PDFUtil.class);

	public static byte[] generatePDF(String html,String fontPath) {
	   byte[] byteArray = null;
	   ByteArrayOutputStream output = new ByteArrayOutputStream();
	   ITextRenderer renderer = new ITextRenderer();
	   renderer.setDocumentFromString(html);
	   // 添加宋体
	   try {
		   renderer.getFontResolver().addFont(fontPath, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		   renderer.layout();
		   renderer.createPDF(output);
		   byteArray = output.toByteArray();
	   } catch (DocumentException e) {
		   logger.error("PDF设置字体异常",e);
		   throw new ServiceException("生成PDF异常");
	   } catch (IOException e) {
		   logger.error("生成PDF文件IO异常",e);
		   throw new ServiceException("生成PDF异常");
	   }
	   return byteArray;
	}

}

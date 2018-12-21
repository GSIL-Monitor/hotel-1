package com.fangcang.message.weixin.util;

import com.ning.http.client.Request;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 功能描述：构造上传数据
 * file、fileDataInputStream 为文件内容或文件输入流，二者必需且只能输入一个，以file优先
 * 要么输入file
 * 要么输入fileName + fileDataInputStream
 */
public class UploadEntityWriter implements Request.EntityWriter {
    byte[] end_data;
    File file;
    String fileName;
    InputStream fileDataInputStream;

    public UploadEntityWriter(byte[] end_data, File file, String fileName, InputStream fileDataInputStream) {
        this.end_data = end_data;
        this.file = file;
        this.fileName = fileName;
        this.fileDataInputStream = fileDataInputStream;
    }

    @Override
    public void writeEntity(OutputStream out) throws IOException {
        String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; // 定义数据分隔线
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + (file!=null ? file.getName() : fileName) + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] data = sb.toString().getBytes();
        out.write(data);
        DataInputStream fs = new DataInputStream(file!=null ? new FileInputStream(file) : fileDataInputStream);
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = fs.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
        fs.close();
        if(fileDataInputStream != null){
        	fileDataInputStream.close();
        }
        out.write(end_data);
        out.flush();
        out.close();
    }
}

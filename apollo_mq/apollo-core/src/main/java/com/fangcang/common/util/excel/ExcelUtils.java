package com.fangcang.common.util.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExcelUtils {

    public static void exportExcel(ServletOutputStream out,Class<?> pojoClass, List data) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        createSheetByAnnotation(workbook,pojoClass,data);
        workbook.write(out);
        out.flush();
        out.close();
    }

    public static void createSheetByAnnotation(HSSFWorkbook workbook,Class<?> pojoClass, List data) {
        try {
            ExcelSheet sheetAnnotation=pojoClass.getAnnotation(ExcelSheet.class);
            Sheet sheet=workbook.createSheet(sheetAnnotation.value());
            CellStyle style=workbook.createCellStyle();

            //写title中的数据
//            LinkedHashMap<String, String> title=getColumnAnnotation(pojoClass);
            Field[]  fields = pojoClass.getDeclaredFields();
            Map<String,String> titleMap = new HashMap();
            Map<String,Integer> orderNumMap=new HashedMap();
            Map<String,Field> fieldMap=new HashMap();
            Map<String,Map<String,String>> replaceMap=new HashMap();
            for(Field field:fields){
                ExcelColumn column = field.getAnnotation(ExcelColumn.class);
                if(column != null){
                    titleMap.put(field.getName(), column.name());
                    fieldMap.put(field.getName(),field);
                    orderNumMap.put(field.getName(),Integer.valueOf(column.orderNum()));
                    if (column.replace()!=null && column.replace().length>0){
                        Map<String,String> replace=new HashMap<>();
                        for (String str:column.replace()){
                            String[] a=str.split("_");
                            replace.put(a[1],a[0]);
                        }
                        replaceMap.put(field.getName(),replace);
                    }
                }
            }
            if(titleMap!= null && titleMap.size() >0){
                //设置标题
                Row row = sheet.createRow(0);
                for(Map.Entry<String, String> entry:titleMap.entrySet()){
                    String val = entry.getValue();
                    Cell cell = row.createCell(orderNumMap.get(entry.getKey()));
                    cell.setCellValue(val);
                }
                if(data != null && data.size() >0){
                    //内容从第二行开始
                    int rownum =1;
                    for(Object obj:data){
                        row = sheet.createRow(rownum);
                        //列从第一行开始
                        for(Map.Entry<String, String> entry:titleMap.entrySet()){
                            String key = entry.getKey();
                            //获取装的对象数据
                            String val = BeanUtils.getProperty(obj, key);
                            if(val == null || "".equals(val.trim())){
                                val = "";
                            }
                            Cell cell  = row.createCell(orderNumMap.get(entry.getKey()));
                            if (replaceMap.get(entry.getKey())!=null) {
                                String replaceValue = replaceMap.get(entry.getKey()).get(val);
                                cell.setCellValue(replaceValue);
                                continue;
                            }
                            Field field=fieldMap.get(key);
                            if (field.getType().equals(BigDecimal.class)){
                                if("".equals(val.trim())){
                                    val = "0";
                                }
                                val=(new BigDecimal(val)).stripTrailingZeros().toPlainString();
                            }
                            Boolean isNum = false;//data是否为数值型
                            Boolean isInteger=false;//data是否为整数
                            Boolean isPercent=false;//data是否为百分数
                            if (!field.getType().equals(String.class) && (val != null || "".equals(val))) {
                                //判断data是否为数值型
                                isNum = val.matches("^(-?\\d+)(\\.\\d+)?$");
                                //判断data是否为整数（小数部分是否为0）
                                isInteger=val.matches("^[-\\+]?[\\d]*$");
                                //判断data是否为百分数（是否包含“%”）
                                isPercent=val.contains("%");
                            }
                            //如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                            if (isNum && !isPercent) {
                                HSSFDataFormat df = workbook.createDataFormat(); // 此处设置数据格式
                                if (isInteger) {
                                    style.setDataFormat(df.getBuiltinFormat("#,#0"));//数据格式只显示整数
                                }else{
                                    style.setDataFormat(df.getBuiltinFormat("#,##0.00"));//保留两位小数点
                                }
                                // 设置单元格格式
                                cell.setCellStyle(style);
                                // 设置单元格内容为double类型
                                cell.setCellValue(Double.parseDouble(val));
                            } else {
                                cell.setCellStyle(style);
                                // 设置单元格内容为字符型
                                cell.setCellValue(val);
                            }
                        }
                        rownum ++;
                    }
                }
            }
        } catch (Exception e) {
            log.error("createSheetByAnnotation:",e);
        }
    }

    public static void createSheetByAnnotationOrderNum(Workbook workbook,Class<?> pojoClass, List data) {
        try{
            ExcelSheet sheetAnnotation=pojoClass.getAnnotation(ExcelSheet.class);
            Sheet sheet=workbook.createSheet(sheetAnnotation.value());
            Field[] fields = pojoClass.getDeclaredFields();
            Row row;
            Cell cell;// 产生单元格
            row = sheet.createRow(0);// 产生一行
            // 写入各个字段的列头名称
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(ExcelColumn.class)) {
                    ExcelColumn attr = field.getAnnotation(ExcelColumn.class);
                    int col = Integer.valueOf(attr.orderNum());// 获得列号
                    cell = row.createCell(col);// 创建列
                    cell.setCellValue(attr.name());// 写入列名
                }
            }
            int startNo = 0;
            int endNo = data.size();
            // 写入各条记录,每条记录对应excel表中的一行
            for (int i = startNo; i < endNo; i++) {
                row = sheet.createRow(i + 1 - startNo);
                Object vo = data.get(i); // 得到导出对象.
                for (int j = 0; j < fields.length; j++) {
                    Field field = fields[j];// 获得field.
                    field.setAccessible(true);// 设置实体类私有属性可访问
                    ExcelColumn attr = field.getAnnotation(ExcelColumn.class);
                    if (field.isAnnotationPresent(ExcelColumn.class)) {
                        cell = row.createCell(Integer.valueOf(attr.orderNum()));// 创建cell
                        if (field.get(vo) == null){
                            cell.setCellValue("");
                        }else if(field.getType().equals(BigDecimal.class)){
                            cell.setCellValue((new BigDecimal(String.valueOf(field.get(vo)))).stripTrailingZeros().toPlainString());
                        }else{
                            cell.setCellValue(String.valueOf(field.get(vo)));// 如果数据存在就填入,不存在填入空格.
                        }
                    }
                }
            }
        }catch (Exception e){
            log.error("createSheetByAnnotation:",e);
        }
    }

    public static void readExcel(String fileUrl, Class<?> pojoClass, List data){
        InputStream in=getTemplateStreamRemote(fileUrl);
        readExcel(in,pojoClass,data);
    }

    public static void readExcel(InputStream in, Class<?> pojoClass, List data){
        try {
            Workbook wb = WorkbookFactory.create(in);
            Sheet sheet = wb.getSheetAt(0);// 获取第一个sheet
            int rows = sheet.getPhysicalNumberOfRows();

            if (rows > 0) {// 有数据时才处理
                Map<Integer, Field> fieldsMap = getMappedFiled(pojoClass);
                for (int i = 1; i < rows; i++) {// 从第2行开始取数据,默认第一行是表头.
                    Row row = sheet.getRow(i);
                    Object entity = null;
                    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                        Cell cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        }
                        int cellType = cell.getCellType();
                        String c = "";
                        if (cellType == Cell.CELL_TYPE_NUMERIC) {
                            DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
                            c = String.valueOf(decimalFormat.format(cell.getNumericCellValue()));
                        } else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
                            c = String.valueOf(cell.getBooleanCellValue());
                        } else {
                            c = cell.getStringCellValue();
                        }
                        if (c == null || c.equals("")) {
                            continue;
                        }
                        entity = (entity == null ? pojoClass.newInstance() : entity);// 如果不存在实例则新建.
                        Field field = fieldsMap.get(j);// 从map中得到对应列的field.
                        if (field==null) {
                            continue;
                        }
                        // 取得类型,并根据对象类型设置值.
                        Class<?> fieldType = field.getType();
                        if (String.class == fieldType) {
                            field.set(entity, String.valueOf(c));
                        } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                            field.set(entity, Integer.parseInt(c));
                        } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                            field.set(entity, Long.valueOf(c));
                        } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                            field.set(entity, Float.valueOf(c));
                        } else if (BigDecimal.class == fieldType) {
                            field.set(entity, BigDecimal.valueOf(Float.valueOf(c)));
                        } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                            field.set(entity, Short.valueOf(c));
                        } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                            field.set(entity, Double.valueOf(c));
                        } else if (Character.TYPE == fieldType) {
                            if ((c != null) && (c.length() > 0)) {
                                field.set(entity, Character.valueOf(c.charAt(0)));
                            }
                        }
                    }
                    if (entity != null) {
                        data.add(entity);
                    }
                }
            }
        } catch (Exception e){
            log.error("readExcel:",e);
        }
    }

    private static Map<Integer, Field> getMappedFiled(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn attr = field.getAnnotation(ExcelColumn.class);
                int col = Integer.valueOf(attr.orderNum());// 获得列号
                field.setAccessible(true);// 设置类的私有字段属性可访问.
                fieldsMap.put(col, field);
            }
        }
        return fieldsMap;
    }

    private static LinkedHashMap<String,String> getColumnAnnotation(Class<?> clazz){
        Field[]  fields = clazz.getDeclaredFields();
        LinkedHashMap<String,String> title = new LinkedHashMap<String, String>();
        for(Field field:fields){
            ExcelColumn column = field.getAnnotation(ExcelColumn.class);
            if(column != null){
                title.put(field.getName(), column.name());
            }
        }
        return title;
    }

    /**
     * 远程读取模板数据流
     */
    private static InputStream getTemplateStreamRemote(String url){
        URL remoteUrl = null;
        InputStream is =null;
        try{
            remoteUrl = new URL(url);
        }catch(MalformedURLException e){
            log.error("getTemplateStreamRemote",e);
        }
        try{
            HttpURLConnection conn = (HttpURLConnection) remoteUrl.openConnection();//利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream(); //得到网络返回的输入流
        }catch(IOException e){
            log.error("getTemplateStreamRemote",e);
        }
        return is;
    }
}

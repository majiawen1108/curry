package com.example.curry.utils.file;

import com.example.curry.enums.Yn;
import com.example.curry.model.TemplateIinfo;
import com.example.curry.utils.DateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author jw.ma
 * @title: ExcelExportUtils
 * @description: 动态导出Excle表格
 * @date 2022/1/30 09:47
 */
public class ExcelExportUtils {

    public static String excelPortWithDataBase(List<Object> data, Map<String,String> templateInfo,String templateName,Map<String, TemplateIinfo> configMap) throws FileNotFoundException {
        HSSFWorkbook excel = createExcel(data,templateInfo,templateName,configMap);
        String fileName = getTemplateFileName(templateName);
        String templateDir = getTemplateDir() + "temp/";
        File file = new File(templateDir);
        if (!file.exists()){
            FileUtil.mkdir(templateDir);
        }
        String outputFile = templateDir + generateOutputExcelFile(fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outputFile);
        } catch (FileNotFoundException e) {
            throw e;
        }finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
        return outputFile;
    }

    private static String generateOutputExcelFile(String fileName) {
        String fileName_1 = fileName;
        String dateTimeString = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        if (fileName.toLowerCase(Locale.ROOT).endsWith(".xls")){
            fileName_1 = fileName.substring(0,fileName.length() - 4) + "_" + dateTimeString + ".xls";
        }else if (fileName.toLowerCase(Locale.ROOT).endsWith(".xlsx")){
            fileName_1 = fileName.substring(0,fileName.length() - 5) + "_" + dateTimeString + ".xlsx";
        }
        return fileName_1;
    }

    private static String getTemplateDir() {

        String templateDir = FileUtil.getWebPath("/");
        if (!templateDir.endsWith("/")){
            templateDir = templateDir + "/";
        }
        templateDir = templateDir + "templates/";
        return templateDir;
    }

    private static String getTemplateFileName(String templateName) {
        if (templateName.indexOf(".xls") == -1){
            return templateName + ".xls";
        }
        return templateName;
    }

    private static HSSFWorkbook createExcel(List<Object> data, Map<String, String> templateInfo, String templateName, Map<String, TemplateIinfo> configMap) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        //创建第一行，即表头
        HSSFRow rowTiltle = sheet.createRow(0);
        HSSFCell cell = rowTiltle.createCell(0);
        //写title
        cell.setCellValue(templateName);
        //设置title样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(style);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,templateInfo.size() - 1));
        //在sheet中创建第二行，即列标题
        HSSFRow rowColumnTitle = sheet.createRow(1);
        //写head
        int i = 0;
        for (Map.Entry<String, String> stringStringEntry : templateInfo.entrySet()) {
            rowColumnTitle.createCell(i).setCellValue(stringStringEntry.getValue());
            sheet.setColumnWidth(i,configMap.get(stringStringEntry.getKey()).getColumnWidth() * 256);
            i++;
        }
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setWrapText(true);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setVerticalAlignment(VerticalAlignment.CENTER);

        //写数据
        for (int a = 0; a < data.size(); a++) {
            Map map = (Map) data.get(a);
            HSSFRow row = sheet.createRow(a + 2);
            int j = 0;
            for (Map.Entry<String, String> stringStringEntry : templateInfo.entrySet()) {
                HSSFCell hssfCell = row.createCell(j);
                String value = formatData(map.get(stringStringEntry.getKey()),configMap.get(stringStringEntry.getKey()).getIsFilter());
                if (value.indexOf("\\n") > 0){
                    value = value.replace("\\n","\n");
                    hssfCell.setCellStyle(style1);
                }else {
                    hssfCell.setCellStyle(style2);
                }
                hssfCell.setCellValue(value);
                j++;
            }
        }
        return wb;
    }

    private static String formatData(Object o, String isFilter) {
        String value;
        if (null == o){
            return "";
        }
        if (o instanceof BigDecimal || o instanceof Double){
            value = new DecimalFormat("0.00").format(0);
            return value;
        }
        if (Yn.YES.key().equals(isFilter)){
            //如果有html需要过滤
            value = String.valueOf(o).replaceAll("<[^>]+>","");
            return value;
        }
        return String.valueOf(o);
    }
}

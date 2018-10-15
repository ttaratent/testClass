package com.sqb.poi.excel.e1;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;

/**
 * poi中不同类型数据的格式化
 */
public class PoiCellStyle {

    // HSSF
    public void setHSSFCellStyleValue(Workbook wb, HSSFCell cell) {
        // 日期格式
        cell.setCellValue(new Date(2008,5,5));
        // set data format
        HSSFCellStyle cellStyle = (HSSFCellStyle) wb.createCellStyle();
        HSSFDataFormat format = (HSSFDataFormat) wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy年m月d日"));
        cell.setCellStyle(cellStyle);
        
        // 保留两位小数格式
        cell.setCellValue(1.2);
        cellStyle = (HSSFCellStyle) wb.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cell.setCellStyle(cellStyle);
        
        // 货币格式
        cell.setCellValue(20000);
        cellStyle = (HSSFCellStyle) wb.createCellStyle();
        format = (HSSFDataFormat) wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("￥#,##0"));
        cell.setCellStyle(cellStyle);
        
        // 百分比格式
        cell.setCellValue(20);
        cellStyle = (HSSFCellStyle) wb.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
        cell.setCellStyle(cellStyle);
    }
    
    // XSSF
    public void setXSSFCellStyleValue(Workbook wb, XSSFCell cell) {
        // 设置日期格式--使用Excel内嵌的格式
        cell.setCellValue(new Date());
        XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
        XSSFDataFormat format = (XSSFDataFormat) wb.createDataFormat();
        style.setDataFormat(format.getFormat("yyyy-MM-dd"));
        
        // 其余皆可使用format进行格式化
    }
}

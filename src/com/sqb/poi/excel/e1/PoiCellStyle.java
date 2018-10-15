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
 * poi�в�ͬ�������ݵĸ�ʽ��
 */
public class PoiCellStyle {

    // HSSF
    public void setHSSFCellStyleValue(Workbook wb, HSSFCell cell) {
        // ���ڸ�ʽ
        cell.setCellValue(new Date(2008,5,5));
        // set data format
        HSSFCellStyle cellStyle = (HSSFCellStyle) wb.createCellStyle();
        HSSFDataFormat format = (HSSFDataFormat) wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy��m��d��"));
        cell.setCellStyle(cellStyle);
        
        // ������λС����ʽ
        cell.setCellValue(1.2);
        cellStyle = (HSSFCellStyle) wb.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cell.setCellStyle(cellStyle);
        
        // ���Ҹ�ʽ
        cell.setCellValue(20000);
        cellStyle = (HSSFCellStyle) wb.createCellStyle();
        format = (HSSFDataFormat) wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("��#,##0"));
        cell.setCellStyle(cellStyle);
        
        // �ٷֱȸ�ʽ
        cell.setCellValue(20);
        cellStyle = (HSSFCellStyle) wb.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
        cell.setCellStyle(cellStyle);
    }
    
    // XSSF
    public void setXSSFCellStyleValue(Workbook wb, XSSFCell cell) {
        // �������ڸ�ʽ--ʹ��Excel��Ƕ�ĸ�ʽ
        cell.setCellValue(new Date());
        XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
        XSSFDataFormat format = (XSSFDataFormat) wb.createDataFormat();
        style.setDataFormat(format.getFormat("yyyy-MM-dd"));
        
        // ����Կ�ʹ��format���и�ʽ��
    }
}

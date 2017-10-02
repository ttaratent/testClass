package com.sqb.poi.excel.ex3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
    
    public List<Map<String, Object>> readXls(String EXCEL_PATH) throws IOException {
        InputStream is = new FileInputStream(EXCEL_PATH);
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    XSSFCell code = hssfRow.getCell(0);
                    XSSFCell name = hssfRow.getCell(1);
                    XSSFCell phone = hssfRow.getCell(2);
                    map.put("code", getValue(code));
                    map.put("name", getValue(name));
                    map.put("sjh", getValue(phone));
                    list.add(map);
                }
            }
        }
        return list;
    }

    private Object getValue(XSSFCell hssfCell) {
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            hssfCell.setCellType(hssfCell.CELL_TYPE_STRING);
            return String.valueOf(hssfCell.getStringCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}

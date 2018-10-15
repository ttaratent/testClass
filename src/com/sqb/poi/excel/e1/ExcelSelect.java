package com.sqb.poi.excel.e1;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * 测试添加Excel单元格下拉校验
 */
public class ExcelSelect {

    // 参考 https://blog.csdn.net/levelmini/article/details/46646891
    public void setValidationData(Sheet sheet, int firstRow, int lastRow
            , int firstCol, int lastCol, String[] listofValues) throws IllegalArgumentException {
        if (firstRow < 0 || lastRow < 0 || firstCol < 0 || lastCol < 0) {
            throw new IllegalArgumentException("Wrong Row or Column index");
        }
        
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        if (sheet instanceof XSSFSheet) {
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet); 
            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(listofValues);
            XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
            sheet.addValidationData(validation);
        } else if (sheet instanceof HSSFSheet) {
            DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(listofValues);
            DataValidation validation = new HSSFDataValidation(addressList, dvConstraint);
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
            sheet.addValidationData(validation);
        }
    }
            
}

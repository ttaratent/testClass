package com.sqb.poi.excel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;

public class PoiExcelSheetCopy {
    /**
     * ����ԴSheet��ʽcopy��Sheet
     * 
     * @param fromsheetname
     * @param newsheetname
     * @param targetFile
     */
    public void copySheet(String fromsheetname, String newsheetname, String targetFile) {
        HSSFWorkbook wb = null;
        try {
            FileInputStream fis = new FileInputStream(targetFile);
            wb = new HSSFWorkbook(fis);
            HSSFSheet fromsheet = wb.getSheet(fromsheetname);
            if (fromsheet != null && wb.getSheet(newsheetname) == null) {
                HSSFSheet newsheet = wb.createSheet(newsheetname);
                // ���ô�ӡ����
                newsheet.setMargin(HSSFSheet.TopMargin, fromsheet.getMargin(HSSFSheet.TopMargin));// ҳ�߾ࣨ�ϣ�
                newsheet.setMargin(HSSFSheet.BottomMargin, fromsheet.getMargin(HSSFSheet.BottomMargin));// ҳ�߾ࣨ�£�
                newsheet.setMargin(HSSFSheet.LeftMargin, fromsheet.getMargin(HSSFSheet.LeftMargin));// ҳ�߾ࣨ��
                newsheet.setMargin(HSSFSheet.RightMargin, fromsheet.getMargin(HSSFSheet.RightMargin));// ҳ�߾ࣨ��

                HSSFPrintSetup ps = newsheet.getPrintSetup();
                ps.setLandscape(false); // ��ӡ����true������false������(Ĭ��)
                ps.setVResolution((short) 600);
                ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); // ֽ������

                File file = new File(targetFile);
                if (file.exists() && (file.renameTo(file))) {
                    copyRows(wb, fromsheet, newsheet, fromsheet.getFirstRowNum(), fromsheet.getLastRowNum());
                    FileOutputStream fileOut = new FileOutputStream(targetFile);
                    wb.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                } else {
                    System.out.println("�ļ������ڻ�������ʹ��,��ȷ��...");
                }
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ����Excel��
     * 
     * @param wb
     * @param fromsheet
     * @param newsheet
     * @param firstrow
     * @param lastrow
     */
    public void copyRows(HSSFWorkbook wb, HSSFSheet fromsheet, HSSFSheet newsheet, int firstrow, int lastrow) {
        if ((firstrow == -1) || (lastrow == -1) || lastrow < firstrow) {
            return;
        }
        
        // �����ϲ��ĵ�Ԫ��
        CellRangeAddress mergedRegion = null;
//        Region region = null;
        for (int i = 0; i < fromsheet.getNumMergedRegions(); i++) {
            mergedRegion = fromsheet.getMergedRegion(i);
            if((mergedRegion.getFirstRow() >= firstrow) && (mergedRegion.getLastRow() <= lastrow)) {
                newsheet.addMergedRegion(mergedRegion);
//            region = fromsheet.getMergedRegionAt(i);
//            if ((region.getRowFrom() >= firstrow) && (region.getRowTo() <= lastrow)) {
//                newsheet.addMergedRegion(region);
            }
        }

        HSSFRow fromRow = null;
        HSSFRow newRow = null;
        HSSFCell newCell = null;
        HSSFCell fromCell = null;
        // �����п�
        for (int i = firstrow; i <= lastrow; i++) {
            fromRow = fromsheet.getRow(i);
            if (fromRow != null) {
                for (int j = fromRow.getLastCellNum(); j >= fromRow.getFirstCellNum(); j--) {
                    int colnum = fromsheet.getColumnWidth(j); // fromsheet.getColumnWidth((short) j);
                    if (colnum > 100) {
                        newsheet.setColumnWidth(j, colnum);
//                        newsheet.setColumnWidth((short) j, (short) colnum);
                    }
                    if (colnum == 0) {
                        newsheet.setColumnHidden(j, true);
//                        newsheet.setColumnHidden((short) j, true);
                    } else {
                        newsheet.setColumnHidden(j, false);
//                        newsheet.setColumnHidden((short) j, false);
                    }
                }
                break;
            }
        }
        // �����в��������
        for (int i = 0; i <= lastrow; i++) {
            fromRow = fromsheet.getRow(i);
            if (fromRow == null) {
                continue;
            }
            newRow = newsheet.createRow(i - firstrow);
            newRow.setHeight(fromRow.getHeight());
            for (int j = fromRow.getFirstCellNum(); j < fromRow.getPhysicalNumberOfCells(); j++) {
                fromCell = fromRow.getCell(j); //  fromRow.getCell((short) j);
                if (fromCell == null) {
                    continue;
                }
                newCell = newRow.createCell(j); // newRow.createCell((short) j);
                newCell.setCellStyle(fromCell.getCellStyle());
                int cType = fromCell.getCellType();
                newCell.setCellType(cType);
                switch (cType) {
                case HSSFCell.CELL_TYPE_STRING:
                    newCell.setCellValue(fromCell.getRichStringCellValue());
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    newCell.setCellValue(fromCell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    newCell.setCellFormula(fromCell.getCellFormula());
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    newCell.setCellValue(fromCell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    newCell.setCellValue(fromCell.getErrorCellValue());
                    break;
                default:
                    newCell.setCellValue(fromCell.getRichStringCellValue());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        PoiExcelSheetCopy ew = new PoiExcelSheetCopy();
        ew.copySheet("template", "test2", "d:/template.xls");
    }
}

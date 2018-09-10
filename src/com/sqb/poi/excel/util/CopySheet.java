package com.sqb.poi.excel.util;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

public class CopySheet {
    /**
     * ��һ��excel�е�cellstyletable���Ƶ���һ��excel������ᱨ�����������ַ�����������ѽ����������
     * 
     * @param fromBook
     * @param toBook
     */
    // public static void copyBookCellStyle(HSSFWorkbook fromBook,HSSFWorkbook
    // toBook){
    // for(short i=0;i<fromBook.getNumCellStyles();i++){
    // HSSFCellStyle fromStyle=fromBook.getCellStyleAt(i);
    // HSSFCellStyle toStyle=toBook.getCellStyleAt(i);
    // if(toStyle==null){
    // toStyle=toBook.createCellStyle();
    // }
    // copyCellStyle(fromStyle,toStyle);
    // }
    // }
    /**
     * ����һ����Ԫ����ʽ��Ŀ�ĵ�Ԫ����ʽ
     * 
     * @param fromStyle
     * @param toStyle
     */
    public static void copyCellStyle(HSSFCellStyle fromStyle, HSSFCellStyle toStyle) {
        toStyle.setAlignment(fromStyle.getAlignment());
        // �߿�ͱ߿���ɫ
        toStyle.setBorderBottom(fromStyle.getBorderBottom());
        toStyle.setBorderLeft(fromStyle.getBorderLeft());
        toStyle.setBorderRight(fromStyle.getBorderRight());
        toStyle.setBorderTop(fromStyle.getBorderTop());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        // ������ǰ��
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPattern());
        // toStyle.setFont(fromStyle.getFont(null));
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());// ��������
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());// ��ת
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
        toStyle.setWrapText(fromStyle.getWrapText());

    }

    /**
     * Sheet����
     * 
     * @param fromSheet
     * @param toSheet
     * @param copyValueFlag
     */
    public static void copySheet(HSSFWorkbook wb, HSSFSheet fromSheet, HSSFSheet toSheet, boolean copyValueFlag) {
        // �ϲ�������
        mergerRegion(fromSheet, toSheet);
        for (Iterator<Row> rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
            HSSFRow tmpRow = (HSSFRow) rowIt.next();
            HSSFRow newRow = toSheet.createRow(tmpRow.getRowNum());
            // �и���
            copyRow(wb, tmpRow, newRow, copyValueFlag);
        }
    }

    /**
     * �и��ƹ���
     * 
     * @param fromRow
     * @param toRow
     */
    public static void copyRow(HSSFWorkbook wb, HSSFRow fromRow, HSSFRow toRow, boolean copyValueFlag) {
        for (Iterator<Cell> cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
            HSSFCell tmpCell = (HSSFCell) cellIt.next();
            HSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex()); // toRow.createCell(tmpCell.getCellNum());
            copyCell(wb, tmpCell, newCell, copyValueFlag);
        }
    }

    /**
     * ����ԭ��sheet�ĺϲ���Ԫ���´�����sheet
     * 
     * @param sheetCreat
     *            �´���sheet
     * @param sheet
     *            ԭ�е�sheet
     */
    public static void mergerRegion(HSSFSheet fromSheet, HSSFSheet toSheet) {
        int sheetMergerCount = fromSheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress mergedRegion = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(mergedRegion);
//            Region mergedRegionAt = fromSheet.getMergedRegionAt(i);
//            toSheet.addMergedRegion(mergedRegionAt);
        }
    }

    /**
     * ���Ƶ�Ԫ��
     * 
     * @param srcCell
     * @param distCell
     * @param copyValueFlag
     *            true����ͬcell������һ����
     */
    public static void copyCell(HSSFWorkbook wb, HSSFCell srcCell, HSSFCell distCell, boolean copyValueFlag) {
        HSSFCellStyle newstyle = wb.createCellStyle();
        copyCellStyle(srcCell.getCellStyle(), newstyle);
//        distCell.setEncoding(srcCell.getEncoding());
        // ��ʽ
        distCell.setCellStyle(newstyle);
        // ����
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // ��ͬ�������ʹ���
        int srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);
        if (copyValueFlag) {
            if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {
                if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                } else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {
                // nothing21
            } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());
            } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {
                distCell.setCellFormula(srcCell.getCellFormula());
            } else { // nothing29
            }
        }
    }
}

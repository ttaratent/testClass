package com.sqb.poi.excel.util;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCopy {
    /**
     * sheet ���ƣ��������ݡ����ͬһ���ļ���������ʽ����ͬ�ļ���ֻ��������<br/>
     * �����ͬbook�и��ƣ�����ʹ��workbook�е�cloneSheet()����<br/>
     *
     * <br/>�������� ��ͬbook��ֻ��������
     *
     */
    public static void copySheet(Sheet srcSheet, Sheet desSheet) {
        copySheet(srcSheet, desSheet, true, true, null);
    }

    /**
     * sheet ���ƣ����ͬһ���ļ���������ʽ����ͬ�ļ��򲻸���<br/>
     *
     * <br/>�������� ͬbook�У�ֻ������ʽ������������<br/>
     * eg: copySheet(srcSheet, desSheet, false)
     *
     * @param copyValueFlag �����Ƿ�������
     */
    public static void copySheet(Sheet srcSheet, Sheet desSheet, boolean copyValueFlag) {
        copySheet(srcSheet, desSheet, copyValueFlag, true, null);
    }

    /**
     * sheet ���ƣ��������ݡ���ʽ<br/>
     *
     * <br/>�������� ��ͬbook�临�ƣ�ͬʱ�������ݺ���ʽ<br/>
     * eg: copySheet(srcSheet, desSheet, mapping)
     *
     * @param mapping ��ͬ�ļ��临��ʱ�����Ҫ������ʽ���ش������򲻸�����ʽ
     */
    public static void copySheet(Sheet srcSheet, Sheet desSheet, StyleMapping mapping) {
        copySheet(srcSheet, desSheet, true, true, mapping);
    }

    /**
     * sheet ����,��������<br/>
     *
     *  <br/>�������� ͬbook�У�ֻ�������ݣ���������ʽ<br/>
     *  eg: copySheet(srcSheet, desSheet, false, null)
     *
     * @param srcSheet
     * @param desSheet
     * @param copyStyleFlag
     * @param mapping
     */
    public static void copySheet(Sheet srcSheet, Sheet desSheet, boolean copyStyleFlag, StyleMapping mapping) {
        copySheet(srcSheet, desSheet, true, copyStyleFlag, mapping);
    }

    /**
     * sheet ����, �������Ƿ�������ݡ���ʽ<br/>
     *
     * <br/>������ֱ��ʹ��
     *
     * @param copyValueFlag �����Ƿ�������
     * @param copyStyleFlag �����Ƿ�����ʽ
     * @param mapping       ��ͬbook�и�����ʽʱ���ش�
     */
    public static void copySheet(Sheet srcSheet, Sheet desSheet, boolean copyValueFlag, boolean copyStyleFlag, StyleMapping mapping) {
        if (srcSheet.getWorkbook() == desSheet.getWorkbook()) {
            System.out.println("ͳһworkbook�ڸ���sheet����ʹ�� workbook��cloneSheet����");
        }

        //�ϲ�������
        copyMergedRegion(srcSheet, desSheet);

        //�и���
        Iterator<Row> rowIterator = srcSheet.rowIterator();

        int areadlyColunm = 0;
        while (rowIterator.hasNext()) {
            Row srcRow = rowIterator.next();
            Row desRow = desSheet.createRow(srcRow.getRowNum());
            copyRow(srcRow, desRow, copyValueFlag, copyStyleFlag, mapping);

            //�����п�(��������)
            if (srcRow.getPhysicalNumberOfCells() > areadlyColunm) {
                for (int i = areadlyColunm; i < srcRow.getPhysicalNumberOfCells(); i++) {
                    desSheet.setColumnWidth(i, srcSheet.getColumnWidth(i));
                }
                areadlyColunm = srcRow.getPhysicalNumberOfCells();
            }
        }
    }

    /**
     * ������
     */
    public static void copyRow(Row srcRow, Row desRow) {
        copyRow(srcRow, desRow, true, true, null);
    }

    /**
     * ������
     */
    public static void copyRow(Row srcRow, Row desRow, boolean copyValueFlag) {
        copyRow(srcRow, desRow, copyValueFlag, true, null);
    }

    /**
     * ������
     */
    public static void copyRow(Row srcRow, Row desRow, StyleMapping mapping) {
        copyRow(srcRow, desRow, true, true, mapping);
    }

    /**
     * ������
     */
    public static void copyRow(Row srcRow, Row desRow, boolean copyStyleFlag, StyleMapping mapping) {
        copyRow(srcRow, desRow, true, copyStyleFlag, mapping);
    }

    /**
     * ������
     */
    public static void copyRow(Row srcRow, Row desRow,boolean copyValueFlag, boolean copyStyleFlag, StyleMapping mapping) {
        Iterator<Cell> it = srcRow.cellIterator();
        while (it.hasNext()) {
            Cell srcCell = it.next();
            Cell desCell = desRow.createCell(srcCell.getColumnIndex());
            copyCell(srcCell, desCell, copyValueFlag, copyStyleFlag, mapping);
        }
    }

    /**
     * �������򣨺ϲ���Ԫ��
     */
    public static void copyMergedRegion(Sheet srcSheet, Sheet desSheet) {
        int sheetMergerCount = srcSheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            desSheet.addMergedRegion(srcSheet.getMergedRegion(i));
            CellRangeAddress cellRangeAddress = srcSheet.getMergedRegion(i);
        }
    }

    /**
     * ���Ƶ�Ԫ�񣬸������ݣ����ͬ�ļ���������ʽ����ͬ�ļ��򲻸�����ʽ
     */
    public static void copyCell(Cell srcCell, Cell desCell) {
        copyCell(srcCell, desCell, true, true,null);
    }

    /**
     * ���Ƶ�Ԫ�� ���ͬ�ļ���������ʽ����ͬ�ļ��򲻸�����ʽ
     * @param copyValueFlag �����Ƿ�������
     */
    public static void copyCell(Cell srcCell, Cell desCell, boolean copyValueFlag) {
        copyCell(srcCell, desCell, copyValueFlag, true, null);
    }

    /**
     * ���Ƶ�Ԫ�񣬸�������,������ʽ
     * @param mapping       ��ͬ�ļ��临��ʱ�����Ҫ������ʽ���ش������򲻸�����ʽ
     */
    public static void copyCell(Cell srcCell, Cell desCell,  StyleMapping mapping) {
        copyCell(srcCell, desCell, true, true, mapping);
    }

    /**
     * ���Ƶ�Ԫ�񣬸�������
     * @param copyStyleFlag �����Ƿ�����ʽ
     * @param mapping       ��ͬ�ļ��临��ʱ�����Ҫ������ʽ���ش������򲻸�����ʽ
     */
    public static void copyCell(Cell srcCell, Cell desCell, boolean copyStyleFlag, StyleMapping mapping) {
        copyCell(srcCell, desCell, true, copyStyleFlag, mapping);
    }

    /**
     * ���Ƶ�Ԫ��
     * @param copyValueFlag �����Ƿ��Ƶ�Ԫ�������
     * @param copyStyleFlag �����Ƿ�����ʽ
     * @param mapping ��ͬ�ļ��临��ʱ�������Ҫ������ʽ���ƣ��ش������򲻸�����ʽ
     */
    public static void copyCell(Cell srcCell, Cell desCell, boolean copyValueFlag, boolean copyStyleFlag, StyleMapping mapping) {
        Workbook srcBook = srcCell.getSheet().getWorkbook();
        Workbook desBook = desCell.getSheet().getWorkbook();

        //������ʽ
        //�����ͬһ��excel�ļ��ڣ�������ʽһ����
        if (srcBook == desBook && copyStyleFlag) {
            //ͬ�ļ�����������
            desCell.setCellStyle(srcCell.getCellStyle());
        } else if (copyStyleFlag) {
           //��ͬ�ļ���ͨ��ӳ���ϵ����
            if (null != mapping) {
                short desIndex = mapping.desIndex(srcCell.getCellStyle().getIndex());
                desCell.setCellStyle(desBook.getCellStyleAt(desIndex));
            }
        }

        //��������
        if (srcCell.getCellComment() != null) {
            desCell.setCellComment(srcCell.getCellComment());
        }

        //��������
        desCell.setCellType(srcCell.getCellType());

        if (copyValueFlag) {
            switch (srcCell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    desCell.setCellValue(srcCell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    desCell.setCellValue(srcCell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    desCell.setCellFormula(srcCell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    desCell.setCellValue(srcCell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    desCell.setCellValue(srcCell.getErrorCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    //nothing to do
                    break;
                default:
                    break;
            }
        }

    }


    /**
     * ��һ��excel�е�styleTable���Ƶ���һ��excel��<br>
     * �����ͬһ��excel�ļ����Ͳ��ø���styleTable��
     * @return StyleMapping �����ļ���styleTable��ӳ���ϵ
     * @see StyleMapping
     */
    public static StyleMapping copyCellStyle(Workbook srcBook, Workbook desBook){
        if (null == srcBook || null == desBook) {
            throw new ExcelException("Դexcel �� Ŀ��excel ������");
        }
        if (srcBook.equals(desBook)) {
            throw new ExcelException("��Ҫʹ�ô˷�����ͬһ���ļ���copy style��ͬһ��excel�и���sheet����Ҫcopy Style");
        }
        if ((srcBook instanceof HSSFWorkbook && desBook instanceof XSSFWorkbook) ||
                (srcBook instanceof XSSFWorkbook && desBook instanceof HSSFWorkbook)) {
            throw new ExcelException("��֧���ڲ�ͬ�İ汾��excel�и�����ʽ��");
        }

//        logger.debug("src��style number:{}, des��style number:{}", srcBook.getNumCellStyles(), desBook.getNumCellStyles());
        short[] src2des = new short[srcBook.getNumCellStyles()];
        short[] des2src = new short[desBook.getNumCellStyles() + srcBook.getNumCellStyles()];

        for(short i=0;i<srcBook.getNumCellStyles();i++){
            //����˫��ӳ��
            CellStyle srcStyle = srcBook.getCellStyleAt(i);
            CellStyle desStyle = desBook.createCellStyle();
            src2des[srcStyle.getIndex()] = desStyle.getIndex();
            des2src[desStyle.getIndex()] = srcStyle.getIndex();

            //������ʽ
            desStyle.cloneStyleFrom(srcStyle);
        }


        return new StyleMapping(des2src, src2des);
    }

    /**
     * �������excel�ļ��е�styleTable��ӳ���ϵ���Ա����ڸ��Ʊ��ʱ����Ŀ���ļ��л�ȡ����Ӧ����ʽ
     */
    public static class StyleMapping {
        /**
         *
         */
        private short[] des2srcIndexMapping;
        /**
         *
         */
        private short[] src2desIndexMapping;

        /**
         * �����������ഴ�������Ͷ���
         */
        private StyleMapping() {
        }

        public StyleMapping(short[] des2srcIndexMapping, short[] src2desIndexMapping) {
            this.des2srcIndexMapping = des2srcIndexMapping;
            this.src2desIndexMapping = src2desIndexMapping;
        }

        public short srcIndex(short desIndex) {
            if (desIndex < 0 || desIndex >= this.des2srcIndexMapping.length) {
                throw new ExcelException("����Խ�磺Դ�ļ�styleNum=" + this.des2srcIndexMapping.length + " ����λ��=" + desIndex);
            }
            return this.des2srcIndexMapping[desIndex];
        }

        /**
         * ����Դ�ļ���style��index,��ȡĿ���ļ���style��index
         * @param srcIndex Դexcel��style��index
         * @return desIndex Ŀ��excel��style��index
         */
        public short desIndex(short srcIndex) {
            if (srcIndex < 0 || srcIndex >= this.src2desIndexMapping.length) {
                throw new ExcelException("����Խ�磺Դ�ļ�styleNum=" + this.src2desIndexMapping.length + " ����λ��=" + srcIndex);
            }

            return this.src2desIndexMapping[srcIndex];
        }
    }
}

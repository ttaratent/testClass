package com.sqb.poi.excel.sax;

/** 
 * ����: ExcelReaderUtil.java<br> 
 * ����: <br> 
 * ����: JAVA<br> 
 * ����޸�ʱ��:2016��7��5�� ����10:10:20<br> 
 *  
 * @since 2016��7��5�� 
 * @author ���� 
 */  
public class ExcelReaderUtil {  
    // excel2003��չ��  
    public static final String EXCEL03_EXTENSION = ".xls";  
    // excel2007��չ��  
    public static final String EXCEL07_EXTENSION = ".xlsx";  
  
    /** 
     * ��ȡExcel�ļ���������03Ҳ������07�汾 
     *  
     * תhttps://blog.csdn.net/zmx729618/article/details/72639037
     */  
    public static void readExcel(IExcelRowReader reader, String fileName) throws Exception {  
        // ����excel2003�ļ�  
        if (fileName.endsWith(EXCEL03_EXTENSION)) {  
            ExcelXlsReader exceXls = new ExcelXlsReader();  
            exceXls.setRowReader(reader);   
            exceXls.process(fileName);  
            // ����excel2007�ļ�  
        } else if (fileName.endsWith(EXCEL07_EXTENSION)) {  
            ExcelXlsxReader exceXlsx = new ExcelXlsxReader();  
            exceXlsx.setRowReader(reader);   
            exceXlsx.process(fileName);  
        } else {  
            throw new Exception("�ļ���ʽ����fileName����չ��ֻ����xls��xlsx��");  
        }  
    }  
  
    /** 
     * ���� 
     * @param args 
     * @throws Exception 
     */  
    public static void main(String[] args) throws Exception {  
        IExcelRowReader rowReader = new ExcelRowReader();  
        ExcelReaderUtil.readExcel(rowReader, "E://test.xls");  
    }  
}  

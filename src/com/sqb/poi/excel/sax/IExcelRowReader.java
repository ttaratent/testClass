package com.sqb.poi.excel.sax;

import java.util.List;  
  
/** 
 * תhttps://blog.csdn.net/zmx729618/article/details/72639037
 */  
public interface IExcelRowReader {  
    /** 
     * ҵ���߼�ʵ�ַ��� 
     *  
     * @param sheetIndex 
     * @param curRow 
     * @param rowlist 
     */  
    void getRows(int sheetIndex, int curRow, List<String> rowlist);  
}  
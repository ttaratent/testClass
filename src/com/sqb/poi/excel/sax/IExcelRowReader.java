package com.sqb.poi.excel.sax;

import java.util.List;  
  
/** 
 * 转https://blog.csdn.net/zmx729618/article/details/72639037
 */  
public interface IExcelRowReader {  
    /** 
     * 业务逻辑实现方法 
     *  
     * @param sheetIndex 
     * @param curRow 
     * @param rowlist 
     */  
    void getRows(int sheetIndex, int curRow, List<String> rowlist);  
}  
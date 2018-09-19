package com.sqb.poi.excel.sax;

import java.util.List;

/** 
 * תhttps://blog.csdn.net/zmx729618/article/details/72639037
 */  
public class ExcelRowReader implements IExcelRowReader {  
  
    @Override  
    public void getRows(int sheetIndex, int curRow, List<String> rowlist) {  
        System.out.print(curRow+" ");    
        for (int i = 0; i < rowlist.size(); i++) {    
            System.out.print(rowlist.get(i)==""?"*":rowlist.get(i) + " ");    
        }    
        System.out.println();    
    }  
      
}  
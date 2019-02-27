package com.sqb.poi.word.w1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

public class WordReader {
    
    public static void main(String[] args) {
        InputStream is = null;
        try {
            is = new FileInputStream("C:/Users/asus/Desktop/测试1.docx");
            XWPFDocument document = new XWPFDocument(is);
            Iterator<XWPFParagraph> iterator = document.getParagraphsIterator();
            while(iterator.hasNext()) {
                XWPFParagraph next = iterator.next();
                String paragraphText = next.getParagraphText();
                List<XWPFRun> runs = next.getRuns();
                for (XWPFRun xwpfRun : runs) {
                    // XWPFRun region 是一次输入所输入的值， 一个XWPFParagraph由一次或者多次输入
                    // 所以会有多个XWPFRun， 所以在替换模板时可能会出现由于替换原字段由多个XWPFRun中
                    // 导致替换不成功，可以考虑提前将多个XWPFRun合并
                    String text = xwpfRun.getText(0);
                    System.out.println("runs:"+text);
                }
                System.out.println("paragraphText:"+paragraphText);
            }
            List<XWPFTable> tables = document.getTables();
            for (XWPFTable xwpfTable : tables) {
                int rows = xwpfTable.getNumberOfRows();
                for(int i = 0; i < rows; i++) {
                    XWPFTableRow row = xwpfTable.getRow(i);
                    List<XWPFTableCell> tableCells = row.getTableCells();
                    for (int j = 0; j < tableCells.size(); j++) {
                        // 表格中
                        XWPFTableCell xwpfTableCell = tableCells.get(j);
                        
                        // 处理表格中的换行
                        XWPFParagraph xwpfParagraph = xwpfTableCell.getParagraphs().get(0);
                        xwpfParagraph.setAlignment(ParagraphAlignment.LEFT);
                        XWPFRun xwpfRun = xwpfParagraph.getRuns().get(0);
                        xwpfRun.addBreak(); // 换行
                        // 猜测在单元格内创建一个paragraph也可以实现换行
                        
                        CTTc ctTc = xwpfTableCell.getCTTc(); // XMLObject 读取文件
                        System.out.println("ctTc:"+ctTc.toString());
                        System.out.println(i+":"+j+"::"+xwpfTableCell.getText());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
}

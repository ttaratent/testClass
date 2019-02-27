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
            is = new FileInputStream("C:/Users/asus/Desktop/����1.docx");
            XWPFDocument document = new XWPFDocument(is);
            Iterator<XWPFParagraph> iterator = document.getParagraphsIterator();
            while(iterator.hasNext()) {
                XWPFParagraph next = iterator.next();
                String paragraphText = next.getParagraphText();
                List<XWPFRun> runs = next.getRuns();
                for (XWPFRun xwpfRun : runs) {
                    // XWPFRun region ��һ�������������ֵ�� һ��XWPFParagraph��һ�λ��߶������
                    // ���Ի��ж��XWPFRun�� �������滻ģ��ʱ���ܻ���������滻ԭ�ֶ��ɶ��XWPFRun��
                    // �����滻���ɹ������Կ�����ǰ�����XWPFRun�ϲ�
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
                        // �����
                        XWPFTableCell xwpfTableCell = tableCells.get(j);
                        
                        // �������еĻ���
                        XWPFParagraph xwpfParagraph = xwpfTableCell.getParagraphs().get(0);
                        xwpfParagraph.setAlignment(ParagraphAlignment.LEFT);
                        XWPFRun xwpfRun = xwpfParagraph.getRuns().get(0);
                        xwpfRun.addBreak(); // ����
                        // �²��ڵ�Ԫ���ڴ���һ��paragraphҲ����ʵ�ֻ���
                        
                        CTTc ctTc = xwpfTableCell.getCTTc(); // XMLObject ��ȡ�ļ�
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

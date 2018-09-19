package com.sqb.poi.excel.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class ExampleEventUserModel {
    
    private static StylesTable stylesTable;
    
    /**
     * ����һ��sheet
     * @param filename
     * @throws Exception
     */
    public void processOneSheet(String filename) throws Exception {
        
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader( pkg );
        stylesTable = r.getStylesTable(); 
        SharedStringsTable sst = r.getSharedStringsTable();
 
        XMLReader parser = fetchSheetParser(sst);
 
        // Seems to either be rId# or rSheet#
        InputStream sheet2 = r.getSheet("rId1");
        InputSource sheetSource = new InputSource(sheet2);
        parser.parse(sheetSource);
        sheet2.close();
    }
 
    /**
     * ��������sheet
     * @param filename
     * @throws Exception
     */
    public void processAllSheets(String filename) throws Exception {
        
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader( pkg );
        SharedStringsTable sst = r.getSharedStringsTable();
        
        XMLReader parser = fetchSheetParser(sst);
 
        Iterator<InputStream> sheets = r.getSheetsData();
        while(sheets.hasNext()) {
            System.out.println("Processing new sheet:\n");
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
            System.out.println("");
        }
    }
 
    /**
     * ��ȡ������
     * @param sst
     * @return
     * @throws SAXException
     */
    public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser =
            XMLReaderFactory.createXMLReader(
                    "org.apache.xerces.parsers.SAXParser"
            );
        ContentHandler handler = new SheetHandler(sst);
        parser.setContentHandler(handler);
        return parser;
    }
 
    /** 
     * �Զ������������
     * See org.xml.sax.helpers.DefaultHandler javadocs 
     */
    private static class SheetHandler extends DefaultHandler {
        
        private SharedStringsTable sst;
        private String lastContents;
        private boolean nextIsString;
        
        private List<String> rowlist = new ArrayList<String>(); 
        private int curRow = 0; 
        private int curCol = 0;
        
        //����ǰһ��Ԫ�غ͵�ǰԪ�ص�λ�ã������������пյĵ�Ԫ����������A6��A8��
        private String preRef = null, ref = null;
        //������ĵ�һ�����ĵ�Ԫ������������ȫһ��������ȱʧ�ĵ�Ԫ��
        private String maxRef = null;
        
        private CellDataType nextDataType = CellDataType.SSTINDEX; 
        private final DataFormatter formatter = new DataFormatter(); 
        private short formatIndex; 
        private String formatString; 
        
        //��һ��enum��ʾ��Ԫ����ܵ���������
        enum CellDataType{ 
            BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL 
        }
        
        private SheetHandler(SharedStringsTable sst) {
            this.sst = sst;
        }
        
        /**
         * ����һ��element�Ŀ�ʼʱ�����¼�
         */
        public void startElement(String uri, String localName, String name,
                Attributes attributes) throws SAXException {
            
            // c => cell
            if(name.equals("c")) {
                //ǰһ����Ԫ���λ��
                if(preRef == null){
                    preRef = attributes.getValue("r");
                }else{
                    preRef = ref;
                }
                //��ǰ��Ԫ���λ��
                ref = attributes.getValue("r");
                
                this.setNextDataType(attributes); 
                
                // Figure out if the value is an index in the SST
                String cellType = attributes.getValue("t");
                if(cellType != null && cellType.equals("s")) {
                    nextIsString = true;
                } else {
                    nextIsString = false;
                }
                
            }
            // Clear contents cache
            lastContents = "";
        }
        
        /**
         * ����element����������������
         * @param attributes
         */
        public void setNextDataType(Attributes attributes){ 
 
            nextDataType = CellDataType.NUMBER; 
            formatIndex = -1; 
            formatString = null; 
            String cellType = attributes.getValue("t"); 
            String cellStyleStr = attributes.getValue("s"); 
            if ("b".equals(cellType)){ 
                nextDataType = CellDataType.BOOL;
            }else if ("e".equals(cellType)){ 
                nextDataType = CellDataType.ERROR; 
            }else if ("inlineStr".equals(cellType)){ 
                nextDataType = CellDataType.INLINESTR; 
            }else if ("s".equals(cellType)){ 
                nextDataType = CellDataType.SSTINDEX; 
            }else if ("str".equals(cellType)){ 
                nextDataType = CellDataType.FORMULA; 
            }
            if (cellStyleStr != null){ 
                int styleIndex = Integer.parseInt(cellStyleStr); 
                XSSFCellStyle style = stylesTable.getStyleAt(styleIndex); 
                formatIndex = style.getDataFormat(); 
                formatString = style.getDataFormatString(); 
                if ("m/d/yy" == formatString){ 
                    nextDataType = CellDataType.DATE; 
                    //full format is "yyyy-MM-dd hh:mm:ss.SSS";
                    formatString = "yyyy-MM-dd";
                } 
                if (formatString == null){ 
                    nextDataType = CellDataType.NULL; 
                    formatString = BuiltinFormats.getBuiltinFormat(formatIndex); 
                } 
            } 
        }
        
        /**
         * ����һ��elementԪ�ؽ���ʱ�����¼�
         */
        public void endElement(String uri, String localName, String name)
                throws SAXException {
            // Process the last contents as required.
            // Do now, as characters() may be called more than once
            if(nextIsString) {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                nextIsString = false;
            }
 
            // v => contents of a cell
            // Output after we've seen the string contents
            if (name.equals("v")) { 
                String value = this.getDataValue(lastContents.trim(), ""); 
                //��ȫ��Ԫ��֮��Ŀյ�Ԫ��
                if(!ref.equals(preRef)){
                    int len = countNullCell(ref, preRef);
                    for(int i=0;i<len;i++){
                        rowlist.add(curCol, "");
                        curCol++;
                    }
                }
                rowlist.add(curCol, value);
                curCol++; 
            }else { 
                //�����ǩ����Ϊ row����˵���ѵ���β������ optRows() ���� 
                if (name.equals("row")) {
                    String value = "";
                    //Ĭ�ϵ�һ��Ϊ��ͷ���Ը��е�Ԫ����ĿΪ�����Ŀ
                    if(curRow == 0){
                        maxRef = ref;
                    }
                    //��ȫһ��β������ȱʧ�ĵ�Ԫ��
                    if(maxRef != null){
                        int len = countNullCell(maxRef, ref);
                        for(int i=0;i<=len;i++){
                            rowlist.add(curCol, "");
                            curCol++;
                        }
                    }
                    //ƴ��һ�е�����
                    for(int i=0;i<rowlist.size();i++){
                        if(rowlist.get(i).contains(",")){
                            value += "\""+rowlist.get(i)+"\",";
                        }else{
                            value += rowlist.get(i)+",";
                        }
                    }
                    //�ӻ��з�
                    value += "\n";
                    try {
                        writer.write(value);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    curRow++;
                    //һ�е�ĩβ����һЩ����
                    rowlist.clear(); 
                    curCol = 0; 
                    preRef = null;
                    ref = null;
                } 
            } 
        }
        
        /**
         * �����������ͻ�ȡ����
         * @param value
         * @param thisStr
         * @return
         */
        public String getDataValue(String value, String thisStr) 
 
        { 
            switch (nextDataType) 
            { 
                //�⼸����˳������㽻���������˺ܿ��ܻᵼ�����ݴ��� 
                case BOOL: 
                char first = value.charAt(0); 
                thisStr = first == '0' ? "FALSE" : "TRUE"; 
                break; 
                case ERROR: 
                thisStr = "\"ERROR:" + value.toString() + '"'; 
                break; 
                case FORMULA: 
                thisStr = '"' + value.toString() + '"'; 
                break; 
                case INLINESTR: 
                XSSFRichTextString rtsi = new XSSFRichTextString(value.toString()); 
                thisStr = rtsi.toString(); 
                rtsi = null; 
                break; 
                case SSTINDEX: 
                String sstIndex = value.toString(); 
                thisStr = value.toString(); 
                break; 
                case NUMBER: 
                if (formatString != null){ 
                    thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim(); 
                }else{
                    thisStr = value; 
                } 
                thisStr = thisStr.replace("_", "").trim(); 
                break; 
                case DATE: 
                    try{
                        thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString); 
                    }catch(NumberFormatException ex){
                        thisStr = value.toString();
                    }
                thisStr = thisStr.replace(" ", "");
                break; 
                default: 
                thisStr = ""; 
                break; 
            } 
            return thisStr; 
        } 
 
        /**
         * ��ȡelement���ı�����
         */
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            lastContents += new String(ch, start, length);
        }
        
        /**
         * ����������Ԫ��֮��ĵ�Ԫ����Ŀ(ͬһ��)
         * @param ref
         * @param preRef
         * @return
         */
        public int countNullCell(String ref, String preRef){
            //excel2007���������1048576�����������16384�����һ��������XFD
            String xfd = ref.replaceAll("\\d+", "");
            String xfd_1 = preRef.replaceAll("\\d+", "");
            
            xfd = fillChar(xfd, 3, '@', true);
            xfd_1 = fillChar(xfd_1, 3, '@', true);
            
            char[] letter = xfd.toCharArray();
            char[] letter_1 = xfd_1.toCharArray();
            int res = (letter[0]-letter_1[0])*26*26 + (letter[1]-letter_1[1])*26 + (letter[2]-letter_1[2]);
            return res-1;
        }
        
        /**
         * �ַ��������
         * @param str
         * @param len
         * @param let
         * @param isPre
         * @return
         */
        String fillChar(String str, int len, char let, boolean isPre){
            int len_1 = str.length();
            if(len_1 <len){
                if(isPre){
                    for(int i=0;i<(len-len_1);i++){
                        str = let+str;
                    }
                }else{
                    for(int i=0;i<(len-len_1);i++){
                        str = str+let;
                    }
                }
            }
            return str;
        }
    }
    
    static BufferedWriter writer = null;
 
    public static void main(String[] args) throws Exception {
        ExampleEventUserModel example = new ExampleEventUserModel();
        String str = "Book1";
        String filename = "D:\\"+str+".xlsx";
        System.out.println("-- ����ʼ --");
        long time_1 = System.currentTimeMillis();
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\"+str+".csv")));
            example.processOneSheet(filename);
        }finally{
            writer.close();
        }
        long time_2 = System.currentTimeMillis();
        System.out.println("-- ������� --");
        System.out.println("-- ��ʱ --"+(time_2 - time_1)+"ms");
    }
 
 
}
package com.sqb.poi.excel.sax;

import java.io.IOException;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
import org.apache.commons.lang.StringUtils;  
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;  
import org.apache.poi.openxml4j.opc.OPCPackage;  
import org.apache.poi.ss.usermodel.BuiltinFormats;  
import org.apache.poi.ss.usermodel.DataFormatter;  
import org.apache.poi.xssf.eventusermodel.XSSFReader;  
import org.apache.poi.xssf.model.SharedStringsTable;  
import org.apache.poi.xssf.model.StylesTable;  
import org.apache.poi.xssf.usermodel.XSSFCellStyle;  
import org.apache.poi.xssf.usermodel.XSSFRichTextString;  
import org.xml.sax.Attributes;  
import org.xml.sax.InputSource;  
import org.xml.sax.SAXException;  
import org.xml.sax.XMLReader;  
import org.xml.sax.helpers.DefaultHandler;  
import org.xml.sax.helpers.XMLReaderFactory;  
  
/** 
 * תhttps://blog.csdn.net/zmx729618/article/details/72639037
 */  
public class ExcelXlsxReader extends DefaultHandler {  
  
    private IExcelRowReader rowReader;  
  
    public void setRowReader(IExcelRowReader rowReader) {  
        this.rowReader = rowReader;  
    }  
  
    /** 
     * �����ַ����� 
     */  
    private SharedStringsTable sst;  
  
    /** 
     * ��һ�ε����� 
     */  
    private String lastContents;  
  
    /** 
     * �ַ�����ʶ 
     */  
    private boolean nextIsString;  
  
    /** 
     * ���������� 
     */  
    private int sheetIndex = -1;  
  
    /** 
     * �м��� 
     */  
    private List<String> rowlist = new ArrayList<String>();  
  
    /** 
     * ��ǰ�� 
     */  
    private int curRow = 0;  
  
    /** 
     * ��ǰ�� 
     */  
    private int curCol = 0;  
  
    /** 
     * TԪ�ر�ʶ 
     */  
    private boolean isTElement;  
  
    /** 
     * �쳣��Ϣ�����Ϊ�����ʾû���쳣 
     */  
    private String exceptionMessage;  
  
    /** 
     * ��Ԫ���������ͣ�Ĭ��Ϊ�ַ������� 
     */  
    private CellDataType nextDataType = CellDataType.SSTINDEX;  
  
    private final DataFormatter formatter = new DataFormatter();  
  
    private short formatIndex;  
  
    private String formatString;  
  
    // ����ǰһ��Ԫ�غ͵�ǰԪ�ص�λ�ã������������пյĵ�Ԫ����������A6��A8��  
    private String preRef = null, ref = null;  
  
    // ������ĵ�һ�����ĵ�Ԫ������������ȫһ��������ȱʧ�ĵ�Ԫ��  
    private String maxRef = null;  
  
    /** 
     * ��Ԫ�� 
     */  
    private StylesTable stylesTable;  
  
    /** 
     * ���������������еĵ��ӱ�� 
     *  
     * @param filename 
     * @throws IOException 
     * @throws OpenXML4JException 
     * @throws SAXException 
     * @throws Exception 
     */  
    public void process(String filename) throws IOException, OpenXML4JException, SAXException {  
        OPCPackage pkg = OPCPackage.open(filename);  
        XSSFReader xssfReader = new XSSFReader(pkg);  
        stylesTable = xssfReader.getStylesTable();  
        SharedStringsTable sst = xssfReader.getSharedStringsTable();  
        XMLReader parser = this.fetchSheetParser(sst);  
        Iterator<InputStream> sheets = xssfReader.getSheetsData();  
        while (sheets.hasNext()) {  
            curRow = 0;  
            sheetIndex++;  
            InputStream sheet = sheets.next();  
            InputSource sheetSource = new InputSource(sheet);  
            parser.parse(sheetSource);  
            sheet.close();  
        }  
    }  
  
    public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {  
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");  
        this.sst = sst;  
        parser.setContentHandler(this);  
        return parser;  
    }  
  
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {  
        // c => ��Ԫ��  
        if ("c".equals(name)) {  
            // ǰһ����Ԫ���λ��  
            if (preRef == null) {  
                preRef = attributes.getValue("r");  
            } else {  
                preRef = ref;  
            }  
            // ��ǰ��Ԫ���λ��  
            ref = attributes.getValue("r");  
            // �趨��Ԫ������  
            this.setNextDataType(attributes);  
            // Figure out if the value is an index in the SST  
            String cellType = attributes.getValue("t");  
            if (cellType != null && cellType.equals("s")) {  
                nextIsString = true;  
            } else {  
                nextIsString = false;  
            }  
        }  
  
        // ��Ԫ��Ϊtʱ  
        if ("t".equals(name)) {  
            isTElement = true;  
        } else {  
            isTElement = false;  
        }  
  
        // �ÿ�  
        lastContents = "";  
    }  
  
    /** 
     * ��Ԫ���е����ݿ��ܵ��������� 
     */  
    enum CellDataType {  
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL  
    }  
  
    /** 
     * ������������ 
     *  
     * @param attributes 
     */  
    public void setNextDataType(Attributes attributes) {  
        nextDataType = CellDataType.NUMBER;  
        formatIndex = -1;  
        formatString = null;  
        String cellType = attributes.getValue("t");  
        String cellStyleStr = attributes.getValue("s");  
        String columData = attributes.getValue("r");  
  
        if ("b".equals(cellType)) {  
            nextDataType = CellDataType.BOOL;  
        } else if ("e".equals(cellType)) {  
            nextDataType = CellDataType.ERROR;  
        } else if ("inlineStr".equals(cellType)) {  
            nextDataType = CellDataType.INLINESTR;  
        } else if ("s".equals(cellType)) {  
            nextDataType = CellDataType.SSTINDEX;  
        } else if ("str".equals(cellType)) {  
            nextDataType = CellDataType.FORMULA;  
        }  
  
        if (cellStyleStr != null) {  
            int styleIndex = Integer.parseInt(cellStyleStr);  
            XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);  
            formatIndex = style.getDataFormat();  
            formatString = style.getDataFormatString();  
  
            if ("m/d/yy" == formatString) {  
                nextDataType = CellDataType.DATE;  
                formatString = "yyyy-MM-dd hh:mm:ss.SSS";  
            }  
  
            if (formatString == null) {  
                nextDataType = CellDataType.NULL;  
                formatString = BuiltinFormats.getBuiltinFormat(formatIndex);  
            }  
        }  
    }  
  
    /** 
     * �Խ������������ݽ������ʹ��� 
     *  
     * @param value 
     *            ��Ԫ���ֵ����ʱ����һ�����֣� 
     * @param thisStr 
     *            һ�����ַ��� 
     * @return 
     */  
    @SuppressWarnings("deprecation")  
    public String getDataValue(String value, String thisStr) {  
        switch (nextDataType) {  
        // �⼸����˳������㽻���������˺ܿ��ܻᵼ�����ݴ���  
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
            try {  
                int idx = Integer.parseInt(sstIndex);  
                XSSFRichTextString rtss = new XSSFRichTextString(sst.getEntryAt(idx));  
                thisStr = rtss.toString();  
                rtss = null;  
            } catch (NumberFormatException ex) {  
                thisStr = value.toString();  
            }  
            break;  
        case NUMBER:  
            if (formatString != null) {  
                thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim();  
            } else {  
                thisStr = value;  
            }  
  
            thisStr = thisStr.replace("_", "").trim();  
            break;  
        case DATE:  
            thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString);  
  
            // �������ַ��������⴦��  
            thisStr = thisStr.replace(" ", "T");  
            break;  
        default:  
            thisStr = " ";  
  
            break;  
        }  
  
        return thisStr;  
    }  
  
    @Override  
    public void endElement(String uri, String localName, String name) throws SAXException {  
        // ����SST������ֵ�ĵ���Ԫ�������Ҫ�洢���ַ���  
        // ��ʱcharacters()�������ܻᱻ���ö��  
        if (nextIsString  && StringUtils.isNotEmpty(lastContents) && StringUtils.isNumeric(lastContents)) {  
            int idx = Integer.parseInt(lastContents);  
            lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();  
        }  
  
        // tԪ��Ҳ�����ַ���  
        if (isTElement) {  
            // ����Ԫ�����ݼ���rowlist�У�����֮ǰ��ȥ���ַ���ǰ��Ŀհ׷�  
            String value = lastContents.trim();  
            rowlist.add(curCol, value);  
            curCol++;  
            isTElement = false;  
        } else if ("v".equals(name)) {  
            // v => ��Ԫ���ֵ�������Ԫ�����ַ�����v��ǩ��ֵΪ���ַ�����SST�е�����  
            String value = this.getDataValue(lastContents.trim(), "");  
            // ��ȫ��Ԫ��֮��Ŀյ�Ԫ��  
            if (!ref.equals(preRef)) {  
                int len = countNullCell(ref, preRef);  
                for (int i = 0; i < len; i++) {  
                    rowlist.add(curCol, "");  
                    curCol++;  
                }  
            }  
            rowlist.add(curCol, value);  
            curCol++;  
        } else {  
            // �����ǩ����Ϊ row ����˵���ѵ���β������ optRows() ����  
            if (name.equals("row")) {  
                // Ĭ�ϵ�һ��Ϊ��ͷ���Ը��е�Ԫ����ĿΪ�����Ŀ  
                if (curRow == 0) {  
                    maxRef = ref;  
                }  
                // ��ȫһ��β������ȱʧ�ĵ�Ԫ��  
                if (maxRef != null) {  
                    int len = countNullCell(maxRef, ref);  
                    for (int i = 0; i <= len; i++) {  
                        rowlist.add(curCol, "");  
                        curCol++;  
                    }  
                }  
                rowReader.getRows(sheetIndex, curRow, rowlist);  
  
                rowlist.clear();  
                curRow++;  
                curCol = 0;  
                preRef = null;  
                ref = null;  
            }  
        }  
    }  
  
    /** 
     * ����������Ԫ��֮��ĵ�Ԫ����Ŀ(ͬһ��) 
     *  
     * @param ref 
     * @param preRef 
     * @return 
     */  
    public int countNullCell(String ref, String preRef) {  
        // excel2007���������1048576�����������16384�����һ��������XFD  
        String xfd = ref.replaceAll("\\d+", "");  
        String xfd_1 = preRef.replaceAll("\\d+", "");  
  
        xfd = fillChar(xfd, 3, '@', true);  
        xfd_1 = fillChar(xfd_1, 3, '@', true);  
  
        char[] letter = xfd.toCharArray();  
        char[] letter_1 = xfd_1.toCharArray();  
        int res = (letter[0] - letter_1[0]) * 26 * 26 + (letter[1] - letter_1[1]) * 26 + (letter[2] - letter_1[2]);  
        return res - 1;  
    }  
  
    /** 
     * �ַ�������� 
     *  
     * @param str 
     * @param len 
     * @param let 
     * @param isPre 
     * @return 
     */  
    String fillChar(String str, int len, char let, boolean isPre) {  
        int len_1 = str.length();  
        if (len_1 < len) {  
            if (isPre) {  
                for (int i = 0; i < (len - len_1); i++) {  
                    str = let + str;  
                }  
            } else {  
                for (int i = 0; i < (len - len_1); i++) {  
                    str = str + let;  
                }  
            }  
        }  
        return str;  
    }  
  
    @Override  
    public void characters(char[] ch, int start, int length) throws SAXException {  
        // �õ���Ԫ�����ݵ�ֵ  
        lastContents += new String(ch, start, length);  
    }  
  
    /** 
     * @return the exceptionMessage 
     */  
    public String getExceptionMessage() {  
        return exceptionMessage;  
    }  
  
    public static void main(String[] args) {  
        IExcelRowReader rowReader = new ExcelRowReader();  
        try {  
            // ExcelReaderUtil.readExcel(rowReader,  
            // "E://2016-07-04-011940a.xls");  
            System.out.println("**********************************************");  
            ExcelReaderUtil.readExcel(rowReader, "E://test.xlsx");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  
package com.sqb.poi.excel.e1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    // private static final Logger logger = Logger.getLogger(ExcelUtil.class);

    /**
     * @Title: createWorkbook
     * @Description: �ж�excel�ļ���׺�������ɲ�ͬ��workbook
     * @param is
     * @param excelFileName
     * @return
     * @throws IOException
     */
    public Workbook createWorkbook(InputStream is, String excelFileName) throws IOException {
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);
        } else if (excelFileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    /**
     * @Titel: getSheet
     * @Description: ����sheet�����Ż�ȡ��Ӧ��sheet
     * @param workbook
     * @param sheetIndex
     * @return
     */
    public Sheet getSheet(Workbook workbook, int sheetIndex) {
        return workbook.getSheetAt(0);
    }

    /**
     * @Title: importDataFromExcel
     * @Description: ��sheet�е����ݱ��浽list��
     *               1�����ô˷���ʱ��vo�����Ը��������excel�ļ�ÿ�����ݵ�������ͬ��һһ��Ӧ��vo���������Զ�ΪString
     *               2����action���ô˷���ʱ�������� private File excelFile; �ϴ����ļ� private
     *               String excelFileName; ԭʼ�ļ����ļ��� 3��ҳ���file�ؼ�name���Ӧ��File���ļ���
     * @param vo
     *            JavaBean
     * @param is
     *            ������
     * @param excelFileName
     * @return
     * @throws IOException
     */
    public List<Object> importDataFromExcel(Object vo, InputStream is, String excelFileName) throws IOException {
        List<Object> list = new ArrayList<Object>();
        try {
            // ����������
            Workbook workbook = this.createWorkbook(is, excelFileName);
            // ����������sheet
            Sheet sheet = this.getSheet(workbook, 0);
            // ��ȡsheet�����ݵ�����
            int rows = sheet.getPhysicalNumberOfRows();
            // ��ȡ��ͷ��Ԫ�����
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            // ���÷��䣬��JavaBean�����Խ��и�ֵ
            Field[] fields = vo.getClass().getDeclaredFields();
            for (int i = 1; i < rows; i++) { // ��һ��Ϊ���������ӵڶ��п�ʼȡ����
                Row row = sheet.getRow(i);
                int index = 0;
                while (index < cells) {
                    Cell cell = row.getCell(index);
                    if (null == cell) {
                        cell = row.createCell(index);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String value = null == cell.getStringCellValue() ? "" : cell.getStringCellValue();
                    Field field = fields[index];
                    String fieldName = field.getName();
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method setMethod = vo.getClass().getMethod(methodName, new Class[] { String.class });
                    setMethod.invoke(vo, new Object[] { value });
                    index++;
                }
                if (isHasValues(vo)) {// �ж϶��������Ƿ���ֵ
                    list.add(vo);
                    vo = vo.getClass().getConstructor(new Class[] {}).newInstance(new Object[] {});// ���´���һ��vo����
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();// �ر���
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    /**
     * @Title: isHasValues
     * @Description: �ж�һ���������������Ƿ���ֵ�����һ��������ֵ���ֿգ����򷵻�true
     * @param object
     * @return
     */
    private boolean isHasValues(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);
                if (null != obj || "".equals(obj)) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public void exportDataToExcel(List<Object> list, String[] headers, String title, OutputStream os) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        // ����һ�����
        HSSFSheet sheet = workbook.createSheet(title);
        // ���ñ��Ĭ���п�15���ֽ�
        sheet.setDefaultColumnWidth(15);
        // ����һ����ʽ
        HSSFCellStyle style = this.getCellStyle(workbook);
        // ����һ������
        HSSFFont font = this.getFont(workbook);
        // ������Ӧ�õ���ǰ��ʽ
        style.setFont(font);

        // ���ɱ�����
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 300);
        HSSFCell cell = null;

        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // �����ݷ���sheet��
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            Object t = list.get(i);
            // ���÷��䣬����JavaBean���Ե��Ⱥ�˳�򣬶�̬����get�����õ����Ե�ֵ
            Field[] fields = t.getClass().getDeclaredFields();
            try {
                for (int j = 0; j < fields.length; j++) {
                    cell = row.createCell(j);
                    Field field = fields[j];
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method getMethod = t.getClass().getMethod(methodName, new Class[] {});

                    Object value = getMethod.invoke(t, new Object[] {});

                    if (null == value)
                        value = "";
                    cell.setCellValue(value.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Title: getFont
     * @Description: ����������ʽ
     * @param workbook
     * @return
     */
    private HSSFFont getFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        return font;
    }

    /**
     * @Title: getCellStyle
     * @Description: ��ȡ��Ԫ���ʽ
     * @param workbook
     * @return
     */
    public HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        return style;
    }

    // public boolean isIE(HttpServletRequest request) {
    // return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0
    // ? true : false;
    // }
}

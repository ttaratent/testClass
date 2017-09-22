package com.sqb.poi.excel.e1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest {

    public static void main(String[] args) throws IOException {
//        export();
        importFromExcel();
    }

    private static void importFromExcel() throws FileNotFoundException, IOException {
        ExcelUtil excelUtil = new ExcelUtil();
        File file = new File("D:\\xml.xls");
        ExcelVO vo = new ExcelVO();
        FileInputStream is = new FileInputStream(file);
        List<Object> list = excelUtil.importDataFromExcel(vo, is, "xml.xls");
        for (Object object : list) {
            System.out.println(object);
        }
    }

    private static void export() throws FileNotFoundException, IOException {
        ExcelUtil excelUtil = new ExcelUtil();
        List<Object> list = new ArrayList<Object>();
        String[] headers = {"id", "name"};
        String title = "ÄãºÃ";
        for (int i = 0; i < 5; i++) {
            ExcelVO vo = new ExcelVO(i+"",i+"!!");
            list.add(vo);
//            System.out.println(vo);
        }
        File file = new File("D:\\xml.xls");
        FileOutputStream os = new FileOutputStream(file);
        excelUtil.exportDataToExcel(list, headers, title, os);
        os.close();
    }

}

package com.sqb.filereader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class PropertyTest {
    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream in = null;
        FileOutputStream oFile = null;
        try {
            File file0 = new File("src\\com\\sqb\\filereader\\a.properties");
            //读取属性文件a.properties
            in = new BufferedInputStream(new FileInputStream(file0));
            //加载属性列表
            prop.load(in);
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while(it.hasNext()) {
                String key = it.next();
                System.out.println(key + ":" + prop.getProperty(key));
            }
            
            File file1 = new File("src\\com\\sqb\\filereader\\b.properties");
            //保存属性到b.properties文件
            //true 表示追加打开
            oFile = new FileOutputStream(file1, true);
            prop.setProperty("phone", "10086");
            prop.store(oFile, "The New properties file");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (oFile != null) {
                try {
                    oFile.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

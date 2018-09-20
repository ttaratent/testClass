package com.sqb.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SwingJTextAreaAppend extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private JTextArea area = new JTextArea(20,40);
    private JScrollPane pane = new JScrollPane(area);
    private JPanel panel = new JPanel();
    private JButton button = new JButton("开始");
    private ExecutorService service = null;
    
    private SwingJTextAreaAppend() {
        area.setEditable(false);
        pane.setAutoscrolls(true);
        panel.add(pane);
        getContentPane().add(panel);
        
        service = Executors.newCachedThreadPool(new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "output");
            }
            
        }); 
        
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText("");
                button.setEnabled(false);
                // 通过创建线程进行JTextArea的输出，防止append被按钮阻塞
                service.submit(new Runnable() {

                    @Override
                    public void run() {
                        area.append("111");
                    }
                    
                });
            }
            
        });
    }
}

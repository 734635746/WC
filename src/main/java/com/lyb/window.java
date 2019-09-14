package com.lyb;

import com.lyb.Factory.FileProcessorFactory;
import com.lyb.bean.result;
import com.lyb.processor.FileProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * @author liuyoubin
 * @date 2019/9/14 - 16:50
 */
public class window {
    private JLabel Tlitle;
    private JPanel rootJPanel;
    private JButton button1;
    private JTextArea textArea1;
    private JLabel lable2;


    public window() {
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showOpenDialog(button1);
                //获取绝对路径
                String filepath = jFileChooser.getSelectedFile().getAbsolutePath();
                //要执行的操作
                String[] operates = new String[]{"-l","-c","-w","-a"};
                //要输出到界面的内容
                StringBuffer printfStr = new StringBuffer(filepath+"\r\n");
                try {

                    for (String operate : operates) {
                        //获取对应的操作处理类
                        FileProcessor processor = FileProcessorFactory.getFileProcessorOf(operate);
                        result result = processor.disposeFileOf(filepath);
                        printfStr.append(result.printfResult());
                    }

                    textArea1.setText(printfStr.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("统计程序！");
        frame.setLayout(null);
        frame.setSize(650, 500);
        frame.setBackground(Color.darkGray);
        frame.setLocation(600, 200);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setContentPane(new window().rootJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

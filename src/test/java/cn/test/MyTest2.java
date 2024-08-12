package cn.test;

import cn.shuzilm.tools.cdid.CDID;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyTest2 {
    public static void readFileByLine(String filePath) {
        // 使用try-with-resources语句自动关闭资源
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 循环读取每一行，直到文件结束
            while ((line = reader.readLine()) != null) {
                // 处理每一行
                System.out.println(CDID.verifyDid(line));
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // 文件路径，请根据实际情况替换
        String filePath = "cdid.txt";
        readFileByLine(filePath);
    }
}

package org.chapter5;

import java.io.*;
import java.util.Objects;

public class DataInOut {

    public void excute() {
        try {
            dataInOutStreamTest();
            dataReadWriteTest();
        }catch (IOException e){
            System.out.println("エラー発生:" + e.getMessage());
        }
    }
    private void dataInOutStreamTest() throws IOException {
        System.out.println("=== dataInOutStreamTest ===");
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        try {
            byte[] b = new byte[4096];
            int readByte = 0, totalByte = 0;
            inputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("C:\\Users\\User\\IdeaProjects\\game\\src\\main\\java\\org\\chapter5\\test.jpg")));

            outputStream = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("C:\\Users\\User\\IdeaProjects\\game\\src\\main\\java\\org\\chapter5\\copy.jpg")));

            while (-1 != (readByte = inputStream.read(b))) {
                outputStream.write(b, 0, readByte);
                totalByte += readByte;
                System.out.println("Read: " + readByte + " Total: " + totalByte);
            }
        } catch (IOException e) {
            System.out.println("エラー発生:" + e.getMessage());
        } finally {
            if (Objects.nonNull(inputStream)) inputStream.close();
            if (Objects.nonNull(outputStream)) outputStream.close();
            System.out.println("ファイル クローズ済");
        }
    }

    private void dataReadWriteTest() throws IOException {
        System.out.println("=== dataReadWriteTest ===");
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            byte[] b = new byte[4096];
            String readLine;
            int count = 1;
            bufferedReader = new BufferedReader(
                    new FileReader("C:\\Users\\User\\IdeaProjects\\game\\src\\main\\java\\org\\chapter5\\testReader.txt"));

            bufferedWriter = new BufferedWriter(
                    new FileWriter("C:\\Users\\User\\IdeaProjects\\game\\src\\main\\java\\org\\chapter5\\testCopy.txt"));

            while (Objects.nonNull(readLine = bufferedReader.readLine())) {
                bufferedWriter.write(readLine);
                bufferedWriter.write("\n");
                System.out.println(count+"行目");
                count++;
            }
        } catch (IOException e) {
            System.out.println("エラー発生:" + e.getMessage());
        } finally {
            if (Objects.nonNull(bufferedReader)) bufferedReader.close();
            if (Objects.nonNull(bufferedWriter)) bufferedWriter.close();
            System.out.println("ファイル クローズ済");
        }
    }
}

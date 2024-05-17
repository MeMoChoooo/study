package chapter5;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileInOut {

    public void excute() {
        try {
            outputStreamTest();
            inputStreamTest();
            fileWriterTest();
            fileReaderTest();
        }catch (IOException e){
            System.out.println("エラー発生:" + e.getMessage());
        }
    }

    private void inputStreamTest() throws IOException {
        System.out.println("=== inputStreamTest ===");
        FileInputStream stream = null;
        InputStreamReader iSReader = null;
        try {
            stream = new FileInputStream("C:\\Users\\User\\IdeaProjects\\game\\src\\main\\java\\org\\chapter5\\outputTest.txt");
            iSReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            int data;
            while ((data = iSReader.read()) != -1) {
                System.out.println("[toHexString]" + Integer.toHexString(data));
                System.out.println("(char)" + (char) data);
            }
        } catch (IOException e) {
            System.out.println("エラー発生:" + e.getMessage());
        } finally {
            if (Objects.nonNull(iSReader)) iSReader.close();
            if (Objects.nonNull(stream)) stream.close();
            System.out.println("ファイル クローズ済");
        }
    }

    private void outputStreamTest() throws IOException {
        System.out.println("=== outputStreamTest ===");
        FileOutputStream stream = null;
        OutputStreamWriter iSWriter = null;
        try {
            stream = new FileOutputStream("C:\\Users\\User\\IdeaProjects\\game\\src\\main\\java\\org\\chapter5\\outputTest.txt");
            iSWriter = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
            iSWriter.write("a\n");
            iSWriter.write("b\n");
            iSWriter.write("c");
        } catch (IOException e) {
            System.out.println("エラー発生:"+e.getMessage());
        } finally {
            if (Objects.nonNull(iSWriter)) iSWriter.close();
            if (Objects.nonNull(stream)) stream.close();
            System.out.println("ファイル クローズ済");
        }
    }

    private void fileWriterTest() throws IOException {
        System.out.println("=== fileWriterTest ===");
        FileWriter iSWriter = null;
        try {
            File file = new File("C:\\Users\\User\\IdeaProjects\\game\\src\\main\\java\\org\\chapter5\\writeTest.txt");
            iSWriter = new FileWriter(file, StandardCharsets.UTF_8);
            iSWriter.write("a\n");
            iSWriter.write("b\n");
            iSWriter.write("c");
        } catch (IOException e) {
            System.out.println("エラー発生:"+e.getMessage());
        } finally {
            if (Objects.nonNull(iSWriter)) iSWriter.close();
            System.out.println("ファイル クローズ済");
        }
    }

    private void fileReaderTest() throws IOException {
        System.out.println("=== fileReaderTest ===");
        FileReader iSReader = null;
        try {
            File file = new File("C:\\Users\\User\\IdeaProjects\\game\\src\\main\\java\\org\\chapter5\\writeTest.txt");
            iSReader = new FileReader(file, StandardCharsets.UTF_8);
            int data;
            while ((data = iSReader.read()) != -1) {
                System.out.println("[toHexString]" + Integer.toHexString(data));
                System.out.println("(char)" + (char) data);
            }
        } catch (IOException e) {
            System.out.println("エラー発生:"+e.getMessage());
        } finally {
            if (Objects.nonNull(iSReader)) iSReader.close();
            System.out.println("ファイル クローズ済");
        }
    }
}

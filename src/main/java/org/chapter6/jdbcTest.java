package org.chapter6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class jdbcTest {
    public void excute() throws SQLException {
        Connection con = null;
        try {
            Properties conInfo = importProperties();
            con = DriverManager.getConnection(conInfo.getProperty("url"), conInfo.getProperty("user"), conInfo.getProperty("pass"));

            var sql_delete = "DELETE FROM person WHERE id = ?;";
            var sql_insert = "INSERT INTO person (id, name, sex, job) VALUES (? ,?, ?, ?);";
            var sql_select = "SELECT * FROM person WHERE id = ?;";

            PreparedStatement ps_delete = con.prepareStatement(sql_delete);
            ps_delete.setString(1, "000000000000");

            PreparedStatement ps_insert = con.prepareStatement(sql_insert);
            ps_insert.setString(1, "000000000000");
            ps_insert.setString(2, "test");
            ps_insert.setString(3, "1");
            ps_insert.setString(4, "tester");

            PreparedStatement ps_select = con.prepareStatement(sql_select);
            ps_select.setString(1, "000000000000");

            System.out.println("DELETE実行結果：" + ps_delete.executeUpdate());
            System.out.println("INSERT実行結果：" + ps_insert.executeUpdate());
            ResultSet rs = ps_select.executeQuery();
            rs.next();
            System.out.println("SELECT実行結果：");
            System.out.println("id  :" + rs.getString(1));
            System.out.println("name:" + rs.getString(2));
            System.out.println("sex :" + rs.getString(3));
            System.out.println("job :" + rs.getString(4));
        } catch(Exception e) {
            System.out.println("エラー発生:" + e.getMessage());
        } finally {
            if (Objects.nonNull(con)) con.close();
        }
    }

    private static Properties importProperties() throws IOException {
        Properties conInfo = new Properties();
        InputStream istream = new FileInputStream("src/main/java/org/chapter6/connect.properties");
        conInfo.load(istream);
        return conInfo;
    }
}

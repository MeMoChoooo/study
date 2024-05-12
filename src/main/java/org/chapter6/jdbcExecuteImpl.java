package org.chapter6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

import static org.common.common.PROPATIES_PASS;

public class jdbcExecuteImpl implements jdbcExecute {

    public String excute(testCaseEnum testCase) {
        Connection con = null;
        String returnText = "正常終了";
        try {
            con = init(testCase);
            PreparedStatement ps  = set(testCase, con);
            // 実行
            switch (testCase) {
                case DELETE:
                case INSERT:
                    int result = ps.executeUpdate();
                    System.out.println(testCase.name()+"実行結果：" + result);
                    break;
                case SELECT:
                    ResultSet resultSet = ps.executeQuery();
                    resultSet.next();
                    System.out.println(testCase.name()+"実行結果：");
                    System.out.println("id  :" + resultSet.getString(1));
                    System.out.println("name:" + resultSet.getString(2));
                    System.out.println("sex :" + resultSet.getString(3));
                    System.out.println("job :" + resultSet.getString(4));
                    break;
                default:
                    return errorText("存在しない分岐");
            }
        } catch(Exception e) {
            returnText =  errorText(e.getMessage());
        } finally {
            try {
                if (Objects.nonNull(con)) {
                    con.close();
                } else {
                    returnText = errorText("初期化エラー");
                }
            } catch (SQLException e) {
                returnText =  errorText(e.getMessage());
            }
        }
        return returnText;
    }

    private Connection init(testCaseEnum testCase) throws SQLException, IOException {
        if (Objects.isNull(testCase)) throw new NullPointerException();
        Properties conInfo = importProperties();
        return DriverManager.getConnection(
                        conInfo.getProperty("url"),
                        conInfo.getProperty("user"),
                        conInfo.getProperty("pass"));

    }

    private PreparedStatement set(testCaseEnum testCase, Connection con) throws Exception {
        PreparedStatement ps = con.prepareStatement(sqlCase(testCase));
        switch (testCase) {
            case DELETE:
            case SELECT:
                ps.setString(1, "000000000000");
                return ps;
            case INSERT:
                ps.setString(1, "000000000000");
                ps.setString(2, "test");
                ps.setString(3, "1");
                ps.setString(4, "tester");
                return ps;
            default:
                throw new Exception(errorText("存在しない分岐"));
        }
    }

    private String sqlCase (testCaseEnum testCase) throws Exception {
        switch (testCase) {
            case DELETE:
                return "DELETE FROM person WHERE id = ?;";
            case INSERT:
                return "INSERT INTO person (id, name, sex, job) VALUES (? ,?, ?, ?);";
            case SELECT:
                return "SELECT * FROM person WHERE id = ?;";
            default:
                throw new Exception(errorText("存在しない分岐"));
        }
    }

    private Properties importProperties() throws IOException {
        Properties conInfo = new Properties();
        InputStream istream = new FileInputStream(PROPATIES_PASS);
        conInfo.load(istream);
        return conInfo;
    }

    private String errorText(String message){
        return "エラー発生:" + message;
    }
}

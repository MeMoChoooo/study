package org.chapter6;

import org.common.Common;
import org.common.ResultData;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Properties;
import java.util.function.BinaryOperator;

import static org.common.ErrorText.*;

/**
 * JDBCの実装　実装クラス
 *
 */
public class JdbcExecuteImpl implements JdbcExecute {

    @Override
    public ResultData excute(testCaseEnum testCase) {
        Connection con = null;
        String returnText = null;
        try {
            // 引数チェック
            if (isArgumentError(testCase)) throw new IllegalArgumentException();

            // DB接続・設定
            Properties conInfo = Common.importProperties().orElseThrow();
            con = DriverManager.getConnection(
                    conInfo.getProperty("url"),
                    conInfo.getProperty("user"),
                    conInfo.getProperty("pass"));
            PreparedStatement ps = con.prepareStatement(sqlCase(testCase));

            // 実行
            sqlExecute(testCase, ps);

        } catch (IllegalArgumentException e) {
            returnText = ARGUMENT_ERROR;
        } catch (NoSuchElementException e) {
            returnText = SYSTEM_ERROR;
        } catch(Exception e) {
            returnText =  e.getMessage();
        } finally {
            returnText = finallyProcess(con, returnText);
        }
        return new ResultData(returnText, null);
    }

    /**
     * 指定したケースのSQLを返す
     *
     * @param testCase
     * @return sqlQuery 実行するSQLクエリ
     */
    private String sqlCase (testCaseEnum testCase) {
        switch (testCase) {
            case DELETE:
                return "DELETE FROM person WHERE id = ?;";
            case INSERT:
                return "INSERT INTO person (id, name, sex, job) VALUES (? ,?, ?, ?);";
            case SELECT:
                return "SELECT * FROM person WHERE id = ?;";
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * 指定したテストケースに応じた実行処理を行い、実行結果を表示する
     *
     * @param testCase
     * @param ps
     * @throws SQLException
     */
    private void sqlExecute(testCaseEnum testCase, PreparedStatement ps) throws SQLException {
        try {
            // クエリ引数設定
            switch (testCase) {
                case DELETE:
                case SELECT:
                    ps.setString(1, "000000000000");
                    break;
                case INSERT:
                    ps.setString(1, "000000000000");
                    ps.setString(2, "test");
                    ps.setString(3, "1");
                    ps.setString(4, "tester");
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            // 実行 AND 結果表示
            switch (testCase) {
                case DELETE:
                case INSERT:
                    int result = ps.executeUpdate();
                    System.out.println(testCase.name() + "実行結果：" + result);
                    break;
                case SELECT:
                    ResultSet resultSet = ps.executeQuery();
                    resultSet.next();
                    System.out.println(testCase.name() + "実行結果：");
                    System.out.println("id  :" + resultSet.getString(1));
                    System.out.println("name:" + resultSet.getString(2));
                    System.out.println("sex :" + resultSet.getString(3));
                    System.out.println("job :" + resultSet.getString(4));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } finally {
            ps.close();
        }
    }

    private String finallyProcess(Connection con, String currentText){
        BinaryOperator<String> decideText =
                ((current, result) -> (Objects.isNull(current)?result:current));
        String returnText;
        try {
            if (Objects.nonNull(con)) {
                con.close();
                // DB接続正常終了 例外処理未検知ならば NORMAL_COMPLETE
                returnText = decideText.apply(currentText, NORMAL_COMPLETE);
            } else {
                // DB接続異常 例外処理未検知ならば UNREACHBLE_ERRPR
                returnText = decideText.apply(currentText, UNREACHABLE_ERROR);
            }
        } catch (Exception e) {
            returnText =  e.getMessage();
        }
        return returnText;
    }
}

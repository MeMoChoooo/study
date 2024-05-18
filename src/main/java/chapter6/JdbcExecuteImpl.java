package chapter6;



import common.Common;
import common.ResultData;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Properties;

import static common.ErrorText.*;


/**
 * JDBCの実装　実装クラス
 *
 */
public class JdbcExecuteImpl implements JdbcExecute {

    /**
     * 指定したケースの処理を実行する
     *
     * @param testCase 実施テストケース
     * @return 実行結果
     */
    @Override
    public ResultData execute(testCaseEnum testCase) {
        String returnText = null;
        try {
            // 引数チェック
            if (isArgumentError(testCase)) throw new IllegalArgumentException();

            // DB接続・設定
            Properties conInfo = Common.importProperties().orElseThrow();

            // 実行
            sqlExecute(testCase, conInfo);
        } catch (IllegalArgumentException e) {
            returnText = ARGUMENT_ERROR;
        } catch (NoSuchElementException e) {
            returnText = SYSTEM_ERROR;
        } catch(Exception e) {
            returnText =  Objects.nonNull(e.getMessage())?e.getMessage():e.toString();
        } finally {
            returnText = Objects.isNull(returnText)?NORMAL_COMPLETE:returnText;
        }
        return new ResultData(returnText, null);
    }

    /**
     * 指定したケースのSQLを返す
     *
     * @param testCase 実施テストケース
     * @return 実行するSQLクエリ SQL実行エラー
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
     * @param testCase 実施テストケース
     * @param conInfo 接続情報
     * @throws SQLException SQL実行エラー
     */
    private void sqlExecute(testCaseEnum testCase, Properties conInfo) throws SQLException {
        final String ID = "000000000000";
        try (Connection con = DriverManager.getConnection(
                conInfo.getProperty("url"),
                conInfo.getProperty("user"),
                conInfo.getProperty("pass"))) {

            System.out.print(testCase.name() + "実行結果：");
            try (PreparedStatement ps = con.prepareStatement(sqlCase(testCase))) {
                switch (testCase) {
                    case DELETE:
                        // クエリ引数設定
                        ps.setString(1, ID);
                        // 実行 AND 結果表示
                        System.out.println(ps.executeUpdate());
                        break;
                    case SELECT:
                        // クエリ引数設定
                        ps.setString(1, ID);
                        // 実行 AND 結果表示
                        ResultSet resultSet = ps.executeQuery();
                        resultSet.next();
                        System.out.println("id  :" + resultSet.getString(1));
                        System.out.println("name:" + resultSet.getString(2));
                        System.out.println("sex :" + resultSet.getString(3));
                        System.out.println("job :" + resultSet.getString(4));
                        break;
                    case INSERT:
                        // クエリ引数設定
                        ps.setString(1, ID);
                        ps.setString(2, "test");
                        ps.setString(3, "1");
                        ps.setString(4, "tester");
                        // 実行 AND 結果表示
                        System.out.println(ps.executeUpdate());
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
    }
}

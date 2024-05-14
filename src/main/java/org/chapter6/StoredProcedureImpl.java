package org.chapter6;

import org.common.Common;
import org.common.ResultData;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Properties;
import java.util.function.BinaryOperator;

import static org.common.ErrorText.*;
import static org.common.ErrorText.UNREACHABLE_ERROR;

/**
 * ストアド・プロージャの実装　実装クラス
 */
public class StoredProcedureImpl implements StoredProcedure{

    /**
     * 指定したケースの処理を実行する
     *
     * @param testCase 実施テストケース
     * @return resultState 実行結果
     */
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

            // 実行
            sqlExecute(testCase, con);

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
     * 指定したテストケースに応じた実行処理を行い、実行結果を表示する
     *
     * @param testCase 実施テストケース
     * @param con 接続
     * @throws SQLException SQL実行エラー
     */
    private void sqlExecute(testCaseEnum testCase, Connection con) throws SQLException {
        final String SQL_DELETE = "DELETE FROM person;";
        final String SQL_INSERT = "INSERT INTO person (id, name, sex, job) VALUES (? ,?, ?, 'tester');";
        final String SQL_SELECT = "SELECT id, name, sex, job FROM person WHERE name = ?;";
        final int INSERT_COUNT = 10;

        PreparedStatement psDelete = null;
        PreparedStatement psInsert = null;
        PreparedStatement psSelect = null;

        try {
            // PK違反避け
            psDelete = con.prepareStatement(SQL_DELETE);
            psDelete.executeUpdate();

            // クエリ引数設定
            psInsert = con.prepareStatement(SQL_INSERT);
            for (int i = 0; i < (INSERT_COUNT - 1); i++) {
                psInsert.setString(1, "00000000000" + i);
                psInsert.setString(2, testCase.name());
                psInsert.setString(3, Integer.toString(i));
                psInsert.addBatch();
            }

            switch (testCase) {
                case CASE1:
                    // 正常系
                    psInsert.setString(1, "000000000009");
                    psInsert.setString(2, testCase.name());
                    psInsert.setString(3, "9");
                    psInsert.addBatch();
                    break;
                case CASE2:
                    // PK違反発生
                    psInsert.setString(1, "000000000008");
                    psInsert.setString(2, testCase.name());
                    psInsert.setString(3, "9");
                    psInsert.addBatch();
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            // 実行
            psInsert.executeBatch();

            // 結果表示
            psSelect = con.prepareStatement(SQL_SELECT);
            psSelect.setString(1, testCase.name());
            ResultSet rs = psSelect.executeQuery();
            System.out.println("SELECT実行結果：");
            for (int i = 0; i < INSERT_COUNT; i++) {
                rs.next();
                System.out.println("==== PERSON[" + i + "] ====");
                System.out.println("id  :" + rs.getString(1));
                System.out.println("name:" + rs.getString(2));
                System.out.println("sex :" + rs.getString(3));
                System.out.println("job :" + rs.getString(4));
            }
        } finally {
            if(Objects.nonNull(psDelete))psDelete.close();
            if(Objects.nonNull(psInsert))psInsert.close();
            if(Objects.nonNull(psSelect))psSelect.close();
            con.close();
        }
    }

    private String finallyProcess(Connection con, String currentText){
        BinaryOperator<String> decideText =
                ((current, result) -> (Objects.isNull(current)?result:current));
        String returnText;
        try {
            if (Objects.nonNull(con) && con.isClosed()) {
                con.close();
                // DB接続正常終了 例外処理未検知ならば NORMAL_COMPLETE
                returnText = decideText.apply(currentText, NORMAL_COMPLETE);
            } else {
                // DB接続異常 例外処理未検知ならばUNREACHABLE_ERROR
                returnText = decideText.apply(currentText, UNREACHABLE_ERROR);
            }
        } catch (Exception e) {
            returnText =  e.getMessage();
        }
        return returnText;
    }
}

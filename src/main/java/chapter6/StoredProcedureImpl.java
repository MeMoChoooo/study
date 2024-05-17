package chapter6;

import common.Common;
import common.ResultData;
import org.junit.platform.commons.util.StringUtils;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Properties;
import java.util.function.BinaryOperator;

import static common.ErrorText.*;

/**
 * ストアド・プロージャの実装　実装クラス
 */
public class StoredProcedureImpl implements StoredProcedure{

    /**
     * INSERT繰り返し回数
     */
    private static final int INSERT_COUNT = 10;

    /**
     * 指定したケースの処理を実行する
     *
     * @param testCase 実施テストケース
     * @return 実行結果
     */
    @Override
    public ResultData execute(testCaseEnum testCase) {
        String returnText = "";
        try {
            // 引数チェック
            if (isArgumentError(testCase)) throw new IllegalArgumentException();

            // DB接続・設定
            Properties conInfo = Common.importProperties().orElseThrow();

            // 実行
            returnText = sqlDeleteExecute(conInfo, returnText);
            returnText = sqlInsertExecute(testCase, conInfo, returnText);
            returnText = sqlSelectExecute(testCase, conInfo, returnText);
        } catch (IllegalArgumentException e) {
            returnText = ARGUMENT_ERROR;
        } catch (NoSuchElementException e) {
            returnText = SYSTEM_ERROR;
        } catch(Exception e) {
            returnText =  e.getMessage();
        }
        return new ResultData(StringUtils.isBlank(returnText)?NORMAL_COMPLETE:returnText, null);
    }

    /**
     * DELETE処理を行い、実行結果を表示する
     *
     * @param conInfo 接続先情報
     * @throws SQLException SQL実行エラー
     */
    private String sqlDeleteExecute(Properties conInfo, String currentText) throws SQLException {
        final String SQL_DELETE = "DELETE FROM person;";
        final String CON_INFO_URL = conInfo.getProperty("url");
        final String CON_INFO_USER = conInfo.getProperty("user");
        final String CON_INFO_PASS = conInfo.getProperty("pass");

        PreparedStatement psDelete = null;
        String returnText;

        try (Connection con = getConnection(CON_INFO_URL, CON_INFO_USER, CON_INFO_PASS)) {
            // PK違反避け
            psDelete = con.prepareStatement(SQL_DELETE);
            psDelete.executeUpdate();
        } finally {
            returnText =  finallyProcess(psDelete, currentText);
        }
        return returnText;
    }

    /**
     * 指定したテストケースに応じたINSERT処理を行い、実行結果を表示する
     *
     * @param testCase 実施テストケース
     * @throws SQLException SQL実行エラー
     */
    private String sqlInsertExecute(testCaseEnum testCase, Properties conInfo, String currentText) throws SQLException {
        final String SQL_INSERT = "INSERT INTO person (id, name, sex, job) VALUES (? ,?, ?, 'tester');";
        final String CON_INFO_URL = conInfo.getProperty("url");
        final String CON_INFO_USER = conInfo.getProperty("user");
        final String CON_INFO_PASS = conInfo.getProperty("pass");

        PreparedStatement psInsert = null;
        String returnText;

        try (Connection con = getConnection(CON_INFO_URL, CON_INFO_USER, CON_INFO_PASS)){
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
        } finally {
            returnText =  finallyProcess(psInsert, currentText);
        }
        return  returnText;
    }

    /**
     * SELECT処理を行い、実行結果を表示する
     *
     * @param conInfo 接続先情報
     * @throws SQLException SQL実行エラー
     */
    private String sqlSelectExecute(testCaseEnum testCase, Properties conInfo, String currentText) throws SQLException {
        final String SQL_SELECT = "SELECT id, name, sex, job FROM person WHERE name = ?;";
        final String CON_INFO_URL = conInfo.getProperty("url");
        final String CON_INFO_USER = conInfo.getProperty("user");
        final String CON_INFO_PASS = conInfo.getProperty("pass");

        PreparedStatement psSelect = null;
        String returnText;

        try (Connection con = getConnection(CON_INFO_URL, CON_INFO_USER, CON_INFO_PASS)){
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
            returnText =  finallyProcess(psSelect, currentText);
        }
        return returnText;
    }

    /**
     * DB接続
     *
     * @param url URL
     * @param user USER
     * @param pass PASSWORD
     * @return DB接続オブジェクト
     * @throws SQLException 接続失敗
     */
    private Connection getConnection(String url, String user, String pass) throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * 終了処理
     *
     * @param ps SQL実行オブジェクト
     * @param currentText 現在の実行結果
     * @return 実行結果
     */
    private String finallyProcess(PreparedStatement ps, String currentText){
        BinaryOperator<String> decideText =
                ((current, result) -> StringUtils.isBlank(current)?result:current);
        String returnText;
        try {
            if (Objects.nonNull(ps)) {
                ps.close();
                // 正常終了 例外処理未検知ならば NORMAL_COMPLETE
                returnText = decideText.apply(currentText, NORMAL_COMPLETE);
            } else {
                // 終了処理異常 例外処理未検知ならばUNREACHABLE_ERROR
                returnText = decideText.apply(currentText, UNREACHABLE_ERROR);
            }
        } catch (Exception e) {
            returnText =  e.getMessage();
        }
        return returnText;
    }
}

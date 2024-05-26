package chapter6;

import common.ExecuteTemplate;
import common.ResultData;

import java.util.Arrays;
import java.util.Objects;

/**
 * JDBCの実装　インタフェース
 */
public interface JdbcExecute extends ExecuteTemplate<JdbcExecute.testCaseEnum> {

    /**
     * 実行可能なテストケース
     */
    enum testCaseEnum {
        DELETE("DELETE"),
        INSERT("INSERT"),
        SELECT("SELECT");

        public final String outline;

        testCaseEnum(String name){
            this.outline = name;
        }
    }

    /**
     * 指定したケースの処理を実行する
     *
     * @param testCase 実施テストケース
     * @return 実行結果
     */
    ResultData execute(testCaseEnum testCase);

    /**
     * 引数チェック（NULLチェック, 存在チェック)
     *
     * @param argument 引数（型は問わない）
     * @return 引数異常有無チェック結果
     */
    @Override
    default boolean isArgumentError(testCaseEnum argument){
        if (testFixedTrue()) return false;

        return Objects.isNull(argument) || Arrays.stream(testCaseEnum.values())
                .allMatch(val -> Objects.equals(argument, val));
    }
}

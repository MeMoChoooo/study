package org.chapter6;

import org.common.ResultData;

import java.util.Arrays;
import java.util.Objects;

/**
 * JDBCの実装　インタフェース
 */
public interface JdbcExecute {

    /**
     * 実行可能なテストケース
     */
    enum testCaseEnum {
        DELETE("DELETE"),
        INSERT("INSERT"),
        SELECT("SELECT");

        private final String name;

        testCaseEnum(String name){
            this.name = name;
        }
    }

    /**
     * 指定したケースの処理を実行する
     *
     * @param testCase
     * @return resultState 実行結果
     */
    ResultData excute(testCaseEnum testCase);

    /**
     * 引数チェック（NULLチェック, 存在チェック)
     *
     * @param argument
     * @return resultState 実行結果
     */
    default boolean isArgumentError(Object argument){
        if (Objects.isNull(argument)) {
            return true;
        }
        return Arrays.stream(testCaseEnum.values())
                .allMatch(val -> Objects.equals(argument, val));
    }
}

package chapter7;

import common.ExecuteTemplate;
import common.ResultData;

import java.util.Arrays;
import java.util.Objects;

public interface GenericsCheck extends ExecuteTemplate<GenericsCheck.testCaseEnum> {

    enum testCaseEnum {
        CASE1("String"),
        CASE2("Shop<Books>");

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
    ResultData<Object> execute(testCaseEnum testCase);

    /**
     * 引数チェック（NULLチェック, 存在チェック)
     *
     * @param argument 引数（型は問わない）
     * @return 引数異常有無チェック結果
     */
    @Override
    default boolean isArgumentError(testCaseEnum argument){
        if (testFixedTrue()) return false;

        return Objects.isNull(argument) || Arrays.stream(GenericsCheck.testCaseEnum.values())
                .allMatch(val -> Objects.equals(argument, val));
    }
}

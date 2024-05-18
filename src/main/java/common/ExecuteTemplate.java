package common;

import common.test.TestStatus;

import static common.Common.testCaseEnum.ARGUMENT_CHECK_FIXED_TRUE;

public interface ExecuteTemplate {

    /**
     * 引数チェック（NULLチェック, 存在チェック)
     *
     * @param argument 引数（型は問わない）
     * @return 引数異常有無チェック結果
     */
    boolean isArgumentError(Object argument);

    /**
     * testFlagに応じて引数チェックを強制的に通す
     *
     * @return TestFlag
     */
    default boolean testFixedTrue() {
        return TestStatus.getTestFlag(ARGUMENT_CHECK_FIXED_TRUE);
    }
}

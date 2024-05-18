package common.test;

import java.util.Arrays;
import common.Common.testCaseEnum;

/**
 * テストのステータスを管理する
 */
public class TestStatus {

    private static boolean[] testFlag = new boolean[testCaseEnum.values().length];

    private TestStatus(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * TestFlagを取得する
     *
     * @param testCase 実施テストケース
     * @return フラグON/OFF
     */
    public static boolean getTestFlag(testCaseEnum testCase) {
        return testFlag[testCase.index];
    }

    /**
     * TestFlagを設定する
     *
     * @param testCase 実施テストケース
     * @param flag ON/OFF
     */
    static void setTestFlag(testCaseEnum testCase, boolean flag) {
        testFlag[testCase.index] = flag;
    }

    /**
     * 全てのTestFlagをfalseにする
     */
    static void allFalseTestFlag() {
        Arrays.fill(testFlag, false);
    }
}

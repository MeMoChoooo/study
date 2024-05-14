package org.common.test;

import java.util.Arrays;
import org.common.Common.testCaseEnum;

public class TestState {

    private static boolean[] testFlag = new boolean[testCaseEnum.values().length];

    private TestState(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * TestFlagを取得する
     *
     * @param testCase
     * @return
     */
    public static boolean getTestFlag(testCaseEnum testCase) {
        return testFlag[testCase.index];
    }

    /**
     * TestFlagを設定する
     *
     * @param testCase
     * @param flag
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

package org.common.test;

import org.common.Common;
import org.junit.After;
import org.junit.Before;

/**
 * テストテンプレート
 */
public class TestTemplate {

    /**
     * テスト実施前準備
     */
    @Before
    public void init(){
        TestStatus.allFalseTestFlag();
    }

    /**
     * テスト実施後
     */
    @After
    public void after(){
        TestStatus.allFalseTestFlag();
    }

    /**
     * テストフラグ設定クラス
     *
     * @param testCase 実施テストケース
     * @param flag ON/OFF
     */
    protected void setTestFlag(Common.testCaseEnum testCase, boolean flag){
        TestStatus.setTestFlag(testCase, flag);
    }
}

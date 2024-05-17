package common.test;

import common.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


/**
 * テストテンプレート
 */
public class TestTemplate {

    /**
     * テスト実施前準備
     */
    @BeforeEach
    public void init(){
        TestStatus.allFalseTestFlag();
    }

    /**
     * テスト実施後
     */
    @AfterEach
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

package chapter6;

import org.junit.jupiter.api.Test;

import common.ResultData;
import common.annotation.TestFlag;
import common.test.TestTemplate;
import org.junit.jupiter.api.DisplayName;


import static common.Common.testCaseEnum.ARGUMENT_CHECK_FIXED_TRUE;
import static common.Common.testCaseEnum.PROPERTIES_ERROR;
import static common.ErrorText.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JdbcExecuteのテストクラス
 */
class JdbcExecuteImplTest extends TestTemplate {

    @Test
    @DisplayName("正常系：DELETE")
    void test01(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.DELETE);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("正常系：INSERT")
    void test02(){
        JdbcExecuteImpl test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.INSERT);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("正常系：SELECT")
    void test03(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.execute(JdbcExecute.testCaseEnum.INSERT);
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.SELECT);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：引数がNULL")
    void test04(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        ResultData resultData = test.execute(null);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(ARGUMENT_ERROR,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @TestFlag(true)
    @DisplayName("異常系：INSERT2連続によるPK違反")
    void test05(){
        final String TEXT = "ERROR: 重複したキー値は一意性制約\"person_pkey\"違反となります\n  詳細: キー (id)=(000000000000) はすでに存在します。";
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.execute(JdbcExecute.testCaseEnum.INSERT);
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.INSERT);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(TEXT,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：DB接続失敗")
    void test06(){
        setTestFlag(PROPERTIES_ERROR,true);
        JdbcExecute test = new JdbcExecuteImpl();
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.INSERT);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(SYSTEM_ERROR,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：NULL引数チェック通過")
    void test07(){
        final String TEXT = "java.lang.NullPointerException";
        setTestFlag(ARGUMENT_CHECK_FIXED_TRUE,true);
        JdbcExecute test = new JdbcExecuteImpl();
        ResultData resultData = test.execute(null);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(TEXT,resultData.getText());
        assertNull(resultData.getData());
    }
}

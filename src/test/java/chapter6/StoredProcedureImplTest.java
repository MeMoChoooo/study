package chapter6;

import common.ResultData;
import common.test.TestTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static common.Common.testCaseEnum.ARGUMENT_CHECK_FIXED_TRUE;
import static common.Common.testCaseEnum.PROPERTIES_ERROR;
import static common.ErrorText.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * StoredProcedureImplのテストクラス
 */
class StoredProcedureImplTest extends TestTemplate {

    @Test
    @DisplayName("正常系：CASE1")
    void test01(){
        StoredProcedure test = new StoredProcedureImpl();
        ResultData resultData = test.execute(StoredProcedure.testCaseEnum.CASE1);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：CASE2")
    void test02(){
        final String TEXT = "バッチ 9 INSERT INTO person (id, name, sex, job) VALUES (('000000000008') ,('CASE2'), ('9'), 'tester') はアボートしました: ERROR: 重複したキー値は一意性制約\"person_pkey\"違反となります\n" +
                "  詳細: キー (id)=(000000000008) はすでに存在します。 このバッチの他のエラーは getNextException を呼び出すことで確認できます。";
        StoredProcedure test = new StoredProcedureImpl();
        ResultData resultData = test.execute(StoredProcedure.testCaseEnum.CASE2);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(TEXT,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：properties取得失敗")
    void test03(){
        setTestFlag(PROPERTIES_ERROR,true);
        StoredProcedure test = new StoredProcedureImpl();
        ResultData resultData = test.execute(StoredProcedure.testCaseEnum.CASE1);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(SYSTEM_ERROR,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：引数がNULL")
    void test04(){
        StoredProcedure test = new StoredProcedureImpl();
        ResultData resultData = test.execute(null);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(ARGUMENT_ERROR,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：NULL引数チェック通過")
    void test05(){
        final String TEXT = "java.lang.NullPointerException";
        setTestFlag(ARGUMENT_CHECK_FIXED_TRUE,true);
        StoredProcedure test = new StoredProcedureImpl();
        ResultData resultData = test.execute(null);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(TEXT,resultData.getText());
        assertNull(resultData.getData());
    }
}

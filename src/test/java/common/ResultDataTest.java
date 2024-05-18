package common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static common.ErrorText.NORMAL_COMPLETE;
import static common.ErrorText.SYSTEM_ERROR;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ResultDataクラスのテストクラス
 */
class ResultDataTest {


    @Test
    @DisplayName("NULL")
    void test01(){
        ResultData resultData = new ResultData();
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertNull(resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("NORMAL, NULL")
    void test02(){
        ResultData resultData = new ResultData(NORMAL_COMPLETE, null);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertNull(resultData.getData());
    }

    @Test
    @DisplayName("NORMAL, ARRAY")
    void test03(){
        final int[] array = {1, 2, 3, 4};
        ResultData resultData = new ResultData(NORMAL_COMPLETE, array);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertEquals(array, resultData.getData());
    }

    @Test
    @DisplayName("ERROR, ARRAY")
    void test04(){
        final int[] array = {1, 2, 3, 4};
        ResultData resultData = new ResultData(SYSTEM_ERROR, array);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(SYSTEM_ERROR,resultData.getText());
        assertEquals(array, resultData.getData());
    }
}

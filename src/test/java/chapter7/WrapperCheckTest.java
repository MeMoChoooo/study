package chapter7;


import common.ResultData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static common.ErrorText.ARGUMENT_ERROR;
import static common.ErrorText.NORMAL_COMPLETE;
import static org.junit.jupiter.api.Assertions.*;

class WrapperCheckTest {

    @Test
    @DisplayName("正常系：Integer")
    void test01(){
        WrapperCheck test = new WrapperCheckImpl();
        ResultData resultData = test.execute(WrapperCheck.testCaseEnum.CASE1);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertEquals(2147483647,resultData.getData());
        assertEquals("class java.lang.Integer",resultData.getData().getClass().toString());

        // 最大値オーバー
        Integer temp = (Integer) resultData.getData();
        resultData.setData(temp+1);
        assertEquals(-2147483648, resultData.getData());
    }

    @Test
    @DisplayName("正常系：Short")
    void test02(){
        WrapperCheck test = new WrapperCheckImpl();
        ResultData resultData = test.execute(WrapperCheck.testCaseEnum.CASE2);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertNotEquals(32768,resultData.getData());
        assertEquals((short)32768,resultData.getData());
        assertEquals("class java.lang.Short",resultData.getData().getClass().toString());
    }

    @Test
    @DisplayName("正常系：Byte")
    void test03(){
        WrapperCheck test = new WrapperCheckImpl();
        ResultData resultData = test.execute(WrapperCheck.testCaseEnum.CASE3);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertNotEquals(128,resultData.getData());
        assertEquals((byte)128,resultData.getData());
        assertEquals("class java.lang.Byte",resultData.getData().getClass().toString());
    }

    @Test
    @DisplayName("正常系：Long")
    void test04(){
        WrapperCheck test = new WrapperCheckImpl();
        ResultData resultData = test.execute(WrapperCheck.testCaseEnum.CASE4);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertEquals(9223372036854775807L,resultData.getData());
        assertEquals("class java.lang.Long",resultData.getData().getClass().toString());

        // 最大値オーバー
        Long temp = (Long) resultData.getData();
        resultData.setData(temp+1);
        assertEquals(-9223372036854775808L, resultData.getData());
    }

    @Test
    @DisplayName("正常系：Double")
    void test05(){
        WrapperCheck test = new WrapperCheckImpl();
        ResultData resultData = test.execute(WrapperCheck.testCaseEnum.CASE5);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertEquals(1.1,resultData.getData());
    }

    @Test
    @DisplayName("正常系：Float")
    void test06(){
        WrapperCheck test = new WrapperCheckImpl();
        ResultData resultData = test.execute(WrapperCheck.testCaseEnum.CASE6);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertNotEquals(1.1,resultData.getData());
        assertEquals((float) 1.1,resultData.getData());
    }

    @Test
    @DisplayName("正常系：Character")
    void test07(){
        WrapperCheck test = new WrapperCheckImpl();
        ResultData resultData = test.execute(WrapperCheck.testCaseEnum.CASE7);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertEquals('a',resultData.getData());
    }

    @Test
    @DisplayName("正常系：Boolean")
    void test08(){
        WrapperCheck test = new WrapperCheckImpl();
        ResultData resultData = test.execute(WrapperCheck.testCaseEnum.CASE8);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertEquals(true,resultData.getData());
    }

    @Test
    @DisplayName("異常系")
    void test09(){
        WrapperCheck test = new WrapperCheckImpl();
        ResultData resultData = test.execute(null);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(ARGUMENT_ERROR,resultData.getText());
        assertNull(resultData.getData());
    }
}

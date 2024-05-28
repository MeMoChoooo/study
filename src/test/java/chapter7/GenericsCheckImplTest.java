package chapter7;

import chapter7.entry.Books;
import chapter7.entry.Shop;
import common.ResultData;
import common.test.TestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static common.ErrorText.*;
import static org.junit.jupiter.api.Assertions.*;

class GenericsCheckImplTest extends TestTemplate {

    @Test
    @DisplayName("正常系：String")
    void test01(){
        GenericsCheck test = new GenericsCheckImpl();
        ResultData resultData = test.execute(GenericsCheck.testCaseEnum.CASE1);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertEquals("class java.lang.String",resultData.getData().getClass().toString());
        assertEquals("テスト",resultData.getData());
    }

    @Test
    @DisplayName("正常系：Books")
    void test02(){
        GenericsCheck test = new GenericsCheckImpl();
        ResultData resultData = test.execute(GenericsCheck.testCaseEnum.CASE2);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isNormal());
        assertEquals(NORMAL_COMPLETE,resultData.getText());
        assertEquals("class chapter7.entry.Shop",resultData.getData().getClass().toString());
        assertEquals("class chapter7.entry.Books",
                ((Shop<?>) resultData.getData()).getGoods().getClass().toString());
        Shop<Books> retData = (Shop<Books>) resultData.getData();
        assertEquals("技術書",retData.getGoods().toString());
    }

    @Test
    @DisplayName("異常系：引数がNULL")
    void test03(){
        GenericsCheck test = new GenericsCheckImpl();
        ResultData resultData = test.execute(null);
        resultData.display(new Object(){}.getClass().getEnclosingMethod().getName());
        assertTrue(resultData.isError());
        assertEquals(ARGUMENT_ERROR,resultData.getText());
        assertNull(resultData.getData());
    }
}

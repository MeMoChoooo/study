package test.chapter6;

import org.chapter6.jdbcExecute;
import org.chapter6.jdbcExecuteImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class jdbcExecuteImplTest {

    @Test
    @DisplayName("正常系：INSERT")
    public void test01(){
        jdbcExecuteImpl test = new jdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.excute(jdbcExecute.testCaseEnum.DELETE);
        assertThat(test.excute(jdbcExecute.testCaseEnum.INSERT), CoreMatchers.is("正常終了"));
    }

    @Test
    @DisplayName("正常系:DELETE")
    public void test02(){
        jdbcExecuteImpl test = new jdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.excute(jdbcExecute.testCaseEnum.INSERT);
        assertThat(test.excute(jdbcExecute.testCaseEnum.DELETE), CoreMatchers.is("正常終了"));
    }

    @Test
    @DisplayName("正常系：SELECT")
    public void test03(){
        jdbcExecuteImpl test = new jdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.excute(jdbcExecute.testCaseEnum.INSERT);
        assertThat(test.excute(jdbcExecute.testCaseEnum.SELECT), CoreMatchers.is("正常終了"));
    }

    @Test
    @DisplayName("異常系：引数がNULL")
    public void test04(){
        jdbcExecuteImpl test = new jdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        assertThat(test.excute(null), CoreMatchers.is("エラー発生:初期化エラー"));
    }
}

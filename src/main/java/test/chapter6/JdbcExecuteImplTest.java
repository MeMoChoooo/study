package test.chapter6;

import org.chapter6.JdbcExecute;
import org.chapter6.JdbcExecuteImpl;
import org.common.annotation.TestFlag;
import org.common.test.TestTemplate;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.common.Common.testCaseEnum.PROPATIES_ERROR;
import static org.junit.Assert.*;

public class JdbcExecuteImplTest extends TestTemplate {

    @Test
    @DisplayName("正常系：INSERT")
    public void test01(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.excute(JdbcExecute.testCaseEnum.DELETE);
        assertThat(test.excute(JdbcExecute.testCaseEnum.INSERT).getText(),
                CoreMatchers.is("正常終了"));
    }

    @Test
    @DisplayName("正常系:DELETE")
    public void test02(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.excute(JdbcExecute.testCaseEnum.INSERT);
        assertThat(test.excute(JdbcExecute.testCaseEnum.DELETE).getText(),
                CoreMatchers.is("正常終了"));
    }

    @Test
    @DisplayName("正常系：SELECT")
    public void test03(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.excute(JdbcExecute.testCaseEnum.INSERT);
        assertThat(test.excute(JdbcExecute.testCaseEnum.SELECT).getText(),
                CoreMatchers.is("正常終了"));
    }

    @Test
    @DisplayName("異常系：引数がNULL")
    public void test04(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        assertThat(test.excute(null).getText(),
                CoreMatchers.is("想定外の引数"));
    }

    @Test
    @TestFlag(true)
    @DisplayName("異常系：INSERT2連続によるPK違反")
    public void test05(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.excute(JdbcExecute.testCaseEnum.INSERT);
        assertThat(test.excute(JdbcExecute.testCaseEnum.INSERT).getText(),
                CoreMatchers.is("ERROR: 重複したキー値は一意性制約\"person_pkey\"違反となります\n  詳細: キー (id)=(000000000000) はすでに存在します。"));
    }

    @Test
    @DisplayName("異常系：DB接続失敗")
    public void test06(){
        setTestFlag(PROPATIES_ERROR,true);
        JdbcExecute test = new JdbcExecuteImpl();
        assertThat(test.excute(JdbcExecute.testCaseEnum.INSERT).getText(),
                CoreMatchers.is("システムエラー"));
    }
}

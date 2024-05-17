package chapter6;

import org.junit.jupiter.api.Test;

import common.ErrorText;
import common.ResultData;
import common.annotation.TestFlag;
import common.test.TestTemplate;
import org.junit.jupiter.api.DisplayName;


import java.util.Objects;

import static common.Common.testCaseEnum.PROPERTIES_ERROR;


/**
 * JdbcExecuteのテストクラス
 */
public class JdbcExecuteImplTest extends TestTemplate {

    @Test
    @DisplayName("正常系：INSERT")
    public void test01(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.DELETE);
        assert resultData.isNormal();
        assert ErrorText.NORMAL_COMPLETE.equals(resultData.getText());
        assert Objects.isNull(resultData.getData());
    }

    @Test
    @DisplayName("正常系:DELETE")
    public void test02(){
        JdbcExecuteImpl test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.INSERT);
        assert resultData.isNormal();
        assert ErrorText.NORMAL_COMPLETE.equals(resultData.getText());
        assert Objects.isNull(resultData.getData());
    }

    @Test
    @DisplayName("正常系：SELECT")
    public void test03(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.execute(JdbcExecute.testCaseEnum.INSERT);
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.SELECT);
        assert resultData.isNormal();
        assert ErrorText.NORMAL_COMPLETE.equals(resultData.getText());
        assert Objects.isNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：引数がNULL")
    public void test04(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        ResultData resultData = test.execute(null);
        assert resultData.isError();
        assert ErrorText.ARGUMENT_ERROR.equals(resultData.getText());
        assert Objects.isNull(resultData.getData());
    }

    @Test
    @TestFlag(true)
    @DisplayName("異常系：INSERT2連続によるPK違反")
    public void test05(){
        JdbcExecute test = new JdbcExecuteImpl();
        // TODO:初期化処理を工夫する
        test.execute(JdbcExecute.testCaseEnum.INSERT);
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.INSERT);
        assert resultData.isError();
        assert "ERROR: 重複したキー値は一意性制約\"person_pkey\"違反となります\n  詳細: キー (id)=(000000000000) はすでに存在します。".equals(resultData.getText());
        assert Objects.isNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：DB接続失敗")
    public void test06(){
        setTestFlag(PROPERTIES_ERROR,true);
        JdbcExecute test = new JdbcExecuteImpl();
        ResultData resultData = test.execute(JdbcExecute.testCaseEnum.INSERT);
        assert resultData.isError();
        assert ErrorText.SYSTEM_ERROR.equals(resultData.getText());
        assert Objects.isNull(resultData.getData());
    }
}

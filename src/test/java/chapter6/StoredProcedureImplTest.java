package chapter6;

import common.ErrorText;
import common.ResultData;
import common.test.TestTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Objects;

import static common.Common.testCaseEnum.PROPERTIES_ERROR;


/**
 * StoredProcedureImplのテストクラス
 */
public class StoredProcedureImplTest extends TestTemplate {

    @Test
    @DisplayName("正常系：CASE1")
    public void test01(){
        StoredProcedure test = new StoredProcedureImpl();
        ResultData resultData = test.execute(StoredProcedure.testCaseEnum.CASE1);
        assert resultData.isNormal();
        assert ErrorText.NORMAL_COMPLETE.equals(resultData.getText());
        assert Objects.isNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：CASE2")
    public void test02(){
        final String TEXT = "バッチ 9 INSERT INTO person (id, name, sex, job) VALUES (('000000000008') ,('CASE2'), ('9'), 'tester') はアボートしました: ERROR: 重複したキー値は一意性制約\"person_pkey\"違反となります\n" +
                "  詳細: キー (id)=(000000000008) はすでに存在します。 このバッチの他のエラーは getNextException を呼び出すことで確認できます。";
        StoredProcedure test = new StoredProcedureImpl();
        ResultData resultData = test.execute(StoredProcedure.testCaseEnum.CASE2);
        assert resultData.isError();
        assert TEXT.equals(resultData.getText());
        assert Objects.isNull(resultData.getData());    }

    @Test
    @DisplayName("異常系：properties取得失敗")
    public void test04(){
        setTestFlag(PROPERTIES_ERROR,true);
        StoredProcedure test = new StoredProcedureImpl();
        ResultData resultData = test.execute(StoredProcedure.testCaseEnum.CASE1);
        assert resultData.isError();
        assert ErrorText.SYSTEM_ERROR.equals(resultData.getText());
        assert Objects.isNull(resultData.getData());
    }

    @Test
    @DisplayName("異常系：引数がNULL")
    public void test05(){
        StoredProcedure test = new StoredProcedureImpl();
        ResultData resultData = test.execute(null);
        assert resultData.isError();
        assert ErrorText.ARGUMENT_ERROR.equals(resultData.getText());
        assert Objects.isNull(resultData.getData());
    }
}

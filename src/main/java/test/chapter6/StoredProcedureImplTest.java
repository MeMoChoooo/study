package test.chapter6;

import org.chapter6.StoredProcedure;
import org.chapter6.StoredProcedureImpl;
import org.common.test.TestTemplate;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.common.Common.testCaseEnum.PROPATIES_ERROR;
import static org.junit.Assert.assertThat;

public class StoredProcedureImplTest extends TestTemplate {

    @Test
    @DisplayName("正常系：CASE1")
    public void test01(){
        StoredProcedure test = new StoredProcedureImpl();
        assertThat(test.excute(StoredProcedure.testCaseEnum.CASE1).getText(),
                CoreMatchers.is("正常終了"));
    }

    @Test
    @DisplayName("異常系：CASE2")
    public void test02(){
        StoredProcedure test = new StoredProcedureImpl();
        assertThat(test.excute(StoredProcedure.testCaseEnum.CASE2).getText(),
                CoreMatchers.is("バッチ 9 INSERT INTO person (id, name, sex, job) VALUES (('000000000008') ,('CASE2'), ('9'), 'tester') はアボートしました: ERROR: 重複したキー値は一意性制約\"person_pkey\"違反となります\n  詳細: キー (id)=(000000000008) はすでに存在します。 このバッチの他のエラーは getNextException を呼び出すことで確認できます。"));
    }

    @Test
    @DisplayName("異常系：properties取得失敗")
    public void test04(){
        setTestFlag(PROPATIES_ERROR,true);
        StoredProcedure test = new StoredProcedureImpl();
        assertThat(test.excute(StoredProcedure.testCaseEnum.CASE1).getText(),
                CoreMatchers.is("システムエラー"));
    }
}

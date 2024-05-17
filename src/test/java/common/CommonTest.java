package common;

import common.test.TestTemplate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import static common.Common.testCaseEnum.PROPERTIES_ERROR;


/**
 * Commonクラスのテストクラス
 */
class CommonTest extends TestTemplate {

    @Test
    @DisplayName("正常:Properties取得成功")
    void test01() {
        Properties proInfo = Common.importProperties().orElseThrow();
        assert "jdbc:postgresql://localhost:5432/postgres".equals(proInfo.getProperty("url"));
        assert "postgres".equals(proInfo.getProperty("user"));
        assert "test".equals(proInfo.getProperty("pass"));
        assert Objects.isNull(proInfo.getProperty("test"));
    }

    @Test
    @DisplayName("異常：Properties取得失敗")
    void test02() {
        setTestFlag(PROPERTIES_ERROR,true);
        Optional<Properties> proInfo = Common.importProperties();
        assert proInfo.isEmpty();
    }
}

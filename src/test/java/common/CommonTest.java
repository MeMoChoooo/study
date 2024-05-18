package common;

import common.test.TestTemplate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Optional;
import java.util.Properties;

import static common.Common.testCaseEnum.PROPERTIES_ERROR;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Commonクラスのテストクラス
 */
class CommonTest extends TestTemplate {

    final String URL = "jdbc:postgresql://localhost:5432/postgres";
    final String USER = "postgres";
    final String PASS = "test";

    @Test
    @DisplayName("正常:Properties取得成功")
    void test01() {
        Properties proInfo = Common.importProperties().orElseThrow();
        assertEquals(URL, proInfo.getProperty("url"));
        assertEquals(USER, proInfo.getProperty("user"));
        assertEquals(PASS, proInfo.getProperty("pass"));
        assertNull(proInfo.getProperty("test"));
    }

    @Test
    @DisplayName("異常：Properties取得失敗")
    void test02() {
        setTestFlag(PROPERTIES_ERROR,true);
        Optional<Properties> proInfo = Common.importProperties();
        assertTrue(proInfo.isEmpty());
    }
}

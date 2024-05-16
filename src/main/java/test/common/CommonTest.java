package test.common;

import org.common.Common;
import org.common.test.TestTemplate;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Optional;
import java.util.Properties;

import static org.common.Common.testCaseEnum.PROPERTIES_ERROR;
import static org.junit.Assert.assertThat;

public class CommonTest extends TestTemplate {

    @Test
    @DisplayName("正常:Propaties取得成功")
    public void test01() {
        Properties proInfo = Common.importProperties().orElseThrow();
        assertThat(proInfo.getProperty("url"),
                CoreMatchers.is("jdbc:postgresql://localhost:5432/postgres"));
        assertThat(proInfo.getProperty("user"),
                CoreMatchers.is("postgres"));
        assertThat(proInfo.getProperty("pass"),
                CoreMatchers.is("test"));
        assertThat(proInfo.getProperty("test"),
                CoreMatchers.nullValue());
    }

    @Test
    @DisplayName("異常：Propaties取得失敗")
    public void test02() {
        setTestFlag(PROPERTIES_ERROR,true);
        Optional<Properties> proInfo = Common.importProperties();
        assertThat(proInfo.isEmpty(),
                CoreMatchers.is(true));
    }
}

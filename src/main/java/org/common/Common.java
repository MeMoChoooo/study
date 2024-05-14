package org.common;

import org.common.test.TestState;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static org.common.Common.testCaseEnum.PROPATIES_ERROR;

public class Common {

    private Common(){
        throw new IllegalStateException("Utility class");
    }

    public enum testCaseEnum {
        PROPATIES_ERROR("PROPATIES_ERROR", 0);

        public final String outline;
        public final int index;

        testCaseEnum(String outline, int index){
            this.outline = outline;
            this.index = index;
        }
    }

    public static final String PROPATIES_PASS = "src/main/common.properties";

    public static Optional<Properties> importProperties() {
        Properties proInfo;
        try (InputStream istream = new FileInputStream(PROPATIES_PASS)) {
            proInfo = new Properties();
            proInfo.load(istream);
        } catch (Exception e) {
            proInfo = null;
        }
        // テスト用フラグ (プロパティ取得失敗ケース実施時)
        if(TestState.getTestFlag(PROPATIES_ERROR))proInfo = null;

        return Optional.ofNullable(proInfo);
    }

}

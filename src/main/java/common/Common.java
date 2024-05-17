package common;

import common.test.TestStatus;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static common.Common.testCaseEnum.PROPERTIES_ERROR;


/**
 * 共通処理
 */
public class Common {

    private Common(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * テストにて発生可能なエラーケース
     */
    public enum testCaseEnum {
        PROPERTIES_ERROR("PROPERTIES_ERROR", 0);

        public final String outline;
        public final int index;

        testCaseEnum(String outline, int index){
            this.outline = outline;
            this.index = index;
        }
    }

    /**
     * プロパティファイルのパス
     */
    private static final String PROPERTIES_PASS = "src/main/common.properties";

    /**
     * プロパティファイルからのデータ取得処理
     *
     * @return proInfo プロパティ情報
     */
    public static Optional<Properties> importProperties() {
        Properties proInfo;
        try (InputStream stream = new FileInputStream(PROPERTIES_PASS)) {
            proInfo = new Properties();
            proInfo.load(stream);
        } catch (Exception e) {
            proInfo = null;
        }
        // テスト用フラグ (プロパティ取得失敗ケース実施時)
        if(TestStatus.getTestFlag(PROPERTIES_ERROR))proInfo = null;

        return Optional.ofNullable(proInfo);
    }

}

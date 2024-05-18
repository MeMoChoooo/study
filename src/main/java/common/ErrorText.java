package common;

/**
 * 想定エラーのケース
 */
public class ErrorText {

    private ErrorText() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 正常終了
     * エラーが何も発生しなかった
     */
    public static final String NORMAL_COMPLETE = "正常終了";

    /**
     * 引数エラー
     * 範囲外の値（NULLなど)を引数として受け取ったことによるエラー発生時
     */
    public static final String ARGUMENT_ERROR = "想定外の引数";

    /**
     * 想定外のエラー
     * システム障害などによるエラー発生時
     */
    public static final String SYSTEM_ERROR = "システムエラー";

    /**
     * 想定外のエラー
     * 到達することが本来はあり得ない（メモリ破壊など）
     */
    public static final String UNREACHABLE_ERROR = "想定外のエラー";

    /**
     * 受け取ったエラーメッセージが正常であるかを確認する
     *
     * @param text メッセージ
     * @return 正常/正常ではない
     */
    public static boolean isNormal(String text){
        return NORMAL_COMPLETE.equals(text);
    }

    /**
     * 受け取ったエラーメッセージが異常であるかを確認する
     *
     * @param text メッセージ
     * @return 異常/異常ではない
     */
    public static boolean isError(String text){
        return !(NORMAL_COMPLETE.equals(text));
    }
}

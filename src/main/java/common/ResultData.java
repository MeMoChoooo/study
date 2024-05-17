package common;

/**
 *　実行メソッドの実行結果データ
 */
public class ResultData {
    private String text;
    private Object data;

    public ResultData(){
        text = null;
        data = null;
    }

    public ResultData(String text, Object data){
        this.text = text;
        this.data = data;
    }

    /**
     * 結果メッセージを設定
     *
     * @param text 結果メッセージ
     */
    public void setText(String text){
        this.text = text;
    }

    /**
     * 結果メッセージを返却
     */
    public String getText() {
        return text;
    }

    /**
     * 結果データを設定
     *
     * @param data 結果データ
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 結果データを返却
     */
    public Object getData() {
        return data;
    }

    /**
     * 結果状態の異常確認
     *
     * @return 異常/異常ではない
     */
    public boolean isError() {
        return ErrorText.isError(text);
    }

    /**
     * 結果状態の正常確認
     *
     * @return 正常/正常ではない
     */
    public boolean isNormal() {
        return ErrorText.isNormal(text);
    }
}

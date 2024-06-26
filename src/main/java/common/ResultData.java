package common;

import java.util.Objects;

/**
 *　実行メソッドの実行結果データ
 */
public class ResultData<T> {
    private String text;
    private T data;

    public ResultData() {
        text = null;
        data = null;
    }

    public ResultData(String text, T data) {
        this.text = text;
        this.data = data;
    }

    /**
     * 結果メッセージを設定
     *
     * @param text 結果メッセージ
     */
    public void setText(String text) {
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
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 結果データを返却
     */
    public T getData() {
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

    /**
     * 結果表示
     */
    public void display(String testCase) {
        System.out.println("[" + testCase + ".text]:" + text);
        if (Objects.isNull(data)) {
            System.out.println("[" + testCase + ".data]:" + null);
        } else {
            System.out.println("[" + testCase + ".data]:(" + (data.getClass().getTypeName()) + ")");

        }
    }
}
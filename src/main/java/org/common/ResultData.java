package org.common;

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

    public void setText(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public boolean isError() {
        return ErrorText.isError(text);
    }

    public boolean isNormal() {
        return ErrorText.isNormal(text);
    }
}

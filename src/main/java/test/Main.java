package test;

import org.chapter6.JdbcExecute;
import org.chapter6.JdbcExecuteImpl;
import org.common.ResultData;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main実行中");
        JdbcExecute test = new JdbcExecuteImpl();
        ResultData result = test.excute(JdbcExecute.testCaseEnum.DELETE);
        System.out.println(result.getText());
    }
}
package org.example;

import org.chapter6.jdbcExecute;
import org.chapter6.jdbcExecuteImpl;

public class Main {
    public static void main(String[] args) {
        jdbcExecute test = new jdbcExecuteImpl();
        test.excute(jdbcExecute.testCaseEnum.DELETE);
        test.excute(jdbcExecute.testCaseEnum.INSERT);
        test.excute(jdbcExecute.testCaseEnum.SELECT);
    }
}
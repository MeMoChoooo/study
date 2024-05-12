package org.chapter6;

public interface jdbcExecute {
    enum testCaseEnum {
        DELETE("DELETE"),
        INSERT("INSERT"),
        SELECT("SELECT");

        private final String name;

        testCaseEnum(String name){
            this.name = name;
        }
    }

    String excute(testCaseEnum testCase);
}

package org.common.test;

import org.common.Common;
import org.junit.After;
import org.junit.Before;

public class TestTemplate {

    @Before
    public void init(){
        TestState.allFalseTestFlag();
    }

    @After
    public void after(){
        TestState.allFalseTestFlag();
    }

    public void setTestFlag(Common.testCaseEnum testCase, boolean flag){
        TestState.setTestFlag(testCase, flag);
    }
}

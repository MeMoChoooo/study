package chapter7;

import common.ResultData;

import static common.ErrorText.*;

public class WrapperCheckImpl implements WrapperCheck {

    @Override
    public ResultData execute(testCaseEnum testCase) {
        ResultData retData = new ResultData(NORMAL_COMPLETE, null);
        if (!isArgumentError(testCase)) {
            switch (testCase) {
                case CASE1:
                    retData.setData(new Integer(2147483647));
                    break;
                case CASE2:
                    retData.setData(new Short((short) 32768));
                    break;
                case CASE3:
                    retData.setData(new Byte((byte) 128));
                    break;
                case CASE4:
                    retData.setData(new Long(9223372036854775807L));
                    break;
                case CASE5:
                    retData.setData(new Double(1.1));
                    break;
                case CASE6:
                    retData.setData(new Float(1.1));
                    break;
                case CASE7:
                    retData.setData(new Character('a'));
                    break;
                case CASE8:
                    retData.setData(new Boolean(true));
                    break;
                default:
                    retData.setText(UNREACHABLE_ERROR);
                    break;
            }
        } else {
            retData.setText(ARGUMENT_ERROR);
        }
        return retData;
    }
}

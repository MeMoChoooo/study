package chapter7;

import chapter7.entry.Books;
import chapter7.entry.Shop;
import common.ErrorText;
import common.ResultData;

public class GenericsCheckImpl implements GenericsCheck{

    @Override
    public ResultData<Object> execute(testCaseEnum testCase) {
        ResultData<Object> retData = new ResultData<>();
        if(!isArgumentError(testCase)){
            switch (testCase) {
                case CASE1:
                    retData.setText(ErrorText.NORMAL_COMPLETE);
                    retData.setData(new String("テスト"));
                    break;
                case CASE2:
                    retData.setText(ErrorText.NORMAL_COMPLETE);
                    retData.setData(new Shop<Books>(new Books("技術書")));
                    break;
                default:
                    retData.setText(ErrorText.UNREACHABLE_ERROR);
                    retData.setData(null);
                    break;
            }
        } else {
            retData.setText(ErrorText.ARGUMENT_ERROR);
        }
        return retData;
    }
}

package today.news.com.main.tools;

import android.support.annotation.IntDef;

import static today.news.com.main.tools.MainConstantTool.BEIJING;
import static today.news.com.main.tools.MainConstantTool.HANGZHOU;
import static today.news.com.main.tools.MainConstantTool.SHANGHAI;
import static today.news.com.main.tools.MainConstantTool.SHENZHEN;

@IntDef({SHANGHAI,HANGZHOU,BEIJING,SHENZHEN})
public @interface MainConstantTool {
    int SHANGHAI = 0;
    int HANGZHOU= 1;
    int BEIJING = 2;
    int SHENZHEN = 3;





}

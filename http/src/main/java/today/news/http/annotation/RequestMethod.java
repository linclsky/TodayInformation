package today.news.http.annotation;

import android.support.annotation.IntDef;

import static today.news.http.annotation.RequestMethod.GET;
import static today.news.http.annotation.RequestMethod.POST;

@IntDef({GET,POST})
public @interface RequestMethod {

    int GET = 1;
    int POST = 2;


}

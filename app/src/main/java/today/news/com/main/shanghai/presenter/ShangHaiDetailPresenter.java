package today.news.com.main.shanghai.presenter;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;
import today.news.com.base.BasePresenter;
import today.news.com.base.JHTask;
import today.news.com.main.shanghai.dto.ShangHaiDetailBean;
import today.news.com.main.shanghai.lf.IShangHaiDetailContract;
import today.news.com.main.shanghai.module.ShangHaiDetailHttpTask;
import today.news.http.result.IResult;
import today.news.mvp.mvp.base.BaseMvpPresenter;
import today.news.task.LfTask;

public class ShangHaiDetailPresenter extends BasePresenter<IShangHaiDetailContract.Iview> implements IShangHaiDetailContract.IPresenter {
    public ShangHaiDetailPresenter(IShangHaiDetailContract.Iview view) {
        super(view);
    }


    @Override
    protected IShangHaiDetailContract.Iview getEmptyView() {
        return IShangHaiDetailContract.emptyView;
    }

    @Override
    public void getNetData(final int pagesize) {

        //1、数据的结果解析怎么来做
        //2、相同任务的去重工作
//        submitTask(new LfTask() {
//            //一定要回调到主线程
//            @Override
//            public void onSuccess(Object o) {
//                //获取网络结果
//
//                    Log.e("getNetData",o.toString());
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                Log.e("getNetData", throwable.toString());
//            }
//
//            //  运行时子线程
//            @Override
//            public Object onBackground() {
//                IResult desc =  new ShangHaiDetailHttpTask().getXiaoHuaList("desc","1","1");
//
//
//                return null;
//            }
//        });

         /*
              架构师的必备条件
              1、合理利用继承关系
              2、合理利用抽象编程
              3、合理利用泛型传递数据
              4、合理利用一些设计模式
            */
        submitTask(new JHTask<ShangHaiDetailBean>() {
            @Override
            public IResult<ShangHaiDetailBean> onBackground() {
                return new ShangHaiDetailHttpTask<ShangHaiDetailBean>().getXiaoHuaList("desc","1",pagesize + "" );
            }

            @Override
            public void onSuccess(IResult<ShangHaiDetailBean> t) {
                ShangHaiDetailBean data = t.data();
//                Gson gson = new Gson();
//                String s = gson.toJson(data);
//                Log.e("ShangHaiDetailPresenter",s);

                getView().showData(data);
            }
        });
    }
}


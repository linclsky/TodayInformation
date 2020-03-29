package today.news.com.main.shanghai.dto;

import java.util.ArrayList;

public class ShangHaiDataManager {


    //获取坚向数据
    private static ArrayList<ShangHaiBean> getVerData(int len){
        ArrayList<ShangHaiBean> data = new ArrayList<>();

        for (int i = 0; i < len ; i++) {
            ShangHaiBean bean = new ShangHaiBean();
            bean.setShowImg(true).setDec("上海欢迎你");
            data.add(bean);
        }
        return data;
    }

    //获取横向数据

    private static ArrayList<ShangHaiBean> getHorData(int len){
        ArrayList<ShangHaiBean> data = new ArrayList<>();

        for (int i = 0; i < len ; i++) {
            ShangHaiBean bean = new ShangHaiBean();
            bean.setShowImg(true).setDec("上海是魔都");
            data.add(bean);
        }
        return data;
    }
    public static ArrayList<ShangHaiBean> getData(){
    //  进行数据的集合
        ArrayList<ShangHaiBean> data = new ArrayList<>();
        data.addAll(getVerData(5));
        data.add(new ShangHaiBean().setData(getHorData(10)).setItemType(ShangHaiBean.IsShangHaiItemType.HORIZONTAL));

        data.addAll(getVerData(5));
        data.add(new ShangHaiBean().setData(getHorData(10)).setItemType(ShangHaiBean.IsShangHaiItemType.HORIZONTAL));

        return data;
    }

}

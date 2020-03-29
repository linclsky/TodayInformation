package today.news.com.main.shanghai.dto;

import android.widget.Adapter;

import java.util.ArrayList;

public class ShangHaiBean {

    private int mItemType = IsShangHaiItemType.VERTICAL;
    private String mDec;
    private boolean isShowImg;
    private ArrayList<ShangHaiBean> data;

    public int getItemType() {
        return mItemType;
    }

    public ShangHaiBean setItemType(int itemType) {
        mItemType = itemType;
        return this;
    }

    public String getDec() {
        return mDec;
    }

    public ShangHaiBean setDec(String dec) {
        mDec = dec;
        return this;
    }

    public boolean isShowImg() {
        return isShowImg;
    }

    public ShangHaiBean setShowImg(boolean showImg) {
        isShowImg = showImg;
        return this;
    }

    public ArrayList<ShangHaiBean> getData() {
        return data;
    }

    public ShangHaiBean setData(ArrayList<ShangHaiBean> data) {
        this.data = data;
        return this;
    }
    public interface IsShangHaiItemType{
        int VERTICAL = 0;
        int HORIZONTAL = 1;
    }

}

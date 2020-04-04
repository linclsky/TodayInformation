package today.news.com.main.hangzhou.adpter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import today.news.com.R;
import today.news.com.main.shanghai.dto.ShangHaiBean;
import today.news.com.main.shanghai.dto.ShangHaiDetailBean;
import today.news.com.main.shanghai.dto.XiaoHuaBean;
import today.news.com.main.shanghai.view.ShangHaiDetailActivity;

public class ZhiHuAdapter extends RecyclerView.Adapter {


    private final ArrayList<ShangHaiDetailBean.XiaoHuaBean> mData;


    public ZhiHuAdapter(ArrayList<ShangHaiDetailBean.XiaoHuaBean> data) {


        mData = data;
    }
    //创建view，然后进行缓存垂直/缓存view
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shanghai_fragment, viewGroup,false);
            ShangHaiViewHolder viewHolder = new ShangHaiViewHolder(inflate);
            return viewHolder;

    }
    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //获取view的集合
        ShangHaiDetailBean.XiaoHuaBean xiaoHuaBean= mData.get(i);
        //比较是垂直的，还是水平的

        //获取文字
        ((ShangHaiViewHolder)viewHolder).mTv.setText(xiaoHuaBean.content);
        //显示图片
        ((ShangHaiViewHolder)viewHolder).mIV.setVisibility(View.GONE);


    }
    //条目的数量
    @Override
    public int getItemCount() {
        return mData.size();
    }


    //缓存view,内存友好设计
    public class ShangHaiViewHolder extends RecyclerView.ViewHolder {

        public ImageView mIV;
        public   TextView mTv;

        public ShangHaiViewHolder(@NonNull View itemView) {
            super(itemView);
            //获取控件Id
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
            mIV = itemView.findViewById(R.id.item_shanghai_iv);

        }
    }

}

package today.news.com.main.shanghai.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import today.news.com.R;
import today.news.com.main.shanghai.dto.ShangHaiBean;
import today.news.com.main.shanghai.view.ShangHaiDetailActivity;

public class ShangHaiAdapter extends RecyclerView.Adapter {
    private final ArrayList<ShangHaiBean> mData;
    private final Activity mContext;
    private final boolean mIsHor;
    private final RecyclerView.RecycledViewPool recycledViewPool;

    public ShangHaiAdapter(Activity context, ArrayList<ShangHaiBean> data, boolean isHor) {
        //声明4级缓存
        recycledViewPool = new RecyclerView.RecycledViewPool();
        mData = data;
        mContext = context;
        mIsHor = isHor;
    }
    //创建view，然后进行缓存垂直/缓存view
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //如果版本=垂直的就获取
        if (viewType == ShangHaiBean.IsShangHaiItemType.VERTICAL){
            if(mIsHor){
                Log.e("onCreateViewHolder","VERTICAL");
            }
            //获取垂直视图
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shanghai_fragment, viewGroup,false);
            ShangHaiViewHolder viewHolder = new ShangHaiViewHolder(inflate);
            return viewHolder;
        }else if(viewType == ShangHaiBean.IsShangHaiItemType.HORIZONTAL){
            //获取水平视图
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shanghai_fragment_rv, null);
            ShangHaiViewHolderRv viewHolder = new ShangHaiViewHolderRv(inflate);
            return viewHolder;
        }
        return null;
    }
    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //获取view的集合
        ShangHaiBean shangHaiBean = mData.get(i);
        //比较是垂直的，还是水平的
    if (viewHolder instanceof ShangHaiViewHolder){
        //获取文字
        ((ShangHaiViewHolder)viewHolder).mTv.setText(shangHaiBean.getDec());
        //显示图片
        ((ShangHaiViewHolder)viewHolder).mIV.setVisibility(shangHaiBean.isShowImg()? View.VISIBLE :View.GONE);
        //点击事件
        ((ShangHaiViewHolder)viewHolder).itemView.setTag(i);
        //  装载adapter适配器
    }else if(viewHolder instanceof ShangHaiViewHolderRv){

        ((ShangHaiViewHolderRv)viewHolder).mRecyclerView.setAdapter(new ShangHaiAdapter(mContext,shangHaiBean.getData(),true));
    }


    }
    //条目的数量
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
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
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击动画
                    ShangHaiDetailActivity.start_5_0(mContext,mIV);
                }
            });
        }
    }
    //缓存view,内存友好设计
    public class ShangHaiViewHolderRv extends RecyclerView.ViewHolder {

        public   RecyclerView mRecyclerView;

        public ShangHaiViewHolderRv(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.item_shanghai_rv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            //被回收的view设置为true的时候被多个view进行复用
            //linearLayoutManager.setRecycleChildrenOnDetach(true);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            //mRecyclerView.setRecycledViewPool(recycledViewPool);
        }
    }
}

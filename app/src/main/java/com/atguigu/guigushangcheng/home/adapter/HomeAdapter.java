package com.atguigu.guigushangcheng.home.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.guigushangcheng.R;
import com.atguigu.guigushangcheng.home.bean.HomeBean;
import com.atguigu.guigushangcheng.utils.Constants;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.RotateDownTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/2/23.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;
    private final LayoutInflater inflater;


    /**
     * 当前类型
     */
    public int currentType = BANNER;


    private final Context context;
    private final HomeBean.ResultBean result;



    public HomeAdapter(Context context, HomeBean.ResultBean result) {//从 HomeFragment 传过来的数据

        this.context = context;
        this.result = result;

        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getItemViewType(int position) {


        if (position == BANNER) {  // 横幅
            currentType = BANNER;

        } else if (position == CHANNEL) {  //频道
            currentType = CHANNEL;
        } else if (position == ACT) {  //活动
            currentType = ACT;
        } else if (position == SECKILL) {  // 秒杀
            currentType = SECKILL;
        } else if (position == RECOMMEND) {  //推荐
            currentType = RECOMMEND;
        } else if (position == HOT) {  //热卖
            currentType = HOT;
        }
        return currentType; //当前类型
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == BANNER) {  // 横幅

            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, null));

        } else if (viewType == CHANNEL) {  //频道

            return new ChannelViewHolder(context, inflater.inflate(R.layout.channel_item, null));

        } else if (viewType == ACT) {  //活动

            return new ActViewHolder(context, inflater.inflate(R.layout.act_item, null));

        } else if (viewType == SECKILL) {  // 秒杀

        } else if (viewType == RECOMMEND) {  //推荐

        } else if (viewType == HOT) {  //热卖

        }


        return null;


    }

    class ActViewHolder extends RecyclerView.ViewHolder {//活动
        @InjectView(R.id.act_viewpager)
        ViewPager actViewpager;
        private ViewPagerAdapter adapter ;

        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);


        }

        public void setData(List<HomeBean.ResultBean.ActInfoBean> act_info) {

            adapter = new ViewPagerAdapter(context , act_info);

            actViewpager.setPageMargin(20);//设置page间间距
            actViewpager.setOffscreenPageLimit(3);
            actViewpager.setAdapter(adapter);
            actViewpager.setPageTransformer(true , new RotateDownTransformer());

            //设置点击事件  通过借口回调
            adapter.setOnItemClickListener(new ViewPagerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Toast.makeText(context, "position=="+position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;

        GridView gvChannel;
        ChannelAdapter adapter;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gvChannel = (GridView) itemView.findViewById(R.id.gv_channel);//初始化视图


            gvChannel.setOnItemClickListener(new MyOnItemClickListener());//给GridViewGridView的item设置监听


        }


        class MyOnItemClickListener implements AdapterView.OnItemClickListener {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        }

        //设置数据
        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {


            //设置适配器.已经有数据
            adapter = new ChannelAdapter(context, channel_info);
            gvChannel.setAdapter(adapter);

        }

    }
    @Override
    public int getItemCount() {
        return 3;   //从 1 到 六 慢慢的加上来的  不显示的时候注意修改


    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {//绑定ViewHolder
        if (getItemViewType(position) == BANNER) {  // 横幅
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(result.getBanner_info());

        } else if (getItemViewType(position) == CHANNEL) {  //频道

            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(result.getChannel_info());

        } else if (getItemViewType(position) == ACT) {  //活动

            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());


        } else if (getItemViewType(position) == SECKILL) {  // 秒杀

        } else if (getItemViewType(position) == RECOMMEND) {  //推荐

        } else if (getItemViewType(position) == HOT) {  //热卖

        }

    }





    class BannerViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.banner)
        Banner banner;
        private final Context context;


        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            banner = (Banner) itemView.findViewById(R.id.banner);

        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {

            //准备图片集合
            List<String> imageUrls = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                imageUrls.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }
            //简单使用
            banner.setImages(imageUrls)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    })
                    .start();
            //设置动画效果-手风琴效果
            banner.setBannerAnimation(AccordionTransformer.class);

            //设置点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(context, "realPostion==" + position, Toast.LENGTH_SHORT).show();
                }
            });


        }

    }
}





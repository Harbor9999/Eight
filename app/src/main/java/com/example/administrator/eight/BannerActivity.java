package com.example.administrator.eight;

import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {
    private int previousEnabledPosition = 0;
    private ViewPager mViewPager;
    private LinearLayout llPointGroup;
    private String[] imageDescription = {
            "新华社最新消息",
            "澎湃新闻你看了吗",
            "揭秘北京电影如何升级",
            "微博的烦恼丝",
            "公交车的人们"
    };
    private TextView tvDescription;


    private List<ImageView> imageViewList;
    private boolean isStop = false; //开启线程标志，当销毁activity时，停止


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        init();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (!isStop) {
                    SystemClock.sleep(3000);
                    //自动滑动每一个广告条目
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        }
                    });
                }

            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        isStop = true;   //销毁activity时 停止线程
        super.onDestroy();

    }

    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tvDescription = (TextView) findViewById(R.id.id_image_description);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);

        imageViewList = new ArrayList<ImageView>();

        int[] imageResIDs = {
                R.mipmap.a,
                R.mipmap.b,
                R.mipmap.c,
                R.mipmap.d,
                R.mipmap.e,
        };

        ImageView iv;
        View view;

        for (int id : imageResIDs) {
            iv = new ImageView(this);
            iv.setBackgroundResource(id);
            imageViewList.add(iv);

            view = new View(this);
            view.setBackgroundResource(R.drawable.piont_backgroud);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(5, 5);
            params.leftMargin = 5;
            view.setEnabled(false);
            view.setLayoutParams(params);
            //增加“点”
            llPointGroup.addView(view);

        }


        mViewPager.setAdapter(new MyAdapter());
        mViewPager.setOnPageChangeListener(this);
        //设置的当前条目为很大数~~~ 形成一个伪 循环滚动的播放
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % imageViewList.size());
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //求模，循环播放每个条目
        int newPosition = position % imageViewList.size();
        tvDescription.setText(imageDescription[newPosition]);
        llPointGroup.getChildAt(previousEnabledPosition).setEnabled(false);
        llPointGroup.getChildAt(newPosition).setEnabled(true);
        previousEnabledPosition = newPosition;      //先前的位置发现改变

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 适配器
     */

    class MyAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position % imageViewList.size()));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewList.get(position % imageViewList.size()));
            return imageViewList.get(position % imageViewList.size());
        }
    }

}

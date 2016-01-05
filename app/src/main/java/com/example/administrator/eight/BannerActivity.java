package com.example.administrator.eight;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {
    private int previousEnabledPosition =0;
    private ViewPager mViewPager;
    private LinearLayout llPointGroup;
    private String[] imageDescription = {
            "巩俐不低俗，我就不能低俗",
            "扑树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送",
            "热血屌丝的反杀"
    };
    private TextView tvDescription;


    private List<ImageView> imageViewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        init();

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

            llPointGroup.addView(view);

        }


        mViewPager.setAdapter(new MyAdapter());
        mViewPager.setOnPageChangeListener(this);

        int index = (Integer.MAX_VALUE /2) - (Integer.MAX_VALUE /2 % imageViewList.size());
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int newPosition = position % imageViewList.size();

        tvDescription.setText(imageDescription[newPosition]);
        llPointGroup.getChildAt(previousEnabledPosition).setEnabled(false);
        llPointGroup.getChildAt(newPosition).setEnabled(true);
        previousEnabledPosition = newPosition;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



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

package com.example.administrator.eight;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.eight.BannerActivity.*;

public class RecylerActivity extends ActionBarActivity {

    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler);

        initData();

        RecyclerView mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        NewAdapter newAdapter = new NewAdapter();
        mRecycler.setAdapter(newAdapter);
    }

    private void initData() {

        mData = new ArrayList<String>();
        for(int i = 'A';i < 'z';i++)
        {
            mData.add(""+(char)i);
        }
    }

    class  NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder>
    {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(RecylerActivity.this).inflate(R.layout.recyler_item,viewGroup,false));
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int i) {
            viewHolder.tv.setText(mData.get(i));

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.id_num);
            }
        }
    }
}

package com.prashant.shortnews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.prashant.shortnews.NewsModel;
import com.prashant.shortnews.R;


import java.util.ArrayList;

public class VerticlePagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    //String [] mResources ;
    // public ArrayList<AndroidVersion> mDummyItems;
    private ArrayList<NewsModel> newsList;
    TextView textView1;
    TextView textView2;
    TextView label;

    ImageView imgNews;
    TextView txtTitle, txtId;
    ScrollView svScroll;
    TextView tvAuthor, tvSource, tvPublishTime;
    GradientDrawable gradientDrawable;
    RelativeLayout checkout_item_root;


    public VerticlePagerAdapter(Context context, ArrayList<NewsModel> newsList) {

        // mContext = (Context) verticalViewPagerActivity;


        // this.mDummyItems=mDummyItems;

        this.newsList = newsList;
        // this.mContext=Context;
        this.mContext = context;

        this.mLayoutInflater = (LayoutInflater) this.mContext.getSystemService(this.mContext.LAYOUT_INFLATER_SERVICE);

    }



   /* public VerticlePagerAdapter(Context context, String[] r) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    @Override
    public int getCount() {
        //return mDummyItems.size();
        return newsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = this.mLayoutInflater.inflate(R.layout.content_main, container, false);

        label = (TextView) itemView.findViewById(R.id.textView1);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        imgNews = (ImageView) itemView.findViewById(R.id.imgNews);

        tvAuthor = itemView.findViewById(R.id.tvAuthor);
        tvSource = itemView.findViewById(R.id.tvSource);
        tvPublishTime = itemView.findViewById(R.id.tvPublishTime);
        checkout_item_root = itemView.findViewById(R.id.checkout_item_root);


        checkout_item_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(newsList.get(position).getUrl()));
                mContext.startActivity(viewIntent);

            }
        });


        gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(5);
        gradientDrawable.setColor(ContextCompat.getColor(mContext, R.color.colorAccent));


        Glide.with(mContext).load(newsList.get(position).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNews);
        txtTitle.setText(newsList.get(position).getTitle());
        label.setText(newsList.get(position).getDescription());


        if (!newsList.get(position).getAuthor().equals("null")) {
            tvAuthor.setBackgroundDrawable(gradientDrawable);
            tvAuthor.setText(newsList.get(position).getAuthor());
        }

        if (!newsList.get(position).getSource().equals("null")) {
            tvSource.setBackgroundDrawable(gradientDrawable);
            tvSource.setText(newsList.get(position).getSource());
        }

        if (!newsList.get(position).getTime().equals("null")) {
            tvPublishTime.setBackgroundDrawable(gradientDrawable);
            tvPublishTime.setText(newsList.get(position).getTime());
        }


        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }


}




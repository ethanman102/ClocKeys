package com.example.clockeys.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.clockeys.R;

import java.util.List;
import java.util.Objects;

public class ImageViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> imageUrls;
    private LayoutInflater layoutInflater;

    public ImageViewPagerAdapter(Context context, List<String> imageUrls){
        this.context = context;
        this.imageUrls = imageUrls;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.image_view_pager_item,container,false);
        ImageView imageView = itemView.findViewById(R.id.imageViewMain);
        Glide.with(itemView).load(imageUrls.get(position)).fitCenter().into(imageView); // load the image into the correct position with the URLs...
        Objects.requireNonNull(container).addView(itemView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public int getCount(){
        return imageUrls.size();
    }

    // Resource https://www.geeksforgeeks.org/image-slider-in-android-using-viewpager/
    @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object object){
        return view == ((LinearLayout) object);
    }

}

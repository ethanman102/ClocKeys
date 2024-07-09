package com.example.clockeys.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.clockeys.Notifications.Notification;
import com.example.clockeys.Notifications.NotificationType;
import com.example.clockeys.R;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Notification> notifications;
    private Context context;

    public NotificationAdapter(List<Notification> notifications, Context context){
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public int getItemViewType(int position){
        Notification notification = notifications.get(position);
        NotificationType notificationType = notification.getType();
        switch (notificationType){
            case ANNOUNCEMENT:
            case IMAGE:
                return notificationType.ordinal();
            default:
                throw new IllegalArgumentException("Invalid Notification Type");
        }
    }

    public static class AnnouncementNotificationViewHolder extends RecyclerView.ViewHolder{

        TextView posterName,announcement,datePosted,hoursAgo;
        public AnnouncementNotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            posterName = itemView.findViewById(R.id.posterName);
            announcement = itemView.findViewById(R.id.announcementTextView);
            datePosted = itemView.findViewById(R.id.datePostedTextView);
            hoursAgo = itemView.findViewById(R.id.timePassedTextView);
        }
    }

     public static class ImageNotificationViewHolder extends RecyclerView.ViewHolder{
        private TextView title,text,posterName,datePosted,hoursAgo;
        private ViewPager viewPager;
        private ImageViewPagerAdapter imageViewPagerAdapter;

        public ImageNotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.imageViewPager);
            title = itemView.findViewById(R.id.imageNotificationTitle);
            text = itemView.findViewById(R.id.imageTextView);
            posterName = itemView.findViewById(R.id.imagePosterName);
            datePosted = itemView.findViewById(R.id.imageDatePostedTextView);
            hoursAgo = itemView.findViewById(R.id.imageTimePassedTextView);

        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        NotificationType notificationType = NotificationType.values()[viewType];
        switch (notificationType){
            case ANNOUNCEMENT: {
                View view = layoutInflater.inflate(R.layout.announcement_notification_item, parent, false);
                return new AnnouncementNotificationViewHolder(view);
            } case IMAGE: {
                View view = layoutInflater.inflate(R.layout.image_notification_item, parent, false);
                return new ImageNotificationViewHolder(view);
            } default:{
                throw new IllegalArgumentException("Invalid Notification Type");
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return notifications.size();
    }



}

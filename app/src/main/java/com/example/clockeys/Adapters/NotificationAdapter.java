package com.example.clockeys.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.clockeys.Notifications.AnnouncementNotification;
import com.example.clockeys.Notifications.ImageNotification;
import com.example.clockeys.Notifications.Notification;
import com.example.clockeys.Notifications.NotificationType;
import com.example.clockeys.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Notification> notifications;
    private Context context;

    public NotificationAdapter(Context context, List<Notification> notifications){
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

        private TextView title,posterName,announcement,datePosted,hoursAgo;
        private Button dismiss;
        public AnnouncementNotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            posterName = itemView.findViewById(R.id.posterName);
            announcement = itemView.findViewById(R.id.announcementTextView);
            datePosted = itemView.findViewById(R.id.datePostedTextView);
            hoursAgo = itemView.findViewById(R.id.timePassedTextView);
            title = itemView.findViewById(R.id.notificationTitle);
            dismiss = itemView.findViewById(R.id.dismissButton);
        }
    }

     public static class ImageNotificationViewHolder extends RecyclerView.ViewHolder{
        private TextView title,text,posterName,datePosted,hoursAgo;
        private Button dismiss;
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
            dismiss = itemView.findViewById(R.id.imageDismissButton);

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
        NotificationType notificationType = notifications.get(position).getType();
        switch (notificationType){
            case  ANNOUNCEMENT:{

                AnnouncementNotification announcementNotification = (AnnouncementNotification) notifications.get(position);
                AnnouncementNotificationViewHolder viewHolder = (AnnouncementNotificationViewHolder) holder;

                // set the onclick listener for dismissal.
                viewHolder.dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (announcementNotification.isDeletable()){
                            notifications.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    }
                });

                viewHolder.title.setText(announcementNotification.getTitle());
                viewHolder.posterName.setText(announcementNotification.getPosterName());
                viewHolder.hoursAgo.setText(announcementNotification.timeElapsed());
                viewHolder.announcement.setText(announcementNotification.getAnnouncement());

                // set the view!
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM-dd-yyyy", Locale.getDefault());
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                // set the date
                viewHolder.datePosted.setText(sdf.format(announcementNotification.getPostDate()));
                break;

            }case IMAGE:{
                ImageNotification imageNotification = (ImageNotification) notifications.get(position);
                ImageNotificationViewHolder viewHolder = (ImageNotificationViewHolder) holder;

                viewHolder.dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (imageNotification.isDeletable()){
                            notifications.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    }
                });

                viewHolder.title.setText(imageNotification.getTitle());
                viewHolder.hoursAgo.setText(imageNotification.timeElapsed());
                viewHolder.posterName.setText(imageNotification.getPosterName());
                viewHolder.text.setText(imageNotification.getText());

                // set the view!
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM-dd-yyyy", Locale.getDefault());
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                Log.d("imagecreated..", "onBindViewHolder: "+imageNotification.getImages());

                // set the date
                viewHolder.datePosted.setText(sdf.format(imageNotification.getPostDate()));

                // set the view pager..
               viewHolder.imageViewPagerAdapter = new ImageViewPagerAdapter(context.getApplicationContext(), imageNotification.getImages());
               viewHolder.viewPager.setAdapter(viewHolder.imageViewPagerAdapter);

                break;
            }

        }
    }



    @Override
    public int getItemCount() {
        return notifications.size();
    }



}

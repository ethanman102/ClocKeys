package com.example.clockeys.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


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

        TextView posterName,posterId,announcement,datePosted,hoursAgo;
        public AnnouncementNotificationViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

     public static class ImageNotificationViewHolder extends RecyclerView.ViewHolder{
        private TextView title,posterId,text,posterName,datePosted,hoursAgo;
        private ImageView imageView;

        public ImageNotificationViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        NotificationType notificationType = NotificationType.values()[viewType];
        switch (notificationType){
            case ANNOUNCEMENT: {
                View view = layoutInflater.inflate(R.layout.annoucment_notification_item, parent, false);
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

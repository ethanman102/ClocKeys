package com.example.clockeys.Management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.clockeys.Adapters.NotificationAdapter;
import com.example.clockeys.Adapters.PunchAdapter;
import com.example.clockeys.Notifications.AnnouncementNotification;
import com.example.clockeys.Notifications.ImageNotification;
import com.example.clockeys.Notifications.Notification;
import com.example.clockeys.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private NotificationAdapter notificationAdapter;
    private RecyclerView notificationRecyclerView;
    private List<Notification> notifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://th.bing.com/th/id/R.98eb05f2c5d53673e4a760c198e53473?rik=neF4Zgph46xhdQ&riu=http%3a%2f%2fpurepng.com%2fpublic%2fuploads%2flarge%2fpurepng.com-mcdonalds-logologobrand-logoiconslogos-251519940643k8zzo.png&ehk=Zli5PXj64NGGP5AG%2fNTp4P4GK4GO3SBd%2bWH34UC1KVA%3d&risl=&pid=ImgRaw&r=0");
        urls.add("https://th.bing.com/th/id/R.07c1ac85b2efd015deb654898d9db27f?rik=K%2fbd%2boTaPkCfSA&pid=ImgRaw&r=0");
        urls.add("https://th.bing.com/th/id/OIP.2jNruqshISvBDRXat-ag0QAAAA?rs=1&pid=ImgDetMain");

        Notification notification1 = new ImageNotification(90,901,"Welcome to Mcdonalds!",new Date(),9,10,"Ethan Keys",new Date(),"Today is your first shift at our new location! Welcome to the Mcondalds family all!",urls);
        notifications.add(notification1);
        Notification notification2 = new AnnouncementNotification(102,3930,"New Hire: Welcome Isabella",new Date(),2,901,"Alexis Johnson",new Date(),"Isabella is our newest addition to the team, Please welcome her with open arms and show her our Mcdonalds Spirit!");
        notifications.add(notification2);

        setContentView(R.layout.activity_notification);

        notificationRecyclerView = findViewById(R.id.notificationRecyclerView);

        notificationAdapter = new NotificationAdapter(getBaseContext(),notifications);
        notificationRecyclerView.setAdapter(notificationAdapter);

    }
}
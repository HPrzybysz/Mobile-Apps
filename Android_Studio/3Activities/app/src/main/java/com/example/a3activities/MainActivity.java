package com.example.a3activities;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    public static final String CHANNEL_ID_HIGH = "channel_high";
    public static final String CHANNEL_ID_LOW = "channel_low";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        createNotification();
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(view -> showNotification(1, "Social Credits Lost", "Click to proceed to 2", CHANNEL_ID_HIGH, activity_second.class));
        button2.setOnClickListener(view -> showNotification(2, "Social Credits Acquired", "Click to proceed to 3", CHANNEL_ID_LOW, activity_third.class));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void createNotification() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel highChannel = new NotificationChannel(
                    CHANNEL_ID_HIGH, "GOOD Notification", NotificationManager.IMPORTANCE_HIGH
            );

            NotificationChannel lowChannel = new NotificationChannel(
                    CHANNEL_ID_LOW, "BAD Notification", NotificationManager.IMPORTANCE_LOW
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null){
                manager.createNotificationChannel(highChannel);
                manager.createNotificationChannel(lowChannel);
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void showNotification(int id, String title, String text, String channelID, Class<?> activity){
        Intent intent = new Intent(this, activity);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channelID).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(title).setContentText(text).setPriority(channelID.equals(CHANNEL_ID_HIGH) ? NotificationCompat.PRIORITY_HIGH : NotificationCompat.PRIORITY_LOW).setContentIntent(pendingIntent).setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(id, notification.build());
    }
}
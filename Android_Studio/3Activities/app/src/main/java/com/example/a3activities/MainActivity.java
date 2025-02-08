package com.example.a3activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID_HIGH = "channel_high";
    private static final String CHANNEL_ID_LOW = "channel_low";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannels();

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(view -> showNotification(1, "TOP PIORITY",
                "Przejdź do aktywności 2", CHANNEL_ID_HIGH, activity_second.class));

        button2.setOnClickListener(view -> showNotification(2, "Taiwan is a country",
                "Przejdź do aktywności 3", CHANNEL_ID_LOW, activity_third.class));
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel highChannel = new NotificationChannel(
                    CHANNEL_ID_HIGH, "Ważne Powiadomienia", NotificationManager.IMPORTANCE_HIGH);
            NotificationChannel lowChannel = new NotificationChannel(
                    CHANNEL_ID_LOW, "Mało Ważne Powiadomienia", NotificationManager.IMPORTANCE_LOW);

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(highChannel);
                manager.createNotificationChannel(lowChannel);
            }
        }
    }

    private void showNotification(int id, String title, String text, String channelId, Class<?> activity) {
        Intent intent = new Intent(this, activity);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(channelId.equals(CHANNEL_ID_HIGH) ? NotificationCompat.PRIORITY_HIGH : NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat.from(this).notify(id, notification.build());
    }
}

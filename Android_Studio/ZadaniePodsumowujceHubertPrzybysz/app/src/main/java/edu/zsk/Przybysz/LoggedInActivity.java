package edu.zsk.Przybysz;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoggedInActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "2137";
    public static final String CHANNEL_NAME = "Zadanie podsumowujące";
    private String activeFragment = "first";
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, new FirstFragment());
        transaction.commit();

        Button changeFragmentButton = findViewById(R.id.changeFragmentButton);
        changeFragmentButton.setOnClickListener(v -> changeFragment());

        Button showNotificationButton = findViewById(R.id.showNotificationButton);
        showNotificationButton.setOnClickListener(v -> sendNotification());
    }

    private void changeFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if ("first".equals(activeFragment)) {
            activeFragment = "second";
            transaction.replace(R.id.fragment_container, new SecondFragment());
        } else {
            activeFragment = "first";
            transaction.replace(R.id.fragment_container, new FirstFragment());
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void sendNotification() {
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Powiadomienie")
                .setContentText("Wiadomość powiadomienia")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
        );
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
    }

    public void openDialog() {
        AppDialogFragment dialogFragment = new AppDialogFragment();
        dialogFragment.setCancelable(true);
        dialogFragment.show(fragmentManager, "AppDialogFragment");
    }
}
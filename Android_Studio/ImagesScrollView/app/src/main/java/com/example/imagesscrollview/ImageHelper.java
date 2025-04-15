package com.example.imagesscrollview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;

public class ImageHelper {

    public static void setupImages(Context context, ViewGroup container) {
        int[] imageResources = {
                R.drawable.image1, R.drawable.image2, R.drawable.image3,
                R.drawable.image4, R.drawable.image5, R.drawable.image6,
                R.drawable.image7, R.drawable.image8, R.drawable.image9,
                R.drawable.image10, R.drawable.image11, R.drawable.image12,
                R.drawable.image13, R.drawable.image14, R.drawable.image15
        };

        for (int resId : imageResources) {
            ImageView imageView = new ImageView(context);
            Drawable drawable = ContextCompat.getDrawable(context, resId);
            imageView.setImageDrawable(drawable);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(8, 0, 8, 0);

            imageView.setOnClickListener(v -> {
                ImageDialogFragment dialog = new ImageDialogFragment(drawable);
                dialog.show(((MainActivity) context).getSupportFragmentManager(), "image_dialog");
            });

            container.addView(imageView);
        }
    }
}
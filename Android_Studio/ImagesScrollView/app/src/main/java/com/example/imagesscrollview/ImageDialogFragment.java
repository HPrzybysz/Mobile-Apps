package com.example.imagesscrollview;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ImageDialogFragment extends DialogFragment {
    private Drawable imageDrawable;

    public ImageDialogFragment(Drawable drawable) {
        this.imageDrawable = drawable;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(R.layout.dialog_image);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            androidx.appcompat.widget.AppCompatImageView imageView = dialog.findViewById(R.id.dialog_image_view);
            if (imageView != null && imageDrawable != null) {
                imageView.setImageDrawable(imageDrawable);
            }
        }
    }
}
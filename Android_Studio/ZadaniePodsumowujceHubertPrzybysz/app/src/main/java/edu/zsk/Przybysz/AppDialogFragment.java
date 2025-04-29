package edu.zsk.Przybysz;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AppDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Okno Dialogowe")
                .setMessage("Treść wiadomości");
        return builder.create();
    }
}

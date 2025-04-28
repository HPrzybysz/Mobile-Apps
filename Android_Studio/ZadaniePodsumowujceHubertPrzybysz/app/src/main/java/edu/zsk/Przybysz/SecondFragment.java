package edu.zsk.Przybysz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        Button dialogButton = view.findViewById(R.id.dialogFragmentOpenButton);
        dialogButton.setOnClickListener(v -> {
            if (getActivity() instanceof LoggedInActivity) {
                ((LoggedInActivity) getActivity()).openDialog();
            }
        });

        return view;
    }
}
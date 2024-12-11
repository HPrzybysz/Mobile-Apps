package edu.zsk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        TextView emailLabel = view.findViewById(R.id.emailLabel);
        TextView firstNameLabel = view.findViewById(R.id.firstNameLabel);
        TextView lastNameLabel = view.findViewById(R.id.lastNameLabel);

        if (getArguments() != null) {
            String email = getArguments().getString("email");
            String firstName = getArguments().getString("firstName");
            String lastName = getArguments().getString("lastName");

            emailLabel.setText("Email: " + email);
            firstNameLabel.setText("Name: " + firstName);
            lastNameLabel.setText("Surname: " + lastName);
        }

        return view;
    }
}


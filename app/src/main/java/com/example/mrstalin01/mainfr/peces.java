package com.example.mrstalin01.mainfr;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mrstalin01.R;

import java.util.ArrayList;
import java.util.Arrays;

public class peces extends Fragment {
    public void showAlert(){
        new AlertDialog.Builder(getContext())
                .setTitle("Peces")
                .setMessage("¿Te gustan los peces?")
                .setPositiveButton("Si", (dialog, which) -> Toast.makeText(getContext(), "blup.", Toast.LENGTH_SHORT).show())
                .setNegativeButton("No", (dialog, which) -> Toast.makeText(getContext(), "me e aogao", Toast.LENGTH_SHORT).show());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cardsView = inflater.inflate(R.layout.fragment_peces, container, false);

        ArrayList<ConstraintLayout> cards = new ArrayList<>(
                Arrays.asList (
                        cardsView.findViewById(R.id.tarjeta1),
                        cardsView.findViewById(R.id.tarjeta2),
                        cardsView.findViewById(R.id.tarjeta3),
                        cardsView.findViewById(R.id.tarjeta4),
                        cardsView.findViewById(R.id.tarjeta5),
                        cardsView.findViewById(R.id.tarjeta6)
                )
        );
        for (
                ConstraintLayout card: cards) {
            card.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showAlert();
                        }
                    }
            );
        }
        return cardsView;
    }
}
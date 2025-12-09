package com.example.mrstalin01.mainfr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mrstalin01.R;
public class Arrecifes extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentArrecifes = inflater.inflate(R.layout.fragment_arrecifes, container, false);
        return fragmentArrecifes;
    }
}

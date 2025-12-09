package com.example.mrstalin01.frmanager;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.mrstalin01.mainfr.Arrecifes;
import com.example.mrstalin01.mainfr.peces;

public class Paginador extends FragmentPagerAdapter {
    private final Context mcontext;
    public Paginador (Context context, FragmentManager fm) {
        super(fm);
        mcontext = context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Arrecifes();
            case 1:
                return new peces();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 2;
    }
}
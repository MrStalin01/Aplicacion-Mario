package com.example.mrstalin01;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.mrstalin01.frmanager.Paginador;
import com.example.mrstalin01.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    Button mainButton;
    TextView mainTV;
    int contador;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Paginador paginador = new Paginador(this, getSupportFragmentManager());
        ViewPager viewPager = binding.coralViewPager;
        viewPager.setAdapter(paginador);

        Button mainButton = findViewById(R.id.mainButton);
        TextView mainTV = findViewById(R.id.mainTV);
        TextView SaludoUser = findViewById(R.id.SaludoUser);

        Bundle bundle = getIntent().getExtras();
        String nombre = bundle.getString("nombre");
        SaludoUser.setText("Bienvenido, " + nombre);

        contador = 0;
        mainTV.setText(String.valueOf(contador));

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                mainTV.setText(String.valueOf(contador));
            }
        });
    }
}
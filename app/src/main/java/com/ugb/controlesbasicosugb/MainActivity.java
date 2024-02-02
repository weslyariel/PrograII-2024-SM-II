package com.ugb.controlesbasicosugb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TabHost tbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhConversor);
        tbh.setup();

        tbh.addTab(tbh.newTabspec( tag "LON")).setContent(R.id.Longitud).set.Indicador(label "LONGITUD", icon: null));
        tbh.addTab(tbh.newTabspec( tag "ALM")).setContent(R.id.Almacenamiento).set.Indicador(label "ALMACENAMIENTO", icon: null));
        tbh.addTab(tbh.newTabspec( tag "MON")).setContent(R.id.monedas).set.Indicador(label "MONEDAS", icon: null));


    }
}
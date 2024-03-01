package com.ugb.controlesbasicosugb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

//--- Trabajo Hecho por:
//--- Keila Nallely Canales Nuñez - SMTR068223
//--- Wesly Ariel Umanzor Arias - SMTR072723

public class MainActivity extends Activity {
    TabHost tbh;
    TextView tempVal;
    Spinner spn;
    Button btnArea;
    Button btnCalcu;
    conversores miObj = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhParcial);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec( "AGU")).setContent(R.id.ValorAgua).setIndicator( "Agua",  null);
        tbh.addTab(tbh.newTabSpec( "ARE")).setContent(R.id.tabArea).setIndicator("AREA",  null);

        btnArea =findViewById(R.id.btnConvertirArea);
        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    spn = findViewById(R.id.spnDeArea);
                    int de = spn.getSelectedItemPosition();

                    spn = findViewById(R.id.spnAArea);
                    int a = spn.getSelectedItemPosition();

                    tempVal = findViewById(R.id.txtCantidadDeArea);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());

                    double resp = miObj.convertir((0, de, a, cantidad);
                    Toast.makeText(getApplicationContext(), "Respuesta: " + resp, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Error:" + e.getMessage(),
                            Toast.LENGTH_LONG).show();
            }
        }

    });
                    btnCalcu = findViewById(R.id.btnparaCalcular);
                    btnCalcu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tempVal = findViewById(R.id.txtAgua);
                            double agua = Double.parseDouble(tempVal.getText().toString());
                            double cal = 0;
                            if (agua<=18){
                                cal=6;
                            } else if (agua<=28) {
                                cal = (agua-18)*0.45+6;
                            } else if (agua>28) {
                                cal=(agua-28)*0.65+((28-28)*0.45)+6;
                                
                            }
                            tempVal = findViewById(R.id.lblCalculo);
                            tempVal.setText("Total que va a pagar: $" + cal);

                        }

                    });
    }
}
class conversores{
    double[][] valores={
            {1, 1.4308, 1.19599, 10.7639, 0.0022896393817974, 0.0001431, 0.0001},
    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return valores[opcion][a]/valores[opcion][de]*cantidad;
    }
}
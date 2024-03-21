package com.ugb.controlesbasicosugb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends Activity {
    TextView tempVal;
    Button btn;
    FloatingActionButton btnRegresar;
    String id="", accion="nuevo";
    ImageView img;
    String urlCompletaFoto;
    Intent tomarFotoIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegresar = findViewById(R.id.fabListaAmigos);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresarLista = new Intent(getApplicationContext(), lista_amigos.class);
                startActivity(regresarLista);
            }
        });

        btn = findViewById(R.id.btnGuardarAmigo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempVal = findViewById(R.id.txtNombre);
                String Nombre = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtdireccion);
                String Direccion = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtTelefono);
                String Telefono = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtEmail);
                String Email = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtDui);
                String Dui = tempVal.getText().toString();

                String[] datos = new String[]{id,Nombre,Direccion,Telefono,Email,Dui,urlCompletaFoto};
                DB db = new DB(getApplicationContext(),"",  null, 1 );
                String respuesta = db.admininistrar_Amigos(accion, datos);
                if (respuesta.equals("ok")){
                    Toast.makeText(getApplicationContext(),"Amigo registrado con exito", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Error al intentar registrar el amigo: " + respuesta, Toast.LENGTH_LONG).show();
                }

            }
        });
        img = findViewById(R.id.btnImgAmigo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFotoAmigo();
            }
        });
        mostrarDatosAmigos();
    }


}
package com.ugb.controlesbasicosugb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    private void tomarFotoAmigo(){
        tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File fotoAmigo = null;
        try{
            fotoAmigo = crearImagenamigo();
            if (fotoAmigo!=null){
                Uri urifotoAmigo = FileProvider.getUriForFile(MainActivity.this,
                        "com.ugb.controlesbasicosugb.fileprovider",fotoAmigo);
                tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, urifotoAmigo);
                startActivityForResult(tomarFotoIntent, 1);
            }else {
                Toast.makeText(getApplicationContext(), "No se pudo tomar la foto", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error al abrir la camara" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode==1 && resultCode==RESULT_OK){
                Bitmap imagenBitmap = BitmapFactory.decodeFile(urlCompletaFoto);
                img.setImageBitmap(imagenBitmap);
            }else {
                Toast.makeText(getApplicationContext(), "Se cancelo la toma de la foto", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al selecionar la foto" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private  File crearImagenamigo() throws Exception{
        String fechaHoraMs = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()),
                fileName ="imagen_"+fechaHoraMs+"_";
        File dirAlmacenamiento = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        if (dirAlmacenamiento.exists()==false){
            dirAlmacenamiento.mkdirs();
        }
        File image = File.createTempFile(fileName,".jpg",dirAlmacenamiento);
        urlCompletaFoto = image.getAbsolutePath();
        return image;
    }
    private void mostrarDatosAmigos(){
        try {
            Bundle parametros = getIntent().getExtras();
            accion = parametros.getString("accion");
            if (accion.equals("modificar")){
                String[] amigos = parametros.getStringArray("amigos");
                id = amigos[0];

                tempVal = findViewById(R.id.txtNombre);
                tempVal.setText(amigos[1]);

                tempVal = findViewById(R.id.txtdireccion);
                tempVal.setText(amigos[2]);

                tempVal = findViewById(R.id.txtTelefono);
                tempVal.setText(amigos[3]);

                tempVal = findViewById(R.id.txtEmail);
                tempVal.setText(amigos[4]);

                tempVal = findViewById(R.id.txtDui);
                tempVal.setText(amigos[5]);

                urlCompletaFoto = amigos[6];
                Bitmap imagenBitmap = BitmapFactory.decodeFile(urlCompletaFoto);
                img.setImageBitmap(imagenBitmap);
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al mostrar los datos amigos", Toast.LENGTH_SHORT).show();
        }
    }
    private void mostrarMsg(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    private void  listaAmigos(){
        Intent intent = new Intent(getApplicationContext(), lista_amigos.class);
        startActivity(intent);
    }
}
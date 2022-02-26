package com.example.practica_link_sms_llam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText link;
    Button btn_link;

    EditText num_tel;
    EditText mensaje;
    Button btn_enviar;

    EditText marcar1;
    Button btn_llamar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        link = (EditText) findViewById(R.id.txtlink);
        btn_link = (Button) findViewById(R.id.btn_link);

        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtlink = link.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(txtlink));
                startActivity(intent);

            }
        });


        num_tel = (EditText) findViewById(R.id.num_tel);
        mensaje = (EditText) findViewById(R.id.mensaje);
        btn_enviar = (Button) findViewById(R.id.btn_enviar);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(num_tel.getText().toString(), null, mensaje.getText().toString(), null, null);
            }
        });


        marcar1 = (EditText) findViewById(R.id.marcar);
        btn_llamar = (Button) findViewById(R.id.btn_llamar);

        btn_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+marcar1));
                startActivity(intent);

            }
        });



    }
}
package com.example.usuario.practica06s10;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout ll1;
    String[] stringbtn = new String[]{"Abrir Camara", "Abrir Telefono", "Abrir Mapa", "Abrir Video", "Tomar Foto", "Asignar Alarma", "Cambiar Estilo"};
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createButtons();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", S.nackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void createButtons() {
        setContentView(R.layout.activity_main);
        ll1 = findViewById(R.id.linearlayout1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < stringbtn.length; i++) {
            Button button = new Button(this);
            button.setLayoutParams(lp);
            button.setText(stringbtn[i]);
            button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            //button.setBackgroundColor(Color.MAGENTA);
            button.setTypeface(Typeface.SERIF, Typeface.BOLD);
            button.setBackgroundResource(R.drawable.buttonstyle);
            button.setId(i);
            button.setOnClickListener(this);
            registerForContextMenu(button);
            ll1.addView(button);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Toast.makeText(getApplicationContext(), "Clikaste" + id + " " + stringbtn[id], Toast.LENGTH_SHORT).show();
        if (id == 1) {
            Intent i = new Intent(Intent.ACTION_DIAL);
            startActivity(i);
        }
        if (id == 6) {
            for (int i = 0; i < stringbtn.length; i++) {
                Button boton = findViewById(i);
                boton.setBackgroundResource(R.drawable.buttonstyle2);
                boton.setTextColor(Color.WHITE);
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Button boton = findViewById(v.getId());
        menu.setHeaderTitle(boton.getText().toString());
        menu.setHeaderIcon(getResources().getDrawable(android.R.drawable.ic_dialog_email));
        menu.add(0, v.getId(), 0, "Upload");
        menu.add(0, v.getId(), 0, "Search");
        menu.add(0, v.getId(), 0, "Share");
        menu.add(0, v.getId(), 0, "Bookmark");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(), "item seleccionado: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        if (item.getTitle() == "Share") {
            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://google.com"));
            startActivity(i);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

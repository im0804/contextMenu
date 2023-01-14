package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contextmenu.R;

/**
 * @author Inbar Menahem
 * @version 1.0
 * @since 14/1/23
 * captures all the needed values for the sequence
 */
public class MainActivity extends AppCompatActivity {
    EditText etfirst;
    EditText etd;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etfirst = (EditText) findViewById(R.id.etFirst);
        etd = (EditText) findViewById(R.id.etD);
        sw = (Switch) findViewById(R.id.sw);
        etd.setHint("enter a sequence");
        etfirst.setHint("enter the first number");

    }

    /**
     * after 'next' button clicked
     * checks if the parameters are okay to continue
     *
     * @param view the view
     * @return goes to the sec activity
     */
    public void go(View view) {
        if (etfirst == null){
            Toast.makeText(this, "please enter the first number", Toast.LENGTH_LONG).show();
        }
        if (etd == null){
            Toast.makeText(this, "please enter the sequence's number", Toast.LENGTH_LONG).show();
        }

        Intent si = new Intent(this, secActivity.class);
        si.putExtra("firstNum", Double.parseDouble(etfirst.getText().toString()));
        si.putExtra("Dnum", Double.parseDouble(etd.getText().toString()));
        si.putExtra("switch",sw.isChecked());
        startActivity(si);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String str = item.getTitle().toString();
        if (str.equals("Credits")){
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * credits activity
 * @author Inbar Menahem
 * @version 1.0
 * @since 14/1/23
 */
public class credits extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        tv = (TextView) findViewById(R.id.tv);
        tv.setText("Thanks to my parents who got me to where i am." + '\n' +"And, of course, thanks to Albert who taught me everything i know.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.add("first activity");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String str = item.getTitle().toString();
        if (str.equals("first activity")){
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
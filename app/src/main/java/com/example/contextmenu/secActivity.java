package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * sec activity makes the sequence and created context menu
 * @author Inbar Menahem
 * @version 1.0
 * @since 14/1/23
 */
public class secActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {

    ListView lv;
    TextView tvDN,
    tvNd;
    Intent gi;
    double etFirst, etD, sum, num1, sumNum;
    String [] arrSequence = new String[20];
    String str;
    boolean sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        gi = getIntent();
        etFirst = gi.getDoubleExtra("firstNum",0);
        etD = gi.getDoubleExtra("Dnum",0);
        sw = gi.getBooleanExtra("switch",false);
        lv = (ListView) findViewById(R.id.lv);
        tvDN = findViewById(R.id.tvDN);
        tvNd = findViewById(R.id.tvNd);
        registerForContextMenu(lv);
        lv.setOnCreateContextMenuListener(this);

        if (sw == true){
            for (int i = 0; i < 20; i++){
                num1 = etFirst * Math.pow(etD,i);
                str = String.valueOf(num1);
                if (str.contains("E")){
                    NumberFormat num = new DecimalFormat();
                    num = new DecimalFormat("0.####E0");
                    arrSequence[i] = num.format(num1);
                }
                else{
                    arrSequence[i] = str;
                }
            }
        }
        else {
            for (int i = 0; i < 20; i++) {
                num1 = etFirst + (i)*etD;
                str = String.valueOf(num1);
                if (str.contains("E")){
                    NumberFormat num = new DecimalFormat();
                    num = new DecimalFormat("0.####E0");
                    arrSequence[i] = num.format(num1);
                }
                else{
                    arrSequence[i] = str;
                }
            }
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String >(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrSequence);
        lv.setAdapter(adp);
        lv.setOnCreateContextMenuListener(this);
    }

    /**
     * Sum double.
     * calculate the sum of the sequence
     *
     * @param pos the pos
     * @return the double, sum
     */
    public double sum (int pos){
        sum = 0;
        for (int i = 0; i < pos; i++) {
            sum += Double.parseDouble(arrSequence[i]);
        }
        return sum;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Sequence Operations");
        menu.add("sum");
        menu.add("position in sequence");
    }

    public boolean onContextItemSelected(MenuItem item) {
        String oper = item.getTitle().toString();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (oper.equals("sum")) {
            sumNum = sum(info.position+1);
            tvDN.setText("Sn = ");
            tvNd.setText(Double.toString(sumNum));
            return true;
        }
        if (oper.equals("position in sequence")) {
            tvDN.setText("N = ");
            tvNd.setText(""+(info.position+1));
            return true;
        }
        return super.onContextItemSelected(item);
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
        if (str.equals("Credits")){
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        else {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}

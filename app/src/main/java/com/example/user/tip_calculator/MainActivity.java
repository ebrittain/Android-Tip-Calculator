package com.example.user.tip_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity{

    private EditText billAmount;
    private SeekBar tipPercent;
    private TextView tipAmount;
    private TextView totalAmount;
    private TextView tipText;

    private TipCalculator tc;
    private double bill;
    private int tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = findViewById(R.id.amount);
        tipPercent = findViewById(R.id.tipPercent);
        tipPercent.setMax(40);
        tipPercent.setProgress(15);
        tipAmount = findViewById(R.id.tipAmount);
        tipText = findViewById(R.id.tipText);
        totalAmount = findViewById(R.id.total);

        bill = 0;
        tip = 15;
        update();


        billAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

                if(billAmount.getText().toString().isEmpty())bill = 0;
                else bill = Double.parseDouble(billAmount.getText().toString());
                update();

            }
        });

        tipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tip = progress;
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void update(){
            tc = new TipCalculator(bill,tip);
            tipAmount.setText("Tip:\t"+tc.getTip());
            totalAmount.setText("Total:\t"+tc.getTotal());
            tipText.setText(tip + "%");
    }
}

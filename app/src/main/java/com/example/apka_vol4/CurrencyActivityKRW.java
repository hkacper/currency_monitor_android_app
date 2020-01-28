package com.example.apka_vol4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CurrencyActivityKRW extends AppCompatActivity {
    private EditText minValueKrw;
    private EditText maxValueKrw;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueKRW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_krw);

        minValueKrw = (EditText) findViewById(R.id.et_min);
        maxValueKrw = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("KRW");
        valueKRW = mDatabase.child("real");

        valueKRW.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueKrw = dataSnapshot.getValue().toString();
                value.setText(valueKrw  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueKRW = minValueKrw.getText().toString();
                String maxValueKRW = maxValueKrw.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueKRW);
                newPost.put("max", maxValueKRW);

                mDatabase.child("min").setValue(minValueKRW);
                mDatabase.child("max").setValue(maxValueKRW);
            }
        });
    }

}

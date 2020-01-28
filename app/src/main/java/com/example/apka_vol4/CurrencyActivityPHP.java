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

public class CurrencyActivityPHP extends AppCompatActivity {
    private EditText minValuePhp;
    private EditText maxValuePhp;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valuePHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_php);

        minValuePhp = (EditText) findViewById(R.id.et_min);
        maxValuePhp = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("PHP");
        valuePHP = mDatabase.child("real");

        valuePHP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valuePhp = dataSnapshot.getValue().toString();
                value.setText(valuePhp  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValuePHP = minValuePhp.getText().toString();
                String maxValuePHP = maxValuePhp.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValuePHP);
                newPost.put("max", maxValuePHP);

                mDatabase.child("min").setValue(minValuePHP);
                mDatabase.child("max").setValue(maxValuePHP);
            }
        });
    }

}

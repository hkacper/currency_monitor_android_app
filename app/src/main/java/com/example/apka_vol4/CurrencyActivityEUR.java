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

public class CurrencyActivityEUR extends AppCompatActivity {
    private EditText minValueEur;
    private EditText maxValueEur;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueEUR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_eur);

        minValueEur = (EditText) findViewById(R.id.et_min);
        maxValueEur = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("EUR");
        valueEUR = mDatabase.child("real");

        valueEUR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueEur = dataSnapshot.getValue().toString();
                value.setText(valueEur  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueEUR = minValueEur.getText().toString();
                String maxValueEUR = maxValueEur.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueEUR);
                newPost.put("max", maxValueEUR);

                mDatabase.child("min").setValue(minValueEUR);
                mDatabase.child("max").setValue(maxValueEUR);
            }
        });
    }

}

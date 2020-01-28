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

public class CurrencyActivitySEK extends AppCompatActivity {
    private EditText minValueSek;
    private EditText maxValueSek;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueSEK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_sek);

        minValueSek = (EditText) findViewById(R.id.et_min);
        maxValueSek = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("SEK");
        valueSEK = mDatabase.child("real");

        valueSEK.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueSek = dataSnapshot.getValue().toString();
                value.setText(valueSek  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueSEK = minValueSek.getText().toString();
                String maxValueSEK = maxValueSek.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueSEK);
                newPost.put("max", maxValueSEK);

                mDatabase.child("min").setValue(minValueSEK);
                mDatabase.child("max").setValue(maxValueSEK);
            }
        });
    }

}

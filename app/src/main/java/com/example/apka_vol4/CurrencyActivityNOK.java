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

public class CurrencyActivityNOK extends AppCompatActivity {
    private EditText minValueNok;
    private EditText maxValueNok;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueNOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_nok);

        minValueNok = (EditText) findViewById(R.id.et_min);
        maxValueNok = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("NOK");
        valueNOK = mDatabase.child("real");

        valueNOK.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueNok = dataSnapshot.getValue().toString();
                value.setText(valueNok  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueNOK = minValueNok.getText().toString();
                String maxValueNOK = maxValueNok.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueNOK);
                newPost.put("max", maxValueNOK);

                mDatabase.child("min").setValue(minValueNOK);
                mDatabase.child("max").setValue(maxValueNOK);
            }
        });
    }

}

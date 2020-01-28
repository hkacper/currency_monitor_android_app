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

public class CurrencyActivityDKK extends AppCompatActivity {
    private EditText minValueDkk;
    private EditText maxValueDkk;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueDKK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_dkk);

        minValueDkk = (EditText) findViewById(R.id.et_min);
        maxValueDkk = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("DKK");
        valueDKK = mDatabase.child("real");

        valueDKK.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueDkk = dataSnapshot.getValue().toString();
                value.setText(valueDkk  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueDKK = minValueDkk.getText().toString();
                String maxValueDKK = maxValueDkk.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueDKK);
                newPost.put("max", maxValueDKK);

                mDatabase.child("min").setValue(minValueDKK);
                mDatabase.child("max").setValue(maxValueDKK);
            }
        });
    }

}

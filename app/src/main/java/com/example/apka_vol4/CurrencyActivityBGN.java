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

public class CurrencyActivityBGN extends AppCompatActivity {
    private EditText minValueBgn;
    private EditText maxValueBgn;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueBGN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_bgn);

        minValueBgn = (EditText) findViewById(R.id.et_min);
        maxValueBgn = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("BGN");
        valueBGN = mDatabase.child("real");

        valueBGN.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueBgn = dataSnapshot.getValue().toString();
                value.setText(valueBgn  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueBGN = minValueBgn.getText().toString();
                String maxValueBGN = maxValueBgn.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueBGN);
                newPost.put("max", maxValueBGN);

                //db.collection("Currencies").document("USD").set(newPost);

                mDatabase.child("min").setValue(minValueBGN);
                mDatabase.child("max").setValue(maxValueBGN);
            }
        });
    }

}

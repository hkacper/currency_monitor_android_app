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

public class CurrencyActivityIDR extends AppCompatActivity {
    private EditText minValueIdr;
    private EditText maxValueIdr;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueIDR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_idr);

        minValueIdr = (EditText) findViewById(R.id.et_min);
        maxValueIdr = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("IDR");
        valueIDR = mDatabase.child("real");

        valueIDR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueIdr = dataSnapshot.getValue().toString();
                value.setText(valueIdr  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueIDR = minValueIdr.getText().toString();
                String maxValueIDR = maxValueIdr.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueIDR);
                newPost.put("max", maxValueIDR);

                mDatabase.child("min").setValue(minValueIDR);
                mDatabase.child("max").setValue(maxValueIDR);
            }
        });
    }

}

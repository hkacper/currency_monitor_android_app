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

public class CurrencyActivityRUB extends AppCompatActivity {
    private EditText minValueRub;
    private EditText maxValueRub;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueRUB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_rub);

        minValueRub = (EditText) findViewById(R.id.et_min);
        maxValueRub = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("RUB");
        valueRUB = mDatabase.child("real");

        valueRUB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueRub = dataSnapshot.getValue().toString();
                value.setText(valueRub  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueRUB = minValueRub.getText().toString();
                String maxValueRUB = maxValueRub.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueRUB);
                newPost.put("max", maxValueRUB);

                mDatabase.child("min").setValue(minValueRUB);
                mDatabase.child("max").setValue(maxValueRUB);
            }
        });
    }

}

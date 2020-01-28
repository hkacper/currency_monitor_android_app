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

public class CurrencyActivityILS extends AppCompatActivity {
    private EditText minValueIls;
    private EditText maxValueIls;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueILS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_ils);

        minValueIls = (EditText) findViewById(R.id.et_min);
        maxValueIls = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("ILS");
        valueILS = mDatabase.child("real");

        valueILS.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueIls = dataSnapshot.getValue().toString();
                value.setText(valueIls  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueILS = minValueIls.getText().toString();
                String maxValueILS = maxValueIls.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueILS);
                newPost.put("max", maxValueILS);

                mDatabase.child("min").setValue(minValueILS);
                mDatabase.child("max").setValue(maxValueILS);
            }
        });
    }

}

package com.example.apka_vol4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pusher.pushnotifications.PushNotifications;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PushNotifications.start(getApplicationContext(), "776e8433-c7b8-4aa9-bf62-5c23650b37c7");
        PushNotifications.addDeviceInterest("hello");

        createExampleList();
        buildRecyclerView();

    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.aud, "Dolar australijski", "AUD"));
        mExampleList.add(new ExampleItem(R.drawable.bgn, "Lew bułgarski", "BGN"));
        mExampleList.add(new ExampleItem(R.drawable.brl, "Real brazylijski", "BRL"));
        mExampleList.add(new ExampleItem(R.drawable.cad, "Dolar kanadyjski", "CAD"));
        mExampleList.add(new ExampleItem(R.drawable.chf, "Frank szwajcarski", "CHF"));
        mExampleList.add(new ExampleItem(R.drawable.cny, "Juan chiński", "CNY"));
        mExampleList.add(new ExampleItem(R.drawable.czk, "Korona czeska", "CZK"));
        mExampleList.add(new ExampleItem(R.drawable.dkk, "Korona duńska", "DKK"));
        mExampleList.add(new ExampleItem(R.drawable.eur, "Euro", "EUR"));
        mExampleList.add(new ExampleItem(R.drawable.gbp, "Funt brytyjski", "GBP"));
        mExampleList.add(new ExampleItem(R.drawable.hkd, "Dolar hongkoński", "HKD"));
        mExampleList.add(new ExampleItem(R.drawable.hrk, "Kuna chorwacka", "HRK"));
        mExampleList.add(new ExampleItem(R.drawable.huf, "Forint węgierski", "HUF"));
        mExampleList.add(new ExampleItem(R.drawable.idr, "Rupia indonezyjska", "IDR"));
        mExampleList.add(new ExampleItem(R.drawable.ils, "Szekel izraelski", "ILS"));
        mExampleList.add(new ExampleItem(R.drawable.inr, "Rupia indyjska", "INR"));
        mExampleList.add(new ExampleItem(R.drawable.isk, "Korona islandzka", "ISK"));
        mExampleList.add(new ExampleItem(R.drawable.jpy, "Jen japoński", "JPY"));
        mExampleList.add(new ExampleItem(R.drawable.krw, "Won południowokoreański", "KRW"));
        mExampleList.add(new ExampleItem(R.drawable.mxn, "Peso meksykańskie", "MXN"));
        mExampleList.add(new ExampleItem(R.drawable.myr, "Ringgit malezyjski", "MYR"));
        mExampleList.add(new ExampleItem(R.drawable.nok, "Korona norweska", "NOK"));
        mExampleList.add(new ExampleItem(R.drawable.nzd, "Dolar nowozelandzki", "NZD"));
        mExampleList.add(new ExampleItem(R.drawable.php, "Peso filipińskie", "PHP"));
        mExampleList.add(new ExampleItem(R.drawable.ron, "Lej rumuński", "RON"));
        mExampleList.add(new ExampleItem(R.drawable.rub, "Rubel rosyjski", "RUB"));
        mExampleList.add(new ExampleItem(R.drawable.sek, "Korona szwedzka", "SEK"));
        mExampleList.add(new ExampleItem(R.drawable.sgd, "Dolar singapurski", "SGD"));
        mExampleList.add(new ExampleItem(R.drawable.thb, "Baht tajski", "THB"));
        mExampleList.add(new ExampleItem(R.drawable.tur, "Lira turecka", "TRY"));
        mExampleList.add(new ExampleItem(R.drawable.usd, "Dolar amerykański", "USD"));
        mExampleList.add(new ExampleItem(R.drawable.zar, "Rand południowoafrykański", "ZAR"));

    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //mExampleList.get(position);
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityAUD.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityBGN.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityBRL.class);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityCAD.class);
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityCHF.class);
                    startActivity(intent);
                }
                if (position == 5) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityCNY.class);
                    startActivity(intent);
                }
                if (position == 6) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityCZK.class);
                    startActivity(intent);
                }
                if (position == 7) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityDKK.class);
                    startActivity(intent);
                }
                if (position == 8) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityEUR.class);
                    startActivity(intent);
                }
                if (position == 9) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityGBP.class);
                    startActivity(intent);
                }
                if (position == 10) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityHKD.class);
                    startActivity(intent);
                }
                if (position == 11) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityHRK.class);
                    startActivity(intent);
                }
                if (position == 12) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityHUF.class);
                    startActivity(intent);
                }
                if (position == 13) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityIDR.class);
                    startActivity(intent);
                }
                if (position == 14) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityILS.class);
                    startActivity(intent);
                }
                if (position == 15) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityINR.class);
                    startActivity(intent);
                }
                if (position == 16) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityISK.class);
                    startActivity(intent);
                }
                if (position == 17) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityJPY.class);
                    startActivity(intent);
                }
                if (position == 18) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityKRW.class);
                    startActivity(intent);
                }
                if (position == 19) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityMXN.class);
                    startActivity(intent);
                }
                if (position == 20) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityMYR.class);
                    startActivity(intent);
                }
                if (position == 21) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityNOK.class);
                    startActivity(intent);
                }
                if (position == 22) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityNZD.class);
                    startActivity(intent);
                }
                if (position == 23) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityPHP.class);
                    startActivity(intent);
                }
                if (position == 24) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityRON.class);
                    startActivity(intent);
                }
                if (position == 25) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityRUB.class);
                    startActivity(intent);
                }
                if (position == 26) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivitySEK.class);
                    startActivity(intent);
                }
                if (position == 27) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivitySGD.class);
                    startActivity(intent);
                }
                if (position == 28) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityTHB.class);
                    startActivity(intent);
                }
                if (position == 29) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityTRY.class);
                    startActivity(intent);
                }
                if (position == 30) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityUSD.class);
                    startActivity(intent);
                }
                if (position == 31) {
                    Intent intent = new Intent(MainActivity.this, CurrencyActivityZAR.class);
                    startActivity(intent);
                }
            }
        });
    }
}
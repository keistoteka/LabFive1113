package com.example.labfive1113;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> allCurrencyRates;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView currencyListView = findViewById(R.id.currencyListView);
        EditText filterEditText = findViewById(R.id.filterEditText);

        // Initialize and populate ListView with currency rates
        new DataLoader(this, new Parser()).execute();

        // Initialize adapter with an empty list
        allCurrencyRates = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allCurrencyRates);
        currencyListView.setAdapter(adapter);

        // Add TextWatcher to EditText for filtering
        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Filter currency rates based on user input
                String filter = editable.toString().toLowerCase();
                List<String> filteredCurrencyRates = new ArrayList<>();
                for (String currencyRate : allCurrencyRates) {
                    if (currencyRate.toLowerCase().contains(filter)) {
                        filteredCurrencyRates.add(currencyRate);
                    }
                }
                // Update adapter with filtered list
                updateAdapter(filteredCurrencyRates);
            }
        });
    }

    // Make the method private and create a new package-private method for updating the adapter
    private void updateAdapter(List<String> filteredCurrencyRates) {
        adapter.clear();
        adapter.addAll(filteredCurrencyRates);
        adapter.notifyDataSetChanged();
    }

    // Make the method package-private
    void updateListView(List<String> currencyRates) {
        // Save a copy of all currency rates
        allCurrencyRates.clear();
        allCurrencyRates.addAll(currencyRates);

        // Initially, display all currency rates
        updateAdapter(currencyRates);
    }
}

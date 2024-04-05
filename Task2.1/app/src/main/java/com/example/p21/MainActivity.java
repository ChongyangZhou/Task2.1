package com.example.p21;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerSourceUnit;
    Spinner spinnerDestinationUnit;
    EditText editTextValue;
    Button buttonConvert;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerSourceUnit = findViewById(R.id.spinner_source_unit);
        spinnerDestinationUnit = findViewById(R.id.spinner_destination_unit);
        editTextValue = findViewById(R.id.editText_value);
        buttonConvert = findViewById(R.id.button_convert);
        textViewResult = findViewById(R.id.textView_result);

        // Example unit arrays
        String[] units = {"Inch", "Centimeter", "Foot", "Meter", "Pound", "Kilogram", "Celsius", "Fahrenheit", "Kelvin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        spinnerSourceUnit.setAdapter(adapter);
        spinnerDestinationUnit.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String sourceUnit = spinnerSourceUnit.getSelectedItem().toString();
                    String destinationUnit = spinnerDestinationUnit.getSelectedItem().toString();
                    double inputValue = Double.parseDouble(editTextValue.getText().toString());

                    double result = convertUnits(sourceUnit, destinationUnit, inputValue);
                    textViewResult.setText(String.format("%.2f", result));
                } catch (NumberFormatException e) {
                    editTextValue.setError("Please enter a valid number.");
                    editTextValue.requestFocus();
                }
            }
        });
    }

    private double convertUnits(String source, String destination, double value) {
        switch (source + " to " + destination) {
            case "Inch to Centimeter":
                return value * 2.54;
            case "Foot to Meter":
                return value * 0.3048;
            case "Pound to Kilogram":
                return value * 0.453592;
            case "Celsius to Fahrenheit":
                return (value * 9/5) + 32;
            case "Fahrenheit to Celsius":
                return (value - 32) * 5/9;
            case "Celsius to Kelvin":
                return value + 273.15;
            case "Kelvin to Celsius":
                return value - 273.15;

            // You can continue adding more cases here
            default:
                return Double.NaN; // Return 'Not a Number' for unhandled conversions
        }
    }
}
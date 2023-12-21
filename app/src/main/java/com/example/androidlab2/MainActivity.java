package com.example.androidlab2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Spinner spinnerCountType;
    private Button buttonCount;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        spinnerCountType = findViewById(R.id.spinnerCountType);
        buttonCount = findViewById(R.id.buttonCount);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.count_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountType.setAdapter(adapter);

        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countWordsOrCharacters();
            }
        });
    }

    private void countWordsOrCharacters() {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            showToast("Please enter text.");
            return;
        }

        String selectedCountType = spinnerCountType.getSelectedItem().toString();
        int count;

        if (selectedCountType.equals(getString(R.string.type_words))) {
            textViewResult.setText(String.valueOf(WordCounter.countWords(text)));
        } else {
            textViewResult.setText(String.valueOf(WordCounter.countCharacters(text)));
        }


    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
package net.ies.san.clemente.tarea_pmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.ies.san.clemente.tarea_pmdm02.model.DaoPrimeNumber;
import net.ies.san.clemente.tarea_pmdm02.model.PrimeNumber;
import net.ies.san.clemente.tarea_pmdm02.model.Utils;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.idButton);
        final EditText inputPrime = findViewById(R.id.idInput);
        final TextView txtView = findViewById(R.id.idResult);
        // Cargar arrayList de primos
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String result = "";
                File storage = Environment.getDataDirectory();
                File dir = new File(getFilesDir(), "prime_numbers");
                if(!dir.exists()){
                    dir.mkdir();
                }
                File file = new File(dir, "data.json");
                PrimeNumber primeNumbers;
                DaoPrimeNumber daoFile = new DaoPrimeNumber();
                if(file.exists()) {
                    System.out.println("PATH" + file.getAbsolutePath());
                    primeNumbers = daoFile.getPrimeNumberFromFile(file);
                } else {
                    primeNumbers = new PrimeNumber();
                }
                try {
                    Integer position = Integer.parseInt(inputPrime.getText().toString());
                    if(primeNumbers.getPrimeNumbers().size() < position) {
                        primeNumbers.addPrimesNumbersIfNotExistPosition(position);
                    }
                    result = String.format(getString(R.string.result_text), position, primeNumbers.getPrimeNumber(position));
                    txtView.setText(result);
                    daoFile.writeJsonInFile(primeNumbers, file);

                } catch(Exception e) {
                    result = getString(R.string.error_text);
                    txtView.setText(result);
                }
            }
        });
    }
}
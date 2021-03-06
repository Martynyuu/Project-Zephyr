package de.kolpa.projectzephyr;

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int _count = 0;
    private int runden = 10;
    private int _max = runden + 1;
    private GedichtListAdapter listAdapter;
    private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAdapter = new GedichtListAdapter(getApplicationContext());

        final EditText text = (EditText) findViewById(R.id.editText);

        enterButton = (Button) findViewById(R.id.button);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listAdapter.addZeile(text.getText().toString());
                text.setText("");
                countUp();
                int flag = 0;

                if (text.getHint().toString().equals("Titel")) {
                    text.setHint("Neuer Satz");

                    flag++;

                }
                if (text.getHint().toString().equals("A") && (flag == 0)) {
                    text.setHint("Ein Reim");
                    flag++;
                }
                if (text.getHint().toString().equals("Neuer Satz") && (flag == 0)) {
                    text.setHint("Ein Reim");
                    flag++;
                }
                if(text.getHint().toString().equals("Ein Reim") && (flag == 0)) {
                        text.setHint("Neuer Satz");
                    flag++;
                }
                int farbe = enterButton.getDrawingCacheBackgroundColor();
                if (_count == runden) {
                    listAdapter.revealText();
                    enterButton.setRotation(270f);
                    enterButton.setText("Neu");
                    text.setHint("Neues Spiel starten ->");
                }

                if (_count == (_max)) {

                    enterButton.setText("");

                    listAdapter.reset();
                    text.setHint("Titel");
                    enterButton.setRotation(0f);

                    flag = 0;
                    _count = 0;

                }
            }
        });

        ListView listView = (ListView) findViewById(R.id.gedicht_liste);
        listView.setAdapter(listAdapter);
    }

    private void countUp() {
        _count += 1;
        if (_count == runden) {
            listAdapter.revealText();
        }
    }
}

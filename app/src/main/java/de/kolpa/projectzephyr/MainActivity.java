package de.kolpa.projectzephyr;

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
    private int _max = 10;
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

                countUp();
                listAdapter.addZeile(text.getText().toString());

                if (_count == 10) {
                    listAdapter.revealText();
                    enterButton.setText("Reset");
                }

                if (_count == 11) {
                    _count = 0;
                    enterButton.setText("Enter");
                    listAdapter.reset();
                }
            }
        });

        ListView listView = (ListView) findViewById(R.id.gedicht_liste);
        listView.setAdapter(listAdapter);
    }

    private void countUp() {
        _count += 1;
        if (_count == _max) {
            listAdapter.revealText();
        }
    }
}

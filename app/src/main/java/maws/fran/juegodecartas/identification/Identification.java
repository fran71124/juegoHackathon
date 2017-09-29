package maws.fran.juegodecartas.identification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import maws.fran.juegodecartas.R;

public class Identification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        Button button = (Button) findViewById(R.id.button);

    }
}

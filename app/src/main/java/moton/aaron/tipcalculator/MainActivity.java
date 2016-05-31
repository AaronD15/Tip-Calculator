package moton.aaron.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.NumberFormat;

//************************************************************//
// Find a way to convert all numbers into two digit decimals. //
//************************************************************//

//TODO: Fully implement seekbar to change percentage value.

public class MainActivity extends AppCompatActivity {

    // Declaring SeekBar
    SeekBar seekBar;

    // Declaring TextView variable
    TextView tip;
    TextView percentage;

    // Declaring EditText variable to allow user input in this text box
    EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        percentage = (TextView)findViewById(R.id.Percentage);
        amount = (EditText)findViewById(R.id.Amount);
        tip = (TextView)findViewById(R.id.TipAmount);

        percentage.setText(10 + seekBar.getProgress() + "%");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int prog = 0;
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            float price = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress + 10;
                percentage.setText(prog + "%");
                if (!amount.getText().toString().isEmpty()) {
                    price = tryParseFloat(amount.getText().toString());
                    tip.setText(formatter.format(price * ((float) (seekBar.getProgress() + 10) / 100)).toString());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        amount.addTextChangedListener(new TextWatcher() {

            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            float price = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!amount.getText().toString().isEmpty()) {
                    price = tryParseFloat(amount.getText().toString());
                    tip.setText(formatter.format(price * ((float) (seekBar.getProgress() + 10) / 100)).toString());
                }
            }
        });

    }

    float tryParseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}

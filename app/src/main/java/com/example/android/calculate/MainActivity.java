package com.example.android.calculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();








    public void submitAll(View view) {


        Toast.makeText(this, "Submitting....", LENGTH_SHORT).show();

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();


        EditText ansField = (EditText) findViewById(R.id.ans_field);
        String ans = ansField.getText().toString();


        // Figure out what user is clicking

        CheckBox groCheckBox = (CheckBox) findViewById(R.id.g_checkbox);

        boolean hasgro = groCheckBox.isChecked();

        CheckBox staCheckBox = (CheckBox) findViewById(R.id.s_checkbox);

        boolean hassta = staCheckBox.isChecked();

        CheckBox conCheckBox = (CheckBox) findViewById(R.id.c_checkbox);

        boolean hascon = conCheckBox.isChecked();

        RadioButton ocRadioButton = (RadioButton) findViewById(R.id.old_radio_button);
        boolean oldcu = ocRadioButton.isChecked();

        RadioButton ncRadioButton = (RadioButton) findViewById(R.id.new_radio_button);
        boolean newcu = ncRadioButton.isChecked();

        RadioButton deskRadioButton = (RadioButton) findViewById(R.id.on_radio_button);
        boolean desktran  = deskRadioButton.isChecked();

        RadioButton onlineRadioButton = (RadioButton) findViewById(R.id.onl_radio_button);
        boolean onlinetran = onlineRadioButton.isChecked();

        RadioButton oweekRadioButton = (RadioButton) findViewById(R.id.week_radio_button);
        boolean oweekp = oweekRadioButton.isChecked();

        RadioButton ofortRadioButton = (RadioButton) findViewById(R.id.for_radio_button);
        boolean ofortp = ofortRadioButton.isChecked();



        // Calculate the score

        int score = calculateScore(ans , hasgro , hascon , hassta , oldcu , newcu , desktran , onlinetran , oweekp , ofortp );



        // Display the order summary on the screen

        String message = createOrderSummary(name, score);

        displayMessage(message);

    }



    /**

     * Calculates the total scores.

     *

     * @return total scores

     */

    private int calculateScore(String ans , boolean hasgro, boolean hascon , boolean hassta , boolean oldcu ,boolean newcu ,
                               boolean desktran ,boolean onlinetran , boolean oweekp ,boolean ofortp) {
        int score = 0;
        if (ans == "New Delhi") {
            score += 1200;
        }
        else {
            score += 0;
        }
        if(oldcu) {
            score +=  150;
        }
        if(newcu) {
            score +=  900;
        }
        if (hasgro) {
            score +=  90;
        }
        if (hascon) {
            score +=  90;
        }
        if (hassta) {
            score +=  90;
        }
        if(onlinetran) {
            score +=  300;
        }
        if(desktran) {
            score += 240;
        }
        if(oweekp) {
            score +=  900;
        }
        if(ofortp) {
            score +=  540;
        }
        return score;
    }



    /**

     * Create summary of the order.

     *

     * @return text summary

     */

    private String createOrderSummary(String name, int score) {

        Toast.makeText(getApplicationContext(), "Your Total Score is:" + score, LENGTH_SHORT).show();

        String scoreMessage = "Congratulations " + name;

        scoreMessage += " Your total score is " + score;

        scoreMessage += " Thank you! ";

        return scoreMessage;

    }

    /**

     * This method displays the given text on the screen.

     */

    private void displayMessage(String message) {

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);

        orderSummaryTextView.setText(message);

    }



}
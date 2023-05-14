package ua.pashanko.neuronudge;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FocusTimerActivity extends AppCompatActivity {

    private TextView mTextViewCountDown;
    private TextView mTextViewCountDownBreak;
    private CountDownTimer mCountDownTimer;
    private CountDownTimer mCountDownTimerBreak;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_timer);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mTextViewCountDownBreak = findViewById(R.id.text_view_countdown_break);
    }
    public void onStartTimer(View view){
        mCountDownTimer = new CountDownTimer(1500000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the text view with the remaining time
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
                mTextViewCountDown.setText(timeLeftFormatted);
            }

            @Override
            public void onFinish() {
                // Display a message when the timer is finished
                mTextViewCountDown.setText("Timer finished!");
                mCountDownTimerBreak = new CountDownTimer(300000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        // Update the text view with the remaining time
                        int minutes = (int) (millisUntilFinished / 1000) / 60;
                        int seconds = (int) (millisUntilFinished / 1000) % 60;
                        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
                        mTextViewCountDownBreak.setText(timeLeftFormatted);
                    }

                    @Override
                    public void onFinish() {
                        // Display a message when the timer is finished
                        mTextViewCountDownBreak.setText("Timer finished!");
                    }
                }.start();
            }
        }.start();

    }
    public void onRestartTimer(View view){
        int minutes = (int) (1500000 / 1000) / 60;
        int seconds = (int) (1500000 / 1000) % 60;
        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
        mCountDownTimer.cancel();
        minutes = (int) (300000 / 1000) / 60;
        seconds = (int) (300000 / 1000) % 60;
        timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        mTextViewCountDownBreak.setText(timeLeftFormatted);
        if (mCountDownTimerBreak != null)
            mCountDownTimerBreak.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the timer when the activity is destroyed
        mCountDownTimer.cancel();
        mCountDownTimerBreak.cancel();
    }
}
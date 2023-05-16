package ua.pashanko.neuronudge;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void navigateTo(View view) {
        Intent intent;

        if(view.getId() == R.id.button_focus_timer) {
            intent = new Intent(this, FocusTimerActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button_tasks) {
            intent = new Intent(this, TasksActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button_block_apps) {
            intent = new Intent(this, BlockAppActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button_whitenoise) {
            intent = new Intent(this, WhiteNoiseActivity.class);
            startActivity(intent);
        }
            // add more cases for other buttons

    }
}
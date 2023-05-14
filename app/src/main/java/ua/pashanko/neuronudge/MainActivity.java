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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Handle the menu item selection
        if (id == R.id.menu_tasks) {
            Intent intent = new Intent(this, TasksActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_focus_timer) {
            Intent intent = new Intent(this, FocusTimerActivity.class);
            startActivity(intent);
            return true;
        }

//        else if (id == R.id.menu_mood_tracker) {
//            // Open mood tracker activity
//            return true;
//        } else if (id == R.id.menu_resources) {
//            // Open resources activity
//            return true;
//        } else if (id == R.id.menu_settings) {
//            // Open settings activity
//            return true;
//        }

        return super.onOptionsItemSelected(item);
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
package ua.pashanko.neuronudge;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import ua.pashanko.neuronudge.blockapp.BlockAppAdapter;

public class BlockAppActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BlockAppAdapter appListAdapter;
    private Timer timer;
    private List<String> blockedApps;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.app_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create AppListAdapter and set it as the adapter for the RecyclerView
        appListAdapter = new BlockAppAdapter(this, getAppList());
        recyclerView.setAdapter(appListAdapter);

        timer = new Timer();
        timer.scheduleAtFixedRate(new CheckAppUsageTask(), 0, 10000);
    }

    @SuppressLint("QueryPermissionsNeeded")
    private List<String> getAppList() {
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> allApps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        return allApps.stream().map(a -> a.name).collect(Collectors.toList());
    }

    private class CheckAppUsageTask extends TimerTask {
        public void run() {
            // Get current time and day of the week

            // Get list of recent app usage
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(1);
            String packageName = recentTasks.get(0).topActivity.getPackageName();

            // Check if the app is in the blocked list
            if (isAppBlocked(packageName)) {
                // Show a toast message and bring up the home screen
                Toast.makeText(BlockAppActivity.this, "This app is blocked during work hours!", Toast.LENGTH_SHORT).show();
                moveTaskToBack(true);
            }

        }
    }

    // Function to check if an app is in the blocked list
    private boolean isAppBlocked(String packageName) {
        for (String blockedApp : blockedApps) {
            if (blockedApp.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    // Function to add an app to the blocked list
    private void addBlockedApp(String packageName) {
        if (!blockedApps.contains(packageName)) {
            blockedApps.add(packageName);
        }
    }

    // Function to remove an app from the blocked list
    private void removeBlockedApp(String packageName) {
        blockedApps.remove(packageName);
    }
}

package ua.pashanko.neuronudge;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.pashanko.neuronudge.tasks.Task;
import ua.pashanko.neuronudge.tasks.TaskAdapter;

public class TasksActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private List<Task> taskListIU;
    private List<Task> taskListINU;
    private List<Task> taskListNIU;
    private List<Task> taskListNINU;
    private TaskAdapter taskAdapterIU;
    private TaskAdapter taskAdapterINU;
    private TaskAdapter taskAdapterNIU;
    private TaskAdapter taskAdapterNINU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squares);

        // Get SharedPreferences instance
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Initialize task list and adapter
        taskListIU = new ArrayList<>();
        taskAdapterIU = new TaskAdapter(taskListIU, this);
        taskListNIU = new ArrayList<>();
        taskAdapterNIU = new TaskAdapter(taskListNIU, this);
        taskListINU = new ArrayList<>();
        taskAdapterINU = new TaskAdapter(taskListINU,this);
        taskListNINU = new ArrayList<>();
        taskAdapterNINU = new TaskAdapter(taskListNINU, this);

        // Get RecyclerView and set adapter
        RecyclerView recyclerViewIU = findViewById(R.id.rv1);
        recyclerViewIU.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewIU.setAdapter(taskAdapterIU);
        RecyclerView recyclerViewINU = findViewById(R.id.rv2);
        recyclerViewINU.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewINU.setAdapter(taskAdapterINU);
        RecyclerView recyclerViewNIU = findViewById(R.id.rv3);
        recyclerViewNIU.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNIU.setAdapter(taskAdapterNIU);
        RecyclerView recyclerViewNINU = findViewById(R.id.rv4);
        recyclerViewNINU.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNINU.setAdapter(taskAdapterNINU);

        // Load saved tasks
        loadTasks();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadTasks() {
        // Clear current task list
        taskListIU.clear();
        taskListINU.clear();
        taskListNIU.clear();
        taskListNINU.clear();

        // Get saved tasks from SharedPreferences
        Map<String, ?> savedTasks = sharedPreferences.getAll();

        // Convert saved tasks to Task objects and add them to task list
        for (Map.Entry<String, ?> entry : savedTasks.entrySet()) {
            String taskTitle = entry.getKey();
            int taskId = (int) entry.getValue();
            if (taskId == 1)
                taskListIU.add(new Task(taskTitle, 1));
            if (taskId == 2)
                taskListINU.add(new Task(taskTitle, 2));
            if (taskId == 3)
                taskListNIU.add(new Task(taskTitle, 3));
            if (taskId == 4)
                taskListNINU.add(new Task(taskTitle, 4));
        }

        // Notify adapter of data change
        taskAdapterIU.notifyDataSetChanged();
        taskAdapterINU.notifyDataSetChanged();
        taskAdapterNIU.notifyDataSetChanged();
        taskAdapterNINU.notifyDataSetChanged();
    }

    private void saveTask(Task task) {
        // Save task to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(task.getTask(), task.getTaskId());
        editor.apply();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void deleteTask(Task task) {
        // Remove task from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(task.getTask());
        editor.apply();

        // Remove task from task list and notify adapter of data change
        if (taskListIU.contains(task)) {
            taskListIU.remove(task);
            taskAdapterIU.notifyDataSetChanged();
        }
        if (taskListINU.contains(task)) {
            taskListINU.remove(task);
            taskAdapterINU.notifyDataSetChanged();
        }
        if (taskListNIU.contains(task)) {
            taskListNIU.remove(task);
            taskAdapterNIU.notifyDataSetChanged();
        }
        if (taskListNINU.contains(task)) {
            taskListNINU.remove(task);
            taskAdapterNINU.notifyDataSetChanged();
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    public void showEditDialog(Task task) {
        // Create dialog for editing task
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        // Add input field for task title
        EditText input = new EditText(this);
        input.setText(task.getTask());
        builder.setView(input);

        // Add buttons for saving and cancelling
        builder.setPositiveButton("Save", (dialog, which) -> {
            // Get new task title from input field
            String newTask = input.getText().toString();

            // Update task title and save to SharedPreferences
            if (taskListIU.contains(task)) {
                taskListIU.remove(task);
                taskAdapterIU.notifyDataSetChanged();
                task.setTask(newTask);
                task.setTaskId(1);
                saveTask(task);
                taskListIU.add(task);
                taskAdapterIU.notifyDataSetChanged();
            }
            if (taskListINU.contains(task)) {
                taskListINU.remove(task);
                taskAdapterINU.notifyDataSetChanged();
                task.setTask(newTask);
                task.setTaskId(2);
                saveTask(task);
                taskListINU.add(task);
                taskAdapterINU.notifyDataSetChanged();
            }
            if (taskListNIU.contains(task)) {
                taskListNIU.remove(task);
                taskAdapterNIU.notifyDataSetChanged();
                task.setTask(newTask);
                task.setTaskId(3);
                saveTask(task);
                taskListNIU.add(task);
                taskAdapterNIU.notifyDataSetChanged();
            }
            if (taskListNINU.contains(task)) {
                taskListNINU.remove(task);
                taskAdapterNINU.notifyDataSetChanged();
                task.setTask(newTask);
                task.setTaskId(4);
                saveTask(task);
                taskListNINU.add(task);
                taskAdapterNINU.notifyDataSetChanged();
            }
        });
        builder.setNeutralButton("Delete", ((dialog, which) -> {
            deleteTask(task);
        }));
        builder.setNegativeButton("Cancel", null);

        // Show dialog
        builder.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addTask(View view) {
        // Create dialog for adding new task
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Task");

        // Add input field for task title
        EditText input = new EditText(this);
        builder.setView(input);
        RadioButton rb1 = findViewById(R.id.rbi);
        RadioButton rb2 = findViewById(R.id.rbni);
        RadioButton rb3 = findViewById(R.id.rbu);
        RadioButton rb4 = findViewById(R.id.rbnu);
        // Add buttons for saving and cancelling
        builder.setPositiveButton("Save", (dialog, which) -> {
            String taskTitle = input.getText().toString();
            if (rb1.isChecked() && rb3.isChecked()) {
                Task newTask = new Task(taskTitle, 1);
                saveTask(newTask);

                taskListIU.add(newTask);
                taskAdapterIU.notifyDataSetChanged();
            }
            if (rb1.isChecked() && rb4.isChecked()) {
                Task newTask = new Task(taskTitle, 2);
                saveTask(newTask);

                taskListINU.add(newTask);
                taskAdapterINU.notifyDataSetChanged();
            }
            if (rb2.isChecked() && rb3.isChecked()) {
                Task newTask = new Task(taskTitle, 3);
                saveTask(newTask);

                taskListNIU.add(newTask);
                taskAdapterNIU.notifyDataSetChanged();
            }
            if (rb2.isChecked() && rb4.isChecked()) {
                Task newTask = new Task(taskTitle, 4);
                saveTask(newTask);

                taskListNINU.add(newTask);
                taskAdapterNINU.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", null);

        // Show dialog
        builder.show();
    }


//    private RecyclerView mImportantUrgentRecyclerView;
//    private RecyclerView mImportantNotUrgentRecyclerView;
//    private RecyclerView mNotImportantUrgentRecyclerView;
//    private RecyclerView mNotImportantNotUrgentRecyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tasks);
//
//        // Get the data for each square of the 4-Square Method
//        List<String> importantUrgentData = getImportantUrgentData();
//        List<String> importantNotUrgentData = getImportantNotUrgentData();
//        List<String> notImportantUrgentData = getNotImportantUrgentData();
//        List<String> notImportantNotUrgentData = getNotImportantNotUrgentData();
//
//        // Set up the RecyclerViews and their adapters
//        mImportantUrgentRecyclerView = findViewById(R.id.important_urgent_recycler_view);
//        mImportantUrgentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mImportantUrgentRecyclerView.setAdapter(new ImportantUrgentAdapter(importantUrgentData));
//
//        mImportantNotUrgentRecyclerView = findViewById(R.id.important_not_urgent_recycler_view);
//        mImportantNotUrgentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mImportantNotUrgentRecyclerView.setAdapter(new ImportantNotUrgentAdapter(importantNotUrgentData));
//
//        mNotImportantUrgentRecyclerView = findViewById(R.id.not_important_urgent_recycler_view);
//        mNotImportantUrgentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mNotImportantUrgentRecyclerView.setAdapter(new NotImportantUrgentAdapter(notImportantUrgentData));
//
//        mNotImportantNotUrgentRecyclerView = findViewById(R.id.not_important_not_urgent_recycler_view);
//        mNotImportantNotUrgentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mNotImportantNotUrgentRecyclerView.setAdapter(new NotImportantNotUrgentAdapter(notImportantNotUrgentData));
//    }
//
//    // Helper methods to get data for each square of the 4-Square Method
//    private List<String> getImportantUrgentData() {
//        List<String> data = new ArrayList<>();
//        data.add("Finish important report for work");
//        data.add("Schedule doctor's appointment for today");
//        data.add("Submit college application before deadline");
//        return data;
//    }
//
//    private List<String> getImportantNotUrgentData() {
//        List<String> data = new ArrayList<>();
//        data.add("Study for upcoming final exam");
//        data.add("Write thank-you notes to friends and family");
//        data.add("Start planning summer vacation");
//        return data;
//    }
//
//    private List<String> getNotImportantUrgentData() {
//        List<String> data = new ArrayList<>();
//        data.add("Answer non-urgent work emails");
//        data.add("Do laundry before running out of clean clothes");
//        data.add("Pick up groceries before store closes");
//        return data;
//    }
//
//    private List<String> getNotImportantNotUrgentData() {
//        List<String> data = new ArrayList<>();
//        data.add("Watch new movie on Netflix");
//        data.add("Scroll through social media feeds");
//        data.add("Play video games with friends");
//        return data;
//    }
}

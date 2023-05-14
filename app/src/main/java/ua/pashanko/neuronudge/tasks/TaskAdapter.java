package ua.pashanko.neuronudge.tasks;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ua.pashanko.neuronudge.R;
import ua.pashanko.neuronudge.TasksActivity;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> taskList;
    private Context context;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Task task = taskList.get(position);
        holder.tvTask.setText(task.getTask());
        holder.itemView.setOnClickListener(view -> {
            if (context instanceof TasksActivity) {
                ((TasksActivity) context).showEditDialog(task);
            }
        });
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTask;
        TextView tvTaskId;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTask = itemView.findViewById(R.id.tv_task);
        }
    }
}

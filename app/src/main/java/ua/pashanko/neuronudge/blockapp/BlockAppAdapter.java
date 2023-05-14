package ua.pashanko.neuronudge.blockapp;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ua.pashanko.neuronudge.R;

public class BlockAppAdapter extends RecyclerView.Adapter<BlockAppAdapter.ViewHolder> {

    private List<ApplicationInfo> appList;
    private List<String> blockedApps;

    public BlockAppAdapter(Context context, List<String> blockedApps) {
        PackageManager pm = context.getPackageManager();
        appList = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        this.blockedApps = blockedApps;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView appNameTextView;
        public CheckBox blockCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);
            appNameTextView = itemView.findViewById(R.id.app_name_textview);
            blockCheckBox = itemView.findViewById(R.id.block_checkbox);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ApplicationInfo appInfo = appList.get(position);
        holder.appNameTextView.setText(appInfo.loadLabel(holder.itemView.getContext().getPackageManager()));
        holder.blockCheckBox.setChecked(blockedApps.contains(appInfo.packageName));
        holder.blockCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                if (checkBox.isChecked()) {
                    blockedApps.add(appInfo.packageName);
                } else {
                    blockedApps.remove(appInfo.packageName);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public List<String> getBlockedApps() {
        return new ArrayList<>(blockedApps);
    }
}
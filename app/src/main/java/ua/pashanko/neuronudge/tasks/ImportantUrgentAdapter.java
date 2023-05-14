//package ua.pashanko.neuronudge.tasks;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//import ua.pashanko.neuronudge.R;
//
//public class ImportantUrgentAdapter extends RecyclerView.Adapter<ImportantUrgentAdapter.ViewHolder> {
//
//    private List<String> mData;
//
//    public ImportantUrgentAdapter(List<String> data) {
//        mData = data;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String task = mData.get(position);
//        holder.mTextView.setText(task);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView mTextView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            mTextView = itemView.findViewById(R.id.task_text_view);
//        }
//    }
//}

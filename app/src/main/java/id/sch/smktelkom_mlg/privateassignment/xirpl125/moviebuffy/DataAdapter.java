package id.sch.smktelkom_mlg.privateassignment.xirpl125.moviebuffy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Smktelkom on 0010, 5/10/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<DataItem> dataItemList;
    private Context context;

    public DataAdapter(List<DataItem> dataItemList, Context context) {
        this.dataItemList = dataItemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DataItem dataItem = dataItemList.get(position);
        holder.tvTitle.setText(dataItem.getTitle());
        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w500" + dataItem.getImageUrl())
                .into(holder.ivData);

        holder.rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dataItem.getTitle() + " selected", Toast.LENGTH_LONG).show();
                Intent singleBlogIntent = new Intent(context, DetailActivity.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                singleBlogIntent.putExtra("blog_id", position);
                context.startActivity(singleBlogIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivData;
        public TextView tvTitle;
        public TextView tvDesc;
        public RelativeLayout rLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ivData = (ImageView) itemView.findViewById(R.id.imageViewData);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            rLayout = (RelativeLayout) itemView.findViewById(R.id.LinearLayout);
        }
    }
}

package mafken.dotpict;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jensleirens on 17/08/2017.
 */

public class DrawingsAdapter extends RecyclerView.Adapter<DrawingsAdapter.DrawingsViewHolder> {

    private int itemCount;
    private List<Drawing> subjects;
    private DrawingsFragment.DrawingsOnclickListener listener;

    public DrawingsAdapter(List<Drawing> subjects, DrawingsFragment.DrawingsOnclickListener listener) {
        this.subjects = subjects;
        this.itemCount = subjects.size();
        this.listener = listener;
    }

    @Override
    public DrawingsAdapter.DrawingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_drawing, parent, false);
        v.setOnClickListener(listener);
        return new DrawingsAdapter.DrawingsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DrawingsAdapter.DrawingsViewHolder holder, final int position) {
        TextView id = holder.id;
        id.setText("Drawing number: " + String.valueOf(subjects.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class DrawingsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.drawingId)
        public TextView id;


        public DrawingsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

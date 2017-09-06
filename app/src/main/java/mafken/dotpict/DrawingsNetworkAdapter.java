package mafken.dotpict;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jensleirens on 18/08/2017.
 */

public class DrawingsNetworkAdapter extends RecyclerView.Adapter<DrawingsNetworkAdapter.DrawingsNetworkViewHolder> {

    private int itemCount;
    private List<Drawing> subjects;
    private DrawingsNetworkFragment.DrawingsOnclickListener listener;

    public DrawingsNetworkAdapter(List<Drawing> subjects, DrawingsNetworkFragment.DrawingsOnclickListener listener) {
        this.subjects = subjects;
        this.itemCount = subjects.size();
        this.listener = listener;
    }

    @Override
    public DrawingsNetworkAdapter.DrawingsNetworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_drawing, parent, false);
        v.setOnClickListener(listener);
        return new DrawingsNetworkAdapter.DrawingsNetworkViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DrawingsNetworkAdapter.DrawingsNetworkViewHolder holder, final int position) {
        TextView id = holder.id;
        id.setText(String.valueOf(subjects.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class DrawingsNetworkViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.drawingId)
        public TextView id;


        public DrawingsNetworkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

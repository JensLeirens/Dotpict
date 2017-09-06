package mafken.dotpict;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by jensleirens on 17/08/2017.
 */

public class CanvasAdapter extends BaseAdapter {
    private Context mContext;
    private int amountOfPixels = 0;

    public CanvasAdapter(Context c,int amountOfPixels) {
        mContext = c;
        this.amountOfPixels = amountOfPixels;
    }

    public int getCount() {
        return amountOfPixels;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(110, 110));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setBackgroundColor(Color.parseColor("#e2dee0"));
        GameFragment.images.set(position,new Pixel(ContextCompat.getColor(mContext,R.color.colorWhite)));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setBackground(GameFragment.currentColor);
                GameFragment.images.set(position,new Pixel(GameFragment.currentcolorValue));
            }
        });

        return imageView;
    }


}

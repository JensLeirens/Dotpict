package mafken.dotpict;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jensleirens on 17/08/2017.
 */

public class DrawingDetailFragment extends Fragment {

    @BindView(R.id.drawingGrid)
    GridView gridview;

    public static Drawing currentDrawing ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drawing_detail, container, false);
        ButterKnife.bind(this, v);

        if( DrawingsFragment.currentDrawing != null){
            currentDrawing = DrawingsFragment.currentDrawing ;
        } else if ( DrawingsNetworkFragment.currentDrawing != null ){
            currentDrawing = DrawingsNetworkFragment.currentDrawing ;
        }

        /*for(Pixel p : currentDrawing.getColors()){
            System.out.println(p.getColor());
        }*/

        DrawingDetailAdapter adapter = new DrawingDetailAdapter(getContext(),81);
        gridview.setAdapter(adapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}

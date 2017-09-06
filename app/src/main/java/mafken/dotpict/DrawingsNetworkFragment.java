package mafken.dotpict;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mafken.dotpict.network.Calls;
import mafken.dotpict.network.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jensleirens on 18/08/2017.
 */

public class DrawingsNetworkFragment extends Fragment {

    @BindView(R.id.drawings)
    RecyclerView drawingsRecycler;

    public static List<Drawing> drawings = new ArrayList<>();

    private RecyclerView.LayoutManager mLayoutManager;
    public static Drawing currentDrawing ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drawings, container, false);
        ButterKnife.bind(this, v);


        getDataFromBackend();

        mLayoutManager = new LinearLayoutManager(getActivity());
        drawingsRecycler.setLayoutManager(mLayoutManager);

        DrawingsNetworkAdapter drawingsAdapter = new DrawingsNetworkAdapter(drawings,new DrawingsNetworkFragment.DrawingsOnclickListener(getContext()));
        drawingsRecycler.setAdapter(drawingsAdapter);


        return v;
    }

    public void getDataFromBackend(){

        Calls caller = Config.getRetrofit().create(Calls.class);
        Call<List<Drawing>> call = caller.getDrawings();
        call.enqueue(new Callback<List<Drawing>>() {
            @Override
            public void onResponse(Call<List<Drawing>> call, Response<List<Drawing>> response) {
                drawings = response.body();
                System.out.println("succes");
                mLayoutManager = new LinearLayoutManager(getActivity());
                drawingsRecycler.setLayoutManager(mLayoutManager);

                DrawingsNetworkAdapter drawingsAdapter = new DrawingsNetworkAdapter(drawings,new DrawingsNetworkFragment.DrawingsOnclickListener(getContext()));
                drawingsRecycler.setAdapter(drawingsAdapter);
            }

            @Override
            public void onFailure(Call<List<Drawing>> call, Throwable t) {
                System.out.println("failure");
            }
        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public class DrawingsOnclickListener implements View.OnClickListener {

        private final Context context;

        public DrawingsOnclickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int pos;
            pos = drawingsRecycler.getChildAdapterPosition(v);

            currentDrawing = drawings.get(pos);

            for(Pixel p: currentDrawing.getColors()){
                System.out.println(p.getColor());
            }
            Fragment fragment = new DrawingDetailFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(fragment.toString());
            ft.commit();

        }
    }
}

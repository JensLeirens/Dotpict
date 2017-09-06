package mafken.dotpict;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mafken.dotpict.network.Calls;
import mafken.dotpict.network.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jensleirens on 17/08/2017.
 */

public class GameFragment extends Fragment{

    @BindView(R.id.canvasColorBlack)
    ImageButton blackButton;

    @BindView(R.id.canvasColorBlue)
    ImageButton blueButton;

    @BindView(R.id.canvasColorGreen)
    ImageButton greenButton;

    @BindView(R.id.canvasColorOrange)
    ImageButton orangeButton;

    @BindView(R.id.canvasColorPink)
    ImageButton pinkButton;

    @BindView(R.id.canvasColorRed)
    ImageButton redButton;
    
    @BindView(R.id.canvasColorWhite)
    ImageButton whiteButton;

    @BindView(R.id.canvasCurrentColor)
    ImageButton currentColorButton;
    
    @BindView(R.id.canvasGrid)
    GridView gridview;

    public static Drawable currentColor;
    public static int currentcolorValue;
    public static ArrayList<Pixel> images = new ArrayList<>();

    DaoSession daosesion;
    DrawingDao drawingDao;
    PixelDao pd;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        ButterKnife.bind(this, v);

        daosesion = ((App) getActivity().getApplication()).getDaoSession();
        drawingDao = daosesion.getDrawingDao();
        pd = daosesion.getPixelDao();

        currentColor = currentColorButton.getBackground();

        for (int i = 0; i < 81 ; i++) {
            images.add(new Pixel(-1));
        }

        CanvasAdapter adapter = new CanvasAdapter(getContext(),81);
        gridview.setAdapter(adapter);

        return v;
    }

    @OnClick(R.id.canvasSave)
    public void save(){
        ArrayList<Pixel> pixels = new ArrayList<>();
        Drawing drawing = new Drawing();
        drawing.setColor(images);

        //inserting drawing in greedao
        long drawingId = drawingDao.insert(drawing);
        Log.d("DaoFragment", "inserted:" + drawing.getId());

        //adding the pixels to a list and adding them with the drawing id to greendao
        for ( Pixel p : images){
            pixels.add(new Pixel(null,drawingId,p.getColor()));
        }
        pd.insertInTx(pixels);

        final Calls caller = Config.getRetrofit().create(Calls.class);
        Call<Drawing> call = caller.postDrawing(drawing);
        call.enqueue(new Callback<Drawing>() {
            @Override
            public void onResponse(Call<Drawing> call, Response<Drawing> response) {
                System.out.println("succes");
            }

            @Override
            public void onFailure(Call<Drawing> call, Throwable t) {
                System.out.println("failure " + t.getMessage());
            }
        });

        Toast.makeText(getContext(),"The drawing has been saved!", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.canvasColorBlack)
    public void clickedBlackButton(){
        currentColor = blackButton.getBackground();
        currentColorButton.setBackground(currentColor);
        currentcolorValue = ContextCompat.getColor(getContext(),R.color.colorBlack);
    }

    @OnClick(R.id.canvasColorBlue)
    public void clickedBlueButton(){
        currentColor = blueButton.getBackground();
        currentColorButton.setBackground(currentColor);
        currentcolorValue = ContextCompat.getColor(getContext(),R.color.colorBlue);
    }

    @OnClick(R.id.canvasColorGreen)
    public void clickedGreenButton(){
        currentColor = greenButton.getBackground();
        currentColorButton.setBackground(currentColor);
        currentcolorValue = ContextCompat.getColor(getContext(),R.color.colorGreen);
    }

    @OnClick(R.id.canvasColorOrange)
    public void clickedOrangeButton(){
        currentColor = orangeButton.getBackground();
        currentColorButton.setBackground(currentColor);
        currentcolorValue = ContextCompat.getColor(getContext(),R.color.colorOrange);
    }

    @OnClick(R.id.canvasColorPink)
    public void clickedPinkButton(){
        currentColor = pinkButton.getBackground();
        currentColorButton.setBackground(currentColor);
        currentcolorValue = ContextCompat.getColor(getContext(),R.color.colorPink);
    }

    @OnClick(R.id.canvasColorRed)
    public void clickedRedButton(){
        currentColor = redButton.getBackground();
        currentColorButton.setBackground(currentColor);
        currentcolorValue = ContextCompat.getColor(getContext(),R.color.colorRed);
    }

    @OnClick(R.id.canvasColorWhite)
    public void clickedWhiteButton(){
        currentColor = whiteButton.getBackground();
        currentColorButton.setBackground(currentColor);
        currentcolorValue = ContextCompat.getColor(getContext(),R.color.colorWhite);
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}

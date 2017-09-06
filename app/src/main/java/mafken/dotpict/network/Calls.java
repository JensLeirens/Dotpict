package mafken.dotpict.network;

import java.util.List;

import mafken.dotpict.Drawing;
import mafken.dotpict.Pixel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Path;

public interface Calls {

    @GET("/drawings")
    Call<List<Drawing>> getDrawings();

    @POST("/drawings/post")
    Call<Drawing> postDrawing(@Body Drawing drawing) ;





}

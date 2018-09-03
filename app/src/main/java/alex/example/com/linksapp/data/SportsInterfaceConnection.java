package alex.example.com.linksapp.data;

import alex.example.com.linksapp.data.entity.DetailsModel;
import alex.example.com.linksapp.data.entity.NewsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SportsInterfaceConnection {

    @GET("list.php")
    Call<NewsModel> getSportsList(@Query("category") String category);
    @GET("post.php")
    Call<DetailsModel> getDetailsPost(@Query("article") String article);
}

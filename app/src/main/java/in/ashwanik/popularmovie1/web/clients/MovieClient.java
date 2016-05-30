package in.ashwanik.popularmovie1.web.clients;

import in.ashwanik.popularmovie1.response.MovieResponse;
import in.ashwanik.popularmovie1.response.ReviewsAndTrailersResponse;
import in.ashwanik.popularmovie1.web.ApiUrls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public interface MovieClient {
    @GET(ApiUrls.MOVIE)
    Call<MovieResponse> getMovies(
            @Query("api_key") String apiKey,
            @Query("page") Integer page,
            @Query("sort_by") String sortBy);

    @GET(ApiUrls.REVIEWS_AND_TRAILERS)
    Call<ReviewsAndTrailersResponse> getMoviesAndTrailerResponse(
            @Path("id") int id,
            @Query("api_key") String apiKey,
            @Query("append_to_response") String appendToResponse);

}

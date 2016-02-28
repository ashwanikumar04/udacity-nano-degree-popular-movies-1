package in.ashwanik.popularmovie1.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.ashwanik.popularmovie1.entities.Movie;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class MovieResponse extends BaseResponse {
    @SerializedName("results")
    @Expose
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}

package in.ashwanik.popularmovie1.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.ashwanik.popularmovie1.entities.Reviews;
import in.ashwanik.popularmovie1.entities.Trailers;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class ReviewsAndTrailersResponse {
    @SerializedName("trailers")
    @Expose
    private Trailers trailers;
    @SerializedName("reviews")
    @Expose
    private Reviews reviews;

    /**
     * @return The trailers
     */
    public Trailers getTrailers() {
        return trailers;
    }

    /**
     * @param trailers The trailers
     */
    public void setTrailers(Trailers trailers) {
        this.trailers = trailers;
    }

    /**
     * @return The reviews
     */
    public Reviews getReviews() {
        return reviews;
    }

    /**
     * @param reviews The reviews
     */
    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }
}

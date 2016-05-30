package in.ashwanik.popularmovie1.events;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class FloatingActionButtonClickEvent {

    int sortType;

    public FloatingActionButtonClickEvent(int sortType) {
        this.sortType = sortType;
    }

    public int getSortType() {
        return sortType;
    }
}

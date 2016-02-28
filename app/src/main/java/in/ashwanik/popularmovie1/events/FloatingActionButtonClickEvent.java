package in.ashwanik.popularmovie1.events;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class FloatingActionButtonClickEvent {

    int clickedButtonId;

    public FloatingActionButtonClickEvent(int buttonId) {
        this.clickedButtonId = buttonId;
    }

    public int getClickedButtonId() {
        return clickedButtonId;
    }
}

package core.components;

import database.models.Schedule;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jooq.Record;

/**
 * Created by ibnujakaria on 21/03/17.
 */
public class ScheduleComponent extends VBox {
    private Record schedule;
    private Label titleLabel, descriptionLabel, dateLabel, timeLabel, ustadzLabel;

    public ScheduleComponent (Record schedule) {
        this.schedule = schedule;
        prepareLayout();
        getChildren().addAll(titleLabel, descriptionLabel, dateLabel, timeLabel, ustadzLabel);
    }

    private void prepareLayout() {
        titleLabel = new Label((String) schedule.get("title"));
        descriptionLabel = new Label((String) schedule.get("description"));
        dateLabel = new Label(Schedule.getDateLabel(schedule));
        timeLabel = new Label((String) schedule.get("start_at"));
        ustadzLabel = new Label(Schedule.getUstadz(schedule));
    }


}

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
    private Label titleLabel, descriptionLabel, dateLabel, ustadzLabel;
    String time,date;

    public ScheduleComponent (Record schedule) {
        this.schedule = schedule;
        prepareLayout();
        getChildren().addAll(titleLabel, dateLabel, ustadzLabel, descriptionLabel);
    }

    private void prepareLayout() {
        dateLabel = new Label();
        dateLabel.setStyle("-fx-font-size: 13px;");
        time = "jam "+schedule.get("start_at").toString();
        date = "" +Schedule.getDateLabel(schedule).toString();
        dateLabel.setText(date+" "+time);
        titleLabel = new Label((String) schedule.get("title"));
        descriptionLabel = new Label((String) schedule.get("description"));
        descriptionLabel.setStyle("-fx-font-size: 19px;");
        ustadzLabel = new Label("oleh Ustadz "+Schedule.getUstadz(schedule));
        ustadzLabel.setStyle("-fx-font-size: 17px;");
    }


}

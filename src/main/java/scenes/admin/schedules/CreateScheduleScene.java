package scenes.admin.schedules;

import com.jfoenix.controls.*;
import core.components.KeyValueLabelComponent;
import database.models.Schedule;
import database.models.User;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;
import scenes.admin.schedules.pieces.PeriodicScheduleComboBox;

/**
 * Created by ibnujakaria on 19/03/17.
 */
public class CreateScheduleScene extends MyGroup {
    private JFXButton backButton, submitButton;
    private JFXTextField titleField, ustadzField;
    private JFXComboBox ustadzSpinner;
    private JFXDatePicker datePicker;
    private JFXTextArea descriptionField;
    private PeriodicScheduleComboBox periodicSpinner;
    private JFXCheckBox ustadzTamuCheckbox;
    private boolean ustadzTamu = false;

    @Override
    protected void prepareLayout() {
        VBox vBox = new VBox();
        Label label = new Label("Tambah Jadwal Kajian");
        backButton = new JFXButton("Back");
        submitButton = new JFXButton("Submit");

        titleField = new JFXTextField();
        titleField.setPromptText("Judul kajian");

        ustadzField = new JFXTextField();
        ustadzField.setPromptText("Nama ustadz tamu");
        ustadzField.setVisible(ustadzTamu);

        ustadzSpinner = new JFXComboBox();
        ustadzSpinner.setPromptText("Pilih ustadz");
        loadDataUstadz();

        ustadzTamuCheckbox = new JFXCheckBox("Ustadz Tamu");
        periodicSpinner = new PeriodicScheduleComboBox();
        datePicker = new JFXDatePicker();
        datePicker.setPromptText("Waktu mulai");
        datePicker.setShowTime(true);

        descriptionField = new JFXTextArea();
        descriptionField.setPromptText("Deskripsi kajian");

        vBox.getChildren().addAll(backButton, label, titleField, ustadzTamuCheckbox, ustadzSpinner, ustadzField,
                descriptionField, periodicSpinner, datePicker, submitButton);
        getChildren().addAll(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        ustadzTamuCheckbox.setOnAction(event -> {
            ustadzTamu = ustadzTamuCheckbox.isSelected();
            ustadzSpinner.setVisible(!ustadzTamuCheckbox.isSelected());
            ustadzField.setVisible(ustadzTamu);
        });

        submitButton.setOnAction(event -> {
            String selectedUstadz = null;
            if (!ustadzTamu)
                selectedUstadz = ((KeyValueLabelComponent) ustadzSpinner.getValue()).getKey().toString();

            String startAt = "";

            if (datePicker != null && datePicker.getTime() != null) {
                startAt = datePicker.getTime().getHour() + ":" + datePicker.getTime().getMinute();
            }
            Schedule.insert(
                    titleField.getText(), ustadzTamu ? 0 : Integer.parseInt(selectedUstadz),
                    (ustadzTamu ? ustadzField.getText() : null), descriptionField.getText(),
                    periodicSpinner.getPeriodic(), periodicSpinner.getIntervalBy(),
                    periodicSpinner.getSubIntervalBy(), periodicSpinner.getInterval(),
                    startAt, periodicSpinner.getExactDate()
            );

            movePreviousScene();
        });
    }

    private void loadDataUstadz () {
        ustadzSpinner.getItems().clear();
        for (Record ustadz : User.getUstadz()) {
            ustadzSpinner.getItems().add(new KeyValueLabelComponent(ustadz.get("id").toString(), ustadz.get("name").toString()));
        }
    }
}

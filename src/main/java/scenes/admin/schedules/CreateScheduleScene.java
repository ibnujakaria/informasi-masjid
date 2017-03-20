package scenes.admin.schedules;

import com.jfoenix.controls.*;
import database.models.User;
import javafx.scene.control.ComboBox;
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
    private JFXComboBox ustadzSpinner, periodicSpinner, intervalSpinner;
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
        periodicSpinner.setPromptText("Periode");

        vBox.getChildren().addAll(backButton, label, titleField, ustadzTamuCheckbox, ustadzSpinner,
                ustadzField, periodicSpinner, submitButton);
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
    }

    private void loadDataUstadz () {
        ustadzSpinner.getItems().clear();
        for (Record ustadz : User.getUstadz()) {
            ustadzSpinner.getItems().add(new Label(ustadz.get("name").toString()));
        }
    }
}

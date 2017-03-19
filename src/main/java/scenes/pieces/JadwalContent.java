package scenes.pieces;

import com.jfoenix.controls.JFXListView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by abdullah on 17/03/17.
 */
public class JadwalContent extends JFXListView<VBox> {
    VBox jadwal[];
    public JadwalContent() {
//        for(int i = 0 ; i < 4 ; i++) getItems().add(new VBox(new Label("Item"+i)));

        jadwal = new VBox[4];
        for (int i = 0; i < 4; i++) {
            jadwal[i] = new VBox(new Label("item"+i));
            jadwal[i].setPadding(new Insets(10, 10, 10, 10));
            getItems().add(jadwal[i]);
        }

        jadwal[2].setOnMouseClicked(event -> {
            System.out.println("jadwal clicked"+2);
        });

    }
}

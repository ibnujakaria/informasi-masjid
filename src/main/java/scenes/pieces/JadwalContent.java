package scenes.pieces;

import com.jfoenix.controls.JFXListView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by abdullah on 17/03/17.
 */
public class JadwalContent extends JFXListView<VBox> {
    public JadwalContent() {
        for(int i = 0 ; i < 4 ; i++) getItems().add(new VBox(new Label("Item"+i)));

    }
}

package scenes;

import app.Main;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public abstract class MyGroup extends Group{

    protected MyGroup nextScene, previousScene;

    protected abstract void prepareLayout();
    protected abstract void addListeners();

    public MyGroup() {
        System.out.println("INSTANCE BARU: " + this.getClass().toString());

        prepareLayout();
        addListeners();
    }

    protected void onAfterNext() {

    }

    protected void onAfterBack() {

    }

    public void refreshContent () {

    }

    public void moveNextScene() {
        if (nextScene == null) {
            return;
        }

        Main.primaryStage.getScene().getRoot().setLayoutY(0);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(Main.primaryStage.getScene().getRoot());
        translateTransition.setByY(-Main.primaryStage.getHeight()/2);

        translateTransition.play();

        translateTransition.setOnFinished(event -> {
            Main.primaryStage.getScene().setRoot(nextScene);
            nextScene.onAfterNext();
        });
    }

    public void movePreviousScene() {
        if (previousScene == null) {
            return;
        }

        Main.primaryStage.getScene().setRoot(previousScene);
        previousScene.onAfterBack();
        previousScene.setLayoutY(-Main.primaryStage.getHeight()/2);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(Main.primaryStage.getScene().getRoot());
        translateTransition.setByY(Main.primaryStage.getHeight()/2);

        translateTransition.play();
    }

    public MyGroup getNextScene() {
        return nextScene;
    }

    public void setNextScene(MyGroup nextScene) {
        nextScene.setPreviousScene(this);
        this.nextScene = nextScene;
    }

    public MyGroup getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(MyGroup previousScene) {
        this.previousScene = previousScene;
    }

}

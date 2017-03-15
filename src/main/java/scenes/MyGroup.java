package scenes;

import app.Main;
import javafx.scene.Group;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public abstract class MyGroup extends Group{

    protected MyScene view;
    protected MyGroup nextScene, previousScene;

    protected abstract void prepareLayout();
    protected abstract void addListeners();
    protected abstract void onRender();

    public MyGroup() {
        view = new MyScene(this, MyScene.width, MyScene.height);
        prepareLayout();
        onRender();
        addListeners();
    }

    public void moveNextScene() {
        if (nextScene == null) {
            System.out.println("not moving to next scene");
            return;
        }

        Main.primaryStage.setScene(nextScene.getView());
        nextScene.onRender();
        System.out.println("move to next scene: " + nextScene.getClass().toString());
    }

    public void movePreviousScene() {
        if (previousScene == null) {
            System.out.println("not moving to previous scene");
            return;
        }

        Main.primaryStage.setScene(previousScene.getView());
        previousScene.onRender();
        System.out.println("move to previousscene: " + previousScene.getClass().toString());
    }

    public MyGroup getNextScene() {
        return nextScene;
    }

    public void setNextScene(MyGroup nextScene) {
        System.out.println("set next scene: " + nextScene.getClass().toString());
        nextScene.setPreviousScene(this);
        this.nextScene = nextScene;
    }

    public MyGroup getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(MyGroup previousScene) {
        System.out.println("set previous scene: " + previousScene.getClass().toString());
        this.previousScene = previousScene;
    }

    public MyScene getView() {
        return view;
    }

    public void setView(MyScene view) {
        this.view = view;
    }
}

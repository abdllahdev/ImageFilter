package gui.imagefilter;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

public class Controller {
    public ImageView imgView;
    public MenuBar menuBar;
    public Menu tools;
    public MenuItem revert;
    public MenuItem revertAll;
    public MenuItem close;

    private ArrayList<Image> images = new ArrayList<>();

    public void handleOpenImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        File file = fileChooser.showOpenDialog(menuBar.getScene().getWindow());

        if (file != null) {
            this.images.add(new Image("file:" + file.getPath()));
            this.imgView.setImage(this.images.get(0));

            this.close.setDisable(false);

            for (MenuItem item: this.tools.getItems()) {
                item.setDisable(false);
            }
        }
    }

    public void handleCloseImage(ActionEvent actionEvent) {
        imgView.setImage(null);
        this.revert.setDisable(true);
        this.close.setDisable(true);
    }

    public void handleFilter(ActionEvent actionEvent) {
        Filter filter = FilterFactory.createFilter(((MenuItem)actionEvent.getSource()).getId());

        System.out.println(((MenuItem)actionEvent.getSource()).getId());

        Image image = filter.filterImage(this.imgView.getImage());

        this.images.add(image);
        this.imgView.setImage(this.images.get(this.images.size()-1));
        this.revert.setDisable(false);
        this.revertAll.setDisable(false);
    }

    public void handleRevert(ActionEvent actionEvent) {
        this.images.remove(this.images.size()-1);
        this.imgView.setImage(this.images.get(this.images.size()-1));

        if (this.images.size() <= 1) {
            this.revert.setDisable(true);
            this.revertAll.setDisable(true);
        }
    }

    public void handleRevertAll(ActionEvent actionEvent) {
        for (int i=1; i<this.images.size(); i++) {
            this.images.remove(i);
        }

        this.imgView.setImage(this.images.get(0));
        this.revert.setDisable(true);
        this.revertAll.setDisable(true);
    }
}

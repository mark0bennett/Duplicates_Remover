package alex220mark;

import java.io.File;
import java.util.List;
import java.util.Map;

import alex220mark.models.Item;
import alex220mark.utils.CsvReaderWriter;
import alex220mark.utils.DuplicateRemover;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DuplicatesRemoverApp extends Application {

	String selectedFilePath = "";
	Label statusLabel = new Label("");

	// TODO: dont print errors to console, print them to the stage/window
	// TODO: it will overwrite some csv's if it can read them in - should check that
	// ONLY 2 columns are filled
	// TODO: exception if you hit submit without choosing a file
	// TODO: has to be name - number, display the error if you try a file that is
	// number - name
	// TODO: add an example of correct format for a csv 
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FileChooser fileChooser = new FileChooser();

		Button button = new Button("Select File To Remove Duplicates From");
		Label fileLabel = new Label("No File Chosen");
		Button submitButton = new Button("Submit");

		button.setOnAction(e -> {
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			try {
				selectedFilePath = selectedFile.getAbsolutePath();
			} catch (NullPointerException exception) {
			}
			fileLabel.setText(selectedFilePath);
		});

		submitButton.setOnAction(e -> {
			List<Item> readListFromFile = CsvReaderWriter.readCsvFile(selectedFilePath);
			if (readListFromFile.isEmpty()) {
				statusLabel.setTextFill(Color.RED);
				statusLabel.setText("Invalid file, pick a .csv file and check format of csv file");
			} else {

				Map<String, Integer> finalMapFromReadListFromFile = DuplicateRemover
						.convertListToMapAndRemoveDuplicates(readListFromFile);

				CsvReaderWriter.exportCsvFile(finalMapFromReadListFromFile);
				statusLabel.setTextFill(Color.BLACK);
				statusLabel.setText("DONE");
			}
		});

		VBox vBox = new VBox(button, fileLabel, submitButton, statusLabel);
		vBox.setAlignment(Pos.CENTER);
		vBox.setStyle("-fx-background-color: lightblue;");
		Scene scene = new Scene(vBox, 600, 300);
		primaryStage.setTitle("Duplicate Remover App");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}

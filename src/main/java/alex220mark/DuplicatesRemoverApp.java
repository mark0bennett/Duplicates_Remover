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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DuplicatesRemoverApp extends Application {

	String selectedFilePath = "";
	Label statusLabel = new Label("");

	// TODO: dont print errors to console, print them to the stage/window
	// TODO: exception if you hit submit without choosing a file
	// TODO: has to be name - number, display the error if you try a file that is
	// number - name
	// TODO: add an example of correct format for a csv
	// TODO: target - META-INF pom.properties keeps updating time and date?
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FileChooser fileChooser = new FileChooser();

		Button button = new Button("Select File To Remove Duplicates From");
		Label fileLabel = new Label("No File Chosen");
		Button submitButton = new Button("Submit");
		Button backButton = new Button("Back");

		// intro screen setup
		Button continueButton = new Button("Continue");
		Label introLabel = new Label("CORRECT FORMAT FOR CSV FILE");
		Label formatExplanationLabel = new Label("Only 2 columns, NAME in column A and NUMBER in column B!");
		// intro screen image
		// TODO: not have an explicit file path, reference to the class path only?
		Image correctFormatImage = new Image(
				"D:\\Java\\Spring Tools Suite\\Projects\\Duplicates_Remover\\Correct_Format.JPG");
		ImageView imageView = new ImageView(correctFormatImage);

		// duplicates remover screen setup
		VBox removeDuplicatesVBox = new VBox(button, fileLabel, submitButton, statusLabel, backButton);
		removeDuplicatesVBox.setAlignment(Pos.CENTER);
		removeDuplicatesVBox.setStyle("-fx-background-color: lightblue;");
		Scene removeDuplicatesScene = new Scene(removeDuplicatesVBox, 600, 600);

		// intro screen setup
		VBox introVBox = new VBox(introLabel, formatExplanationLabel, imageView, continueButton);
		introVBox.setAlignment(Pos.CENTER);
		introVBox.setSpacing(20);
		introVBox.setStyle("-fx-background-color: lightgray;");
		Scene introScene = new Scene(introVBox, 600, 600);

		// intro screen button
		continueButton.setOnAction(e -> {
			primaryStage.setScene(removeDuplicatesScene);
		});

		// duplicates screen buttons
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

		backButton.setOnAction(e -> {
			primaryStage.setScene(introScene);
		});

		primaryStage.setScene(introScene);
		primaryStage.show();

		primaryStage.setTitle("Duplicate Remover App");

	}

}

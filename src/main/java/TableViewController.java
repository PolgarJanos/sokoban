import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TableViewController {

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Country, String> code;

    @FXML
    private TableColumn<Country, String> name;

    @FXML
    private TableColumn<Country, String> capital;

    @FXML
    private TableColumn<Country, Integer> population;

    @FXML
    private TableColumn<Country, Integer> numberOfTimezones;

    @FXML
    private void initialize() throws IOException {
        String filePath;
        StringBuilder stringBuilder = new StringBuilder();
        filePath = stringBuilder.append(System.getProperty("user.home")).append(File.separator).append(".sokoban").toString();
        File file = new File(filePath);
        if (file.exists()) {
            Logger.info("Already exists {}",file.getAbsolutePath());
        } else {
           Logger.info("Creating folder... {}",file.getAbsolutePath());
            file.mkdirs();
            if (file.exists()) {
                Logger.info("Folder is ready {}",file.getAbsolutePath());
            }
        }

        filePath= stringBuilder.append(File.separator).append("resultList.json").toString();
        file = new File(filePath);
        if (file.exists()) {
            Logger.info("File exists {}",file.getAbsolutePath());
        } else {
            Logger.info("Creating file {}",file.getAbsolutePath());
            file.createNewFile();
            if (file.exists()) {
                Logger.info("Created file {}",file.getAbsolutePath());
            }
        }

        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        capital.setCellValueFactory(new PropertyValueFactory<>("capital"));
        population.setCellValueFactory(new PropertyValueFactory<>("population"));
        numberOfTimezones.setCellValueFactory(
                cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getTimezones().size()).asObject()
        );
        List<Country> countries = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .readValue(TableViewController.class.getResourceAsStream("/countries.json"), new TypeReference<List<Country>>() {});
        ObservableList<Country> observableList = FXCollections.observableArrayList();
        observableList.addAll(countries);
        tableView.setItems(observableList);
    }



}

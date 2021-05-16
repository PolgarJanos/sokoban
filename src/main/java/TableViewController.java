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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class TableViewController {

    @FXML
    private TableView tableView;



    @FXML
    private TableColumn<Country, String> name;



    @FXML
    private void initialize() throws IOException {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        String filePath;
        StringBuilder stringBuilder = new StringBuilder();
        filePath = stringBuilder.append(System.getProperty("user.home")).append(File.separator).append(".sokoban").append(File.separator).append("resultList.json").toString();

        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        List<Country> countries = new LinkedList<>();
        if(inputStream.available()>0) {
            countries = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .readValue(inputStream, new TypeReference<List<Country>>() {
                    });
        }
        ObservableList<Country> observableList = FXCollections.observableArrayList();
        observableList.addAll(countries);
        tableView.setItems(observableList);
    }

}

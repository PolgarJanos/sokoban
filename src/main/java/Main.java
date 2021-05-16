

import javafx.application.Application;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
//toDo repackaging

public class Main {

    public static void main(String[] args) throws IOException {
        String filePath;
        StringBuilder stringBuilder = new StringBuilder();
        filePath = stringBuilder.append(System.getProperty("user.home")).append(File.separator).append(".sokoban").toString();
        File file = new File(filePath);
        if (file.exists()) {
            Logger.info("Already exists {}", file.getAbsolutePath());
        } else {
            Logger.info("Creating folder... {}", file.getAbsolutePath());
            file.mkdirs();
            if (file.exists()) {
                Logger.info("Folder is ready {}", file.getAbsolutePath());
            }
        }
        filePath = stringBuilder.append(File.separator).append("resultList.json").toString();
        file = new File(filePath);
        if (file.exists()) {
            Logger.info("File exists {}", file.getAbsolutePath());
        } else {
            Logger.info("Creating file {}", file.getAbsolutePath());
            file.createNewFile();
            }
            if (file.exists()) {
                Logger.info("Created file {}", file.getAbsolutePath());
            }

        Application.launch(SokobanGameApplication.class, args);
    }

}

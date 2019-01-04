package core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileUtil {

    private static final String PATH_TO_STORE_RESULTS = System.getProperty("user.home");
    private static final String RESULTS_FOLDER = "bme_results";
    private static final String ABSOLUTE_PATH = PATH_TO_STORE_RESULTS + File.separator + RESULTS_FOLDER + File.separator;

    private FileUtil() { }

    public static boolean isFileExists(String path) {
        return Files.exists(Paths.get(path));
    }

    public static void createResultFolder() {
        if (!isFileExists(ABSOLUTE_PATH)) {
            try {
                Files.createDirectory(Paths.get(ABSOLUTE_PATH));
            } catch (IOException e) {
                throw new IllegalArgumentException("Could not create folder ", e);
            }
        }
    }

    public static File createResultsFile() {
        String fileName = "bme_results" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MMM.yyyy")) + ".csv";
        try {
            File file = new File(ABSOLUTE_PATH + fileName);
            if (isFileExists(file.getPath())) {
                Logger.out.warn(String.format("File with %s name already exists", fileName));
                return file;
            }
            file.createNewFile();
            return file;
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    String.format("Could not create %s file with %s path", fileName, ABSOLUTE_PATH), e);
        }
    }
}
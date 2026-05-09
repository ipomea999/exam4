package util;

import models.Cat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("src/data/cats.json");

    public static List<Cat> readCats() {
        try {
            if (!Files.exists(PATH)) {
                return new ArrayList<>();
            }

            String read = Files.readString(PATH);
            Cat[] catsArray = GSON.fromJson(read, Cat[].class);

            if (catsArray == null) {
                return new ArrayList<>();
            }

            return new ArrayList<>(Arrays.asList(catsArray));

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void writeCats(List<Cat> cats) {
        String newJson = GSON.toJson(cats);
        try {
            Files.writeString(PATH, newJson);
        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}
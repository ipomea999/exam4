package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Cat;

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
            String json = Files.readString(PATH);
            Cat[] catsArray = GSON.fromJson(json, Cat[].class);
            return catsArray == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(catsArray));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void writeCats(List<Cat> cats) {
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, GSON.toJson(cats));
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
        }
    }
}
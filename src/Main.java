import models.Cat;
import util.FileUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Cat> cats = FileUtil.readCats();

        printSortedTable(cats);

        Scanner scanner = new Scanner(System.in);
        addNewCat(scanner, cats);

        FileUtil.writeCats(cats);
        System.out.println("\nОбновленная таблица:");
        printSortedTable(cats);
    }

    public static void addNewCat(Scanner scanner, List<Cat> cats) {
        String name;
        while (true) {
            System.out.print("Введите имя нового кота: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty() || name.length() < 2 || name.length() > 15) {
                System.out.println("Ошибка! Имя не может быть пустым и должно содержать от 2 до 15 символов.");
            } else {
                break;
            }
        }

        int age;
        while (true) {
            System.out.print("Введите возраст кота (от 1 до 18): ");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age < 1 || age > 18) {
                    System.out.println("Ошибка! Возраст должен быть в диапазоне от 1 до 18.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число.");
            }
        }

        cats.add(new Cat(name, age));
    }

    public static void printSortedTable(List<Cat> cats) {
        List<Cat> sortedCats = cats.stream()
                .sorted(Comparator.comparingInt(Cat::getAverageLevel).reversed())
                .collect(Collectors.toList());

        System.out.println("+----+------------+---------+----------+------------+---------+-----------------+");
        System.out.println("| #  | Имя        | Возраст | Здоровье | Настроение | Сытость | Средний уровень |");
        System.out.println("+----+------------+---------+----------+------------+---------+-----------------+");

        int index = 1;
        for (Cat cat : sortedCats) {
            System.out.printf("| %-2d | %-10s | %-7d | %-8d | %-10d | %-7d | %-15d |\n",
                    index,
                    cat.getName(),
                    cat.getAge(),
                    cat.getHealth(),
                    cat.getMood(),
                    cat.getSatiety(),
                    cat.getAverageLevel()
            );
            index++;
        }
        System.out.println("+----+------------+---------+----------+------------+---------+-----------------+");
    }
}
import models.Cat;
import strategies.CatActionStrategy;
import strategies.FeedStrategy;
import strategies.HealStrategy;
import strategies.PlayStrategy;
import util.FileUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Cat> cats = FileUtil.readCats();

        if (cats.isEmpty()) {
            cats.add(new Cat("Peach", 11, 50, 86, 78));
            cats.add(new Cat("Jasper", 12, 43, 39, 83));
            cats.add(new Cat("Poppy", 9, 55, 71, 38));
            FileUtil.writeCats(cats);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Cat> sortedCats = cats.stream()
                    .sorted(Comparator.comparingInt(Cat::getAverageLevel).reversed())
                    .collect(Collectors.toList());

            printTable(sortedCats);

            System.out.println("1: покормить");
            System.out.println("2: поиграть");
            System.out.println("3: к ветеринару");
            System.out.println("а: завести нового питомца");
            System.out.println("0: выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                break;
            }

            if (choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("а")) {
                addNewCat(scanner, cats);
                FileUtil.writeCats(cats);
                continue;
            }

            CatActionStrategy strategy = null;
            String actionMessage = "";

            switch (choice) {
                case "1":
                    strategy = new FeedStrategy();
                    actionMessage = "покормили кота";
                    break;
                case "2":
                    strategy = new PlayStrategy();
                    actionMessage = "поиграли с котом";
                    break;
                case "3":
                    strategy = new HealStrategy();
                    actionMessage = "отвезли к ветеринару кота";
                    break;
                default:
                    System.out.println("Неверный ввод, попробуйте снова.\n");
                    continue;
            }

            System.out.print("Введите номер кота (1-" + sortedCats.size() + "): ");
            int catIndex;
            try {
                catIndex = Integer.parseInt(scanner.nextLine().trim());
                if (catIndex < 1 || catIndex > sortedCats.size()) {
                    System.out.println("Кота с таким номером нет.\n");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода номера.\n");
                continue;
            }

            Cat selectedCat = sortedCats.get(catIndex - 1);
            strategy.performAction(selectedCat);
            FileUtil.writeCats(cats);

            System.out.println("\nВы " + actionMessage + " " + selectedCat.getName() + ", возраст: " + selectedCat.getAge() + "\n");
        }
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

    public static void printTable(List<Cat> cats) {
        System.out.println("+----+------------+---------+----------+------------+---------+-----------------+");
        System.out.println("| #  | Имя        | Возраст | Здоровье | Настроение | Сытость | Средний уровень |");
        System.out.println("+----+------------+---------+----------+------------+---------+-----------------+");

        int index = 1;
        for (Cat cat : cats) {
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
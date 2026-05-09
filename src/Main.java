import models.Cat;
import strategies.*;
import util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static Comparator<Cat> currentComparator = Comparator.comparingInt(Cat::getAverageLevel).reversed();

    public static void main(String[] args) {
        List<Cat> cats = FileUtil.readCats();

        if (cats.isEmpty()) {
            cats.add(new Cat("Peach", 11, 50, 86, 78));
            cats.add(new Cat("Jasper", 12, 43, 39, 83));
            cats.add(new Cat("Poppy", 9, 55, 71, 38));
            FileUtil.writeCats(cats);
        }

        Scanner scanner = new Scanner(System.in);
        Map<String, CatActionStrategy> actions = Map.of(
                "1", new FeedStrategy(),
                "2", new PlayStrategy(),
                "3", new HealStrategy()
        );

        while (true) {
            List<Cat> sortedCats = cats.stream()
                    .sorted(currentComparator)
                    .collect(Collectors.toList());

            printTable(sortedCats);

            System.out.println("1: покормить | 2: поиграть | 3: к ветеринару | 4: следующий день | а: новый питомец | s: сортировка | 0: выход");
            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("0")) break;

            if (choice.equals("a") || choice.equals("а")) {
                addNewCat(scanner, cats);
                FileUtil.writeCats(cats);
                continue;
            }

            if (choice.equals("s") || choice.equals("ы")) {
                updateComparator(scanner);
                continue;
            }

            if (choice.equals("4")) {
                cats.forEach(Cat::nextDay);
                System.out.println("\nНаступил следующий день! Характеристики всех питомцев изменились.\n");
                removeDeadCats(cats);
                FileUtil.writeCats(cats);
                continue;
            }

            Optional.ofNullable(actions.get(choice)).ifPresentOrElse(
                    strategy -> processAction(scanner, sortedCats, cats, strategy),
                    () -> System.out.println("Неверный ввод.\n")
            );
        }
    }

    private static void updateComparator(Scanner scanner) {
        System.out.println("Сортировать по: 1 - имя, 2 - возраст, 3 - здоровье, 4 - настроение, 5 - сытость, 6 - средний уровень");
        System.out.print("Выбор: ");
        String sortChoice = scanner.nextLine().trim();
        currentComparator = switch (sortChoice) {
            case "1" -> Comparator.comparing(Cat::getName);
            case "2" -> Comparator.comparingInt(Cat::getAge);
            case "3" -> Comparator.comparingInt(Cat::getHealth);
            case "4" -> Comparator.comparingInt(Cat::getMood);
            case "5" -> Comparator.comparingInt(Cat::getSatiety);
            default -> Comparator.comparingInt(Cat::getAverageLevel).reversed();
        };
    }

    private static void removeDeadCats(List<Cat> cats) {
        List<Cat> deadCats = cats.stream().filter(c -> c.getHealth() <= 0).toList();
        deadCats.forEach(c -> System.out.println(c.getName() + " умер"));
        cats.removeAll(deadCats);
    }

    private static void processAction(Scanner scanner, List<Cat> sortedCats, List<Cat> allCats, CatActionStrategy strategy) {
        System.out.print("Введите номер кота (1-" + sortedCats.size() + "): ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index >= 0 && index < sortedCats.size()) {
                Cat selectedCat = sortedCats.get(index);

                if (selectedCat.hasActedToday()) {
                    System.out.println("\nС этим котом уже выполняли действие сегодня! Ждите следующего дня.\n");
                } else {
                    String resultMessage = strategy.performAction(selectedCat);
                    selectedCat.markActionDone();
                    System.out.println("\nВы " + resultMessage + "\n");
                    removeDeadCats(allCats);
                    FileUtil.writeCats(allCats);
                }
            } else {
                System.out.println("Нет такого номера.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Введите число!\n");
        }
    }

    private static void addNewCat(Scanner scanner, List<Cat> cats) {
        System.out.print("Имя: ");
        String name = scanner.nextLine().trim();
        System.out.print("Возраст: ");
        try {
            int age = Integer.parseInt(scanner.nextLine().trim());
            if (name.length() >= 2 && age >= 1 && age <= 18) {
                cats.add(new Cat(name, age));
            } else {
                System.out.println("Данные некорректны.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка формата.");
        }
    }

    private static void printTable(List<Cat> cats) {
        System.out.println("+----+--------------+---------+----------+------------+---------+----------+");
        System.out.println("| #  | Имя          | Возраст | Здоровье | Настроение | Сытость | Средний  |");
        System.out.println("+----+--------------+---------+----------+------------+---------+----------+");

        IntStream.range(0, cats.size()).forEach(i -> {
            Cat c = cats.get(i);
            String nameColumn = (c.hasActedToday() ? "* " : "  ") + c.getName();
            System.out.printf("| %-2d | %-12s | %-7d | %-8d | %-10d | %-7d | %-8d |\n",
                    (i + 1), nameColumn, c.getAge(), c.getHealth(), c.getMood(), c.getSatiety(), c.getAverageLevel());
        });

        System.out.println("+----+--------------+---------+----------+------------+---------+----------+");
    }
}
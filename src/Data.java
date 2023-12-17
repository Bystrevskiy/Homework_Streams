import com.sun.source.util.SourcePositions;

import java.util.*;
import java.util.stream.Collectors;
public class Data {
    public static void main(String[] args) {


        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        List<Transaction> transactions2011 = transactions.stream()
                .filter(t-> t.getYear() == 2011 )
                .collect(Collectors.toList());

        System.out.println("Список транзакций после фильтрации для 2011 года:");
        for (Transaction transaction : transactions2011) {
            System.out.println(transaction);
        }
        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.

        List<String>uniqueCity = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("\nсписок неповторяющихся городов, в которых работают трейдеры.");
        uniqueCity.forEach(System.out::println);

       //Найти всех трейдеров из Кембриджа и отсортировать их по именам.

       List<Trader> cambNames = transactions.stream()
               .map(t -> t.getTrader()).distinct()
               .filter(t -> t.getCity().equals("Cambridge"))
               .sorted()
               .collect(Collectors.toList());
        System.out.println("\nсписок трейдеров из Кембриджа.");
        cambNames.forEach(System.out::println);

        //4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
        //порядке.

        String abcSort = transactions.stream()
                .map(t -> t.getTrader())
                .map(t-> t.getName())
                .sorted()
                .collect(Collectors.joining(". "));
        System.out.println("\nИмена трейдеров в алфавитном порядке");
        cambNames.forEach(System.out::print);

        boolean existTraderFromMilan = transactions.stream()
                .map(t->t.getTrader())
                .map(t -> t.getCity())
                .anyMatch("Milan"::equals);

        System.out.println(existTraderFromMilan ? "\n\nСуществует трейдер из Милана" : "Не существует трейдера из Милана");

        //Вывести суммы всех транзакций трейдеров из Кембриджа.

        int cambValue = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println("\nCуммы всех транзакций трейдеров из Кембриджа." + cambValue);
        //Какова максимальная сумма среди всех транзакций?
        OptionalInt maxValue = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println("\nМаксимальная сумма транзакций " + maxValue.orElse(0));

       //Найти транзакцию с минимальной суммой.

        OptionalInt minValue = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min();
        System.out.println("\nМинимальная сумма транзакций " + minValue.orElse(0));
    }


}


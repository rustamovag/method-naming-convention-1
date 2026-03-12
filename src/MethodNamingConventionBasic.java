import java.util.*;
import java.util.stream.*;

/**
 *  — Basic examples in Java 21.
 */
public class Basic {

    record DataPoint(String label, int value, String group) {}

    static List<DataPoint> sampleData() {
        return Arrays.asList(
            new DataPoint("Alpha",   88, "A"),
            new DataPoint("Beta",    42, "B"),
            new DataPoint("Gamma",   95, "A"),
            new DataPoint("Delta",   67, "C"),
            new DataPoint("Epsilon", 73, "B"),
            new DataPoint("Zeta",    55, "C"),
            new DataPoint("Eta",     81, "A")
        );
    }

    public static void main(String[] args) {
        var data = sampleData();
        System.out.println("===  — Basic ===");

        // Statistics
        var stats = data.stream().mapToInt(DataPoint::value).summaryStatistics();
        System.out.printf("Count: %d | Min: %d | Max: %d | Avg: %.1f%n",
            stats.getCount(), stats.getMin(), stats.getMax(), stats.getAverage());

        // Group by category
        System.out.println("\nGroups:");
        data.stream()
            .collect(Collectors.groupingBy(DataPoint::group))
            .forEach((g, list) -> System.out.println("  " + g + ": " +
                list.stream().map(DataPoint::label).collect(Collectors.joining(", "))));

        // Top 3
        System.out.println("\nTop 3:");
        data.stream()
            .sorted(Comparator.comparingInt(DataPoint::value).reversed())
            .limit(3)
            .forEach(d -> System.out.printf("  %s: %d%n", d.label(), d.value()));

        System.out.println("\n[Done]  completed.");
    }
}

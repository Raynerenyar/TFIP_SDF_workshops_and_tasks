package day07;

import java.util.stream.Collectors;
import java.io.BufferedReader;
import day07.SplitLine.*;
import java.io.FileReader;
import java.util.List;
import java.io.Reader;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String filename = args[0];

        // List<String> headers = new LinkedList<>();

        Reader r;
        BufferedReader bfr;

        try {
            r = new FileReader(filename);
            bfr = new BufferedReader(r);

            List<App> apps = bfr.lines()
                    .skip(1)
                    // split string by ',' and/or '\"'
                    .map(line -> SplitLine.splitLine(line)) // returns List<String>
                    // stores each app into App object
                    .map(col -> {
                        App app = new App();
                        app.setName(col.get(0));
                        app.setCategory(col.get(1));
                        app.setRating(Float.parseFloat(col.get(2)));
                        return app;
                    })
                    .toList();

            bfr.close();
            r.close();

            // put into Map. key = category, App = each app stored as object from class
            Map<String, List<App>> groupByCategory = apps.stream()
                    .collect(Collectors.groupingBy(app -> app.getCategory()));

            // for (String category : groupByCategory.keySet()) {
            // List<App> listOApps = groupByCategory.get(category);
            // for (App app : listOApps) {
            // System.out.printf("Category = %s, Rating = %.2f, Name = %s, \n", category,
            // app.getRating(),
            // app.getName());
            // }
            // }

            for (String category : groupByCategory.keySet()) {
                Double avgRating = groupByCategory.get(category).stream()
                        .map(app -> app.getRating())
                        .filter(rating -> !rating.isNaN())
                        .collect(Collectors.averagingDouble(rating -> {
                            return (double) rating;
                        }));
                System.out.printf("Category: %s ====> Average Rating: %.2f\n", category, avgRating);
            }

            // TESTING
            // check if above stream() calculates average correctly.
            // Float total = 0f;
            // int size = groupByCategory.get("SHOPPING").size();
            // for (App app : groupByCategory.get("SHOPPING")) {
            // if (app.getRating().isNaN()) {
            // size--;
            // continue;
            // }
            // total += app.getRating();
            // }
            // System.out.printf("total: %f, size: %d, avg rat: %.2f\n", total, size, total
            // / size);

        } catch (Exception e) {
            e.getMessage();
        }
    }
}

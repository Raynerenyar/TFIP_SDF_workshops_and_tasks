package milton;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String inputCSVfilename = args[0];
        String outputCSVfilename = args[1];
        Map<String, List<Map<String, String>>> sortByUser = new HashMap<>();
        List<String> headers = new ArrayList<>();
        String line;

        Scanner scn;
        Integer indexSortBy = 0;

        try {
            FileInputStream fis = new FileInputStream(inputCSVfilename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bfr = new BufferedReader(isr);

            // getting headers
            headers.addAll(Arrays.asList(bfr.readLine().trim().split(",")));

            // get user choice of sort by
            scn = new Scanner(System.in);
            headers.forEach(x -> System.out.printf("%d: %s\n", headers.indexOf(x), x.stripIndent()));
            System.out.printf("Please input index you want to sort by: ");
            indexSortBy = Integer.parseInt(scn.nextLine());
            System.out.printf("Processing %s, to be sorted by %s\n", inputCSVfilename, headers.get(indexSortBy));

            Long lineCounter = 0L;

            while ((line = bfr.readLine()) != null) {
                lineCounter++;
                // get content of each col in csv
                String[] contentInLine = line.split(",");
                // new list of books
                List<Map<String, String>> listOfBooks = new LinkedList<>();
                // get list if key exist, otherwise get create default with empty list
                List<Map<String, String>> list = sortByUser.getOrDefault(contentInLine[indexSortBy], listOfBooks);
                // new book object
                Map<String, String> book = new HashMap<>();

                Boolean toFix = false;
                // add each header as key and the corresponding column as value into book(map)
                for (int i = 0; i < headers.size(); i++) {

                    /*
                     * hard coded to fix average rating col
                     * once it encounters average_rating and it's string are not digits,
                     * concat previous header with current string
                     * then on subesequent headers,
                     * assign string one index ahead to the key
                     */
                    if ((headers.get(i).equals("average_rating")
                            && !contentInLine[i].stripIndent().matches("-?\\d+(\\.\\d+)?"))) {

                        toFix = true;

                        // curr col add to string of previous index (authors)
                        String fix = book.getOrDefault(headers.get(i - 1).trim(), contentInLine[i - 1].trim());
                        fix = fix.concat("/" + contentInLine[i].trim());
                        book.put(headers.get(i - 1).trim(), fix);

                        // assign the string one index ahead to the key
                        book.put(headers.get(i).trim(), contentInLine[i + 1].trim());

                    } else if (toFix) {
                        // assign the string one index ahead to the key
                        book.put(headers.get(i).trim(), contentInLine[i + 1].trim());
                    } else {
                        book.put(headers.get(i).trim(), contentInLine[i].trim());
                    } // if else

                } // for
                list.add(book);
                sortByUser.put(contentInLine[indexSortBy], list);

            } // while
            bfr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // get list of sortByUser and sort it (default sort)
        List<String> arr = new ArrayList<String>(sortByUser.keySet());
        arr.sort(null);

        /*
         * after sorting
         * for each title check against the next index.
         * if sortByUser category is similar, add the titles from latest to the earliest
         * then remove the latest entry
         */
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                // if char sequence contains char sequence
                if (arr.get(i).contains(arr.get(j)) || arr.get(j).contains(arr.get(i))) {

                    // unify similar sortByUser catergory into one category
                    for (Map<String, String> item : sortByUser.get(arr.get(j))) {
                        item.put(headers.get(indexSortBy), arr.get(i));
                    }
                    // add the higher order sortByUser's category to the lower order category
                    sortByUser.get(arr.get(i)).addAll(sortByUser.get(arr.get(j)));

                    // remove after adding to lower order titles
                    sortByUser.remove(arr.get(j));
                    arr.remove(j);

                } // if
            } // for
        } // for

        try {
            FileOutputStream os = new FileOutputStream(outputCSVfilename);
            OutputStreamWriter osw = new OutputStreamWriter(os);

            // push sortByUser category to index 0 in the headers obj
            int index = indexSortBy;
            headers.add(0, headers.remove(index));
            for (int i = 0; i < headers.size(); i++) {
                // remove whitespace at headers
                headers.add(i, headers.remove(i).trim());
            }

            String firstLine = String.join(",", headers);

            // write headers
            osw.write(firstLine + "\n");

            /*
             * for each category in sortByUser
             * and each book,
             * concat details into a string
             * then write string to file
             * can also use sortedMap or treeMap
             */
            for (String item : sortByUser.keySet()) {
                for (int i = 0; i < sortByUser.get(item).size(); i++) {
                    List<String> list = new ArrayList<>();
                    for (String header : headers) {
                        list.add(sortByUser.get(item).get(i).get(header));
                    }
                    String aline = String.join(",", list);
                    osw.write(aline + "\n");
                }
            }
            osw.close();

        } catch (

        Exception e) {
            e.printStackTrace();
        } // try-catch
        System.out.println("Processing completed");

    } // main

} // class

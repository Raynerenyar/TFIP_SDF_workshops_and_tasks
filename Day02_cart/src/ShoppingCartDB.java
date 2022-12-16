import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartDB {

    // const uses all uppercase
    public static String LOGIN = "login";
    public static String ADD = "add";
    public static String LIST = "list";
    public static String SAVE = "save";
    public static String EXIT = "exit";
    public static String USERS = "users";

    // valid command
    // final -> makes var a constant -> cannot be modified or reassigned later on.
    public static final List<String> VALID_COMMANDS = Arrays.asList(LOGIN, ADD, LIST, SAVE, EXIT, USERS);

    private CartDBInMemory db;
    private String currentUser;
    private String baseFolder;
    private Boolean stop = true;

    /*
     * 1st constructor for when db folder name
     * is not specified in Main.java
     */
    public ShoppingCartDB() {
        baseFolder = "db";
        // setupDir();
        db = new CartDBInMemory();
    }

    /*
     * 2nd constructor for when db folder name
     * has been specified in Main.java
     */
    public ShoppingCartDB(String baseFolder) {
        this.baseFolder = baseFolder;
        // setupDir();
        db = new CartDBInMemory();
    }

    // creates folder based on the name of baseFolder
    public void setupDir() {
        Path dirPath = Paths.get(baseFolder);
        if (!Files.isDirectory(dirPath)) {
            // SKIP if directoary already exists
            try {
                Files.createDirectories(dirPath);

            } catch (IOException e) {
                ; // nothing happens if dir exists
            }
        }
    }

    public void startShell() {
        System.out.println("welcome to Multiuser shopping cart.");
        System.out.println("Please login");

        try {
            Scanner sc = new Scanner(System.in);
            String line;
            while (stop) {
                System.out.printf("> ");
                line = sc.nextLine();
                line.trim();
                // String line2 = sc.next();

                // todo: check valid command
                if (ValidateInput(line)) {
                    ProcessInput(line);
                } else {
                    System.out.println("Invalid input");
                }

            }
            sc.close();
        } finally {

        }

    }

    public Boolean ValidateInput(String input) {
        String[] part = input.split("[,\s]+");
        String command = part[0];
        return VALID_COMMANDS.contains(command);
    }

    // Process command
    public void ProcessInput(String input) {
        Scanner sc = new Scanner(input);
        String command = sc.next().trim();

        if (command.equalsIgnoreCase(LOGIN)) {
            String username = sc.next().trim();
            LoginAction(username);
            ListAction(username);
        }
        if (command.equalsIgnoreCase(LIST)) {
            ListAction(currentUser);
        }
        if (command.equalsIgnoreCase(ADD)) {
            sc.useDelimiter("[, \s]+");
            if (sc.hasNext() == true) {
                String[] fruit = sc.nextLine().trim().split("[,\s]+");
                AddAction(fruit);
            }
        }
        if (command.equalsIgnoreCase(SAVE)) {
            saveAction();
        }
        if (command.equalsIgnoreCase(USERS)) {
            File f = new File("./" + baseFolder + "/");

            // abstract function
            FilenameFilter filenameFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    String lowercaseName = name.toLowerCase();
                    if (lowercaseName.endsWith(".db")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            File[] fileList = f.listFiles(filenameFilter);
            Integer i = 1;
            for (File file2 : fileList) {
                System.out.println(file2.getName());
                System.out.printf("%d. %s\n", i, file2.getName().replace(".db", ""));
                i++;
            }
        }
        if (command.equalsIgnoreCase(EXIT)) {
            stop = false;
        }
        sc.close();

    }

    // Command: logi <username>
    // login function
    public void LoginAction(String username) {
        setupDir();
        if (!db.userMap.containsKey(username)) {
            db.userMap.put(username, new ArrayList<String>());
        }
        currentUser = username;
        db.loadDataFromDB(baseFolder, username);
    }

    public void AddAction(String[] items) {
        for (String item : items) {
            db.userMap.get(currentUser).add(item);
            System.out.printf("%s added to cart\n", item);
        }
    }

    public void ListAction(String username) {
        // if user in storage (.db), list items
        if (db.userMap.get(username).size() > 0) {
            System.out.printf("%s, your cart contains the following item\n", currentUser);
            Integer i = 1;
            for (String item : db.userMap.get(currentUser)) {
                System.out.printf("%d. %s\n", i++, item);
            }
        } else {
            System.out.printf("%s, your cart is empty\n", currentUser);
        }
    }

    public void saveAction() {
        db.saveDataToDB(baseFolder, currentUser);
        System.out.println("Your cart has been saved");
    }

    public Boolean isUserInDB(String currentUser) {
        File f = new File("./" + baseFolder + "/");

        // abstract function to filter filenames ending with ".db"
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".db")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        File[] fileList = f.listFiles(filenameFilter);
        String user;
        Boolean state = false;
        for (File file : fileList) {
            System.out.println(file.getName());
            user = file.getName().replace(".db", "");
            if (user.equalsIgnoreCase(currentUser)) {
                state = true;
                break;
            }
        }
        return state;
    }

}

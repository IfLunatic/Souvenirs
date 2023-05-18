package Menu;


import java.util.*;
import java.util.stream.Collectors;

import Menu.DataSerializationAndDeserializer.Manufacturer.ManufacturerSerializer;
import Menu.DataSerializationAndDeserializer.Souvenir.SouvenirSerializer;
import Souvenir.Souvenir;
import Manufacturer.Manufacturer;

import static Menu.ManufacturerMenu.*;
import static Menu.SouvenirMenu.addSouvenir;
import static Menu.SouvenirMenu.editSouvenir;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static int choice;
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Souvenir> souvenirs = new ArrayList<>();

    public static void menu() {
        do {
            System.out.println("""
            <------------------------------------------------------------------------------------------------------------------------->
                                   Hello user!
                             Select an option from the menu
            1. Add
            2. Edit
            3. View
            4. Information about souvenirs from a given manufacturer
            5. Information about souvenirs made in a given country
            6. Information about manufacturers whose prices for souvenirs are less than the specified price
            7. Information about all manufacturers and for each manufacturer display information about all souvenirs produced by them
            8. Information about manufacturers of a given souvenir produced in a given year
            9. List of souvenirs made in a given year
            10. Delete a specified manufacturer and its souvenirs
            11. Exit
            <------------------------------------------------------------------------------------------------------------------------->
            """);
            String choiceString = scanner.next();
            if (choiceString.matches("\\d+")) {
                choice = Integer.parseInt(choiceString);
                switch (choice) {
                    case 1 -> addMenu();
                    case 2 -> editMenu();
                    case 3 -> viewMenu();
                    case 4 -> infoAboutSouvenirsFromAGivenManufacturer();
                    case 5 -> infoCountry();
                    case 6 -> infoPrice();
                    case 7 -> infoAllManufacturers();
                    case 8 -> infoManufacturersByYear();
                    case 9 -> infoSouvenirsByYear();
                    case 10 -> deleteMenu();
                    case 11 -> closeProgram();
                    default -> System.out.println("Invalid choice. Please enter a number from 1 to 11.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number from 1 to 11.");
            }
        } while (choice < 1 || choice > 11);
    }


    private static void closeProgram() {
        System.out.println("Closing program... ");
        try {
            Thread.sleep(1000);
            System.out.println("Wait...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    private static void deleteMenu() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter the name of the manufacturer");
        String name = scanner1.nextLine();
        System.out.println("Enter the country of the manufacturer");
        String country = scanner1.nextLine();
        manufacturers.removeIf(manufacturer -> manufacturer.getName().equals(name) && manufacturer.getCountry().equals(country));
        souvenirs.removeIf(souvenir -> souvenir.getManufacturer().getName().equals(name) && souvenir.getManufacturer().getCountry().equals(country));
        System.out.println("Souvenirs");
        SouvenirMenu.normalViewSouvenir(souvenirs);
        System.out.println("Manufacturers");
        ManufacturerMenu.normalViewManufacturer();
        SouvenirSerializer.serializeSouvenirs();
        ManufacturerSerializer.serializeManufacturers();
        menu();
    }

    private static void infoSouvenirsByYear() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter year of creating souvenir");
        int year = scanner1.nextInt();
        souvenirs.stream()
                .filter(souvenir -> souvenir.getDateOfRelease().getYear() == year)
                .forEach(s -> SouvenirMenu.normalViewSouvenir(List.of(s)));
        menu();
    }

    private static void infoManufacturersByYear() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter the name of the souvenir");
        String souvenirName = scanner1.nextLine();
        System.out.println("Enter year of creating souvenir");
        int year = scanner1.nextInt();

        souvenirs.stream()
                .filter(souvenir -> souvenir.getName().equals(souvenirName))
                .filter(souvenir -> souvenir.getDateOfRelease().getYear() == year)
                .map(Souvenir::getManufacturer)
                .distinct()
                .forEach(s -> ManufacturerMenu.normalViewManufacturer(List.of(s)));
        menu();
    }

    private static void infoAllManufacturers() {
        Map<Manufacturer, List<Souvenir>> manufacturersMap = souvenirs.stream()
                .collect(Collectors.groupingBy(Souvenir::getManufacturer));

        manufacturersMap.forEach((manufacturer, souvenirList) -> {
            System.out.println("Manufacturer: " + "\n\t" + "Name - " + manufacturer.getName() + "\n\tCountry" + manufacturer.getCountry());
            SouvenirMenu.normalViewWithoutManufacturer(souvenirList);
        });
        menu();
    }

    private static void infoPrice() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter the maximum price");
        double maxPrice = scanner1.nextDouble();
        souvenirs.stream().filter(souvenir -> souvenir.getPrice() < maxPrice)
                .map(Souvenir::getManufacturer)
                .distinct()
                .forEach(s -> ManufacturerMenu.normalViewManufacturer(List.of(s)));
        menu();
    }

    private static void infoCountry() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter the country of origin");
        String country = scanner1.nextLine();
        souvenirs.stream()
                .filter(souvenir -> souvenir.getManufacturer().getCountry().equals(country))
                .forEach(s -> SouvenirMenu.normalViewSouvenir(List.of(s)));
        menu();
    }

    private static void infoAboutSouvenirsFromAGivenManufacturer() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter the name of the manufacturer");
        String name = scanner1.nextLine();
        System.out.println("Enter the country of origin");
        String country = scanner1.nextLine();
        souvenirs.stream()
                .filter(souvenir -> souvenir.getManufacturer().getName().equals(name) && souvenir.getManufacturer().getCountry().equals(country))
                .forEach(s -> SouvenirMenu.normalViewWithoutManufacturer(List.of(s)));
        menu();
    }

    private static void viewMenu() {
        System.out.println("""
                Choose variant 1-3
                1 - View all Souvenir
                2 - View all Manufacturer
                3 - <-Menu
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> SouvenirMenu.normalViewSouvenir(souvenirs);
            case 2 -> normalViewManufacturer();
            case 3 -> menu();
        }
        menu();
    }

    private static void editMenu() {
        System.out.println("""
                Choose variant 1-3
                1 - Edit Souvenir
                2 - Edit Manufacturer
                3 - <-Menu
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> editSouvenir();
            case 2 -> editManufacturer();
            case 3 -> menu();
        }
        menu();
    }

    private static void addMenu() {
        System.out.println("""
                Choose variant 1-3
                1 - Add Souvenir
                2 - Add Manufacturer
                3 - <-Menu
                """);
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> addSouvenir();
            case 2 -> addManufacturer();
            case 3 -> menu();
        }
        menu();
    }

}


package Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Manufacturer.Manufacturer;
import Menu.DataSerializationAndDeserializer.Manufacturer.ManufacturerSerializer;
import Menu.DataSerializationAndDeserializer.Souvenir.SouvenirSerializer;
import Souvenir.RealizationOfASouvenirFactory.*;
import Souvenir.Souvenir;

import static Menu.ManufacturerMenu.normalViewManufacturer;
import static Menu.Menu.*;

public class SouvenirMenu {
    private static final Scanner scanner = new Scanner(System.in);
    protected static void normalViewSouvenir(List<Souvenir> souvenirs) {
        for (Souvenir souvenir : souvenirs) {
            System.out.println("<----------------------------------------->");
            System.out.println("Souvenir name: " + souvenir.getName());
            System.out.println("Souvenir Date: " + souvenir.getDateOfRelease());
            System.out.println("Souvenir price: " + souvenir.getPrice() + "$");
            System.out.println("Souvenir type: " + souvenir.getClass().getSimpleName());
            List<Manufacturer> list = new ArrayList<>(List.of(souvenir.getManufacturer()));
            System.out.println("Manufacturer: ");
            normalViewManufacturer(list);
        }
    }

    protected static void normalViewWithoutManufacturer(List<Souvenir> souvenirs) {
        for (Souvenir souvenir : souvenirs) {
            System.out.println("<----------------------------------------->");
            System.out.println("Souvenir name: " + souvenir.getName());
            System.out.println("Souvenir Date: " + souvenir.getDateOfRelease());
            System.out.println("Souvenir price: " + souvenir.getPrice() + "$");
            System.out.println("Souvenir type: " + souvenir.getClass().getSimpleName());
        }
    }



//    Implementation of methods for adding a souvenir
static void addSouvenir() {
    String name = getSouvenirName().trim();
    LocalDate date = getSouvenirDate();
    double price = getSouvenirPrice();
    Manufacturer manufacturer = getManufacturer();
    String type = getSouvenirType();
    Souvenir souvenir = createSouvenir(name, date, price, manufacturer, type);
    if (souvenir != null) {
        souvenirs.add(souvenir);
        System.out.println("Souvenir added successfully!");
    }
    SouvenirSerializer.serializeSouvenirs();
}

    private static String getSouvenirName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter souvenir name:");
        return scanner.nextLine();
    }

    private static LocalDate getSouvenirDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter souvenir date in the following format: dd/MM/yyyy:");
        String inputDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(inputDate, formatter);
    }


    private static double getSouvenirPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter souvenir price:");
        return scanner.nextDouble();
    }

    private static Manufacturer getManufacturer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter manufacturer name:");
        String manufacturerName = scanner.nextLine();
        System.out.println("Enter manufacturer country:");
        String manufacturerCountry = scanner.nextLine();
        Manufacturer inputManufacturer = new Manufacturer(manufacturerName, manufacturerCountry);

        Optional<Manufacturer> existingManufacturer = manufacturers.stream()
                .filter(manufacturer -> manufacturer.equals(inputManufacturer))
                .findFirst();

        if (existingManufacturer.isPresent()) {
            return existingManufacturer.get();
        } else {
            Manufacturer newManufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
            manufacturers.add(newManufacturer);
            ManufacturerSerializer.serializeManufacturers();
            return newManufacturer;
        }
    }

    private static String getSouvenirType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter souvenir type. (We now have the following types of souvenirs: Cap, Cup, Keychain, T-Shirt)");
        return scanner.nextLine();
    }

    private static Souvenir createSouvenir(String name, LocalDate date, double price, Manufacturer manufacturer, String type) {
        Souvenir souvenir = null;
        switch (type.trim()) {
            case "Cap" -> souvenir = new CapFactory().createSouvenir(name, date, price, manufacturer);
            case "Cup" -> souvenir = new CupFactory().createSouvenir(name, date, price, manufacturer);
            case "Keychain" -> souvenir = new KeychainFactory().createSouvenir(name, date, price, manufacturer);
            case "T-Shirt" -> souvenir = new TShirtFactory().createSouvenir(name, date, price, manufacturer);
            default -> System.out.println("Invalid souvenir type!");
        }
        return souvenir;
    }
    // We implement methods for changing a souvenir
    protected static void editSouvenir() {
        normalViewSouvenir(souvenirs);
        System.out.println("Enter the name of the souvenir you want to change");
        String nameInputSouvenirs = scanner.nextLine();
        System.out.println("Enter the name of the manufacturer you want to change");
        String nameInputManufacturer = scanner.nextLine();
        Souvenir receivedSouvenir = souvenirs
                .stream()
                .filter(s -> s.getName().equals(nameInputSouvenirs) && s.getManufacturer().getName().equals(nameInputManufacturer))
                .findFirst()
                .orElse(null);
        System.out.println("Okay, you have chosen which souvenir to edit, what exactly do you want to change?");
        System.out.println("""
                1 - Name
                2 - Date
                3 - price
                4 - manufacturer
                """);
        int choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1 -> nameChangeSouvenir(Objects.requireNonNull(receivedSouvenir));
            case 2 -> dateChangeSouvenir(Objects.requireNonNull(receivedSouvenir));
            case 3 -> priceChangeSouvenir(Objects.requireNonNull(receivedSouvenir));
            case 4 -> manufacturerChangeSouvenir(receivedSouvenir);
        }
        Menu.menu();
    }

    private static void manufacturerChangeSouvenir(Souvenir receivedSouvenir) {
        System.out.println("""
                What do you want to change?
                1 - Manufacturer's name
                2 - Country
                3 - Total manufacturer
                """);
        int choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1 -> nameChangeManufacturer(receivedSouvenir);
            case 2 -> countryChangeManufacturer(receivedSouvenir);
            case 3 -> totalChangeManufacturer(receivedSouvenir);
        }
            SouvenirSerializer.serializeSouvenirs();
    }

    private static void totalChangeManufacturer(Souvenir receivedSouvenir) {
        nameChangeManufacturer(receivedSouvenir);
        countryChangeManufacturer(receivedSouvenir);
    }

    private static void countryChangeManufacturer(Souvenir receivedSouvenir) {
        System.out.println("Input new country for manufacturer");
        String newCountry = scanner.nextLine().trim();
        receivedSouvenir.getManufacturer().setCountry(newCountry);
    }

    private static void nameChangeManufacturer(Souvenir receivedSouvenir) {
        System.out.println("Input new name for manufacturer");
        String newName = scanner.nextLine().trim();
        receivedSouvenir.getManufacturer().setName(newName);
    }

    private static void priceChangeSouvenir(Souvenir receivedSouvenir) {
        System.out.println("Enter new price for souvenir");
        double newPrice = scanner.nextDouble();
        receivedSouvenir.setPrice(newPrice);
    }

    private static void dateChangeSouvenir(Souvenir receivedSouvenir) {
        System.out.println("Enter new date in format (day/month/year) for souvenir");
        String newDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        receivedSouvenir.setDateOfRelease(LocalDate.parse(newDate, formatter));
    }

    private static void nameChangeSouvenir(Souvenir receivedSouvenir) {
        System.out.println("Enter new name for souvenir");
        String newName = scanner.nextLine().trim();
        receivedSouvenir.setName(newName);
    }
}
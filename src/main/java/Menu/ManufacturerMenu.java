package Menu;

import Manufacturer.Manufacturer;
import Menu.DataSerializationAndDeserializer.Manufacturer.ManufacturerSerializer;

import java.util.List;
import java.util.Scanner;

import static Menu.Menu.manufacturers;


public class ManufacturerMenu {

    private static final Scanner scanner = new Scanner(System.in);

    protected static void normalViewManufacturer() {
        for (Manufacturer manufacturer : manufacturers) {
            System.out.println("<----------------------------------------->");
            System.out.println("Manufacturer name: " + manufacturer.getName());
            System.out.println("Manufacturer country: " + manufacturer.getCountry());
        }
    }
    protected static void normalViewManufacturer(Manufacturer manufacturer) {
            System.out.println("<----------------------------------------->");
            System.out.println("Manufacturer name: " + manufacturer.getName());
            System.out.println("Manufacturer country: " + manufacturer.getCountry());
    }

    protected static void normalViewManufacturer(List<Manufacturer> manufacturers) {
        for (Manufacturer manufacturer : manufacturers) {
            System.out.println("<----------------------------------------------->");
            System.out.println("Manufacturer name: " + manufacturer.getName());
            System.out.println("Manufacturer country: " + manufacturer.getCountry());
        }
    }

    protected static void addManufacturer() {
        System.out.println("Enter manufacturer name:");
        String name = scanner.nextLine();

        System.out.println("Enter manufacturer country:");
        String country = scanner.nextLine();

        manufacturers.add(new Manufacturer(name, country));

        ManufacturerSerializer.serializeManufacturers();
        System.out.println("Manufacturer added successfully!");
    }

    protected static void editManufacturer() {
        normalViewManufacturer();
        System.out.println("Enter the name of the manufacturer you want to change");
        String inputName = scanner.nextLine();
        System.out.println("Enter the country of the manufacturer you want to change");
        String inputCountry = scanner.nextLine();
        Manufacturer manufacturer = manufacturers.stream()
                .filter(s -> s.getName().equals(inputName) && s.getCountry().equals(inputCountry))
                .findFirst()
                .orElse(null);
        changeManufacturer(manufacturer);
    }
    private static void changeManufacturer(Manufacturer manufacturer) {
        System.out.println("""
                What do you want to change?
                1 - Manufacturer's name
                2 - Country
                3 - Total manufacturer
                """);
        int choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1 -> nameChangeManufacturer(manufacturer);
            case 2 -> countryChangeManufacturer(manufacturer);
            case 3 -> totalChangeManufacturer(manufacturer);
        }
    }

    private static void nameChangeManufacturer(Manufacturer manufacturer) {
        System.out.println("Input new name for manufacturer");
        String newName = scanner.nextLine().trim();
        manufacturer.setName(newName);
    }

    private static void countryChangeManufacturer(Manufacturer manufacturer) {
        System.out.println("Input new country for manufacturer");
        String newCountry = scanner.nextLine().trim();
        manufacturer.setName(newCountry);
    }

    private static void totalChangeManufacturer(Manufacturer manufacturer) {
        nameChangeManufacturer(manufacturer);
        totalChangeManufacturer(manufacturer);
    }
}

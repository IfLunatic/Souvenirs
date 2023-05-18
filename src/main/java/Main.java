import Menu.DataSerializationAndDeserializer.Manufacturer.ManufacturerSerializer;
import Menu.DataSerializationAndDeserializer.Souvenir.SouvenirSerializer;
import Menu.Menu;


import static Menu.Menu.manufacturers;
import static Menu.Menu.souvenirs;


public class Main {

    static {
        souvenirs = SouvenirSerializer.deserializeSouvenirs();
        manufacturers = ManufacturerSerializer.deserializeManufacturers();
    }

    public static void main(String[] args) {
        Menu.menu();
    }
}

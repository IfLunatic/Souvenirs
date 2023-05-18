package Menu.DataSerializationAndDeserializer.Manufacturer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import Manufacturer.Manufacturer;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import static Menu.Menu.manufacturers;

public class ManufacturerSerializer {
    public static void serializeManufacturers() {
        Gson gson = new Gson();
        String json = gson.toJson(manufacturers);

        try (FileWriter writer = new FileWriter("manufacturers.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Manufacturer> deserializeManufacturers() {

        try (BufferedReader reader = new BufferedReader(new FileReader("manufacturers.json"))) {
            Gson gson = new Gson();
            manufacturers = gson.fromJson(reader, new TypeToken<List<Manufacturer>>(){}.getType());
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }
}

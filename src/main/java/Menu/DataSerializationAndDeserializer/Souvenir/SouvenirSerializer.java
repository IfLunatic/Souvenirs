package Menu.DataSerializationAndDeserializer.Souvenir;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

import Manufacturer.Manufacturer;
import Souvenir.Souvenir;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import static Menu.Menu.souvenirs;

public class SouvenirSerializer {
    public static void serializeSouvenirs()  {
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(Manufacturer.class, new ManufacturerAdapter());
        gsonBuilder.registerTypeAdapter(Souvenir.class, new SouvenirsAdapter());
        Gson gson = gsonBuilder.create();

        JsonArray jsonArray = new JsonArray();
        for (Souvenir souvenir : souvenirs) {
            JsonObject jsonObject = gson.toJsonTree(souvenir).getAsJsonObject();
            jsonObject.addProperty("type", souvenir.getClass().getSimpleName());
            jsonArray.add(jsonObject);
        }

        FileWriter writer;
        try {
            writer = new FileWriter("souvenirs.json");
            gson.toJson(jsonArray, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Souvenir> deserializeSouvenirs() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(Manufacturer.class, new ManufacturerAdapter())
                .registerTypeAdapter(Souvenir.class, new SouvenirsAdapter())
                .create();
        try (Reader reader = new FileReader("souvenirs.json")) {
            Type listType = new TypeToken<List<Souvenir>>() {}.getType();
            souvenirs = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return souvenirs;
    }
}

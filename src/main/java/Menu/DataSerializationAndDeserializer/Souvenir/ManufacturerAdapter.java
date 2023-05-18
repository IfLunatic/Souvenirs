package Menu.DataSerializationAndDeserializer.Souvenir;

import Manufacturer.Manufacturer;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ManufacturerAdapter implements JsonSerializer<Manufacturer>, JsonDeserializer<Manufacturer> {
    @Override
    public JsonElement serialize(Manufacturer src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("name", src.getName());
        obj.addProperty("country", src.getCountry());
        return obj;
    }

    @Override
    public Manufacturer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String name = obj.get("name").getAsString();
        String country = obj.get("country").getAsString();
        return new Manufacturer(name, country);
    }
}
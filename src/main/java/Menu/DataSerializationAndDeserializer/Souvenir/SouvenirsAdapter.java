package Menu.DataSerializationAndDeserializer.Souvenir;

import Souvenir.Souvenir;
import Souvenir.TypeOfSouvenirs.Cap;
import Souvenir.TypeOfSouvenirs.Cup;
import Souvenir.TypeOfSouvenirs.Keychain;
import Souvenir.TypeOfSouvenirs.TShirt;
import com.google.gson.*;

import java.lang.reflect.Type;

import static Menu.Menu.souvenirs;

public class SouvenirsAdapter implements JsonSerializer<Souvenir>, JsonDeserializer<Souvenir> {
    private static final String TYPE_FIELD = "type";

    @Override
    public Souvenir deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement typeElement = jsonObject.get(TYPE_FIELD);

        if (typeElement == null || typeElement.isJsonNull()) {
            throw new JsonParseException("Missing or null 'type' field in JSON");
        }

        String typeName = typeElement.getAsString();

        return switch (typeName) {
            case "Cap" -> jsonDeserializationContext.deserialize(jsonObject, Cap.class);
            case "Cup" -> jsonDeserializationContext.deserialize(jsonObject, Cup.class);
            case "TShirt" -> jsonDeserializationContext.deserialize(jsonObject, TShirt.class);
            case "Keychain" -> jsonDeserializationContext.deserialize(jsonObject, Keychain.class);
            default -> throw new JsonParseException("Unknown type: " + typeName);
        };
    }

    @Override
    public JsonElement serialize(Souvenir souvenir, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = jsonSerializationContext.serialize(souvenir).getAsJsonObject();
        jsonObject.addProperty(TYPE_FIELD, souvenir.getClass().getSimpleName());

        return jsonObject;
    }
}

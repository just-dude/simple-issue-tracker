package controller.struts.customComponent.util;
 
import java.io.IOException;
 
import com.google.gson.*;
import java.time.LocalDate;
import controller.struts.customComponent.serializer.LocalDateJsonSerializer;
 
public final class JsonUtils {
 
    private JsonUtils() {
    }
 
    public static String toJson(Object value) throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateJsonSerializer())
			.create();
        return gson.toJson(value);
    }
 
}

package home.mad.simpleshop.model.mapers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import rx.functions.Func1;

/**
 * Created by mad on 05.12.2016.
 */

public class JsonMaper implements Func1<JsonObject, List<ItemDTO>> {
    @Override
    public List<ItemDTO> call(JsonObject object) {
        List<ItemDTO> items = new ArrayList<>();
        String categories = object.get("params").getAsJsonObject().get("category").getAsString();
        for (JsonElement result : object.get("results").getAsJsonArray()) {
            items.add(new ItemDTO(result, categories));
        }
        return items;
    }
}

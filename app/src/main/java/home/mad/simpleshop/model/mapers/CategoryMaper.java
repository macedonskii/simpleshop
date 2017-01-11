package home.mad.simpleshop.model.mapers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import home.mad.simpleshop.model.dto.CategoryDTO;
import rx.functions.Func1;


public class CategoryMaper implements Func1<JsonObject, List<CategoryDTO>> {

    @Override
    public List<CategoryDTO> call(JsonObject object) {
        ArrayList<CategoryDTO> items = new ArrayList<>();
        for (JsonElement element : object.get("results").getAsJsonArray()) {
            items.add(new CategoryDTO(element.getAsJsonObject().get("long_name").getAsString(),element.getAsJsonObject().get("name").getAsString()));
        }
        return items;
    }
}

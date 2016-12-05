package home.mad.simpleshop.model.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by mad on 02.12.2016.
 */
public class ItemDTO {
    private long listingId;
    private float price;
    private String category;
    private String priceDescription;
    private String title;
    private String description;
    private String image7575;
    private String image170135;
    private String image570;
    private String imageFull;
    private boolean favorites;

    public ItemDTO(JsonObject object) {
        listingId = object.get("listing_id").getAsLong();
        price = object.get("price").getAsFloat();
        priceDescription = object.get("currency_code").getAsString();
        title = object.get("title").getAsString();
        description = object.get("description").getAsString();
        JsonObject images = object.get("MainImage").getAsJsonObject();
        image7575 = images.get("url_75x75").getAsString();
        image170135 = images.get("url_170x135").getAsString();
        image570 = images.get("url_570xN").getAsString();
        imageFull = images.get("url_fullxfull").getAsString();
    }

    public ItemDTO(JsonElement element){
        this((JsonObject)element);
    }
    public String getDescription() {
        return description;
    }

    public String getImageMedium() {
        return image170135;
    }

    public String getImageBig() {
        return image570;
    }

    public String getImageSmall() {
        return image7575;
    }

    public String getImageFull() {
        return imageFull;
    }

    public long getListingId() {
        return listingId;
    }

    public float getPrice() {
        return price;
    }

    public String getPriceDescription() {
        return priceDescription;
    }

    public String getPriceString(){
        // TODO: 05.12.2016 test this
        return String.format("%1$.2f %2s",price, priceDescription);
    }

    public String getTitleString(){
        // TODO: 05.12.2016 change this
        return title;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public String getTitle() {
        return title;
    }
}

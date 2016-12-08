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

    private String image170135;
    private String image570;
    private String imageFull;
    private boolean favorites;

    public ItemDTO(){}

    public ItemDTO(JsonElement result, String categories){
        this((JsonObject) result,categories);
    }

    public ItemDTO(JsonObject object, String categories) {
        listingId = object.get("listing_id").getAsLong();
        price = object.get("price").getAsFloat();
        priceDescription = object.get("currency_code").getAsString();
        title = object.get("title").getAsString();
        description = object.get("description").getAsString();
        JsonObject images = object.get("MainImage").getAsJsonObject();

        image170135 = images.get("url_170x135").getAsString();
        image570 = images.get("url_570xN").getAsString();
        imageFull = images.get("url_fullxfull").getAsString();
        category = categories;
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

    public String getPriceString() {
        // TODO: 05.12.2016 test this
        return String.format("%1$.2f %2s", price, priceDescription);
    }

    public String getTitleString() {
        // TODO: 05.12.2016 change this
        return title;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        // TODO: 06.12.2016 create category
        return category != null ? category : "category";
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
    }

    public void setImageMedium(String image170135) {
        this.image170135 = image170135;
    }

    public void setImageBig(String imageBig) {
        this.image570 = imageBig;
    }

    public void setImageFull(String imageFull) {
        this.imageFull = imageFull;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPriceDescription(String priceDescription) {
        this.priceDescription = priceDescription;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

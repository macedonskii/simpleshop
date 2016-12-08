package home.mad.simpleshop.model.dto;

/**
 * Created by mad on 08.12.2016.
 */
public class CategoryDTO {
    private String longName;
    private String name;

    public CategoryDTO(String fullName, String name) {
        this.longName = fullName;
        this.name = name;
    }

    public String getLongName() {
        return longName;
    }

    public String getName() {
        return name;
    }
}

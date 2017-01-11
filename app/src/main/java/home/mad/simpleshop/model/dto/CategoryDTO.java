package home.mad.simpleshop.model.dto;


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

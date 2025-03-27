import java.util.Arrays;
import java.util.List;

class Section {
    private String name;
    private List<Shelf> shelves;

    public Section(String name, Shelf... shelves) {
        this.name = name;
        this.shelves = Arrays.asList(shelves);
    }

    public String getName() { return name; }
    public List<Shelf> getShelves() { return shelves; }
}

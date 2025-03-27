import java.util.Arrays;
import java.util.List;

public class Library {
    private List<Section> sections;

    public Library(Section... sections) {
        this.sections = Arrays.asList(sections);
    }

    public List<Section> getSections() {
        return sections;
    }
}

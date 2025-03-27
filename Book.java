
class Book {
    private String title;
    private Author author;
    private String link;

    public Book(String title, Author author, String link) {
        this.title = title;
        this.author = author;
        this.link = link;
    }

    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public String getLink() { return link; }
}

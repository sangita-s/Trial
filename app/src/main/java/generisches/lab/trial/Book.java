package generisches.lab.trial;

public class Book {
    private String BookTitle;
    private String Category;
    private String Description;
    private int Thumbnail;


    public Book() {
    }

    public Book(String bookTitle, String category, String description, int thumbnail) {
        BookTitle = bookTitle;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}

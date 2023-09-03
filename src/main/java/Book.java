class Book {
    private final String Name;
    private final String Author;
    private final int BookID;
    private Boolean IsIssued;
    private long IssueDate;

    Book(String name, String author, int bookID) {
        Name = name;
        Author = author;
        BookID = bookID;
        IsIssued = false;
        IssueDate = -1;
    }

    public int getBookID() {
        return BookID;
    }

    public String getAuthor() {
        return Author;
    }

    public String getName() {
        return Name;
    }

    public Boolean getIssued() {
        return IsIssued;
    }

    public void setIssued(Boolean issued) {
        IsIssued = issued;
    }

    public long getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(long issueDate) {
        IssueDate = issueDate;
    }
}
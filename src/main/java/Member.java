import java.util.Vector;
class Member {
    private final String Name;
    private final int Age;
    private final String PhoneNumber;
    private final int MemberId;
    private Vector<Book> IssuedBooks;
    private int fine;
    Member(String name, int age, String phone_num, int ind){
        Name = name;
        Age = age;
        PhoneNumber = phone_num;
        MemberId = ind;
        fine = 0;
        IssuedBooks = new Vector<>();
    }
    public int getMemberId() {
        return MemberId;
    }
    public int getAge() {
        return Age;
    }
    public String getName() {
        return Name;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public Vector<Book> getIssuedBooks() {
        return IssuedBooks;
    }
    public void setIssuedBooks(Vector<Book> issuedBooks) {
        IssuedBooks = issuedBooks;
    }
    public int getFine() {
        return fine;
    }
    public void setFine(int fine) {
        this.fine = fine;
    }
    public int updateFine(Book temp) {
        for (Book i : IssuedBooks) {
            if (i.getBookID() != temp.getBookID()) continue;
            IssuedBooks.remove(i);
            long currentTime = System.currentTimeMillis();
            if((currentTime - temp.getIssueDate())/1000 > 10){
                fine += (int) (((currentTime - temp.getIssueDate())/1000 - 10)*3);
                return (int) (((currentTime - temp.getIssueDate())/1000 - 10)*3);
            }
        }
        return 0;
    }
    public void printIssuedBooks(){
        System.out.println("Issued Books:");
        if(IssuedBooks.isEmpty()){
            System.out.println(" No currently issued books.");
            return;
        }
        for(Book i : IssuedBooks){
            System.out.println("Book ID - " + i.getBookID());
            System.out.println("Name - " + i.getName());
            System.out.println("Author - " + i.getAuthor());
        }
    }
}

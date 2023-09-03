import java.util.Scanner;
import java.util.Vector;
import java.util.stream.IntStream;

class Library {
    private final Vector<Member> MemberList;
    private int MembersNumbers;
    private final Vector<Book> BookList;
    private int BookNumbers;
    Library(){
        this.MemberList = new Vector<>();
        this.BookList = new Vector<>();
        BookNumbers = 0;
        MembersNumbers = 0;
    }
    public static boolean isValidPhoneNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return (str.length()) == 10;
    }
    public int returnMemberListIndex(Scanner scanner){
        int memberListIndex = -1;
        System.out.print("Enter member name : ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number : ");
        String phoneNumber = scanner.nextLine();
        if(!Library.isValidPhoneNumber(phoneNumber)){
            System.out.println("The phone number you have given is invalid!");
            return memberListIndex;
        }
        for (int i = 0; i < MemberList.size();i++){
            Member member = MemberList.get(i);
            if(member.getPhoneNumber().equals(phoneNumber) && member.getName().equals(name)) memberListIndex = i;
        }
        if(memberListIndex == -1){
            System.out.println("Incorrect name or phone number!");
        }else{
            System.out.println("Welcome to the library. your Member ID is "+MemberList.get(memberListIndex).getMemberId());
        }
        return memberListIndex;
    }
    public void addMember(Scanner scanner){
        System.out.print("Enter member name : ");
        String name = scanner.nextLine();
        if (name.isEmpty()){
            System.out.println("A member cannot exist without a name! Try Again.");
            return;
        }
        System.out.print("Enter phone number : ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter age : ");
        int age;
        do{
            String line = scanner.nextLine();
            try{
                age = Integer.parseInt(line);
                if(age > 0) break;
                else System.out.print("Please enter a valid age!\nage : ");
            }catch (Exception e){
                System.out.print("This is not a valid age! Please enter a valid age : ");
            }
        }while(true);
        if(isValidPhoneNumber(phoneNumber)){
            for(Member member : MemberList){
                if(phoneNumber.equals(member.getPhoneNumber())){
                    System.out.println("A person with this phone number is already registered!");
                    return;
                }
            }
            MembersNumbers++;
            Member member = new Member(name, age, phoneNumber, MembersNumbers);
            MemberList.add(member);
            System.out.println("Member successfully registered.");
        }else System.out.println("Invalid phone number(must be numeric as well as 10 digit)!");
    }
    public void removeMember(Scanner scanner){
         System.out.print("Enter MemberID : ");
         int memberID;
         do{
             String line = scanner.nextLine();
             try{
                 memberID = Integer.parseInt(line);
                 break;
             }catch (Exception e){
                 System.out.print("This is not a valid Member ID! Please enter a valid Member ID : ");
             }
         }while(true);
         for(Member i : MemberList){
             if(i.getMemberId() == memberID){
                 if (!i.getIssuedBooks().isEmpty()){
                     System.out.println("This member has issued books cannot remove them.");
                     return;
                 }
                 MemberList.remove(i);
                 System.out.println("Member was successfully removed.");
                 return;
             }
         }
         System.out.println("Member with this ID does not exist.");
     }
    public void addBook(Scanner scanner){
        System.out.print("Enter book title : ");
        String name = scanner.nextLine();
        if (name.isEmpty()){
            System.out.println("A book cannot exist without a name! Try Again.");
            return;
        }
        System.out.print("Enter author name : ");
        String author = scanner.nextLine();
        System.out.print("Enter number of copies of the book : ");
        int copies;
        do{
            String line = scanner.nextLine();
            try{
                copies = Integer.parseInt(line);
                if (copies > 0) break;
                else System.out.println("Cannot have copies less than 0. Enter the number of copies : ");
            }catch (Exception e){
                System.out.print("This is not a valid number of copies! Please enter a valid a valid integer : ");
            }
        }while(true);
        IntStream.range(0, copies).forEach(i -> {
            BookNumbers++;
            Book book = new Book(name, author, BookNumbers);
            BookList.add(book);
        });
        System.out.println("All books were added successfully to the library.");
    }
    public void removeBook(Scanner scanner){
        System.out.print("Enter BookID : ");
        int bookID;
        do{
            String line = scanner.nextLine();
            try{
                bookID = Integer.parseInt(line);
                break;
            }catch (Exception e){
                System.out.print("This is not a valid BookID! Please enter a valid BookID : ");
            }
        }while(true);
        for(Book i : BookList){
            if(i.getBookID() == bookID){
                if(i.getIssued()){
                    System.out.println("Cannot remove an issued book.");
                    return;
                }
                BookList.remove(i);
                System.out.println("Book was successfully removed.");
                return;
            }
        }
        System.out.println("Book with this ID does not exist.");
    }
    private boolean alreadyIssued(int bookID){
        for(Book i : BookList){
            if (i.getBookID() == bookID && i.getIssued()) return true;
        }
        return false;
    }
    public void issueBook(int memberListIndex, Scanner scanner){
        if(MemberList.get(memberListIndex).getFine() > 0){
            System.out.println("You have pay your outstanding fine before issuing another book.");
            return;
        }
        if(MemberList.get(memberListIndex).getIssuedBooks().size() >= 2) {
            System.out.println("You have already issued the maximum number of books you can issue.");
            return;
        }
        int bookID = getBookID(scanner);
        if(alreadyIssued(bookID)){
            System.out.println("This book is already issued.");
            return;
        }
        Member mem = MemberList.get(memberListIndex);
        if(!mem.getIssuedBooks().isEmpty()){
            Book b = mem.getIssuedBooks().get(0);
            for(Book i : BookList){
                if (b.getBookID() == i.getBookID()){
                    long t = System.currentTimeMillis()/1000;
                    long is = i.getIssueDate()/1000;
                    if(t-is > 10){
                        System.out.println("Your issued book has exceeded its 10 day limit please return it and pay the fine before issuing another book.");
                        return;
                    }
                }
            }
        }
        for(int i = 0; i < BookList.size(); i++){
            if(BookList.get(i).getBookID() == bookID && !(BookList.get(i).getIssued())){
                Book temp = BookList.get(i);
                temp.setIssued(true);
                temp.setIssueDate(System.currentTimeMillis());
                BookList.set(i,temp);
                Member member =  MemberList.get(memberListIndex);
                Vector <Book> list = member.getIssuedBooks();
                list.add(temp);
                member.setIssuedBooks(list);
                MemberList.set(memberListIndex,member);
                member.printIssuedBooks();
                System.out.println("Book was successfully issued.");
                return;
            }
        }
        System.out.println("No such book exists.");
    }
    private boolean notIssuedByYou(int bookID, int memberListIndex){
        Vector <Book> bookList = MemberList.get(memberListIndex).getIssuedBooks();
        for(Book i : bookList){
            if (i.getBookID() == bookID) return true;
        }
        return false;
    }
    public void returnBook(int memberListIndex, Scanner scanner){
        int bookID = getBookID(scanner);
        if(!alreadyIssued(bookID) || !notIssuedByYou(bookID,memberListIndex)){
            System.out.println("You don't have this book issued or a book with this ID does not exist try using the command \"List My Books\".");
            return;
        }
        for(int i = 0; i < BookList.size(); i++){
            if(BookList.get(i).getBookID() == bookID){
                Book temp = BookList.get(i);
                Member member = MemberList.get(memberListIndex);
                int fine = member.updateFine(temp);
                int delay = fine/3;
                MemberList.set(memberListIndex,member);
                if(delay == 0) System.out.println("Book ID : " + bookID + " was successfully returned without any fine.");
                else System.out.println("Book ID : " + bookID + " was successfully returned with a fine of " + (delay * 3) + " Rupees for a delay of " + delay + " days.");
                temp.setIssued(false);
                temp.setIssueDate(-1);
                BookList.set(i,temp);
                return;
            }
        }
    }

    private int getBookID(Scanner scanner) {
        System.out.print("Enter BookID : ");
        int bookID;
        do{
            String line = scanner.nextLine();
            try{
                bookID = Integer.parseInt(line);
                break;
            }catch (Exception e){
                System.out.print("This is not a valid Book ID! Please enter a valid Book ID : ");
            }
        }while(true);
        return bookID;
    }

    public void printBookList(){
        for(Book i : BookList){
            System.out.println("Book ID - " + i.getBookID());
            System.out.println("Name - " + i.getName());
            System.out.println("Author - " + i.getAuthor());
            System.out.println(" ");
        }
    }
    public void printAvailableBooks(){
        for(Book i : BookList){
            if(!i.getIssued()){
                System.out.println("Book ID - " + i.getBookID());
                System.out.println("Name - " + i.getName());
                System.out.println("Author - " + i.getAuthor());
                System.out.println(" ");
            }
        }
    }
    public void printMembersList(){
        for(Member i : MemberList){
            System.out.println("Member ID - " + i.getMemberId());
            System.out.println("Name - " + i.getName());
            System.out.println("Phone Number - " + i.getPhoneNumber());
            System.out.println("Age - "+i.getAge());
            i.printIssuedBooks();
            System.out.println("Total Fine Due - " + i.getFine());
            System.out.println(" ");
        }
    }
    public void payFine(int memberListIndex){
        Member member = MemberList.get(memberListIndex);
        System.out.println("You had a total fine of Rs. " + member.getFine() + ". It has been paid successfully.");
        member.setFine(0);
        MemberList.set(memberListIndex, member);
    }

    public Vector<Member> getMemberList() {
        return MemberList;
    }
}
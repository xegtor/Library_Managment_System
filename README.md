# Library Management System (Java)

## Brief Overview

This project is a Library Management System developed in Java. It is designed to help libraries efficiently manage their resources, including books, members, and borrowing records. The system provides a user-friendly interface for librarians and members to perform various operations, making the process of library management more streamlined and organized.

## Classes and Methods

### 1. `Library`

**Attributes**:
- `books: List<Book>`: A list that holds all the books in the library.
- `members: List<Member>`: A list that stores information about library members.
- `MembersNumbers: int` : A seed used to generate unique MemberID's.
- `BookNumbers: int` : A seed used to generate unique BookID's.

**Methods**:
- `addBook(Book book)`: Adds a new book to the library inventory.
- `removeBook(Book book)`: Removes a book from the library inventory.
- `addMember(Member member)`: Adds a member to the library database.
- `removeMember(Member member)`: Removes a member from the library database.
- `listBooks()`: Displays a list of all books in the library.
- `listAvailableBooks()`: Displays a list of all available books in the library.
- `issueBook(Member member, Book book)`: allows a member to issue a book.
- `returnBook(Member member, Book book)`: Allows a member to return an issued book.
- `listMembers()`: Displays a list of all library members along with their fines and issued books.

### 2. `Book`

**Attributes**:
- `title: String`: The title of the book.
- `author: String`: The author of the book.
- `BookID: int`: The BookID of the book.
- `IsIssued: boolean`: The status of the book.
- `IssueDate: long`: The date at which the book was issued.

**Methods**:
- `getTitle()`: Returns the title of the book.
- `getAuthor()`: Returns the author of the book.
- `getBookID()`: Returns the BookID of the book.
- `setIssued(Boolean issued)`: sets the status of the book.
- `getIssued()`: returns the status of the book.
- `getIssuedDate()`: returns the issued date of the book.
- `setIssueDate(long issueDate)`: sets the issue date of the book.

### 3. `Member`

**Attributes**:
- `name: String`: The name of the library member.
- `id: int`: A unique identification number for the member.
- `borrowedBooks: List<Book>`: A list of books currently borrowed by the member.
- `PhoneNumber: String`: The phone number of the member.
- `Fine: int`: The amount of money the member ows to the library.

**Methods**:
- `getIssuedBooks()`: Returns a list of books issued by the member.
- `getMemberID()`: Returns the ID of the member.
- `getAge()`: Returns the age of the member.
- `getName()`: Returns the name of the member.
- `getPhoneNumber()`: Returns the phone number of the member.
- `getFine()`: Returns the amount of money the member ows to the library.
- `setIssuedBooks(Vector<Book> issuedBooks)`: Sets the list of books that the member has issued from the library.
- `setFine(int fine)`: Sets the fine of the member.
- `updateFine()`: Update the fine of the user and returns the fine added.
- `printIssuedBooks()`: Prints a list of the books the user has issued.
 
## Classes and Their Relationships

### Composition
- The `Library` class has a composition relationship with the classes of `Book` and `Member` as it contains a list of `Book` objects and a list of `Member` objects where its functions call their respective getter and setter methods as shown by the code snippets below:
    - ```java
      // atributes of the `library` class which contain lists of `Book` and `Member` classes.
      private final Vector<Member> MemberList;
      private int MembersNumbers;
      private final Vector<Book> BookList;
      private int BookNumbers;
      
    - ```java
      // example of composition relationship between the `Library` and `Book` classes.
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
    - ```java
      // example of composition relationship between the `Library` and `Member` classes.
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

### Association
- There is an Association relationship between the `Member` and `Book` classes as the class `Member` contains a list of `Book` objects and its functions call the getter functions of the class `Book` while not calling any setter functions as shown in the code snippet bellow.
  - ```java
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

### Dependency
- The `Library` class is Dependent on `java.util.stream.IntStream` as a function of `InstStream` is being used by library without forming an `IntStream` object as shown in the code snippet bellow.
  - ```java
    IntStream.range(0, copies).forEach(i -> {
      BookNumbers++;
      Book book = new Book(name, author, BookNumbers);
      BookList.add(book);
    });

## Basic Operations and Features

- **Add Book**: Librarians can add new books to the library inventory.
- **Remove Book**: Librarians can remove books from the library inventory.
- **Register Member**: Allows the librarian to add a new member to the library database.
- **Remove Member**: Allows the librarians to remove  a member from the library database.
- **List Books**: Librarians can view a list of all books owned by the library.
- **List Available Books**: Members can view all the books they can issue from the library.
- **Borrow Book**: Members can borrow books from the library.
- **Return Book**: Members can return borrowed books to the library.
- **List Members**: Librarians can see all the members with their issued books and fines in the library database.
- **PayFine**: Members can pay their outstanding fines to the library.

## How to Run the Project

### Using the terminal
1. Extract the project from the zip file to your local machine.
2. Open the terminal at `..\A1_2022412`.
3. run the following terminal commands in order:
   - `mvn clean`
   - `mvn compile`
   - `mvn package`
   - `cd target`
   - `java -jar .\A1_2022412-1.0-SNAPSHOT.jar`
4. To re-run it, you can simply enter `java -jar .\A1_2022412-1.0-SNAPSHOT.jar` if you are in the `..\A1_2022412\target` directory.

### Using an Java development environment
1. Extract the project from the zip file to your local machine.
2. Open the project in your preferred Java development environment (e.g., Eclipse, IntelliJ).
3. Build and run the project.
4. Follow the on-screen instructions to interact with the Library Management System.

Please note that you may need to configure your Java development environment and dependencies to run the project successfully.

Enjoy managing your library efficiently with this Library Management System!


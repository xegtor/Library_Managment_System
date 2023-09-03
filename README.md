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

**Methods**:
- `borrowBook(Book book)`: Allows a member to borrow a book.
- `returnBook(Book book)`: Allows a member to return a borrowed book.
- `getBorrowedBooks()`: Returns a list of books borrowed by the member.

## Classes and Their Relationships

- The `Library` class contains a list of `Book` objects and a list of `Member` objects.
- Each `Book` is associated with a unique ISBN.
- Each `Member` can borrow multiple books.

## Basic Operations and Features

- **Add Book**: Librarians can add new books to the library inventory.
- **Remove Book**: Librarians can remove books from the library inventory.
- **Find Book**: Librarians and members can search for books by title.
- **List Books**: Librarians and members can view a list of all available books.
- **Borrow Book**: Members can borrow books from the library.
- **Return Book**: Members can return borrowed books to the library.

## How to Run the Project

### Using the terminal
1. Extract the project from the zip file to your local machine.
2. Open the terminal at `..\A1_2022412`.
3. run the following terminal commands in order:<br>
-`mvn clean`<br>
-`mvn compile`<br>
-`mvn package`<br>
-`cd target`<br>
-`java -jar .\A1_2022412-1.0-SNAPSHOT.jar`
4. To re-run it, you can simply enter `java -jar .\A1_2022412-1.0-SNAPSHOT.jar` if you are in the `..\A1_2022412\target` directory.

### Using an Java development environment
1. Extract the project from the zip file to your local machine.
2. Open the project in your preferred Java development environment (e.g., Eclipse, IntelliJ).
3. Build and run the project.
4. Follow the on-screen instructions to interact with the Library Management System.

Please note that you may need to configure your Java development environment and dependencies to run the project successfully.

Enjoy managing your library efficiently with this Library Management System!


# Library Management System (Java)

## Brief Overview

This project is a Library Management System developed in Java. It is designed to help libraries efficiently manage their resources, including books, members, and borrowing records. The system provides a user-friendly interface for librarians and members to perform various operations, making the process of library management more streamlined and organized.

## Classes and Methods

### 1. `Library`

- `addBook(Book book)`: Adds a new book to the library inventory.
- `removeBook(Book book)`: Removes a book from the library inventory.
- `findBook(String title)`: Searches for a book by title and returns information about it.
- `listBooks()`: Displays a list of all available books in the library.

### 2. `Book`

- `getTitle()`: Returns the title of the book.
- `getAuthor()`: Returns the author of the book.
- `getISBN()`: Returns the ISBN of the book.

### 3. `Member`

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

1. Extract the project from the zip file to your local machine.
2. Open the project in your preferred Java development environment (e.g., Eclipse, IntelliJ).
3. Build and run the project.
4. Follow the on-screen instructions to interact with the Library Management System.

Please note that you may need to configure your Java development environment and dependencies to run the project successfully.

Enjoy managing your library efficiently with this Library Management System!


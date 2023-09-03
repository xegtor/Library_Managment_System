import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  System.out.println("Library Portal Initialized...");
  Library library = new Library();
  mainMenu(library, scanner);
  scanner.close();
 }
 private static void mainMenu(Library library, Scanner scanner){
  int command;
  do{
   printMainMenu();
   do{
    String line = scanner.nextLine();
    try{
     command = Integer.parseInt(line);
     break;
    }catch (Exception e){
     System.out.print("This is not a valid command! Please enter a valid command : ");
    }
   }while(true);
   if (command == 1){
    librarianMenu(library,scanner);
   } else if (command == 2) {
    memberMenu(library,scanner);
   } else if (command == 3) {
    break;
   }else System.out.println("Invalid input. Enter 3 to exit or 1 or 2 to continue.");
  }while(true);
  System.out.println("Exiting the Library management program.\nNote: All data stored in the library has been lost by closing this program.");
 }
 private static void memberMenu(Library library, Scanner scanner) {
  int memberListIndex = library.returnMemberListIndex(scanner);
  if (memberListIndex == -1) return;
  int command;
  do{
   printMemberMenu();
   do{
    String line = scanner.nextLine();
    try{
     command = Integer.parseInt(line);
     break;
    }catch (Exception e){
     System.out.print("This is not a valid command! Please enter a valid command : ");
    }
   }while(true);
   if(command == 1){
    library.printAvailableBooks();
   } else if (command == 2) {
    library.getMemberList().get(memberListIndex).printIssuedBooks();
   } else if (command == 3) {
    library.issueBook(memberListIndex, scanner);
   } else if (command == 4) {
    library.returnBook(memberListIndex, scanner);
   } else if (command == 5) {
    library.payFine(memberListIndex);
   } else if (command == 6) {
    break;
   }else {
    System.out.println("Invalid input. Enter 6 to exit or an integer from 1 to 5 to continue.");
   }
  }while(true);
 }

 private static void librarianMenu(Library library, Scanner scanner) {
  int command;
  do{
   printLibrarianMenu();
   do{
    String line = scanner.nextLine();
    try{
     command = Integer.parseInt(line);
     break;
    }catch (Exception e){
     System.out.print("This is not a valid command! Please enter a valid command : ");
    }
   }while(true);
   if(command == 1){
    library.addMember(scanner);
   } else if (command == 2) {
    library.removeMember(scanner);
   } else if (command == 3) {
    library.addBook(scanner);
   } else if (command == 4) {
    library.removeBook(scanner);
   } else if (command == 5) {
    library.printMembersList();
   } else if (command == 6) {
    library.printBookList();
   } else if (command == 7) {
    break;
   }else {
    System.out.println("Invalid input. Enter 7 to exit or an integer from 1 to 6 to continue.");
   }
  }while(true);
 }

 private static void printMainMenu(){
  System.out.println("---------------------------------");
  System.out.println("1.\tEnter as a librarian");
  System.out.println("2.\tEnter as a member");
  System.out.println("3.\tExit");
  System.out.println("---------------------------------");
 }
 private static void printLibrarianMenu(){
  System.out.println("---------------------------------");
  System.out.println("1.\tRegister a member");
  System.out.println("2.\tRemove a member");
  System.out.println("3.\tAdd a book");
  System.out.println("4.\tRemove a book");
  System.out.println("5.\tView all members along with their books and fines to be paid");
  System.out.println("6.\tView all books");
  System.out.println("7.\tBack");
  System.out.println("---------------------------------");
 }
 private static void printMemberMenu(){
  System.out.println("---------------------------------");
  System.out.println("1.\tList Available Books");
  System.out.println("2.\tList My Books");
  System.out.println("3.\tIssue book");
  System.out.println("4.\tReturn book");
  System.out.println("5.\tPay Fine");
  System.out.println("6.\tBack");
  System.out.println("---------------------------------");
 }
}
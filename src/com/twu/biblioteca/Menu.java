package com.twu.biblioteca;

public class Menu {

    private final static String OPTION_INVALID_PROMPT_MSG = "Select a valid option! Please select again.\n";
    private final static String BOOK_INVALID_MSG = "That book information is invalid";

    private final static String LIST_BOOKS = "1";
    private final static String LIST_MOVIES = "2";
    private final static String CHECK_OUT_BOOK = "3";
    private final static String RETURN_BOOK = "4";
    private final static String CHECK_OUT_MOVIE = "5";
    private final static String USER_INFORMATION = "6";
    private final static String USER_LOGOUT = "7";
    private final static String QUIT = "8";

    private final static String USER_LOGIN = "1";
    private final static String LIBRARIAN_LOGIN = "2";
    private final static String CHECK_OUT_RECORD = "1";
    private final static String LIBRARIAN_LOGOUT = "2";
    private final static String QUIT_SYSTEM = "3";

    private final static int INDEX_BOOK_NAME = 0;
    private final static int INDEX_AUTHOR = 1;
    private final static int INDEX_PUBLISH_YEAR = 2;

    private final static int INDEX_MOVIE_NAME = 0;
    private final static int INDEX_MOVIE_YEAR = 1;
    private final static int INDEX_DIRECTOR = 2;

    private InputReader reader;
    private Librarian librarian;
    private UserAccounts userAccounts;

    public Menu(InputReader reader) {
        this.reader = reader;
        librarian = new Librarian();
        userAccounts = new UserAccounts();
    }

    public boolean userOrLibrarianLogin() {
        System.out.print("1. User\n2. Librarian\n3. Quit\nPlease enter your choice(1～3):\n");
        String choice = reader.readChooseUserOrLibrarian();
        if(choice.equals(USER_LOGIN)) {
            while (!userLogin()){}
            while(userProcessAccordingToOption()) {}
            return false;
        }
        if(choice.equals(LIBRARIAN_LOGIN)) {
            while(librarianProcessAccordingToOption()) {}
            return false;
        }
        if(choice.equals(QUIT_SYSTEM)) return false;

        return userOrLibrarianLogin();
    }

    public boolean userLogin() {
        System.out.print("Please enter your libraryNumber and password,only can be digits,format as:111-2222,123456\n");
        String inputLibraryNumberAndPassword = reader.readLibraryNumberAndPassword();
        if(!inputLibraryNumberAndPassword.equals("That libraryNumber or password is invalid")) {
            String[] str = inputLibraryNumberAndPassword.split(",");
            return  userAccounts.login(str[0], str[1]);
        } else {
            return false;
        }
    }

    public boolean librarianProcessAccordingToOption(){
        System.out.print("1. Check Out Record\n2. Logout\n3. Quit\nPlease enter your choice(1～3):\n");
        boolean result = true;
        switch (reader.readLibrarianOption()) {
            case CHECK_OUT_RECORD:
                showCheckOutBookRecord();
                break;

            case LIBRARIAN_LOGOUT:
                result = userOrLibrarianLogin();
                break;

            case QUIT_SYSTEM:
                result = false;
                break;

            default:
                System.out.print(OPTION_INVALID_PROMPT_MSG);
                break;
        }
        return result;
    }

    public boolean userProcessAccordingToOption() {
        System.out.print("1. List Books\n2. List Movies\n3. Check Out Book\n4. Return Book\n5. Check Out Movie\n"
            + "6. User Information\n7. User Logout\n8. Quit\nPlease enter your choice(1～8):\n");
        boolean result = true;
        switch (reader.readUserOption()) {
            case LIST_BOOKS:
                printBooksList();
                break;

            case LIST_MOVIES:
                printMoviesList();
                break;

            case CHECK_OUT_BOOK:
                checkOutBook();
                break;

            case RETURN_BOOK:
                returnBook();
                break;

            case CHECK_OUT_MOVIE:
                checkOutMovie();
                break;

            case USER_INFORMATION:
                System.out.print(userAccounts.showUserInformation());
                break;

            case USER_LOGOUT:
                result = userOrLibrarianLogin();
                break;

            case QUIT:
                result = false;
                break;

            default:
                System.out.print(OPTION_INVALID_PROMPT_MSG);
                break;
        }
        return result;

    }

    public void printBooksList() {
        System.out.println(librarian.getLibrary().toBooksString());
    }

    public void printMoviesList() {
        System.out.println(librarian.getLibrary().toMoviesString());
    }

    public void showCheckOutBookRecord() {
        System.out.print(librarian.showBookCheckOutRecord());
    }

    public void checkOutBook() {
        while (true) {
            System.out.print("Please input book you want to check out,the format as:<book name>,author,publishedYear\n");
            String bookInfo = reader.readBook();
            if(!bookInfo.equals(BOOK_INVALID_MSG)
                && librarian.checkOutBook(transformBookInfoToObjectBook(bookInfo))) {
                System.out.print("Thank you! Enjoy the book.\n");
                break;

            } else {
                System.out.print("That book is not available.\n");
            }
        }
    }

    public void returnBook() {
        System.out.print("Please input book you want to return,the format as:<book name>,author,publishedYear\n");
        String bookInfo = reader.readBook();
        if(!bookInfo.equals(BOOK_INVALID_MSG) && librarian.returnBook(transformBookInfoToObjectBook(bookInfo))) {
            System.out.print("Thank you for returning the book.\n");

        } else {
            System.out.print("That is not a valid book to return.\n");
        }
    }

    public void checkOutMovie() {
        while (true) {
            System.out.print("Please input movie you want to check out,the format as:<movie name>,year,director\n");
            String movieInfo = reader.readMovie();
            if(!movieInfo.equals("That movie information is invalid")
                && librarian.checkOutMovie(transformMovieInfoToObjectMovie(movieInfo))) {
                System.out.print("Thank you! Enjoy the movie.\n");
                break;

            } else {
                System.out.print("That movie is not available.\n");
            }
        }
    }

    public void init() {
        System.out.println("Welcome to Biblioteca!");
        while (userOrLibrarianLogin()) { }
        System.out.print("Goodbye! welcome to the next time!\n");
    }

    public Book transformBookInfoToObjectBook(String bookInfo) {
        String[] bookInfoArray = bookInfo.split(",");
        return new Book(bookInfoArray[INDEX_BOOK_NAME].substring(1, bookInfoArray[INDEX_BOOK_NAME].length() - 1),
            bookInfoArray[INDEX_AUTHOR], Integer.valueOf(bookInfoArray[INDEX_PUBLISH_YEAR]));
    }

    public Movie transformMovieInfoToObjectMovie(String movieInfo) {
        String[] movieInfoArray = movieInfo.split(",");
        return new Movie(movieInfoArray[INDEX_MOVIE_NAME].substring(1, movieInfoArray[INDEX_MOVIE_NAME].length() - 1),
            Integer.valueOf(movieInfoArray[INDEX_MOVIE_YEAR]), movieInfoArray[INDEX_DIRECTOR],"");
    }

}

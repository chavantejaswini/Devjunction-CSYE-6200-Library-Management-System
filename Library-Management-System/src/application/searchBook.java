package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Code.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class searchBook implements Initializable {
    
    @FXML private TextField textBookSearch;
    @FXML private TableView<Book> searchTable;
    @FXML private TableColumn<Book, String> nameColumn;
    @FXML private TableColumn<Book, String> isbnColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, Integer> pagesColumn;
    
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pageNumber"));
        
        // Load books
        loadBooks();
        
        // Setup search functionality
        setupSearch();
    }
    
    private void loadBooks() {
        List<Book> books = SceneController.admin.searchBook("");
        bookList.clear();
        bookList.addAll(books);
        searchTable.setItems(bookList);
    }
    
    private void setupSearch() {
        FilteredList<Book> filteredData = new FilteredList<>(bookList, p -> true);
        
        textBookSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(book -> {
                // If filter text is empty, show all books
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Convert to lower case for case-insensitive search
                String lowerCaseFilter = newValue.toLowerCase();
                
                // Check all fields for match
                if (book.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (book.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (book.getISBN().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(book.getPageNumber()).contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        
        SortedList<Book> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(searchTable.comparatorProperty());
        searchTable.setItems(sortedData);
    }
}
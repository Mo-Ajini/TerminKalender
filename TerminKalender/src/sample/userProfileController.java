package sample;


import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 *
 * @brief Die userProfileController Klasse ermoeglicht dem Benutzer das Erstellen, Bearbeiten und Loeschen von Termine sowie Einladung an anderen
 * Benutzern Schicken.
 *
 */


public class userProfileController implements Initializable {

    // Textfeld, in das die Termin_ID eingegeben wird.
    @FXML
    private TextField idField;

    // Textfeld, in das den Namen von Einladungssender eingegeben wird.
    @FXML
    private TextField souField;

    // Textfeld, in das den Namen von Einladungsempfaenger eingegeben wird.
    @FXML
    private TextField destField;

    // Textfeld, in das das Datum eingegeben wird.
    @FXML
    private DatePicker datetField;

    // Textfeld, in das den Terminszweck eingegeben wird.
    @FXML
    private TextField detailsField;

    @FXML
    private TextField apprv;

    // Buttom fuer das Senden von Einladungen und Erstellen von Terminen.
    @FXML
    private Button insertButton;

    // Buttom den Termin zu aenderen.
    @FXML
    private Button updateButton;

    // Buttom den Termin zu loeschen.
    @FXML
    private Button deleteButton;

    // Buttom um alle Textfelder zu leeren.
    @FXML
    private Button refreshButton;

    // Button um die Einladung zu akzeptieren oder zu ablehnen.
    @FXML
    private Button Invitation;

    @FXML
    private TextField searchButton;

    ObservableList<Books> dataList;


    @FXML
    private Button logoutButton;


    // Textfeld fuer die Termin_ID, wird bei der Statusaenderung von Einladungen benutzt.
    @FXML
    private TextField idField1;



    // Kombinationsfeld, um die die Einladungstatus zu aenderen.
    @FXML
    private ComboBox apprv1;


    // Kalenderview wo alle die Termine gezeigt werden.
    @FXML
    private TableView<Books> TableView;


    // Saeule fuer die Termin_ID im Kalenderview.
    @FXML
    private TableColumn<Books, Integer> idColumn;


    // Saeule fuer den Sender der Einladung im Kalenderview.
    @FXML
    private TableColumn<Books, String> souColumn;


    // Saeule fuer den Empfaenger der Einladung im Kalenderview.
    @FXML
    private TableColumn<Books, String> destColumn;


    // Saeule fuer das Datum von den Termin oder die Einladung im Kalenderview.
    @FXML
    private TableColumn<Books, String> datetColumn;


    // Saeule fuer den Terminzweck im Kalenderview.
    @FXML
    private TableColumn<Books, String> detailsColumn;


    // Saeule fuer den Status der Einladung im Kalenderview.
    @FXML
    private TableColumn<Books, String> apprvColumn;

    //for sending termin


    // Eine Methode, um das Senden von Einladungen und Erstellen von Terminen zu ermoeglichen.

    @FXML
    private void insertButton() {
        //String query = "insert into termininfo values("+idField.getText()+",'"+souField.getText()+"','"+destField.getText()+"','"+datetField.getValue()+"','"+detailsField.getText()+"')";
        String notify = "sent";
        //String query = "insert into termininfo1 values("+idField.getText()+",'"+readUserInfo()+"','"+destField.getText()+"','"+datetField.getValue()+"','"+detailsField.getText()+"', '"+apprv.getText()+"')";
        String query = "insert into termininfo1 values("+idField.getText()+",'"+readUserInfo()+"','"+destField.getText()+"','"+datetField.getValue()+"','"+detailsField.getText()+"', '"+notify+"')";

        executeQuery(query);
        showBooks();
    }

    // Eine Methode, um das Aenderen von Terminen zu ermoeglichen.
    @FXML
    private void updateButton() throws RemoteException, Exception{
        //String query = "UPDATE termininfo1 SET Sou='"+souField.getText()+"',Dest='"+destField.getText()+"',Datet='"+datetField.getValue()+"',Details='"+detailsField.getText()+"', Apprv='"+apprv.getText()+"' WHERE ID="+idField.getText()+"";
        String query = "UPDATE termininfo1 SET Dest='"+destField.getText()+"',Datet='"+datetField.getValue()+"',Details='"+detailsField.getText()+"' WHERE ID="+idField.getText()+"";

        executeQuery(query);
        showBooks();
    }


    // Eine Methode, um das Loeschen von Terminen zu ermoeglichen.
    @FXML
    private void deleteButton() throws RemoteException, Exception{
        String query = "DELETE FROM termininfo1 WHERE ID="+idField.getText()+"";
        executeQuery(query);
        showBooks();
    }


    // Eine Methode, um eine Einladung zu akzeptieren oder zu ablehnen.
    @FXML
    private void Invitation() throws RemoteException, Exception{
        String query = "UPDATE termininfo1 SET Apprv='"+apprv1.getValue()+"' WHERE ID="+idField1.getText()+"";
        executeQuery(query);
        showBooks();
        /*String query = "DELETE FROM termininfo1 WHERE ID="+idField1.getText()+"";
        executeQuery(query);
        showBooks();

         */

    }

    // Eine Methode, um die Textfelder zu leeren.
    @FXML
    private void refreshButton() throws RemoteException, Exception{
        idField.clear();
        //souField.clear();
        destField.clear();
        //datetField.clear();
        detailsField.clear();
    }

    //Search Box
    @FXML
    private void searchButton() throws RemoteException, Exception{

        datetColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("datet"));

        TableView.setItems(dataList);
        FilteredList<Books> filteredData = new FilteredList<>(dataList, b -> true);
        searchButton.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(person.getDatet()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });

        });

        SortedList<Books> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableView.comparatorProperty());
        TableView.setItems(sortedData);



    }



    // Eine Methode, um das Auslogen zu ermoeglichen.
    @FXML
    private void logoutButton() throws RemoteException, Exception{
        logoutButton.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.setTitle("Terminkalender");
            mainStage.show();
        } catch (Exception e) {
            //np

        }
    }

    // Methode, um SQL mit meiner Datenbank zu verbinden.
    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        apprv1.getItems().addAll("accept", "reject");

        showBooks();
    }



    // Zugriff von dieser Klasse auf die Datenbank erhalten.
    public Connection getConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/termin","root","");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    //Datei für einen bestimmten Benutzer lesen. Es hilft, Informationen zu einem bestimmten angemeldeten Benutzer anzuzeigen.
    private String readUserInfo(){
        BufferedReader reader;
        String file = "userinfo.txt";
        String user = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line =reader.readLine();
            if (line != null){
                user = line.strip();
            }
            else {user = "admin";}
        }catch (IOException e){
            e.printStackTrace();
        }
        return user;
    }



    // Eine Methode, um die Termine von den aktuell eingeloten Benutzer auf die Kalenderview zu zeigen.
    public ObservableList<Books> getBooksList(){
        ObservableList<Books> booksList = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String userName = readUserInfo();
        String all;
        String query = "SELECT * FROM termininfo1 where sou = '" +userName + "' or dest = '" +userName + "' or dest = 'all'";
        //System.out.println(query);
        //System.out.println(userName);
        //String query = "SELECT termininfo.id, termininfo.sou, termininfo.dest, termininfo.datet, termininfo.details FROM termininfo INNER JOIN users ON termininfo.sou =" + userName;




        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Books books;
            while(rs.next()) {
                books = new Books(rs.getInt("Id"),rs.getString("Sou"),rs.getString("Dest"),rs.getString("Datet"),rs.getString("Details"),rs.getString("Apprv"));
                booksList.add(books);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksList;
    }


    // Methode, um die Saeulen von der Kalenderview mit den entsprechenden Informationen auszufuellen.
    public void showBooks() {
        ObservableList<Books> list = getBooksList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
        souColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("sou"));
        destColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("dest"));
        datetColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("datet"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("details"));
        apprvColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("apprv"));


        TableView.setItems(list);
    }

}

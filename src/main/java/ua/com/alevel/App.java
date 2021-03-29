package ua.com.alevel;
import java.sql.Connection;
import static ua.com.alevel.connection.DBConnector.buildConnection;
import static ua.com.alevel.helpers.HelperApp.appSuggestion;
import static ua.com.alevel.helpers.HelperApp.methodsApp;


public class App {
    public static void main(String[] args) {
        Connection connection = buildConnection();
        methodsApp(appSuggestion(),connection);
    }
}

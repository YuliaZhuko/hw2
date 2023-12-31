package db;

import settings.ISettings;
import settings.PropertiesReader;

import java.sql.*;
import java.util.Map;

public class DBConnector implements IDBConnector{

    private static  Connection connection = null;
    private static Statement statement = null;

    private Map<String, String> settings = new PropertiesReader().read();

    public DBConnector(){
      connect();
    }


    private void connect(){
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(settings.get("url") + "/" + settings.get("db_name"),
                        settings.get("db_username"),
                        settings.get("db_password"));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (statement == null) {
            try {
               statement =  connection.createStatement();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    @Override
    public ResultSet execute(String request) {
        try {
            Statement statement = connection.createStatement();
          return statement.executeQuery(request);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void executeRequest(String request){
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void close() {
        if (statement == null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    if (connection == null) {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }
}

package tables;

import db.DBConnector;
import db.IDBConnector;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbsTable {
    private IDBConnector  idbConnector = new DBConnector();

    private String tableName;

       public AbsTable(String tableName) {
       this.tableName = tableName;

    }

    private String convertMapColumnsToString (Map <String, String> columns){
      return  columns.entrySet().stream().map((Map.Entry entry) -> String.format("%s %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
    }
    private String convertMapColumnsToStringInsert (Map <String, String> columns){
        return  columns.entrySet().stream().map((Map.Entry entry) -> String.format("%s", entry.getValue()))
                .collect(Collectors.joining(", "));
    }


    public void create (Map <String,String> columns){

        String sqlRequest = String.format("create table %s (%s);", this.tableName, convertMapColumnsToString(columns));

        idbConnector.executeRequest(sqlRequest);
    }

    public void insert(Map <String,String> columns){
           String sqlRequest = String.format("insert into %s values (%s);", this.tableName,convertMapColumnsToStringInsert(columns));
          idbConnector.executeRequest(sqlRequest);
    }
    public void update(String sqlRequest){
       idbConnector.executeRequest(sqlRequest);
    }


    public void delete(Map <String,String> columns){
        String sqlRequest = String.format("drop table if exists %s", this.tableName);
        idbConnector.executeRequest(sqlRequest);
    }
    public ResultSet getData(String sqlRequest){
           return idbConnector.execute(sqlRequest);
    }

    public void print(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        for (int i = 1; i <= columnsNumber ; i++) {
            System.out.print(rsmd.getColumnName(i)+" (" + rsmd.getTableName(i)+ ")     ");
        }
        System.out.println("");
        while (resultSet.next()) {

            for (int i = 1; i <= columnsNumber; i++) {

                if (i > 1) System.out.print("  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + "            ");
            }
            System.out.println("");
        }
        System.out.println();
    }
    public void close(){
           idbConnector.close();
    }


}

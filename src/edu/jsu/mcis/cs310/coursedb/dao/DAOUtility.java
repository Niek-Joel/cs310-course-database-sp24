package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

                ResultSetMetaData rsmd = rs.getMetaData();
                
                while (rs.next()) {
                    JsonObject row = new JsonObject();   // A single record
                    for (int i=1; i <= rsmd.getColumnCount(); i++) {
                        String columnName = rsmd.getColumnName(i);
                        String columnValue = rs.getString(columnName);
                        row.put(columnName, columnValue);
                    }
                    records.add(row);
                    //System.out.println("records size = " + records.size() + " = " + row);
                }
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}

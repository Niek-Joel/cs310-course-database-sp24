package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // Specifying create query
                String query = "INSERT INTO jsu_fa24_v1.registration (studentid, termid, crn) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                
                int updateCount = ps.executeUpdate();
                if (updateCount > 0) {
                    result = true;
                }
                else {
                    result = false;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // specifying delete query
                String query = "DELETE FROM registration WHERE studentid=? AND termid=? AND crn=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);   
                
                // executing query
                int updateCount = ps.executeUpdate();
                if (updateCount > 0) {
                    result = true;
                }
                else {
                    result = false;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // specifying delete query
                String query = "DELETE FROM registration WHERE studentid=? AND termid=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                // executing query
                int updateCount = ps.executeUpdate();
                if (updateCount > 0) {
                    result = true;
                }
                else {
                    result = false;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // Specifying query
                String query = "SELECT * FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                // execute query 
                boolean hasResults = ps.execute(); 
                
                if (hasResults) {
                    rs = ps.getResultSet();
                    result = DAOUtility.getResultSetAsJson(rs);
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}

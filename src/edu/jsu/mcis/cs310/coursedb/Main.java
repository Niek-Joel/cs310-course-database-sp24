package edu.jsu.mcis.cs310.coursedb;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsoner;
import edu.jsu.mcis.cs310.coursedb.dao.*;

public class Main {
    
    private static final String USERNAME = "nobody@jsu.edu";
    
    public static void main(String[] args) {
        
        // Create DAO Objects
        
        DAOFactory daoFactory = new DAOFactory("coursedb");
        
        RegistrationDAO registrationDao = daoFactory.getRegistrationDAO();
        SectionDAO sectionDao = daoFactory.getSectionDAO();
        
        int studentid = daoFactory.getStudentDAO().find(USERNAME);
        
        // Test Connection
        
        if ( !daoFactory.isClosed() ) {
            
            System.out.println("Connected Successfully!");
            
        }
        // TESTING
        //sectionDao.find(1, "CS", "201");   // For "search"
        //registrationDao.create(1, 1, 10520);   // for "register"
        //registrationDao.delete(1, 1, 10520);   // for "drop" one cours
        //registrationDao.list(1,1);
    }
    
}
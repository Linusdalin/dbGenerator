package commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/****************************************************
 *
 *
 *          Generated Property handler
 *
 *
 */
public class PropertyManager {


    private String propertyFile = "config.properties";
    Properties prop;
    InputStream input = null;

    private static String dbConnection = null;
    private static String dbUser = null;
    private static String dbPassword = null;

    public PropertyManager(String propertyFile){

        prop = new Properties();
        this.propertyFile = propertyFile;

    }


    public String loadString(String propertyName)  {

        try {

            if(input == null)
                input = new FileInputStream(propertyFile);

            prop.load(input);

        } catch (IOException ex) {

            ex.printStackTrace();

        }

        // get the property value and return
        return prop.getProperty(propertyName);

    }

    public int loadInt(String propertyName){

        String stringValue = loadString(propertyName);
        return Integer.valueOf(stringValue);

    }

    // Generated property getter
    public String getDBConnection(){

        if(dbConnection == null)
            dbConnection = loadString("dbConnection");

        if(dbConnection == null)
            dbConnection = "jdbc:mysql://localhost:3306/no DB Set";
        return dbConnection;

    }


    // Generated property getter
    public String getDBUser(){

        if(dbUser == null)
            dbUser = loadString("dbUser");

        if(dbUser == null)
            dbUser = "root";
        return dbUser;

    }

    // Generated property getter
    public String getDBPassword(){

        if(dbPassword == null)
            dbPassword = loadString("dbPwd");

        if(dbPassword == null)
            dbPassword = "...";
        return dbPassword;

    }


}

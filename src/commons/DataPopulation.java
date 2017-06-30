package commons;

import exceptionHandling.PukkaException;
import java.sql.Connection;
import java.util.Arrays;

/******************************************************************************'''
 *
 *          Creating base demo data
 *
 *          The configuration possibilities are:
 *
 *          - clear
 *          - testData
 *
 *
 */

public class DataPopulation {

    private Connection connection;
    private boolean clear = false;
    private boolean testData = false;

    /********************************************************
     *
     *          Main function to be able to generate data from command line
     *
     *          usage: DataPopulation [clear][testData]
     *
     *              - clear     optionally clear the database first
     *              - testData  optionally generate the test data
     *
     *
     * @param par              - command line parameters
     * @throws PukkaException
     */


    public static void main(String[] par) throws PukkaException{

        DataPopulation creator = new DataPopulation();

        if(Arrays.asList(par).contains("clear"))
            creator.withClear();
        if(Arrays.asList(par).contains("testData"))
            creator.withTestData();

        creator.execute();

    }

    public DataPopulation() throws PukkaException{

        connection = ConnectionHandler.getConnection("generate data");

    }


    private DataPopulation withClear() {

        this.clear = true;
        return this;

    }

    private DataPopulation withTestData() {

        this.testData = true;
        return this;

    }



    /**********************************************************************'
     *
     *              Run the data generation given the
     *              set configuration
     *
     */

    private void execute() {


        if(clear){
            clearDatabase( connection );
            System.out.println(" -- Cleared data from database");
        }

        addBaseData( connection );

        System.out.println(" -- Generated static demo data (accounts etc)");

        if(testData){

            addTestData( connection );
            System.out.println(" -- Populated test data");
        }

    }

    /* Permanent content after this point (Do not remove this line !!!) */

    private void clearDatabase( Connection connection) {

        // Manually add permanent code here

    }


    private void addBaseData( Connection connection ) {

        // Manually add permanent code here

    }

    private void addTestData( Connection connection ) {

        // Manually add permanent code here


    }

}


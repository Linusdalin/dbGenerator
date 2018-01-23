package activeProjects;

import databaseModel.DataElement;
import databaseModel.DataTable;
import databaseModel.DataType;
import databaseModel.Project;
import parameter.SystemParameter;
import service.ServiceInterface;

/****************************************************************************
 *
 *          The Twigeo WaterDrop project
 *
 *
 */
public class PukkaMessaging extends Project {

    public static void main(String[] args){

        new PukkaMessaging();

    }
    private static final DataTable[] tables = {

            new DataTable("VIPPayment", "vip", "VIP payments to match")

                    .withDataElement(new DataElement(DataType.TIMESTAMP, "date"))
                    .withDataElement(new DataElement(DataType.STRING, "userId").setIndex())
                    .withDataElement(new DataElement(DataType.INT, "coins"))
                    .withDataElement(new DataElement(DataType.BOOLEAN, "paid"))

    };

    private static final ServiceInterface[] services = {


    };

    private static final SystemParameter[] parameters = {



    };

    public PukkaMessaging(){

        super("PukkaMessaging", "C:\\Users\\Linus\\Documents\\GitHub\\PukkaMessaging\\src", "commons");

        withDatabase("C:\\Users\\Linus\\Documents\\GitHub\\PukkaMessaging\\scripts", "pukka");
        withServiceLayer("C:\\Users\\Linus\\Documents\\GitHub\\PukkaMessaging\\src");
        withTestDirectory("test");
        withTables(tables);
        withServices(services);
        withParameters(parameters);

        // Adding standard modules
        //withModule(new ScheduleModule());
        //withModule(new UserModule( ));

        // Generate the actual files
        generate();

    }


}

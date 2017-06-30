package modules;

import databaseModel.DataElement;
import databaseModel.DataTable;
import databaseModel.DataType;
import service.MandatoryParameter;
import service.RestService;
import service.ServiceInterface;
import service.SingleActionService;

/********************************************************************
 *
 *              Basic module for user management
 *
 */

public class UserModule extends GenericModule implements ModuleInterface {

    private static final String targetPackage = "userManagement";


    private static final ServiceInterface[] services = {

            new RestService("Organisation", "services/userServices")
                    .withLoginValidation()
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "application"))
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "name")),

            new RestService("Invite", "services/userServices")
                    .withLoginValidation()
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "user")),

            new SingleActionService("Password", "services/waterDrop")
                    .withLoginValidation()
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "password"))
                    .withInclude("userManagement")
                    .withInclude("accountManagement")
            ,


    };

    private static final DataTable[] tables = {

            new DataTable("User", targetPackage, "System end user")

                    .withDataElement(new DataElement(DataType.STRING, "application"))
                    .withDataElement(new DataElement(DataType.INT, "organisation"))
                    .withDataElement(new DataElement(DataType.STRING, "userName"))
                    .withDataElement(new DataElement(DataType.STRING, "name"))
                    .withDataElement(new DataElement(DataType.STRING, "email"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "registered"))
                    .withOrderByColumn("id")
                    //.withRenderer(EDIT, "boComponents")
            ,

            new DataTable("Organisation", targetPackage, "Organization that a user belongs to")

                    .withDataElement(new DataElement(DataType.STRING, "application"))
                    .withDataElement(new DataElement(DataType.STRING, "name"))
                    //.withRenderer(EDIT, "boComponents")
            ,

            new DataTable("Membership", targetPackage, "The connection between a user and an organization, with a membership type")

                    .withDataElement(new DataElement(DataType.INT, "user"))
                    .withDataElement(new DataElement(DataType.INT, "organisation"))
                    .withDataElement(new DataElement(DataType.STRING, "role"))
                    //.withRenderer(EDIT, "boComponents")
            ,

            new DataTable("Access", targetPackage, "The user access")

                    .withDataElement(new DataElement(DataType.STRING, "application"))
                    .withDataElement(new DataElement(DataType.INT, "userId").setIndex())
                    .withDataElement(new DataElement(DataType.STRING, "activationCode"))
                    .withDataElement(new DataElement(DataType.STRING, "password"))
                    .withDataElement(new DataElement(DataType.STRING, "salt"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "activation").setIndex())
                    .withDataElement(new DataElement(DataType.BOOLEAN, "active"))
                    .withDataElement(new DataElement(DataType.STRING, "role"))
                    .withOrderByColumn("activation")
                    //.withRenderer(EDIT, "boComponents")
            ,

            new DataTable("Session", targetPackage, "A time dependent session")

                    .withDataElement(new DataElement(DataType.INT, "userId").setIndex())
                    .withDataElement(new DataElement(DataType.INT, "organisation").setIndex())
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "timestamp").setIndex())
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "lastAccess"))
                    .withDataElement(new DataElement(DataType.STRING, "sessionToken"))
                    .withDataElement(new DataElement(DataType.STRING, "role"))                // role from the user account access
                    .withDataElement(new DataElement(DataType.BOOLEAN, "active"))
                    .withOrderByColumn("timestamp")
                    //.withRenderer(EDIT, "boComponents")
            ,



    };


    private static final StaticFile[] files = {

            new StaticFile("ScheduleManager", "scheduler"),
            new StaticFile("PukkaScheduler", "scheduler"),
            new StaticFile("PukkaJob", "scheduler"),
    };


    public UserModule(  ){

        super(services, tables, files);

    }

}

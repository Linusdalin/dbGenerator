package activeProjects;

import databaseModel.DataElement;
import databaseModel.DataTable;
import databaseModel.DataType;
import databaseModel.Project;
import parameter.ParameterType;
import parameter.SystemParameter;
import service.*;

/****************************************************************************
 *
 *          The Twigeo WaterDrop project
 *
 *
 */
public class PukkaACS extends Project {

    private static final String targetPackage = "acs/userManagement";

    public static void main(String[] args){

        new PukkaACS();

    }
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

            new DataTable("AccountAccess", targetPackage, "Access to an account for a user")

                    .withDataElement(new DataElement(DataType.INT, "user"))
                    .withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.STRING, "role")),           // guest, user, admin


            new DataTable("ActiveAccount", targetPackage, "Active account for a user")

                    .withDataElement(new DataElement(DataType.INT, "user"))
                    .withDataElement(new DataElement(DataType.INT, "account")),





    };





    private static final ServiceInterface[] services = {

            new RestService("Organisation", "acs/services")
                    .withLoginValidation()
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "application"))
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "name")),

            new RestService("Invite", "acs/services")
                    .withLoginValidation()
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "user")),

            new SingleActionService("Password", "acs/services")
                    .withLoginValidation()
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "password"))
                    .withInclude("acs.userManagement")
            ,
            new RestService("Account", "services/waterDrop")
                    .withLoginValidation()
                    .withGetParameter(new OptionalParameter(DataType.BOOLEAN, "all"))            // As admin user, you can get all accounts
                    .withPostParameter(new OptionalParameter(DataType.STRING, "name"))
                    .withPostParameter(new OptionalParameter(DataType.STRING, "facebookAccount"))
                    .withPostParameter(new OptionalParameter(DataType.BOOLEAN, "active"))
                    .withPostParameter(new OptionalParameter(DataType.STRING, "slackChannel"))
                    .withPostParameter(new OptionalParameter(DataType.STRING, "adminEmail"))
                    .withInclude("campaignData")
                    .withInclude("acs.userManagement")
                    .withInclude("facebook")
                    .withInclude("randomization")
                    .withInclude("tagging")
            ,



    };

    private static final SystemParameter[] parameters = {

            new SystemParameter("dbPwd", ParameterType.String, "noPassword"),
            new SystemParameter("dbUser", ParameterType.String, "noPassword"),
            new SystemParameter("dbConnection", ParameterType.String, "jdbc:mysql://localhost:3306/no DB Set"),

    };


    public PukkaACS(){

        super("PukkaACS", "C:\\Users\\Linus\\Documents\\GitHub\\PukkaCommons\\src", "commons");

        withDatabase("C:\\Users\\Linus\\Documents\\GitHub\\PukkaCommons\\scripts", "pukka_commons");
        withServiceLayer("C:\\Users\\Linus\\Documents\\GitHub\\PukkaCommons\\src");
        withTestDirectory("test");
        withTables(tables);
        withServices(services);
        withParameters(parameters);

        // Generate the actual files
        generate();

    }


}

package modules;

import databaseModel.DataElement;
import databaseModel.DataTable;
import databaseModel.DataType;
import service.OptionalParameter;
import service.RestService;
import service.ServiceInterface;

/********************************************************************
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2017-03-07
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */

public class ScheduleModule extends GenericModule implements ModuleInterface {

    private static final String targetPackage = "scheduler";

    private static final ServiceInterface[] services = {

        new RestService("Schedule", "services/scheduler")
                //.withLoginValidation()
                .withInclude("scheduler")
                .withInclude("report"),

    new RestService("TaskLog", "services/scheduler")
                //.withLoginValidation()
                .withGetParameter(new OptionalParameter(DataType.STRING, "filter"))
                .withInclude("scheduler")


    };

    private static final DataTable[] tables = {

        new DataTable("Task", targetPackage, "List of all active scheduled tasks")

                .withDataElement(new DataElement(DataType.STRING, "name"))
                .withDataElement(new DataElement(DataType.TEXT, "className"))
                .withDataElement(new DataElement(DataType.STRING, "cronSchedule"))
                .withDataElement(new DataElement(DataType.BOOLEAN, "isScheduled"))
                .withDataElement(new DataElement(DataType.TIMESTAMP, "lastChange"))
                .withDataElement(new DataElement(DataType.TEXT, "parameters"))
                .withOrderByColumn("lastChange"),

        new DataTable("TaskLog", targetPackage, "Schedule log")

                .withDataElement(new DataElement(DataType.STRING, "task"))
                .withDataElement(new DataElement(DataType.STRING, "source"))
                .withDataElement(new DataElement(DataType.STRING, "outcome"))
                .withDataElement(new DataElement(DataType.TIMESTAMP, "timestamp"))
                .withDataElement(new DataElement(DataType.INT, "duration"))
                .withDataElement(new DataElement(DataType.LONG_TEXT, "text"))
                .withDataElement(new DataElement(DataType.TEXT, "parameters"))
                .withOrderByColumn("timestamp")


    };


    private static final StaticFile[] files = {

            new StaticFile("ScheduleManager", "scheduler"),
            new StaticFile("PukkaScheduler", "scheduler"),
            new StaticFile("PukkaJob", "scheduler"),
    };


    public ScheduleModule( ){

        super(services, tables, files);
    }

}

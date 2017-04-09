package modules;

import databaseModel.DataElement;
import databaseModel.DataTable;
import databaseModel.DataType;
import service.MandatoryParameter;
import service.PukkaService;
import service.ServiceInterface;

/********************************************************************
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2017-03-07
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */

public class ScheduleModule extends GenericModule implements ModuleInterface {

    private static final ServiceInterface[] services = {

        new PukkaService("Schedule", "services/scheduler")
                //.withLoginValidation()
                .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                .withPostParameter(new MandatoryParameter(DataType.INT, "account"))
                .withInclude("scheduler"),

        new PukkaService("TaskLog", "services/scheduler")
                //.withLoginValidation()
                .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                .withInclude("scheduler")


    };

    private static final DataTable[] tables = {

        new DataTable("Task", "scheduler", "List of all active scheduled tasks")

                .withDataElement(new DataElement(DataType.STRING, "name"))
                .withDataElement(new DataElement(DataType.INT, "account"))
                .withDataElement(new DataElement(DataType.STRING, "className"))
                .withDataElement(new DataElement(DataType.STRING, "cronSchedule"))
                .withDataElement(new DataElement(DataType.BOOLEAN, "isScheduled"))
                .withDataElement(new DataElement(DataType.TIMESTAMP, "lastChange"))
                .withOrderByColumn("lastChange"),

        new DataTable("TaskLog", "scheduler", "Schedule log")

                .withDataElement(new DataElement(DataType.STRING, "taskName"))
                .withDataElement(new DataElement(DataType.INT, "account"))
                .withDataElement(new DataElement(DataType.TIMESTAMP, "timestamp"))
                .withDataElement(new DataElement(DataType.STRING, "outcome"))
                .withDataElement(new DataElement(DataType.LONG_TEXT, "text"))
                .withOrderByColumn("timestamp")


    };


    private static final StaticFile[] files = {

            new StaticFile("ScheduleManager", "scheduler"),
            new StaticFile("PukkaScheduler", "scheduler"),
            new StaticFile("PukkaJob", "scheduler"),
    };


    public ScheduleModule(){

        super(services, tables, files);
    }

}

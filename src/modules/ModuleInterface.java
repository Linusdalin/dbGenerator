package modules;

import databaseModel.DataTable;
import service.ServiceInterface;

import java.util.List;

/***************************************************************
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2017-03-07
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
public interface ModuleInterface {

    List<ServiceInterface> getServices();
    List<DataTable> getTables();
    List<StaticFile> getFiles();

}

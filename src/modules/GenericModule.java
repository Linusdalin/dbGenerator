package modules;

import databaseModel.DataTable;
import service.ServiceInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/****************************************************************************
 *
 *          Generic module.
 *
 *          This contains:
 *
 *           - data tables
 *           - services
 */
public abstract class GenericModule implements ModuleInterface {

    private List<ServiceInterface> moduleServices = new ArrayList<>();
    private List<DataTable> moduleTables = new ArrayList<>();
    private List<StaticFile> moduleFiles = new ArrayList<>();    // TODO: Not implemented module files


    GenericModule(ServiceInterface[] services, DataTable[] tables, StaticFile[] files){

        Collections.addAll(moduleServices, services);
        Collections.addAll(moduleTables, tables);
        Collections.addAll(moduleFiles, files);
    }


    @Override
    public List<ServiceInterface> getServices() {

        return this.moduleServices;
    }

    @Override
    public List<DataTable> getTables() {

        return this.moduleTables;
    }

    @Override
    public List<StaticFile> getFiles() {

        return this.moduleFiles;
    }


}

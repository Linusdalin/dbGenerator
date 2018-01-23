package service;

import databaseModel.JavaCodeComponent;

import java.util.ArrayList;
import java.util.List;

/***************************************'
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2017-02-10
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */

abstract class GenericService extends JavaCodeComponent implements ServiceInterface{

    protected String name;
    protected String packageName;
    protected String packagePath;
    protected String comment = "";

    protected boolean loggedIn = false;
    protected boolean rwAccess = false;
    protected List<ParameterInterface> postParameters = new ArrayList<>();
    protected List<ParameterInterface> getParameters = new ArrayList<>();
    protected List<String> includeDirectories = new ArrayList<>();


    public ServiceInterface withPostParameter(ParameterInterface parameter) {

        postParameters.add(parameter);
        return this;

    }

    public ServiceInterface withGetParameter(ParameterInterface parameter) {

        getParameters.add(parameter);
        return this;

    }

    public String getPackagePath() {
        return packagePath;
    }


    public String getName() {
        return name;
    }

    public ServiceInterface withLoginValidation() {
        this.loggedIn = true;
        return this;
    }

    public ServiceInterface withRWRequirement() {
        this.rwAccess = true;
        return this;
    }

    public ServiceInterface withInclude(String includeDirectory) {

        includeDirectories.add(includeDirectory.replaceAll("/", "."));
        return this;

    }


    protected String getParameterAssignment(List<ParameterInterface> parameters) {

        StringBuffer java = new StringBuffer();

        for (ParameterInterface parameter : parameters) {

            String restriction = parameter.getRestriction().name();

            switch (parameter.getType()) {

                case INT:
                case REFERENCE:
                    java.append("            int _"+parameter.getName()+" = (int)get"+restriction+"Long(\""+parameter.getName()+"\", req"+(parameter.getRestriction() == ParameterRestriction.Optional?", -1":"")+");\n");
                    break;
                case STRING:
                    java.append("            String _"+parameter.getName()+" = get"+restriction+"String(\""+parameter.getName()+"\", req);\n");
                    break;
                case TEXT:
                    java.append("            String _"+parameter.getName()+" = get"+restriction+"String(\""+parameter.getName()+"\", req);\n");
                    break;
                case LONG_TEXT:
                    java.append("            String _"+parameter.getName()+" = get"+restriction+"String(\""+parameter.getName()+"\", req);\n");
                    break;
                case TIMESTAMP:
                    java.append("            Timestamp _"+parameter.getName()+" = get"+restriction+"Date(\""+parameter.getName()+"\", req);\n");
                    break;
                case BOOLEAN:
                    java.append("            boolean _"+parameter.getName()+" = get"+restriction+"Boolean(\""+parameter.getName()+"\", req);\n");

                    break;
            }


        }


        return java.toString();
    }



    protected String getLoggedInValidation(boolean loggedInValidation) {
        if(loggedInValidation)
            return  "            Credentials credentials = getValidatedUser(req, resp, connection);\n" +
                    "            if(credentials.getUser() < 0)\n" +
                    "                return;\n" +
                    "\n";

        return singleLineComment("No logged in validation. Service is publicly available", 3);

    }



    protected String includes(String corePackage) {

        StringBuffer includePackages = new StringBuffer();

        includePackages.append("import "+corePackage.replaceAll("/", ".")+".*;\n");
        includePackages.append("import net.sf.json.*;\n");
        includePackages.append("import javax.servlet.http.*;\n");
        includePackages.append("import java.io.IOException;\n");
        includePackages.append("import java.sql.*;\n");
        includePackages.append("import java.util.*;\n\n");
        includePackages.append("import javax.servlet.annotation.WebServlet;\n");
        includePackages.append("import exceptionHandling.*;\n");
        includePackages.append("import logger.Logger;\n");
        includePackages.append("import static commons.Restrictions.*;\n\n");

        for (String directory : includeDirectories) {
            includePackages.append("import " + directory.replaceAll("/", ".") + ".*;\n");
        }


        return includePackages.toString();
    }


    public ServiceInterface withComment(String comment) {
        this.comment = comment;
        return this;
    }


    protected String getServiceMapping(String name, String location) {

        return "    <servlet>\n" +
                "        <servlet-name>"+name+"</servlet-name>\n" +
                "        <servlet-class>"+location+"."+name+"Service</servlet-class>\n" +
                "    </servlet>\n" +
                "    <servlet-mapping>\n" +
                "        <servlet-name>"+ name +"</servlet-name>\n" +
                "        <url-pattern>/"+name+"</url-pattern>\n" +
                "    </servlet-mapping>\n";
    }

}

package service;

import java.util.List;

/******************************************************************''
 *
 *              A service to generate
 *
 *              Single action service will perform a post action
 *
 *
 *
 */
public class SingleActionService extends GenericService implements ServiceInterface{


    public SingleActionService(String name, String packagePath) {

        this(name, packagePath, true);
    }

    public SingleActionService(String name, String packagePath, boolean addId) {
        this.name = name;
        this.packagePath = packagePath;
        this.packageName = packagePath.replaceAll("/", ".");

    }


    public String generateServiceClass(String projectName, String corePackage){

        StringBuffer java = new StringBuffer();

        java.append(packageStatement(packageName));

        java.append(includes(corePackage));

        java.append(classComment(name, "\n" + " *   " +  comment +"\n\n" +getServiceMapping(name, packagePath)));
        //java.append(classComment(name, "\n\n" + comment +"\n\n" +getServiceMapping(name, packagePath)));


        java.append(classHead(name + "Service", projectName +"Servlet", null, 0));

        java.append("   private static final boolean Trace = true;\n\n");
        java.append(getMethod(name, loggedIn));
        java.append(postMethod(name, loggedIn));


        return java.toString();

    }

    private String functionDeclaration( boolean loggedInValidation) {

        return
                "    public ActionOutcome _"+name+"Action( "+ actualParameters(getParameters)+ (loggedInValidation?"Credentials credentials, ":"") +" Connection connection )throws ServiceUsageException {\n\n" +
                "       " + singleLineComment("Manual function ") + "\n\n" +
                "       throw new ServiceUsageException(\"Not implemented service function\");\n" +
                "    }\n\n";
                //"    public JSONObject _"+name+"PostFunction( "+ actualParameters(postParameters)+ (loggedInValidation?"Credentials credentials, ":"") +" Connection connection )throws ServiceUsageException {\n\n" +
                //"       " + singleLineComment("Manual function ") + "\n\n" +
                //"       throw new ServiceUsageException(\"Not implemented service function\");\n" +
                //"    }\n\n";

    }

    private String getMethod(String name, boolean loggedInValidation) {
        return "    @Override\n" +
               "    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {\n\n" +
                "       doPost(req, resp);" +
                "\n" +
                "   }\n\n";
    }

    private String postMethod(String name, boolean loggedInValidation) {
        return "    @Override\n" +
                "    public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {\n\n" +
                "        try {\n" +
                "\n" +
                "            _log.info(\"In "+name+"Service/POST\");\n" +
                "\n" +
                "            Connection connection = ConnectionHandler.getConnection(\"POST "+ name +"\");\n" +
                "\n" +
                getLoggedInValidation(loggedInValidation) +
                getParameterAssignment(postParameters) +
                //getParameterTrace(getParameters) +
                "\n" +
                "            setRespHeaders(resp, 0);\n" +
                "            resp.setContentType(\"application/json\");\n" +
                "\n" +
                "            ActionOutcome result = _"+ name+"Action( "+ getParameterList(postParameters) + (loggedInValidation?"credentials, ":"") +" connection );\n" +
                "            _log.debug(\" -- Returning \" + result.toString());\n" +
                "            resp.getWriter().write(result.toJSON().toString());\n" +
                "\n" +
                "            connection.close();\n\n" +
                "\n" +
                "        } catch (PukkaException e) {\n" +
                "            _log.exception( e );\n" +
                "            resp.getWriter().write(new ActionOutcome(ActionOutcome.Outcome.error, \"Something went wrong. See the logs\").toJSON().toString());\n" +
                "\n" +
                "        } catch (ServiceUsageException e) {\n" +
                "            _log.exception( e );\n" +
                "            resp.getWriter().write(new ActionOutcome(ActionOutcome.Outcome.error, e.getMessage()).toJSON().toString());\n" +
                "\n" +
                "        } catch (Exception e) {\n" +
                "\n" +
                "            _log.exception( e );\n" +
                "            resp.getWriter().write(new ActionOutcome(ActionOutcome.Outcome.error, \"Something went wrong. See the logs\").toJSON().toString());\n" +
                "        }\n" +
                "\n" +
                "}\n\n";
    }




    private String getParameterList(List<ParameterInterface> parameters) {

        StringBuffer parameterList = new StringBuffer();

        for (ParameterInterface parameter : parameters) {

            parameterList.append(" _" + parameter.getName() + ", ");
        }


        return parameterList.toString();
    }

    private String actualParameters(List<ParameterInterface> parameters) {

        StringBuffer parameterList = new StringBuffer();

        for (ParameterInterface parameter : parameters) {

            parameterList.append(parameter.getType().getJavaRepresentation() + " _" + parameter.getName() + ", ");
        }


        return parameterList.toString();
    }


    public String getPlaceholderContent() {

        StringBuffer java = new StringBuffer();
        java.append(functionDeclaration(loggedIn));
        java.append(classEnd(name, 0));
        return java.toString();
    }


    public String getRestJSONImpl() {

        StringBuffer java = new StringBuffer();
        java.append(functionDeclaration(loggedIn));
        java.append(classEnd(name, 0));
        return java.toString();
    }


    public ServiceInterface withGetParameter(ParameterInterface parameter) {

        System.out.println(" !WARNING! Get parameter not supported for single action service. Using post");
        postParameters.add(parameter);
        return this;

    }

}
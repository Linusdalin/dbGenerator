package parameter;

import databaseModel.JavaCodeComponent;
import databaseModel.Project;

/*******************************************************''''
 *
 *          Generate the property manager file
 *
 *
 */

public class ParameterHandler extends JavaCodeComponent {

    private static final String Name = "PropertyManager";

    public String getPropertyManager(Project project) {

        StringBuffer file = new StringBuffer();

        file.append(packageStatement("commons"));
        file.append(classComment(Name, "desc.."));
        file.append(classHead(Name, "PropertyGeneral", null, 0));

        for (SystemParameter systemParameter : project.getParameters()) {

            file.append("   private static String _"+ systemParameter.getName()+" = null;\n");
        }

        file.append("\n\n");

        file.append(
                "    public PropertyManager(String propertyFile){\n" +
                "        \n" +
                "        super(propertyFile);\n" +
                "    }\n\n\n");


        for (SystemParameter systemParameter : project.getParameters()) {

            file.append(parameterGetter(systemParameter));

        }
        file.append(classEnd(Name, 0));
        return file.toString();
    }

    private String parameterGetter(SystemParameter systemParameter) {
        return "    /************************************************\n" +
                "     *\n" +
                "     *      Generated property getter\n" +
                "     *      for "+ systemParameter.getName()+"\n" +
                "     *\n" +
                "     * @return        - value\n" +
                "     */\n\n" +
                "    public String get"+systemParameter.getName()+"(){\n" +
                "\n" +
                "        if(_"+ systemParameter.getName()+" == null)\n" +
                "            _"+systemParameter.getName()+" = System.getenv(\""+systemParameter.getName()+"\");\n" +
                "\n" +
                "        if(_"+ systemParameter.getName()+" == null)\n" +
                "            _"+ systemParameter.getName()+" = load"+ systemParameter.getType().getJavaType()+"(\""+ systemParameter.getName()+"\");\n" +
                "\n" +
                "        if(_"+ systemParameter.getName()+" == null)\n" +
                "            _"+ systemParameter.getName()+" = \""+ systemParameter.getValue()+"\";\n" +
                "        return _"+ systemParameter.getName()+";\n" +
                "\n" +
                "    }\n\n\n";
    }
}

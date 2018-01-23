package pukkaEnum;

import databaseModel.JavaCodeComponent;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2018-01-04
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 *
 */

public class NamedIdEnum extends JavaCodeComponent implements PukkaEnumInterface {

    private List<EnumElement> elements = new ArrayList<>();

    private final String name;
    private final String destinationPackage;
    private boolean asDropdown = false;            // Generate the drop down to inherit drop down functionality
    private String description;

    public NamedIdEnum(String name, String destinationPackage) {

        this.name = name;
        this.destinationPackage = destinationPackage;
    }

    public PukkaEnumInterface withDataElement(EnumElement element) {

        this.elements.add(element);
        return this;
    }

    @Override
    public PukkaEnumInterface useAsDropDown() {

        this.asDropdown = true;
        return this;
    }

    @Override
    public String generateEnumClass() {

        StringBuilder html = new StringBuilder();

        html.append(packageStatement(this.destinationPackage));
        html.append(includes());
        html.append(classComment(this.name, this.description));

        html.append(enumHead(this.name, "NamedIdEnumInterface"));
        html.append(declareValues());
        html.append(loggerDeclaration(this.name));
        html.append(attributes(this.name));
        html.append(constructorAndAssignment(this.name));
        html.append(getters());
        html.append(loookupByValue(this.name));
        //html.append(classEnd(this.name, 0));

        return html.toString();
    }


    private String loookupByValue(String name) {
        return "    public static "+ name+" getFromName( String name) {\n\n" +
                "        for ("+ name +" element : "+name+".values()) {\n\n" +
                "            if(name.equals(element.getName()))\n" +
                "                return element;\n\n" +
                "        }\n\n" +
                "        return NONE;\n" +
                "    }\n" +
                (hasId()?
                        "    public static "+ name+" getFromId( int id) {\n\n" +
                                "        for ("+ name +" element : "+name+".values()) {\n\n" +
                                "            if(element.getId() == id)\n" +
                                "                return element;\n\n" +
                                "        }\n\n" +
                                "        return NONE;\n" +
                                "    }\n"
                :
                                ""
                );
    }


    private String attributes(String name) {

        String declaration = "    private final String displayName;\n";
        if(hasId())
            declaration += "    private final int id;\n";

        declaration += "\n\n";
        return declaration;
    }

    private String declareValues() {

        StringBuilder html = new StringBuilder();

        html.append("   NONE(" + (hasId() ?  "0, " : "") + "\"None\"),\n");

        for (EnumElement element : elements) {

            html.append("   " + element.getConstant() + "(" + (element.useId() ? element.getId() + ", " : "") + "\"" + element.getDisplayName() + "\"),\n");
        }

        html.append("   ;\n\n");
        return html.toString();

    }

    private String constructorAndAssignment(String name) {
        return

           (hasId() ?
                   "    "+name+"(int id, String displayName) {\n\n" +
                   "        this.id = id;\n" +
                   "        this.displayName = displayName;\n" +
                   "    }\n\n"
           :
                    "    "+name+"(String displayName) {\n\n" +
                    "        this.displayName = displayName;\n" +
                    "    }\n\n"
           );
    }

    private String getters() {
        return

                "    public String getName() {\n" +
                "        return displayName;\n" +
                "    }\n\n" +
                        (hasId() ?
                                "    public int getId() {\n" +
                                "        return id;\n" +
                                "    }" : "" ) +
                                        "\n\n";
    }


    private boolean hasId() {

        return elements.size() > 0 &&  elements.get(0).useId();
    }

    private String includes() {
        return "import commons.NamedIdEnumInterface;\n" +
                "import logger.Logger;\n"+
                (asDropdown ? "import html.components.EnumDropDownInterface;\n" : "") + "\n\n";
    }

    private String enumHead(String name, String interfaceName) {
        return "public enum " + name + " implements " +interfaceName + (asDropdown ? ", EnumDropDownInterface" : "") + "{\n\n";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPackageLocation() {
        return destinationPackage;
    }

    public PukkaEnumInterface withDescription(String text) {

        description = text;
        return this;
    }
}

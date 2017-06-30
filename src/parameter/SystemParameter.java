package parameter;

/************************************************************
 *
 *          A system parameter is the definition of
 *          a parameter that can be read from:
 *
 *           - system env
 *           - parameter file
 *           - static value
 *
 *
 */
public class SystemParameter {


    private final String name;
    private final ParameterType type;
    private final String defaultValue;

    public SystemParameter(String name, ParameterType type, String defaultValue) {

        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return defaultValue;
    }

    public ParameterType getType() {
        return type;
    }
}

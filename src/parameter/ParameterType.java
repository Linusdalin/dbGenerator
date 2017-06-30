package parameter;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2017-05-14
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public enum ParameterType {

    String("String"),
    Int("Int"),
    ;

    private final String type;

    ParameterType(String type) {

        this.type = type;
    }

    public String getJavaType() {
        return type;
    }
}

package pukkaEnum;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2018-01-04
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
public class EnumElement {

    private final String constant;
    private final String displayName;
    private final int id;
    private boolean useId = true;

    public EnumElement(String constant, String displayName) {

        this(constant, 0, displayName);
        this.useId = false;
    }

    public EnumElement(String constant, int id, String displayName) {

        this.constant = constant;
        this.displayName = displayName;
        this.id = id;
    }

    public String getConstant() {
        return constant;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean useId(){
        return useId;
    }

    public int getId() {
        return id;
    }
}

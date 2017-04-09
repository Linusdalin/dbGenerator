package modules;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2017-03-07
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
public class StaticFile {

    private final String file;
    private final String targetPackage;

    public StaticFile(String file, String targetPackage) {

        this.file = file;
        this.targetPackage = targetPackage;
    }
}

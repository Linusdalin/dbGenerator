package service;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2017-02-10
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
public interface ServiceInterface {

    ServiceInterface withPostParameter(ParameterInterface parameter);
    ServiceInterface withGetParameter(ParameterInterface parameter);
    String generateServiceClass(String corePackage);

    public String getPackagePath();
    public String getName();
    public ServiceInterface withLoginValidation();

    public ServiceInterface withInclude(String includeDirectory);

    String getPlaceholderContent();
}

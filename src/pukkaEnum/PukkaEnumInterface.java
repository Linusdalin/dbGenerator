package pukkaEnum;

/******************************************************
 *
 *              Common functionality for the enums
 *
 */

public interface PukkaEnumInterface {

    PukkaEnumInterface useAsDropDown();
    String generateEnumClass( );

   PukkaEnumInterface withDataElement(EnumElement element);



        String getName();
    String getPackageLocation();
}

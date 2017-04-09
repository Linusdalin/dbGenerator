package test;

import commons.CommonTest;
import commons.Credentials;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**************************************************************************
 *
 *          Unit tests for the commons files
 *
 */

public class CommonsTests extends CommonTest{

    @Test
    public void credentialsTest(){

        Credentials credentials = new Credentials(1, 2);
        assertVerbose("Credentials contains values", credentials.getUser(), is( 1 ));
        assertVerbose("Credentials contains values", credentials.getOrganisation(), is( 2 ));
    }

}

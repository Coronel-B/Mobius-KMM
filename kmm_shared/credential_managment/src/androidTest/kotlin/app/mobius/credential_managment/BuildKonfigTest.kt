package app.mobius.credential_managment

import org.junit.Assert
import org.junit.Test

class BuildKonfigTest {

    @Test
    fun keyAppAuthorizationDeveloperExistsInBuildKonfig() {
        Assert.assertTrue(BuildKonfig.keyAppAuthorizationDeveloper.isNotEmpty())
    }

}

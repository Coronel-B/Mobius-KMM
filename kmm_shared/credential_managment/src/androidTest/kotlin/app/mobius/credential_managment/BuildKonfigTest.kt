package app.mobius.credential_managment

import org.junit.Assert
import org.junit.Test

class BuildKonfigTest {

    @Test
    fun keyAppAuthorizationDeveloperExistsInBuildKonfig() {
        Assert.assertTrue(BuildKonfig.MOBIUS_KMM_AUTHORIZATION_DEVELOPER_KEY.isNotEmpty())
    }

}

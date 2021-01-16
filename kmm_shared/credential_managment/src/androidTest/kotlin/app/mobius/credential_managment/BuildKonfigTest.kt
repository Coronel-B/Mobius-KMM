package app.mobius.credential_managment

import org.junit.Assert
import org.junit.Test

class BuildKonfigTest {

    @Test
    fun appAuthorizationDeveloperFullNameExistsInBuildKonfig() {
        Assert.assertTrue(BuildKonfig.MOBIUS_KMM_AUTHORIZATION_DEVELOPER_FULL_NAME.isNotEmpty())
    }

    @Test
    fun appAuthorizationDeveloperSecretApiKeyExistsInBuildKonfig() {
        Assert.assertTrue(BuildKonfig.MOBIUS_KMM_AUTHORIZATION_DEVELOPER_SECRET_API_KEY.isNotEmpty())
    }

}

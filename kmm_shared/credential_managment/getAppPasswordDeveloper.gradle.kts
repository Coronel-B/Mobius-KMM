//gradle -PmySecretKey="This key is so secret" yourTask

// Describes the appAuthorizationKey from home properties on build time
// PRECONDITION: Add appAuthorizationKey property in ~/.gradle/gradle.properties with <YOUR-KEY>
// Get variable with DSL: https://stackoverflow.com/a/59871066/5279996
// https://stackoverflow.com/a/46805257/5279996

val appAuthorizationKey: String by project
println(appAuthorizationKey)
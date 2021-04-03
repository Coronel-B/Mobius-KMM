# R.drawable cannot resolve image

Check the package of the `AndroidManifest.xml` file of your module and make sure that it has been defined correctly.

    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="app.mobius.featureWelcome">
    </manifest>


Finally use the package as an import in the class:

    import app.mobius.featureWelcome.R

Source: https://stackoverflow.com/a/66935135/5279996
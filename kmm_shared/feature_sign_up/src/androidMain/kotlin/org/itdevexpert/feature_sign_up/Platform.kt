package org.itdevexpert.feature_sign_up

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual class TestExpect actual constructor(actual val test: String)
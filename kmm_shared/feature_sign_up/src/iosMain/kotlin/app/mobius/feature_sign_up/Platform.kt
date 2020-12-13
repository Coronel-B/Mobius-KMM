package app.mobius.feature_sign_up

import app.mobius.domain.dataTypes.UUID
import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual class TestExpect actual constructor(actual val test: UUID) {

}
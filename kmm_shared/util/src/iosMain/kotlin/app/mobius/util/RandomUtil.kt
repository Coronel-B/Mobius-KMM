package app.mobius.util

import platform.Foundation.NSUUID

actual fun randomUUID(): String = NSUUID().UUIDString()
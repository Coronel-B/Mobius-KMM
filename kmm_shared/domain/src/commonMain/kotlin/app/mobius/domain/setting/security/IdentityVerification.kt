package app.mobius.domain.setting.security

import app.mobius.domain.LivenessStatus
import app.mobius.domain.Country
import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDate

data class IdentityVerification(
    val uuid: Uuid?,
    val documentationVerification: DocumentationVerification,
    val liveness: Liveness
)

data class DocumentationVerification(
    val uuid: Uuid?,
    val documentationVerificationStatus: DocumentationVerificationStatus = DocumentationVerificationStatus.UNSOLICITED,
    val dni: DNI? = null,
)

data class DNI(
    val uuid: Uuid?,
    val surname: String,
    val name: String,
    val sex: Sex,
    val nationality: Country,
    val ejemplar: String,
    val birthdate: LocalDate,
    val dateIssue: LocalDate,
    val dateExpiry: LocalDate,
    val identificationNumber: Long,
    val number: Int,
)

enum class Sex {
    F, M
}

enum class DocumentationVerificationStatus {
    UNSOLICITED, PENDING, VERIFIED, BLOCKED
}

data class Liveness(
    val uuid: Uuid?,
    val livenessStatus: LivenessStatus = LivenessStatus.UNSOLICITED
)
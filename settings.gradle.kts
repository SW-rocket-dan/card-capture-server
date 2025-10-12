rootProject.name = "cardcapture"

include("payment")

// ---------- platform ----------
include(
    "auth-domain",
    "auth-application",
    "auth-adapter",
    "member-domain",
    "member-application",
    "member-adapter",
    "platform-api")

project(":auth-domain").projectDir = file("platform/auth/domain")
project(":auth-application").projectDir = file("platform/auth/application")
project(":auth-adapter").projectDir = file("platform/auth/adapter")
project(":member-domain").projectDir = file("platform/member/domain")
project(":member-application").projectDir = file("platform/member/application")
project(":member-adapter").projectDir = file("platform/member/adapter")

project(":platform-api").projectDir = file("platform/services/api")

// ---------- payment ----------
include(
    "voucher-domain",
    "voucher-application",
    "voucher-adapter",
    "payment-core-domain",
    "payment-core-application",
    "payment-core-adapter",
    "payment-api")

project(":voucher-domain").projectDir = file("payment/voucher/domain")
project(":voucher-application").projectDir = file("payment/voucher/application")
project(":voucher-adapter").projectDir = file("payment/voucher/adapter")
project(":payment-core-domain").projectDir = file("payment/payment-core/domain")
project(":payment-core-application").projectDir = file("payment/payment-core/application")
project(":payment-core-adapter").projectDir = file("payment/payment-core/adapter")
project(":payment-api").projectDir = file("payment/services/api")

// ---------- libs ----------

include(
    "contracts-auth",
    "security")

project(":contracts-auth").projectDir = file("libs/contracts-auth")
project(":security").projectDir = file("libs/security")

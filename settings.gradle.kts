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

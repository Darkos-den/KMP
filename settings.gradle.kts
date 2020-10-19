include(
    ":android",
    ":common:core",
    ":common:validation",
    ":common:entity",
    ":common:domain",
    ":common:source:remote",

    ":common:feature:login-email:login-email-api",
    ":common:feature:login-email:login-email-reducer",
    ":common:feature:login-email:login-email-effect-handler",

    ":common:feature:auth:auth-api",
    ":common:feature:auth:auth-android",
    ":common:feature:auth:auth-reducer",
    ":common:feature:auth:auth-effect-handler"
)

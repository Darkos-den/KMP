include(
    ":android",
    ":common:core",
    ":common:entity",
    ":common:domain",
    ":common:source:remote",

    ":common:feature:login:login-api",
    ":common:feature:login:login-reducer",
    ":common:feature:login:login-effect-handler",

    ":common:feature:signin:signin-api",
    ":common:feature:signin:signin-reducer",
    ":common:feature:signin:signin-effect-handler",

    ":common:feature:auth:auth-api",
    ":common:feature:auth:auth-android",
    ":common:feature:auth:auth-reducer",
    ":common:feature:auth:auth-effect-handler"
)

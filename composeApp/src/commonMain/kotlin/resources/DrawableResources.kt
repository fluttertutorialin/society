package resources

data class DrawableLightResources(
    val filledStar: String = "",

    val backgroundPattern: String = "background_pattern.png",

    val backgroundCard: String = "img_background.png",
    val cart: String = "img_cart.png",

    val lastPayment: String = "img_last_payment.png",

    val signWithGoogle: String = "sign_with_google.png",
    val signWithApple: String = "sign_with_apple.png",

    val homeOutlined: String = "ic_home_outlined.xml",
    val homeFilled: String = "ic_home_filled.xml",
    val notificationsOutlined: String = "ic_notifications_outlined.xml",
    val notificationsFilled: String = "ic_notifications_filled.xml",
    val profileOutlined: String = "ic_profile_outlined.xml",
    val profileFilled: String = "ic_profile_filled.xml",
    val searchOutlined: String = "ic_search_outlined.xml",
    val searchFilled: String = "ic_search_filled.xml",
    val ordersOutlined: String = "ic_orders_outlined.xml",
    val ordersFilled: String = "ic_orders_filled.xml",

    val logout: String = "logout.xml",

    val arrowRight: String = "ic_right_arrow.xml",
    val arrowLeft: String = "arrow_left.xml",
    var iconBack: String = "ic_back.xml",

    val icError: String = "ic_error_icon.xml",

    val chatImage: String = "img_chat.png",
    )

val DrawableDarkResources = DrawableLightResources(
    filledStar = "",
)
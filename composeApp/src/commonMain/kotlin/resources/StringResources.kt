package resources

data class StringResources(
    val home: String = "Home",
    val search: String = "Search",
    val orders: String = "Orders",
    val notification: String = "Notification",
    val profile: String = "Profile",

    //region Login
    val beepBeep: String = "Beep Beep",
    val backgroundDescription: String = "background image",
    val loginWelcomeMessage: String = "HomeCovey",
    val loginSubWelcomeMessage: String = "Login to access all the features",
    val usernameLabel: String = "Username",
    val passwordLabel: String = "Password",
    val login: String = "Login",
    val keepMeLoggedIn: String = "Keep me logged in",
    val signupWithBeepBeep: String = "Sign up with Beep Beep account",
    val foodsAreWaiting: String = "Foodies are waiting!",
    val tapStartTitle: String = "Tap Start and become their food \nsuperhero",
    val invalidUsername: String = "Invalid username",
    val invalidPassword: String = "Invalid password",
    val invalidEmail: String = "Invalid Email",
    val invalidPhoneNumber: String = "Invalid Phone Number",
    val start: String = "Start",
    //endregion

    val joinBpToday: String = "Join HomeCovey Today!",
    val createYourAccount: String = "Create your account",

    val next: String = "Next",

    //region permission
    val wrongPermissionMessage: String = "Looks like your account isn't assigned as a Delivery User, ask for permission?",
    val wrongPermission: String = "Wrong permission",
    val requestAPermission: String = "Request a permission",
    val close: String = "Close",
    val cancel: String = "Cancel",
    val askForPermission: String = "Ask for permission",
    val userEmailLabel: String = "User email",
    val deliveryUsername: String = "Delivery Username",
    val questionHint: String = "Describe why you want to join us",
    val whyBeepBeep: String = "Why beep beep?",
    val submit: String = "Submit",
    //endregion
    //region Map
    val beReady: String = "Be ready!",
    val subLoadingText: String = "to take orders, and they will be \nassigned soon",
    val newOrder: String = "NewOrder",
    val accept: String = "Accept",
    val received: String = "Received",
    val delivered: String = "Delivered",
    val reject: String = "reject",
    val deliverAt: String = "Deliver At",
    val welcome: String = "Welcome, ",
    val accessDeniedMessage: String = "To continue, Please access location permission",
    //endregion
)
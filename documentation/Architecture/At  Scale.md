:androidApp has presentation layer as package (not module)
:kmm-shared has domain and data layer as package (not module)

:androidApp (presentation)
    :feature-login-public
    :feature-login-impl
    :feature-login-impl-wiring

[comment]: <> (:feature-login-fake)
[comment]: <> (:feature-login-fake-wiring)

:kmm-shared (domain & data)
    :feature-sign-up-public
    :feature-sign-up-impl
    :feature-sign-up-impl-wiring
    :feature-sign-up-fake
    :feature-sign-up-fake-wiring



Sources: 
 - [Android Showcase](https://github.com/igorwojda/android-showcase)
 - [Android at Scale | Video](https://www.droidcon.com/media-detail?video=380843878)
 - [Android at Scale | Slideshow](https://speakerdeck.com/vrallev/android-at-scale-at-square)
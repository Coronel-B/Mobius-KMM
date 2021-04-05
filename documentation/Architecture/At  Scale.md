 - _:androidApp has presentation layer as package (not module)_
 - _:kmm-shared has domain and data layer as package (not module)_

 - :androidApp (presentation)
    - :feature-login-public
    - :feature-login-impl
    - :feature-login-impl-wiring

[comment]: <> (For second MVP :feature-login-fake)
[comment]: <> (:For Second MVP :feature-login-fake-wiring)

 - :kmm-shared (domain & data)
    - :feature-sign-up-public
    - :feature-sign-up-impl
    - :feature-sign-up-impl-wiring

[comment]: <> (For second MVP :feature-login-fake)
[comment]: <> (:For Second MVP :feature-login-fake-wiring)


___

Sources: 
 - [Android Showcase](https://github.com/igorwojda/android-showcase)
 - [Android at Scale | Video](https://www.droidcon.com/media-detail?video=380843878)
 - [Android at Scale | Slideshow](https://speakerdeck.com/vrallev/android-at-scale-at-square)
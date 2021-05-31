 - _:androidApp has presentation and navigation layer as package (not module)_
 - _:kmm-shared has domain and data layer as package (not module)_

 - :androidApp (presentation)
    - :feature-login-open
    - :feature-login-impl
    - :feature-login-impl-wiring

:feature-login:open <- :feature-login:impl -> :feature-login:impl-wiring
:feature-login:impl-wiring -> :feature-login:impl, :feature-login:open
:app -> :feature-login:open, :feature-login:impl-wiring 

# TODO
[comment]: <> (For second MVP :feature-login:fake)
[comment]: <> (:For Second MVP :feature-login:fake-wiring)
[comment]: <> (:For Second MVP ::feature-login:demo)

___

 - :kmm-shared (domain & data)
    - :feature-sign-up-open
    - :feature-sign-up-impl
    - :feature-sign-up-impl-wiring

Observations:
- Use open because in package names cannot contain java keywords like 'public'
___

Sources: 
 - [Android Showcase](https://github.com/igorwojda/android-showcase)
 - [Android at Scale | Video](https://www.droidcon.com/media-detail?video=380843878)
 - [Android at Scale | Slideshow](https://speakerdeck.com/vrallev/android-at-scale-at-square)
 - [Why do we need to separate DI wiring from impl?](https://stackoverflow.com/q/64661640/5279996)
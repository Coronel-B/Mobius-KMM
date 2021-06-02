 - _:androidApp has presentation and navigation layer as package (not module)_
 - _:kmm-shared has domain and data layer as package (not module)_

 - :androidApp (presentation)
    - :feature-welcome
      - :open
      - :impl
      - :impl-wiringg

:feature-welcome:open <- :feature-welcome:impl -> :feature-welcome:impl-wiring
:feature-welcome:impl-wiring -> :feature-welcome:impl, :feature-welcome:open
:app -> :feature-welcome:open, :feature-welcome:impl-wiring 

[comment]: <> (For second MVP :feature-welcome:fake)
[comment]: <> (:For Second MVP :feature-welcome:fake-wiring)
[comment]: <> (:For Second MVP ::feature-welcome:demo)
___

 - :kmm-shared (domain & data)
    - :feature-welcome-open
    - :feature-welcome-impl
    - :feature-welcome-impl-wiring

Observations:
- Use open because in package names cannot contain java keywords like 'public'
___

Sources: 
 - [Android Showcase](https://github.com/igorwojda/android-showcase)
 - [Android at Scale | Video](https://www.droidcon.com/media-detail?video=380843878)
 - [Android at Scale | Slideshow](https://speakerdeck.com/vrallev/android-at-scale-at-square)
 - [Why do we need to separate DI wiring from impl?](https://stackoverflow.com/q/64661640/5279996)
CA:

    Presentation layer - presents data to a screen and handle user interactions
    Domain layer - contains business logic (entities and interactors)
    Data layer - access, retrieve and manage application data through repository and data sources.


## Approach — CA layers inside the feature module
---------------------------------------------

Mobile module wires everything together.

Each feature will contain its own set of CA layers.

> Usually package structure of the "mobile module" is a bit different than feature modules, because it mostly contains
> “fundamental app configuration” (dependency injection, application class, retrofit configurations, etc.) and
> code that wire multiple module together (eg. some internal event bus).

With this approach, we gained proper code separation from a feature perspective and more solid cross-feature boundaries.
It is much easier to define cross-feature dependencies and certain features may depend on 3rd party libraries
that are not needed for other features. On top of that Gradle adds some caching where only some of thee modules are may be
recompiled instead of the whole project and using feature modules you can take advantage of Android dynamic delivery.
Now we can also have proper feature ownership per team (eg. the team X can work on code stored within a single feature module)
and unit tests separation(unit tests for a feature are contained within feature module).

While a feature module approach has more benefits it still has the initial issue that each feature can break the dependency rule.
To solve this problem we can implement a custom lint check rule or ArchUnit test that would check all the imports (layer dependencies).

> We can check if imports of each class defined in the domain layer and flag and error if there are imortas that point to other layers

We should also consider having additional "shared modules" containing common classes (BaseActivity, BaseFragment etc.)
or modules that contain some common resources/code to avoid duplication


Source:
 . https://github.com/igorwojda/android-showcase
 . https://proandroiddev.com/multiple-ways-of-defining-clean-architecture-layers-bbb70afa5d4a

Other source:
 . https://github.com/google/iosched
 . https://antonioleiva.com/clean-architecture-android/
 . https://github.com/antoniolg/clean-architecture
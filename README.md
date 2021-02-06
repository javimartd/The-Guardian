

## The Guardian News

This application allows you to check the latest news in the world thanks to the API provided by the [The Guardian](https://www.theguardian.com/uk).

The purpose of this application is first of all to offer a simple example of the use of Clean architecture using Kotlin and its many advantages over Java. In second place, this project also constitutes a repository of functionalities and utilities that are usually needed daily for the development of Android applications. If you dive into the code, you'll get more details.

<img src = "https://github.com/javimartd/The-Guardian/blob/master/screenshots/pixel_2_home.png" width ="200" /> <img src = "https://github.com/javimartd/The-Guardian/blob/master/screenshots/pixel_2_web.png" width ="200" />

### Documentation

Overview:
- AndroidX
- [Kotlin][8]
- Example of [MVVM][10] and [MVP][9] patterns with Clean architecture
- [RxJava 2][1]
- Dependencies injection: [Dagger 2][2]
- Data base: [Room][3]
- Images: [Glide][4]
- Testing: JUnit, Espresso, [Mockito][5], [MockWebServer][6]
- Integration with [SonarQube][7]

All the content offered is through the API provided by [The Guardian](https://www.theguardian.com/uk). You can check all the information about how to use [here](https://open-platform.theguardian.com/).

[1]: https://github.com/ReactiveX/RxJava
[2]: https://google.github.io/dagger/
[3]: https://developer.android.com/training/data-storage/room/index.html
[4]: https://github.com/bumptech/glide
[5]: https://site.mockito.org/
[6]: https://github.com/square/okhttp/tree/master/mockwebserver
[7]: https://www.sonarqube.org/
[8]: https://kotlinlang.org/docs/reference/
[9]: https://upday.github.io/blog/model-view-presenter/
[10]: https://upday.github.io/blog/model-view-viewmodel/

### Design patterns
Below are the design patterns that have been used in this app

#### Builder
This pattern separates de construction of a complex object from its representation.
  cvb
```kotlin
open fun create(): AlertDialog {
    alertDialog = builder
            .setCancelable(cancelable)
            .create()

    if (isBackGroundTransparent)
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    return alertDialog!!
}
```

#### Singleton
The singleton pattern specifies that only a single instance of a class should exist. Object declaration's initialization is thread-safe and done at first access.

```kotlin
object SectionFactory {

    fun getSection(sectionId: String, sectionName: String) : Section {
        //...
    }
}
```

#### Dependency Injection
It's a design pattern in which objects are supplied to a class instead of the class itself creating those objects.

```kotlin
@Module
class AppModule {
    //...
}
```

#### Adapter
This pattern lets two incompatible classes work together by converting the interface of a class into another interface the client expects.
```kotlin
class Adapter(private val typeFactory: TypeFactory): RecyclerView.Adapter<BaseViewHolder<Visitable>>() {
    //...
}
```

#### Observer
According to wikipedia, the observer pattern defines a one-to-many dependency between objects, so that when one of the objects changes its state, it notifies all the dependents of this change. This pattern is one of the foundations on which reactive programming is based

```kotlin
newsViewModel.newsObservable.observe(this, Observer<Resource<List<Visitable>>> {
    //...
})
```

#### Factory

The factory design pattern is used when when we have to create instances of different objects. This pattern takes out the responsibility of the instantiation of a class and delegates it to the factory class.

```kotlin
object SectionFactory {

    fun getSection(sectionId: String, sectionName: String) : Section {
        return when (sectionId) {
            SectionsTypes.BUSINESS.id -> Business(sectionName)
            SectionsTypes.MEDIA.id -> Media(sectionName)
            SectionsTypes.OPINION.id -> Opinion(sectionName)
            SectionsTypes.FILM.id -> Film(sectionName)
            SectionsTypes.SPORT.id -> Sport(sectionName)
            SectionsTypes.POLITICS.id -> Politics(sectionName)
            SectionsTypes.WORLD.id -> World(sectionName)
            SectionsTypes.LIFE_AND_STYLE.id -> LifeAndStyle(sectionName)
            SectionsTypes.MONEY.id -> Money(sectionName)
            SectionsTypes.ARTICLE.id -> Article(sectionName)
            SectionsTypes.TRAVEL.id -> Travel(sectionName)
            else -> Other(sectionName)
        }
    }
}
```

#### Repository

"Mediates between the domain and data mapping layers using a collection-like interface for accessing domain objects." 

```kotlin
interface NewsRepository {
    fun getNews(): Single<List<News>>
    fun saveNews(news: News): Completable
}

```
#### Visitor

"Visitor pattern is used when we have to perform an operation on a group of similar kind of Objects. With the help of visitor pattern, we can move the operational logic from the objects to another class."

```kotlin
interface TypeFactory {
    fun type(header: HeaderView): Int
    fun type(news: NewsView): Int
    fun type(ad: AdView): Int
    fun holder(type: Int, view: View): BaseViewHolder<Visitable>
}
```

```kotlin
interface Visitable {
    fun type(typeFactory: TypeFactory): Int
}
```

#### Template Method

The template method pattern is a design pattern that defines the skeleton of an algorithm in such a way that it allows us to redefine certain safe steps of an algorithm without changing its overall structure.

```kotlin
abstract class CompletableUseCase<in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        //...
    }

    private fun addDisposable(disposable: Disposable) {
        //...
    }

    fun dispose() {
        //...
    }
}
```

#### DAO (Data Access Object)

"Is used to separate the data persistence logic in a separate layer. This way, the service remains completely in dark about how the low-level operations to access the database is done"

```kotlin
@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): Single<List<NewsEntity>>
    
    //...
}
```

### More design patterns 

Apart from the design patterns just mentioned, there are many others that we must take into account when developing quality software, such as the Memento, State, Strategy, Iterator, Command, Decorator, Facade, Prototype or MVI (Model View Intent) 


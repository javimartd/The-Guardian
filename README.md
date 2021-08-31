

## The Guardian News

This app allows you to check the latest news in the world thanks to the API provided by the [The Guardian](https://www.theguardian.com/uk).

The purpose of this application is, first of all, to offer a simple example of the use of Clean architecture using Kotlin and its many advantages over Java. In second place, this project also constitutes a repository of functionalities and utilities that are usually needed daily for the development of Android applications. If you dive into the code, you'll get more details.

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

```kotlin
object SectionFactory {

    fun getSection(sectionId: String, sectionName: String) : Section {
        //...
    }
}
```

#### Dependency Injection

```kotlin
open class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase): ViewModel() {
```

#### Adapter

```kotlin
class Adapter(private val typeFactory: TypeFactory): RecyclerView.Adapter<BaseViewHolder<Visitable>>() {
    //...
}
```

#### Observer

```kotlin
newsViewModel.newsObservable.observe(this, Observer<Resource<List<Visitable>>> {
    //...
})
```

#### Factory

```kotlin
object SectionFactory {

    fun getSection(sectionId: String, sectionName: String) : Section {
        return when (sectionId) {
            SectionsTypes.BUSINESS.id -> Business(sectionName)
            SectionsTypes.MEDIA.id -> Media(sectionName)
            // ...
        }
    }
}
```

#### Repository

```kotlin
interface NewsRepository {
    fun getNews(): Single<List<News>>
    fun saveNews(news: News): Completable
}

```
#### Visitor

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

#### Command

```kotlin
interface Command <T>{
    fun execute(): T
}
```

#### Template Method

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

```kotlin
@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): Single<List<NewsEntity>>
    
    //...
}
```

### More design patterns 

In addition to the design patterns I have just mentioned, there are many others that we must take into account when developing quality software, such as the Memento, State, Strategy, Iterator, Decorator, Service Locator, Facade, Prototype or MVI (Model View Intent) 


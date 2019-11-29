package com.javimartd.theguardian.ui.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.extensions.toPresentation
import com.javimartd.theguardian.ui.news.model.NewsView
import com.javimartd.theguardian.ui.news.state.Resource
import com.javimartd.theguardian.ui.news.state.ResourceState
import io.reactivex.observers.DisposableObserver
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Important guiding principles for MVVM implementation:
 *
 * - As shown in the sample, ViewModels do not and must not reference Views directly because
 * if this is done, ViewModels can outlive the View’s lifecycle and memory leakage can happen.
 *
 * - Model and ViewModel are recommended to expose their data using LiveData since LiveData
 * respects the lifecycle state of app components (activities, fragments, services) and
 * handles object life cycle management which ensures that LiveData objects do not leak.
 *
 * ViewModel retains its data during configuration changes. This means that after configuration
 * changes, this ViewModel data is immediately available to the next activity or fragment instance.
 *
 * LiveData is an observable data holder. This allows the components in your app to be able
 * to observe LiveData objects for changes without creating explicit and rigid dependency
 * paths between them. Is used to make the task of implementing ViewModel easier.
 *
 * Unlike a regular observable, LiveData respects the lifecycle state of your app components
 * (activities, fragments, services) and handles object life cycle management which ensures
 * that LiveData objects do not leak.
 *
 * You can forget if the activity is destroyed or not, so you can disconnect from its life
 * cycle and do your work at any time. Thanks to ViewModel and LiveData, you don't need to
 * worry when the activity is recreated or when it is destroyed.
 *
 * This is how it works: while the activity is recreated, ViewModel stays alive.
 * It's just when the activity ends forever, when the ViewModel onCleared () method is called.
 *
 * LiveData is that the data will not be updated when your View is in the background.
 *
 * LiveData is immutable by default (you cannot change its value).
 * We want it to be public so that observers can subscribe, but we don't want other parts
 * of the code to change the value.
 *
 */

open class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase): ViewModel() {
    /**
     * 1) first task should be to create an object of LiveData in your ViewModel class.
     */
    private val newsObservable: MutableLiveData<Resource<List<NewsView>>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        getNewsUseCase.dispose()
    }

    /**
     * Expose the LiveData News query so the UI can observe it.
     *
     * A good practice that helps us limit the modification of MutableLiveData to the
     * class is to return it as LiveData. This is because the setValue and postValue
     * methods are not implemented in LiveData, so they cannot be modified outside of this class.
     *
     * If we need it to be modified, it is better to add a function that updates it rather than
     * exposing it as MutableLiveData:
     */
    fun getNewsObservable(): LiveData<Resource<List<NewsView>>> {
        return newsObservable
    }

    fun fetchNews() {
        newsObservable.postValue(Resource(ResourceState.LOADING, null, null))
        getNewsUseCase.execute(NewsSubscriber())
    }

    inner class NewsSubscriber: DisposableObserver<List<News>>() {

        override fun onError(e: Throwable) {
            if (e is UnknownHostException) {
                newsObservable.postValue(Resource(ResourceState.CONNECTION_ERROR, null, e.localizedMessage))
            } else {
                newsObservable.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
            }
        }

        override fun onComplete() {
            // does not require implementation at this moment
        }

        override fun onNext(news: List<News>) {
            /**
             * A transformation may be needed before passing the result data to the Observing View.
             * In order to make a transformation, you can use Transformation class as shown in the
             * documentation below:
             * @link: https://developer.android.com/topic/libraries/architecture/livedata.html#transformations_of_livedata
             */
            if (news.isEmpty()) {
                newsObservable.postValue(Resource(ResourceState.NO_DATA, emptyList(), null))
            } else {
                newsObservable.postValue(Resource(ResourceState.SUCCESS, news.toPresentation(), null))
            }
        }
    }
}
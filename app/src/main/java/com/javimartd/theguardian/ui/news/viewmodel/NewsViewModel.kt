package com.javimartd.theguardian.ui.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.domain.usecases.GetNewsUseCase
import com.javimartd.theguardian.ui.common.state.Resource
import com.javimartd.theguardian.ui.extensions.toPresentation
import com.javimartd.theguardian.ui.news.model.NewsView
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Important guiding principles for MVVM implementation:
 *
 * - ViewModels must not reference Views directly because
 * if this is done, ViewModels can outlive the View’s lifecycle and memory leakage can happen.
 *
 * - Model and ViewModel are recommended to expose their data using LiveData since LiveData,
 * Unlike a regular observable, respects the lifecycle state of app components (activities,
 * fragments, services) and handles object life cycle management which ensures that LiveData
 * objects do not leak.
 *
 * ViewModel retains its data during configuration changes. This means that after configuration
 * changes, this ViewModel data is immediately available to the next activity or fragment instance.
 *
 * LiveData is an observable data holder. This allows the components in your app to be able
 * to observe LiveData objects for changes without creating explicit and rigid dependency
 * paths between them. Is used to make the task of implementing ViewModel easier.
 *
 * You can forget if the activity is destroyed or not, so you can disconnect from its life
 * cycle and do your work at any time. Thanks to ViewModel and LiveData, you don't need to
 * worry when the activity is recreated or when it is destroyed.
 *
 * With LiveData, the data will not be updated when your View is in the background.
 *
 * LiveData is immutable by default (you cannot change its value).
 */

open class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase): ViewModel() {

    private val _newsObservable: MutableLiveData<Resource<List<NewsView>>> = MutableLiveData()

    /**
     * Expose the LiveData News so the UI can observe it.
     *
     * A good practice that helps us limit the modification of MutableLiveData to the
     * class is to return it as LiveData. This is because the setValue and postValue
     * methods are not implemented in LiveData, so they cannot be modified outside of this class.
     *
     * If we need it to be modified, it is better to add a function that updates it rather than
     * exposing it as MutableLiveData:
     */
    val newsObservable: LiveData<Resource<List<NewsView>>>
        get() = _newsObservable

    override fun onCleared() {
        super.onCleared()
        getNewsUseCase.dispose()
    }

    fun fetchNews() {
        _newsObservable.value = Resource.Loading()
        getNewsUseCase.execute(NewsSubscriber())
    }

    inner class NewsSubscriber: DisposableSingleObserver<List<News>>() {
        override fun onError(e: Throwable) {
            _newsObservable.value = Resource.Error(e.localizedMessage, e)
        }
        override fun onSuccess(t: List<News>) {
            /**
             * A transformation may be needed before passing the result data to the Observing View.
             * In order to make a transformation, you can use Transformation class as shown in the
             * documentation below:
             * @link: https://developer.android.com/topic/libraries/architecture/livedata.html#transformations_of_livedata
             */
            _newsObservable.value = (Resource.Success(t.toPresentation()))
        }
    }
}
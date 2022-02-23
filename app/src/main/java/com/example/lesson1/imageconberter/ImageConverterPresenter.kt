package com.example.lesson1.imageconberter

import android.net.Uri
import com.example.lesson1.interfaces.ImageConverterView
import com.example.lesson1.model.ConverterJpgToPng
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ImageConverterPresenter (
    private val converter: ConverterJpgToPng,
    val router: Router
) : MvpPresenter<ImageConverterView>() {

    var disposables = CompositeDisposable()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    override fun onDestroy() {
        disposables.clear()
    }

    fun startConvertingPressed(imageUri: Uri) {
        viewState.signWaitingShow()
        viewState.signGetStartedHide()
        viewState.btnAbortConvertEnabled()
        converter
            .convertRx(imageUri)
            .subscribe(object : SingleObserver<Uri> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: Uri?) {
                    if (t != null) {
                        onConvertingSuccess(t)
                    }
                }

                override fun onError(e: Throwable?) {
                    onConvertingError(e)
                }
            })
    }

    private fun onConvertingSuccess(uri: Uri) {
        viewState.showConvertedImage(uri)
        viewState.btnAbortConvertDisabled()
        viewState.signAbortConvertHide()
        viewState.signWaitingHide()
    }

    private fun onConvertingError(e: Throwable?) {
        viewState.showErrorBar()
        viewState.btnAbortConvertDisabled()
        viewState.signWaitingHide()
    }

    fun abortConvertImagePressed() {
        viewState.signWaitingHide()
        viewState.btnAbortConvertDisabled()
        viewState.signAbortConvertShow()
    }

    fun originalImageSelected(imageUri: Uri) {
        viewState.showOriginImage(imageUri)
        viewState.btnStartConvertEnable()
        viewState.signAbortConvertHide()
        viewState.signGetStartedHide()
        viewState.hideErrorBar()
        viewState.signWaitingShow()
    }
}
package br.com.flying.dutchman.offers_challenge.ui

class ViewState<D>(val status: Status, val data: D? = null, val error: Throwable? = null) {
    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}
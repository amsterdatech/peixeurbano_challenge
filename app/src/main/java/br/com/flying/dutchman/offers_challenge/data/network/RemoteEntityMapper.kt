package br.com.flying.dutchman.data.remote

interface RemoteEntityMapper< M,  E> {
    fun mapFromRemote(type: M): E
    fun mapTo(type: E): M
}

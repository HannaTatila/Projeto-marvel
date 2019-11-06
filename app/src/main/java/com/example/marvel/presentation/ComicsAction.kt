package com.example.marvel.presentation

sealed class ComicsAction {

    class ShowSnackBar(val message: String) : ComicsAction()
    //troca de tela: lista -> detalhes

}
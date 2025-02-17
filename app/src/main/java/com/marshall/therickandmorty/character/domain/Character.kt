package com.marshall.therickandmorty.character.domain


data class Character(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
    val type: String,
    var location: String,
    val url: String
) {
    fun isAlive() = status == "Alive"
}
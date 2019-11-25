package com.ayan.appy

data class Hobbyt(var title:String)

object Supplier {
    val hobbies = listOf<Hobbyt>(
        Hobbyt("Swimming"),
        Hobbyt("Singing"),
        Hobbyt("Dancing"),
        Hobbyt("Wrinting"),
        Hobbyt("BOxing"),
        Hobbyt("Sports"),
        Hobbyt("Scuba"),
        Hobbyt("Sky diving")
    )
}
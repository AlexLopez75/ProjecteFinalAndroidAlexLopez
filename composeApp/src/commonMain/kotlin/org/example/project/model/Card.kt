package org.example.project.model

import kotlinproject.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

data class Card(
    val id: Int,
    val name: String,
    val image: DrawableResource,
)

object CardProvider{
    fun ObtainCards(): MutableList<Card> {
        return mutableListOf(
            Card(1, "Jotaro Kujo", Res.drawable.jotaro_kujo),
            Card(2, "Joseph Joestar", Res.drawable.joseph_joestar),
            Card(3, "Muhammad Abdol", Res.drawable.muhammad_avdol),
            Card(4, "Noriaki Kakyoin", Res.drawable.noriaki_kakyoin),
            Card(5, "Jean Pierre Polnareff", Res.drawable.jean_pierre_polnareff),
            Card(6, "Iggy", Res.drawable.iggy),
            Card(7, "DIO", Res.drawable.dio),
            Card(8, "Hol Horse", Res.drawable.hol_horse),
            Card(9, "Mariah", Res.drawable.mariah),
            Card(10, "Daniel J.D'arby", Res.drawable.daniel_j_darby),
            Card(11, "Vanilla Ice", Res.drawable.vanilla_ice),
            Card(12, "Josuke Higashikata", Res.drawable.josuke_higashikata),
            Card(13, "Koichi Hirose", Res.drawable.koichi_hirose),
            Card(14, "Okuyasu Nijimura", Res.drawable.okuyasu_nijimura),
            Card(15, "Rohan Kishibe", Res.drawable.rohan_kishibe),
            Card(16, "Yoshikage Kira", Res.drawable.kira_yoshikage),
            Card(17, "Giorno Giovanna", Res.drawable.giorno_giovanna),
            Card(18, "Bruno Bucciarati", Res.drawable.bruno_bucciarati),
            Card(19, "Guido Mista", Res.drawable.guido_mista),
            Card(20, "Naranccia Ghirga", Res.drawable.narancia_ghirga),
            Card(21, "Diavolo", Res.drawable.diavolo),
            Card(22, "Jolyne Kujo", Res.drawable.jolyne_kujo),
            Card(23, "Ermes Costello", Res.drawable.ermes_costello),
            Card(24, "Narciso Anasui", Res.drawable.narciso_anasui),
            Card(25, "Enrico Pucci", Res.drawable.enrico_pucci),
            Card(1, "Star Platinum", Res.drawable.star_platinum),
            Card(2, "Hermit Purple", Res.drawable.hermit_purple),
            Card(3, "Magician's Red", Res.drawable.magicians_red),
            Card(4, "Hierofant Green", Res.drawable.hierophant_green),
            Card(5, "Silver Chariot", Res.drawable.silver_chariot),
            Card(6, "The Fool", Res.drawable.the_fool),
            Card(7, "The World", Res.drawable.the_world),
            Card(8, "Emperor", Res.drawable.emperor),
            Card(9, "Bastet", Res.drawable.bastet),
            Card(10, "Osiris", Res.drawable.osiris),
            Card(11, "Cream", Res.drawable.cream),
            Card(12, "Crazy Diamond", Res.drawable.crazy_diamond),
            Card(13, "Echoes", Res.drawable.echoes),
            Card(14, "The Hand", Res.drawable.the_hand),
            Card(15, "Heaven's Door", Res.drawable.heavens_door),
            Card(16, "Killer Queen", Res.drawable.killer_queen),
            Card(17, "Gold Experience", Res.drawable.gold_experience),
            Card(18, "Sticky Fingers", Res.drawable.sticky_fingers),
            Card(19, "Sex Pistols", Res.drawable.sex_pistols),
            Card(20, "Aerosmith", Res.drawable.aerosmith),
            Card(21, "King Crimson", Res.drawable.king_crimson),
            Card(22, "Stone Free", Res.drawable.stone_free),
            Card(23, "Kiss", Res.drawable.kiss),
            Card(24, "Diver Down", Res.drawable.diver_down),
            Card(25, "Whitesnake", Res.drawable.whitesnake),
            )
    }
}
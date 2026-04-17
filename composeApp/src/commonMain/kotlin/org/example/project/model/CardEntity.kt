package org.example.project.model

import kotlinproject.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

data class CardEntity(
    val id: Int,
    val name: String,
    val image: DrawableResource,
)

object CardProvider{
    fun ObtainCards(): MutableList<CardEntity> {
        return mutableListOf(
            CardEntity(1, "Jotaro Kujo", Res.drawable.jotaro_kujo),
            CardEntity(2, "Joseph Joestar", Res.drawable.joseph_joestar),
            CardEntity(3, "Muhammad Abdol", Res.drawable.muhammad_avdol),
            CardEntity(4, "Noriaki Kakyoin", Res.drawable.noriaki_kakyoin),
            CardEntity(5, "Jean Pierre Polnareff", Res.drawable.jean_pierre_polnareff),
            CardEntity(6, "Iggy", Res.drawable.iggy),
            CardEntity(7, "DIO", Res.drawable.dio),
            CardEntity(8, "Hol Horse", Res.drawable.hol_horse),
            CardEntity(9, "Mariah", Res.drawable.mariah),
            CardEntity(10, "Daniel J.D'arby", Res.drawable.daniel_j_darby),
            CardEntity(11, "Vanilla Ice", Res.drawable.vanilla_ice),
            CardEntity(12, "Josuke Higashikata", Res.drawable.josuke_higashikata),
            CardEntity(13, "Koichi Hirose", Res.drawable.koichi_hirose),
            CardEntity(14, "Okuyasu Nijimura", Res.drawable.okuyasu_nijimura),
            CardEntity(15, "Rohan Kishibe", Res.drawable.rohan_kishibe),
            CardEntity(16, "Yoshikage Kira", Res.drawable.kira_yoshikage),
            CardEntity(17, "Giorno Giovanna", Res.drawable.giorno_giovanna),
            CardEntity(18, "Bruno Bucciarati", Res.drawable.bruno_bucciarati),
            CardEntity(19, "Guido Mista", Res.drawable.guido_mista),
            CardEntity(20, "Naranccia Ghirga", Res.drawable.narancia_ghirga),
            CardEntity(21, "Diavolo", Res.drawable.diavolo),
            CardEntity(22, "Jolyne Kujo", Res.drawable.jolyne_kujo),
            CardEntity(23, "Ermes Costello", Res.drawable.ermes_costello),
            CardEntity(24, "Narciso Anasui", Res.drawable.narciso_anasui),
            CardEntity(25, "Enrico Pucci", Res.drawable.enrico_pucci),
            CardEntity(1, "Star Platinum", Res.drawable.star_platinum),
            CardEntity(2, "Hermit Purple", Res.drawable.hermit_purple),
            CardEntity(3, "Magician's Red", Res.drawable.magicians_red),
            CardEntity(4, "Hierofant Green", Res.drawable.hierophant_green),
            CardEntity(5, "Silver Chariot", Res.drawable.silver_chariot),
            CardEntity(6, "The Fool", Res.drawable.the_fool),
            CardEntity(7, "The World", Res.drawable.the_world),
            CardEntity(8, "Emperor", Res.drawable.emperor),
            CardEntity(9, "Bastet", Res.drawable.bastet),
            CardEntity(10, "Osiris", Res.drawable.osiris),
            CardEntity(11, "Cream", Res.drawable.cream),
            CardEntity(12, "Crazy Diamond", Res.drawable.crazy_diamond),
            CardEntity(13, "Echoes", Res.drawable.echoes),
            CardEntity(14, "The Hand", Res.drawable.the_hand),
            CardEntity(15, "Heaven's Door", Res.drawable.heavens_door),
            CardEntity(16, "Killer Queen", Res.drawable.killer_queen),
            CardEntity(17, "Gold Experience", Res.drawable.gold_experience),
            CardEntity(18, "Sticky Fingers", Res.drawable.sticky_fingers),
            CardEntity(19, "Sex Pistols", Res.drawable.sex_pistols),
            CardEntity(20, "Aerosmith", Res.drawable.aerosmith),
            CardEntity(21, "King Crimson", Res.drawable.king_crimson),
            CardEntity(22, "Stone Free", Res.drawable.stone_free),
            CardEntity(23, "Kiss", Res.drawable.kiss),
            CardEntity(24, "Diver Down", Res.drawable.diver_down),
            CardEntity(25, "Whitesnake", Res.drawable.whitesnake),
            )
    }
}
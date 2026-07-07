package com.random.problems.other.dragonByte

import com.random.problems.other.dragonByte.twentySix.round1.kitchenDutyRoster.KitchenDutyRoster
import com.random.problems.other.dragonByte.twentySix.round1.subtractFirstDigit.SubtractFirstDigit
import com.random.problems.other.dragonByte.twentySix.round1.waterSlides.WaterSlides

val subtractFirstDigit = SubtractFirstDigit()
val kitchenDutyRoster = KitchenDutyRoster()
val waterSlides = WaterSlides()

fun main() {
    /*subtractFirstDigit.test("S0_1", "S1", "S0_2", "S2")
    subtractFirstDigit.solve("S00")*/

    /*kitchenDutyRoster.test("K1", "K2", "K3")
    kitchenDutyRoster.solve("K5")*/

    waterSlides.test("W1")
    waterSlides.solveAndTestWithChecker("W1", "W2")
}
//package com.brandoncano.resistancecalculator.util
//
//import com.brandoncano.resistancecalculator.resistor.Resistor
//import com.brandoncano.resistancecalculator.constants.Colors as C
//import com.brandoncano.resistancecalculator.constants.Symbols as S
//import org.junit.Assert.assertEquals
//import org.junit.Test
//
///**
// * Note: This will only test valid number inputs, since only valid inputs are allowed through this.
// */
//class ResistorFormatterTest {
//
//    @Test
//    fun `non number resistance values`() {
//        val resistor = Resistor()
//        ResistorFormatter.generateResistor(resistor)
//        assertEquals("", resistor.multiplierBand)
//
//        resistor.resistance = "NotValid"
//        ResistorFormatter.generateResistor(resistor)
//        assertEquals("", resistor.multiplierBand)
//    }
//
//    @Test
//    fun `numerical input for multiplier band of four band resistors`() {
//        val resistances = listOf(
//            "1", "10", "100", "1000", "10000",
//            "1", "10", "100", "1000", "10000",
//            "1", "10", "100", "1000", "10000",
//            "1", "10",
//            "23", "230", "2300", "23000",
//            "23", "230", "2300", "23000",
//            "23", "230", "2300", "23000",
//            "23", "230" // blank, since invalid
//        )
//
//        val units = listOf(
//            S.Ohms, S.Ohms, S.Ohms, S.Ohms, S.Ohms,
//            S.kOhms, S.kOhms, S.kOhms, S.kOhms, S.kOhms,
//            S.MOhms, S.MOhms, S.MOhms, S.MOhms, S.MOhms,
//            S.GOhms, S.GOhms,
//            S.Ohms, S.Ohms, S.Ohms, S.Ohms,
//            S.kOhms, S.kOhms, S.kOhms, S.kOhms,
//            S.MOhms, S.MOhms, S.MOhms, S.MOhms,
//            S.GOhms, S.GOhms
//        )
//
//        val answers = listOf(
//            C.GOLD, C.BLACK, C.BROWN, C.RED, C.ORANGE,
//            C.RED, C.ORANGE, C.YELLOW, C.GREEN, C.BLUE,
//            C.GREEN, C.BLUE, C.VIOLET, C.GRAY, C.WHITE,
//            C.GRAY, C.WHITE,
//            C.BLACK, C.BROWN, C.RED, C.ORANGE,
//            C.ORANGE, C.YELLOW, C.GREEN, C.BLUE,
//            C.BLUE, C.VIOLET, C.GRAY, C.WHITE,
//            C.WHITE, C.BLANK
//        )
//
//        val resistor = Resistor()
//        resistor.toleranceValue = "${S.PM}5%"
//        resistor.setNumberOfBands(4)
//
//        // test multiplier
//        for (i in answers.indices) {
//            resistor.units = units[i]
//            resistor.resistance = resistances[i]
//            ResistorFormatter.generateResistor(resistor)
//            assertEquals(answers[i], resistor.multiplierBand)
//        }
//    }
//
//    @Test
//    fun `numerical input for multiplier band of five band resistors`() {
//        val resistances = listOf(
//            "1", "10", "100", "1000", "10000",
//            "1", "10", "100", "1000", "10000",
//            "1", "10", "100", "1000", "10000",
//            "1", "10", "100",
//            "23", "230", "2300", "23000",
//            "23", "230", "2300", "23000",
//            "23", "230", "2300", "23000",
//            "23", "230",
//            "456", "4560", "45600",
//            "456", "4560", "45600",
//            "456", "4560", "45600",
//            "456", "4560" // blank, since invalid
//        )
//
//        val units = listOf(
//            S.Ohms, S.Ohms, S.Ohms, S.Ohms, S.Ohms,
//            S.kOhms, S.kOhms, S.kOhms, S.kOhms, S.kOhms,
//            S.MOhms, S.MOhms, S.MOhms, S.MOhms, S.MOhms,
//            S.GOhms, S.GOhms, S.GOhms,
//            S.Ohms, S.Ohms, S.Ohms, S.Ohms,
//            S.kOhms, S.kOhms, S.kOhms, S.kOhms,
//            S.MOhms, S.MOhms, S.MOhms, S.MOhms,
//            S.GOhms, S.GOhms,
//            S.Ohms, S.Ohms, S.Ohms,
//            S.kOhms, S.kOhms, S.kOhms,
//            S.MOhms, S.MOhms, S.MOhms,
//            S.GOhms, S.GOhms
//        )
//
//        val answers = listOf(
//            C.SILVER, C.GOLD, C.BLACK, C.BROWN, C.RED,
//            C.BROWN, C.RED, C.ORANGE, C.YELLOW, C.GREEN,
//            C.YELLOW, C.GREEN, C.BLUE, C.VIOLET, C.GRAY,
//            C.VIOLET, C.GRAY, C.WHITE,
//            C.GOLD, C.BLACK, C.BROWN, C.RED,
//            C.RED, C.ORANGE, C.YELLOW, C.GREEN,
//            C.GREEN, C.BLUE, C.VIOLET, C.GRAY,
//            C.GRAY, C.WHITE,
//            C.BLACK, C.BROWN, C.RED,
//            C.ORANGE, C.YELLOW, C.GREEN,
//            C.BLUE, C.VIOLET, C.GRAY,
//            C.WHITE, C.BLANK
//        )
//
//        val resistor = Resistor()
//        resistor.toleranceValue = "${S.PM}5%"
//        resistor.setNumberOfBands(5)
//
//        // test multiplier
//        for (i in answers.indices) {
//            resistor.units = units[i]
//            resistor.resistance = resistances[i]
//            ResistorFormatter.generateResistor(resistor)
//            assertEquals(answers[i], resistor.multiplierBand)
//        }
//    }
//
//    @Test
//    fun `decimal inputs for multiplier band of four band resistors`() {
//        val resistances = listOf(
//            "1.0", "10.0", "1.0", "10.0", "1.0", "10.0", "1.0", "10.0",
//            "23.0", "23.0", "23.0", "23.0", "23.", "23.", "23.", "23.",
//            "4.5", "4.5", "4.5", "4.5",
//        )
//
//        val units = listOf(
//            S.Ohms, S.Ohms, S.kOhms, S.kOhms, S.MOhms, S.MOhms, S.GOhms, S.GOhms,
//            S.Ohms, S.kOhms, S.MOhms, S.GOhms, S.Ohms, S.kOhms, S.MOhms, S.GOhms,
//            S.Ohms, S.kOhms, S.MOhms, S.GOhms,
//        )
//
//        val answers = listOf(
//            C.GOLD, C.BLACK, C.RED, C.ORANGE, C.GREEN, C.BLUE, C.GRAY, C.WHITE,
//            C.BLACK, C.ORANGE, C.BLUE, C.WHITE, C.BLACK, C.ORANGE, C.BLUE, C.WHITE,
//            C.GOLD, C.RED, C.GREEN, C.GRAY,
//        )
//
//        val resistor = Resistor()
//        resistor.toleranceValue = "${S.PM}5%"
//        resistor.setNumberOfBands(4)
//
//        // test multiplier
//        for (i in answers.indices) {
//            resistor.units = units[i]
//            resistor.resistance = resistances[i]
//            ResistorFormatter.generateResistor(resistor)
//            assertEquals(answers[i], resistor.multiplierBand)
//        }
//    }
//
//    @Test
//    fun `decimal inputs for multiplier band of five band resistors`() {
//        val resistances = listOf(
//            "1.0", "10.0", "100.0", "1.0", "10.0", "100.0", "1.0", "10.0", "100.0", "1.0", "10.0", "100.0",
//            "23.0", "230.0", "23.0", "230.0", "23.0", "230.0", "23.0", "230.0",
//            "456.0", "456.0", "456.0", "456.0", "456.", "456.", "456.", "456.",
//            "4.5", "4.5", "4.50", "4.50", "4.56", "4.56", "4.56", "4.56",
//            "78.9", "78.9", "78.9", "78.9", "70.9", "70.9", "70.9", "70.9", "1.00"
//        )
//
//        val units = listOf(
//            S.Ohms, S.Ohms, S.Ohms, S.kOhms, S.kOhms, S.kOhms, S.MOhms, S.MOhms, S.MOhms, S.GOhms, S.GOhms, S.GOhms,
//            S.Ohms, S.Ohms, S.kOhms, S.kOhms, S.MOhms, S.MOhms, S.GOhms, S.GOhms,
//            S.Ohms, S.kOhms, S.MOhms, S.GOhms, S.Ohms, S.kOhms, S.MOhms, S.GOhms,
//            S.Ohms, S.kOhms, S.MOhms, S.GOhms, S.Ohms, S.kOhms, S.MOhms, S.GOhms,
//            S.Ohms, S.kOhms, S.MOhms, S.GOhms, S.Ohms, S.kOhms, S.MOhms, S.GOhms, S.Ohms
//        )
//
//        val answers = listOf(
//            C.SILVER, C.GOLD, C.BLACK, C.BROWN, C.RED, C.ORANGE, C.YELLOW, C.GREEN, C.BLUE, C.VIOLET, C.GRAY, C.WHITE,
//            C.GOLD, C.BLACK, C.RED, C.ORANGE, C.GREEN, C.BLUE, C.GRAY, C.WHITE,
//            C.BLACK, C.ORANGE, C.BLUE, C.WHITE, C.BLACK, C.ORANGE, C.BLUE, C.WHITE,
//            C.SILVER, C.BROWN, C.YELLOW, C.VIOLET, C.SILVER, C.BROWN, C.YELLOW, C.VIOLET,
//            C.GOLD, C.RED, C.GREEN, C.GRAY, C.GOLD, C.RED, C.GREEN, C.GRAY, C.SILVER
//        )
//
//        val resistor = Resistor()
//        resistor.toleranceValue = "${S.PM}5%"
//        resistor.setNumberOfBands(5)
//
//        // test multiplier
//        for (i in answers.indices) {
//            resistor.units = units[i]
//            resistor.resistance = resistances[i]
//            ResistorFormatter.generateResistor(resistor)
//            assertEquals(answers[i], resistor.multiplierBand)
//        }
//    }
//
//    @Test
//    fun `leading zeros as inputs for four band resistors`() {
//        val resistances = listOf(
//            "0.1", "0.10", "0.12", "0.1", "0.10", "0.12", "0.1", "0.10", "0.12", "0.1", "0.10", "0.12",
//            "0.01", "0.01", "0.01", "0.01"
//        )
//
//        var units = listOf(
//            S.Ohms, S.Ohms, S.Ohms, S.kOhms, S.kOhms, S.kOhms, S.MOhms, S.MOhms, S.MOhms, S.GOhms, S.GOhms, S.GOhms,
//            S.Ohms, S.kOhms, S.MOhms, S.GOhms
//        )
//
//        val answers = listOf(
//            C.SILVER, C.SILVER, C.SILVER, C.BROWN, C.BROWN, C.BROWN, C.YELLOW, C.YELLOW, C.YELLOW, C.VIOLET, C.VIOLET, C.VIOLET,
//            C.SILVER, C.BLACK, C.ORANGE, C.BLUE
//        )
//
//        val resistor = Resistor()
//        resistor.toleranceValue = "${S.PM}5%"
//        resistor.setNumberOfBands(4)
//
//        // test multiplier
//        for (i in answers.indices) {
//            resistor.units = units[i]
//            resistor.resistance = resistances[i]
//            ResistorFormatter.generateResistor(resistor)
//            assertEquals(answers[i], resistor.multiplierBand)
//        }
//
//        // test resistor
//        val resistors = listOf(
//            Resistor(C.BLACK, C.BROWN, "", C.SILVER, C.GOLD), // 0.01 Ohms
//            Resistor(C.BROWN, C.BLACK, "", C.BLACK, C.GOLD), // 0.01 kOhms -> 10 Ohms
//            Resistor(C.BROWN, C.BLACK, "", C.ORANGE, C.GOLD), // 0.01 MOhms -> 10 kOhms
//            Resistor(C.BROWN, C.BLACK, "", C.BLUE, C.GOLD), // 0.01 GOhms -> 10 MOhms
//        )
//
//        resistor.resistance = "0.01"
//        resistor.toleranceBand = C.GOLD
//        units = listOf(S.Ohms, S.kOhms, S.MOhms, S.GOhms)
//        for (i in resistors.indices) {
//            resistor.units = units[i]
//            ResistorFormatter.generateResistor(resistor)
//            assertEquals(resistors[i], resistor)
//        }
//    }
//
//    @Test
//    fun `leading zeros as inputs for five band resistors`() {
//        val resistances = listOf(
//            "0.10", "0.12", "0.10", "0.12", "0.10", "0.12", "0.10", "0.12",
//            "0.01", "0.01", "0.01", "0.01"
//        )
//
//        var units = listOf(
//            S.Ohms, S.Ohms, S.kOhms, S.kOhms, S.MOhms, S.MOhms, S.GOhms, S.GOhms,
//            S.Ohms, S.kOhms, S.MOhms, S.GOhms,
//        )
//
//        val answers = listOf(
//            C.SILVER, C.SILVER, C.BLACK, C.BLACK, C.ORANGE, C.ORANGE, C.BLUE, C.BLUE,
//            C.SILVER, C.GOLD, C.RED, C.GREEN
//        )
//
//        val resistor = Resistor()
//        resistor.toleranceValue = "${S.PM}5%"
//        resistor.setNumberOfBands(5)
//
//        // test multiplier
//        for (i in answers.indices) {
//            resistor.units = units[i]
//            resistor.resistance = resistances[i]
//            ResistorFormatter.generateResistor(resistor)
//            assertEquals(answers[i], resistor.multiplierBand)
//        }
//
//        // test resistor
//        val resistors = listOf(
//            Resistor(C.BLACK, C.BLACK, C.BROWN, C.SILVER, C.GOLD), // 0.01 Ohms
//            Resistor(C.BROWN, C.BLACK, C.BLACK, C.GOLD, C.GOLD), // 0.01 kOhms -> 10 Ohms
//            Resistor(C.BROWN, C.BLACK, C.BLACK, C.RED, C.GOLD), // 0.01 MOhms -> 10 kOhms
//            Resistor(C.BROWN, C.BLACK, C.BLACK, C.GREEN, C.GOLD), // 0.01 GOhms -> 10 MOhms
//        )
//
//        resistor.resistance = "0.01"
//        resistor.toleranceBand = C.GOLD
//        units = listOf(S.Ohms, S.kOhms, S.MOhms, S.GOhms)
//        for (i in resistors.indices) {
//            resistor.units = units[i]
//            ResistorFormatter.generateResistor(resistor)
//            assertEquals(resistors[i], resistor)
//        }
//    }
//
//    @Test
//    fun `not valid unit string`() {
//        val resistor = Resistor()
//        resistor.resistance = "12"
//        resistor.units = "notValid"
//        ResistorFormatter.generateResistor(resistor)
//        assertEquals(C.BLACK, resistor.multiplierBand)
//    }
//}

package signature

import java.io.File

fun main() {
//    var fn = "./myFiles/roman.txt"
//    val fontRoman = loadFont(fn)
    print("Enter name and surname: ")
    val name = readLine()!!
    print("Enter person's status: ")
    val s = readLine()!!

    val str = MutableList(10) { "" }
    for (i in 0 until 10) {
        str[i] = mText(name, i + 1, 1)
    }

    val st = MutableList(3) { "" }
    for (i in 0 until 3) {
        st[i] = mText(s, i + 1, 2)
    }

    var len = str[0].length
    var (s11, s12, s21, s22) = listOf("", "", "", "")
    var l = 0
    if (str[0].length > st[0].length) {
        l = len - st[0].length
        s21 = " ".repeat(l / 2)
        s22 = " ".repeat(l / 2 + l % 2)
    } else {
        len = st[0].length
        l = len - str[0].length
        s11 = " ".repeat(l / 2)
        s12 = " ".repeat(l / 2 + l % 2)
    }
    val line = "8".repeat(len + 9)
    println(line)
    for (i in 0 until 10) {
        println("88  $s11${str[i]}$s12   88")
    }
    for (i in 0 until 3) {
        println("88  $s21${st[i]}$s22   88")
    }
    println(line)
}

data class Font(val ch: Char, val w: Int, val rows: List<String>)

fun loadFont(fn: String): MutableList<Font> {
    val font = mutableListOf<Font>()
    if (File(fn).exists()) {
        val lines = File(fn).readLines()
        val (rows, num) = lines[0].split(" ")
        val r = rows.toInt() + 1
        for (i in 0 until num.toInt()) {
            val k = i * r + 1
            val (ch, w) = lines[k].split(" ")
            val rows = mutableListOf<String>()
            for (j in 1 until r) {
                rows.add(lines[k + j])
            }
            font.add(Font(ch[0], w.toInt(), rows))
        }
    } else {
        println("File $fn not found")
    }
    return font
}

fun mChar(ch: Char, pos: Int, f: Int): String? {
    val font1 = mapOf(
        'a' to listOf("          ", "          ", " .oooo.   ", "`P  )88b  ", " .oP\"888  ", "d8(  888  ", "`Y888\"\"8o ", "          ", "          ", "          "),
        'b' to listOf(" .o8       ", "\"888       ", " 888oooo.  ", " d88\' `88b ", " 888   888 ", " 888   888 ", " `Y8bod8P\' ", "           ", "           ", "           "),
        'c' to listOf("          ", "          ", " .ooooo.  ", "d88\' `\"Y8 ", "888       ", "888   .o8 ", "`Y8bod8P\' ", "          ", "          ", "          "),
        'd' to listOf("      .o8  ", "     \"888  ", " .oooo888  ", "d88\' `888  ", "888   888  ", "888   888  ", "`Y8bod88P\" ", "           ", "           ", "           "),
        'e' to listOf("          ", "          ", " .ooooo.  ", "d88\' `88b ", "888ooo888 ", "888    .o ", "`Y8bod8P\' ", "          ", "          ", "          "),
        'f' to listOf(" .o88o. ", " 888 `\" ", "o888oo  ", " 888    ", " 888    ", " 888    ", "o888o   ", "        ", "        ", "        "),
        'g' to listOf("           ", "           ", " .oooooooo ", "888\' `88b  ", "888   888  ", "`88bod8P\'  ", "`8oooooo.  ", "d\"     YD  ", "\"Y88888P\'  ", "           "),
        'h' to listOf("oooo        ", "`888        ", " 888 .oo.   ", " 888P\"Y88b  ", " 888   888  ", " 888   888  ", "o888o o888o ", "            ", "            ", "            "),
        'i' to listOf(" o8o  ", " `\"\'  ", "oooo  ", "`888  ", " 888  ", " 888  ", "o888o ", "      ", "      ", "      "),
        'j' to listOf("    o8o ", "    `\"\' ", "   oooo ", "   `888 ", "    888 ", "    888 ", "    888 ", "    888 ", ".o. 88P ", "`Y888P  "),
        'k' to listOf("oooo        ", "`888        ", " 888  oooo  ", " 888 .8P\'   ", " 888888.    ", " 888 `88b.  ", "o888o o888o ", "            ", "            ", "            "),
        'l' to listOf("oooo  ", "`888  ", " 888  ", " 888  ", " 888  ", " 888  ", "o888o ", "      ", "      ", "      "),
        'm' to listOf("                  ", "                  ", "ooo. .oo.  .oo.   ", "`888P\"Y88bP\"Y88b  ", " 888   888   888  ", " 888   888   888  ", "o888o o888o o888o ", "                  ", "                  ", "                  "),
        'n' to listOf("            ", "            ", "ooo. .oo.   ", "`888P\"Y88b  ", " 888   888  ", " 888   888  ", "o888o o888o ", "            ", "            ", "            "),
        'o' to listOf("          ", "          ", " .ooooo.  ", "d88\' `88b ", "888   888 ", "888   888 ", "`Y8bod8P\' ", "          ", "          ", "          "),
        'p' to listOf("           ", "           ", "oo.ooooo.  ", " 888\' `88b ", " 888   888 ", " 888   888 ", " 888bod8P\' ", " 888       ", "o888o      ", "           "),
        'q' to listOf("           ", "           ", " .ooooo oo ", "d88\' `888  ", "888   888  ", "888   888  ", "`V8bod888  ", "      888. ", "      8P\'  ", "      \"    "),
        'r' to listOf("         ", "         ", "oooo d8b ", "`888\"\"8P ", " 888     ", " 888     ", "d888b    ", "         ", "         ", "         "),
        's' to listOf("         ", "         ", " .oooo.o ", "d88(  \"8 ", "`\"Y88b.  ", "o.  )88b ", "8\"\"888P\' ", "         ", "         ", "         "),
        't' to listOf("    .   ", "  .o8   ", ".o888oo ", "  888   ", "  888   ", "  888 . ", "  \"888\" ", "        ", "        ", "        "),
        'u' to listOf("            ", "            ", "oooo  oooo  ", "`888  `888  ", " 888   888  ", " 888   888  ", " `V88V\"V8P\' ", "            ", "            ", "            "),
        'v' to listOf("            ", "            ", "oooo    ooo ", " `88.  .8\'  ", "  `88..8\'   ", "   `888\'    ", "    `8\'     ", "            ", "            ", "            "),
        'w' to listOf("                 ", "                 ", "oooo oooo    ooo ", " `88. `88.  .8\'  ", "  `88..]88..8\'   ", "   `888\'`888\'    ", "    `8\'  `8\'     ", "                 ", "                 ", "                 "),
        'x' to listOf("            ", "            ", "oooo    ooo ", " `88b..8P\'  ", "   Y888\'    ", " .o8\"\'88b   ", "o88\'   888o ", "            ", "            ", "            "),
        'y' to listOf("            ", "            ", "oooo    ooo ", " `88.  .8\'  ", "  `88..8\'   ", "   `888\'    ", "    .8\'     ", ".o..P\'      ", "`Y8P\'       ", "            "),
        'z' to listOf("           ", "           ", "  oooooooo ", " d\'\"\"7d8P  ", "   .d8P\'   ", " .d8P\'  .P ", "d8888888P  ", "           ", "           ", "           "),
        'A' to listOf("      .o.       ", "     .888.      ", "    .8\"888.     ", "   .8\' `888.    ", "  .88ooo8888.   ", " .8\'     `888.  ", "o88o     o8888o ", "                ", "                ", "                "),
        'B' to listOf("oooooooooo.  ", "`888\'   `Y8b ", " 888     888 ", " 888oooo888\' ", " 888    `88b ", " 888    .88P ", "o888bood8P\'  ", "             ", "             ", "             "),
        'C' to listOf("  .oooooo.   ", " d8P\'  `Y8b  ", "888          ", "888          ", "888          ", "`88b    ooo  ", " `Y8bood8P\'  ", "             ", "             ", "             "),
        'D' to listOf("oooooooooo.   ", "`888\'   `Y8b  ", " 888      888 ", " 888      888 ", " 888      888 ", " 888     d88\' ", "o888bood8P\'   ", "              ", "              ", "              "),
        'E' to listOf("oooooooooooo ", "`888\'     `8 ", " 888         ", " 888oooo8    ", " 888    \"    ", " 888       o ", "o888ooooood8 ", "             ", "             ", "             "),
        'F' to listOf("oooooooooooo ", "`888\'     `8 ", " 888         ", " 888oooo8    ", " 888    \"    ", " 888         ", "o888o        ", "             ", "             ", "             "),
        'G' to listOf("  .oooooo.    ", " d8P\'  `Y8b   ", "888           ", "888           ", "888     ooooo ", "`88.    .88\'  ", " `Y8bood8P\'   ", "              ", "              ", "              "),
        'H' to listOf("ooooo   ooooo ", "`888\'   `888\' ", " 888     888  ", " 888ooooo888  ", " 888     888  ", " 888     888  ", "o888o   o888o ", "              ", "              ", "              "),
        'I' to listOf("ooooo ", "`888\' ", " 888  ", " 888  ", " 888  ", " 888  ", "o888o ", "      ", "      ", "      "),
        'J' to listOf("   oooo ", "   `888 ", "    888 ", "    888 ", "    888 ", "    888 ", ".o. 88P ", "`Y888P  ", "        ", "        "),
        'K' to listOf("oooo    oooo ", "`888   .8P\'  ", " 888  d8\'    ", " 88888[      ", " 888`88b.    ", " 888  `88b.  ", "o888o  o888o ", "             ", "             ", "             "),
        'L' to listOf("ooooo        ", "`888\'        ", " 888         ", " 888         ", " 888         ", " 888       o ", "o888ooooood8 ", "             ", "             ", "             "),
        'M' to listOf("ooo        ooooo ", "`88.       .888\' ", " 888b     d\'888  ", " 8 Y88. .P  888  ", " 8  `888\'   888  ", " 8    Y     888  ", "o8o        o888o ", "                 ", "                 ", "                 "),
        'N' to listOf("ooooo      ooo ", "`888b.     `8\' ", " 8 `88b.    8  ", " 8   `88b.  8  ", " 8     `88b.8  ", " 8       `888  ", "o8o        `8  ", "               ", "               ", "               "),
        'O' to listOf("  .oooooo.   ", " d8P\'  `Y8b  ", "888      888 ", "888      888 ", "888      888 ", "`88b    d88\' ", " `Y8bood8P\'  ", "             ", "             ", "             "),
        'P' to listOf("ooooooooo.   ", "`888   `Y88. ", " 888   .d88\' ", " 888ooo88P\'  ", " 888         ", " 888         ", "o888o        ", "             ", "             ", "             "),
        'Q' to listOf("  .oooooo.      ", " d8P\'  `Y8b     ", "888      888    ", "888      888    ", "888      888    ", "`88b    d88b    ", " `Y8bood8P\'Ybd\' ", "                ", "                ", "                "),
        'R' to listOf("ooooooooo.   ", "`888   `Y88. ", " 888   .d88\' ", " 888ooo88P\'  ", " 888`88b.    ", " 888  `88b.  ", "o888o  o888o ", "             ", "             ", "             "),
        'S' to listOf(" .oooooo..o ", "d8P\'    `Y8 ", "Y88bo.      ", " `\"Y8888o.  ", "     `\"Y88b ", "oo     .d8P ", "8\"\"88888P\'  ", "            ", "            ", "            "),
        'T' to listOf("ooooooooooooo ", "8\'   888   `8 ", "     888      ", "     888      ", "     888      ", "     888      ", "    o888o     ", "              ", "              ", "              "),
        'U' to listOf("ooooo     ooo ", "`888\'     `8\' ", " 888       8  ", " 888       8  ", " 888       8  ", " `88.    .8\'  ", "   `YbodP\'    ", "              ", "              ", "              "),
        'V' to listOf("oooooo     oooo ", " `888.     .8\'  ", "  `888.   .8\'   ", "   `888. .8\'    ", "    `888.8\'     ", "     `888\'      ", "      `8\'       ", "                ", "                ", "                "),
        'W' to listOf("oooooo   oooooo     oooo ", " `888.    `888.     .8\'  ", "  `888.   .8888.   .8\'   ", "   `888  .8\'`888. .8\'    ", "    `888.8\'  `888.8\'     ", "     `888\'    `888\'      ", "      `8\'      `8\'       ", "                         ", "                         ", "                         "),
        'X' to listOf("ooooooo  ooooo ", " `8888    d8\'  ", "   Y888..8P    ", "    `8888\'     ", "   .8PY888.    ", "  d8\'  `888b   ", "o888o  o88888o ", "               ", "               ", "               "),
        'Y' to listOf("oooooo   oooo ", " `888.   .8\'  ", "  `888. .8\'   ", "   `888.8\'    ", "    `888\'     ", "     888      ", "    o888o     ", "              ", "              ", "              "),
        'Z' to listOf(" oooooooooooo ", "d\'\"\"\"\"\"\"d888\' ", "      .888P   ", "     d888\'    ", "   .888P      ", "  d888\'    .P ", ".8888888888P  ", "              ", "              ", "              "))

    val font2 = mapOf(
        'a' to listOf("____ ", "|__| ", "|  | "),
        'b' to listOf("___  ", "|__] ", "|__] "),
        'c' to listOf("____ ", "|    ", "|___ "),
        'd' to listOf("___  ", "|  \\ ", "|__/ "),
        'e' to listOf("____ ", "|___ ", "|___ "),
        'f' to listOf("____ ", "|___ ", "|    "),
        'g' to listOf("____ ", "| __ ", "|__] "),
        'h' to listOf("_  _ ", "|__| ", "|  | "),
        'i' to listOf("_ ", "| ", "| "),
        'j' to listOf(" _ ", " | ", "_| "),
        'k' to listOf("_  _ ", "|_/  ", "| \\_ "),
        'l' to listOf("_    ", "|    ", "|___ "),
        'm' to listOf("_  _ ", "|\\/| ", "|  | "),
        'n' to listOf("_  _ ", "|\\ | ", "| \\| "),
        'o' to listOf("____ ", "|  | ", "|__| "),
        'p' to listOf("___  ", "|__] ", "|    "),
        'q' to listOf("____ ", "|  | ", "|_\\| "),
        'r' to listOf("____ ", "|__/ ", "|  \\ "),
        's' to listOf("____ ", "[__  ", "___] "),
        't' to listOf("___ ", " |  ", " |  "),
        'u' to listOf("_  _ ", "|  | ", "|__| "),
        'v' to listOf("_  _ ", "|  | ", " \\/  "),
        'w' to listOf("_ _ _ ", "| | | ", "|_|_| "),
        'x' to listOf("_  _ ", " \\/  ", "_/\\_ "),
        'y' to listOf("_   _ ", " \\_/  ", "  |   "),
        'z' to listOf("___  ", "  /  ", " /__ "),
        'A' to listOf("____ ", "|__| ", "|  | "),
        'B' to listOf("___  ", "|__] ", "|__] "),
        'C' to listOf("____ ", "|    ", "|___ "),
        'D' to listOf("___  ", "|  \\ ", "|__/ "),
        'E' to listOf("____ ", "|___ ", "|___ "),
        'F' to listOf("____ ", "|___ ", "|    "),
        'G' to listOf("____ ", "| __ ", "|__] "),
        'H' to listOf("_  _ ", "|__| ", "|  | "),
        'I' to listOf("_ ", "| ", "| "),
        'J' to listOf(" _ ", " | ", "_| "),
        'K' to listOf("_  _ ", "|_/  ", "| \\_ "),
        'L' to listOf("_    ", "|    ", "|___ "),
        'M' to listOf("_  _ ", "|\\/| ", "|  | "),
        'N' to listOf("_  _ ", "|\\ | ", "| \\| "),
        'O' to listOf("____ ", "|  | ", "|__| "),
        'P' to listOf("___  ", "|__] ", "|    "),
        'Q' to listOf("____ ", "|  | ", "|_\\| "),
        'R' to listOf("____ ", "|__/ ", "|  \\ "),
        'S' to listOf("____ ", "[__  ", "___] "),
        'T' to listOf("___ ", " |  ", " |  "),
        'U' to listOf("_  _ ", "|  | ", "|__| "),
        'V' to listOf("_  _ ", "|  | ", " \\/  "),
        'W' to listOf("_ _ _ ", "| | | ", "|_|_| "),
        'X' to listOf("_  _ ", " \\/  ", "_/\\_ "),
        'Y' to listOf("_   _ ", " \\_/  ", "  |   "),
        'Z' to listOf("___  ", "  /  ", " /__ "))
    val font = if (f == 1) font1[ch] else font2[ch]
    return font?.get(pos - 1) ?: ""
}

fun mText(str: String, pos: Int, f: Int): String {
    var s = ""
    val spc = if (f == 1) 10 else 5
    for (ch in str) {
        val ch0 = if (ch in 'a'..'z') 'a' else 'A' - 26
        s += if (ch == ' ') " ".repeat(spc) else mChar(ch, pos, f)
    }
    return s.substringBeforeLast(" ")
}


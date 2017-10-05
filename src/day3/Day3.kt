package day3

import java.io.File

class Day3 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(getInput().filter { triangle -> isTriangle(triangle) }.size );
            println(getInputVertically().filter { triangle -> isTriangle(triangle) }.size );
        }

        private fun isTriangle(sides: List<Int>): Boolean{
            return sides[0] < sides[1] + sides[2]
                    && sides[1] < sides[2] + sides[0]
                    && sides[2] < sides[0] + sides[1];
        }

        private fun getInput(): List<List<Int>> {
            return File("./src/day3/input.txt").readText().split("\n")
                    .map { line -> line.split(Regex("[ \t]+"))
                            .filter { side -> side != "" }
                            .map { side -> side.toInt(); }
                    };
        }

        private fun getInputVertically(): List<List<Int>> {

            val input = File("./src/day3/input.txt").readText();

            val firstCol = input.replace(Regex("(^\\s+)", RegexOption.MULTILINE), "")
                    .replace(Regex("( \\s+\\d{1,3})+"), "")
                    .split("\n").toMutableList();

            val secondCol = input.replace(Regex("(^\\s+)", RegexOption.MULTILINE), "")
                    .replace(Regex("^\\d+\\s+", RegexOption.MULTILINE), "")
                    .replace(Regex("( \\s+\\d{1,3})+"), "")
                    .split("\n");

            val thirdCol = input.replace(Regex("(^\\s+)", RegexOption.MULTILINE), "")
                    .replace(Regex("^\\d+\\s+", RegexOption.MULTILINE), "")
                    .replace(Regex("^\\d+\\s+", RegexOption.MULTILINE), "")
                    .replace(Regex("( \\s+\\d{1,3})+"), "")
                    .split("\n");

            firstCol.addAll(secondCol);
            firstCol.addAll(thirdCol);

            return firstCol.withIndex()
                    .groupBy { it.index / 3 }
                    .map { it.value.map { it.value.toInt() } };
        }

    }
}
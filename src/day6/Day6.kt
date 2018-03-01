package day6

import java.io.File

class Day6 {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val reversedMatrix: ArrayList<ArrayList<String>> = arrayListOf();

            getInput().split("\n")
                    .map { it.split("").filter {it != ""} }.toList()
                    .forEach{
                        it.forEachIndexed { i,v ->
                            if(reversedMatrix.size <= i){
                                reversedMatrix.add(arrayListOf(v));
                            } else{
                                reversedMatrix[i].add(v);
                            }
                        }
                    };

            reversedMatrix.map { it.groupBy { it }.minBy { it.value.size }?.key }.forEach{print(it)};

        }

        private fun getInput(): String {
            return File("./src/day6/input.txt").readText();
        }



    }
}
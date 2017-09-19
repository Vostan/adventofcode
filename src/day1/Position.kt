package day1

class Position(var direction: String, var x: Int, var y: Int) {

    fun getDistance(): Int{
        return Math.abs(x) + Math.abs(y);
    }

}
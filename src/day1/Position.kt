package day1

class Position(var direction: String, var x: Int, var y: Int) {

    companion object {
        var vistis: MutableList<Pair<Int, Int>> = ArrayList<Pair<Int, Int>>();

        fun contains(cord: Pair<Int, Int>): Boolean{
            return vistis.contains(cord);
        }
    }

    fun getDistance(): Int{
        return Math.abs(x) + Math.abs(y);
    }

}
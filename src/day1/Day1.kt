package day1

import javafx.geometry.Pos

class Day1 {

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            print(doMove(Position("N", 0, 0), getCordinates()).getDistance());
        }

        private fun doMove(position: Position, cords: List<String>): Position {

            if(cords.isEmpty()){
                return position;
            }

            val turn = cords[0].replace(Regex("[0-9]"), "");
            val steps = cords[0].replace(Regex("[^0-9]"), "").toInt();

            if((position.direction == "N" && turn == "R")
                    || (position.direction == "S" && turn == "L")){
                if(moveEast(position, steps) == null) return position;
            } else if ((position.direction == "N" && turn == "L")
                    || (position.direction == "S" && turn == "R")){
                if(moveWest(position, steps) == null) return position;
            } else if ((position.direction == "W" && turn == "R")
                    || (position.direction == "E" && turn == "L")){
                if(moveNorth(position, steps) == null) return position;
            } else {
                if(moveSouth(position, steps) == null) return position;
            }

            return doMove(position, cords.drop(1));

        }

        private fun moveEast(position: Position, steps: Int): Position? {

            position.direction = "E";
            for (step in 1..steps){

                position.x += 1;
                if(Position.contains(Pair(position.x, position.y))){
                    return null;
                } else {
                    Position.vistis.add(Pair(position.x, position.y));
                }

            }

            return position;
        }

        private fun moveWest(position: Position, steps: Int): Position? {

            position.direction = "W";
            for (step in 1..steps){

                position.x -= 1;
                if(Position.contains(Pair(position.x, position.y))){
                    return null;
                } else {
                    Position.vistis.add(Pair(position.x, position.y));
                }

            }

            return position;
        }

        private fun moveNorth(position: Position, steps: Int): Position? {

            position.direction = "N";
            for (step in 1..steps){

                position.y += 1;
                if(Position.contains(Pair(position.x, position.y))){
                    return null;
                } else {
                    Position.vistis.add(Pair(position.x, position.y));
                }
                
            }

            return position;
        }

        private fun moveSouth(position: Position, steps: Int): Position? {

            position.direction = "S";
            for (step in 1..steps){

                position.y -= 1;
                if(Position.contains(Pair(position.x, position.y))){
                    return null;
                } else {
                    Position.vistis.add(Pair(position.x, position.y));
                }

            }

            return position;
        }

        private fun getCordinates(): List<String> {
            val input = "L4, R2, R4, L5, L3, L1, R4, R5, R1, R3, L3, L2, L2, R5, R1, L1, L2, R2, R2, L5, R5, R5, L2, R1, R2, L2, L4, L1, R5, R2, R1, R1, L2, L3, R2, L5, L186, L5, L3, R3, L5, R4, R2, L5, R1, R4, L1, L3, R3, R1, L1, R4, R2, L1, L4, R5, L1, R50, L4, R3, R78, R4, R2, L4, R3, L4, R4, L1, R5, L4, R1, L2, R3, L2, R5, R5, L4, L1, L2, R185, L5, R2, R1, L3, R4, L5, R2, R4, L3, R4, L2, L5, R1, R2, L2, L1, L2, R2, L2, R1, L5, L3, L4, L3, L4, L2, L5, L5, R2, L3, L4, R4, R4, R5, L4, L2, R4, L5, R3, R1, L1, R3, L2, R2, R1, R5, L4, R5, L3, R2, R3, R1, R4, L4, R1, R3, L5, L1, L3, R2, R1, R4, L4, R3, L3, R3, R2, L3, L3, R4, L2, R4, L3, L4, R5, R1, L1, R5, R3, R1, R3, R4, L1, R4, R3, R1, L5, L5, L4, R4, R3, L2, R1, R5, L3, R4, R5, L4, L5, R2";

            return input.split(", ");
        }

    }

}
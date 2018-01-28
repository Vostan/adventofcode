package day4

import java.io.File

class Day4 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val encryptedRooms: List<EncryptedRoom> = getInput();

            println(encryptedRooms
                    .filter { isReal(it) }
                    .sumBy { it.id });

            val rooms = encryptedRooms
                    .filter { isReal(it) }
                    .map{ room -> EncryptedRoom(
                            room.encryption.map {
                                shift(it, room.id);
                            }.joinToString(""),
                            room.id,
                            room.checksum);
                        };

            println(rooms.filter { it.encryption.contains("northpole object storage") }[0].id);


        }

        private fun shift(char: Char, by: Int): Char{
            var charM = char;
            var byM = by;

            if (charM == ' '){
                return ' ';
            }

            while (byM > 0) {
                byM--;
                if(charM == 'z'){
                    charM = 'a';
                } else {
                    charM++;
                }
            }

            return charM;
        }

        private fun isReal(encryptedRoom: EncryptedRoom):Boolean{

            var encryptionChecksum: String = "";

            encryptedRoom.encryption
                    .groupingBy { it }
                    .eachCount()
                    .toList()
                    .filter{ it.first != ' ' }
                    .sortedByDescending { it.second }
                    .groupBy { it.second }
                    .map { i -> i.value.sortedBy{it.first} }
                    .forEach { i ->
                        i.forEach{ j->
                            if(encryptionChecksum.length < 5){
                                encryptionChecksum += j.first
                            }
                        }
                    }

            return encryptedRoom.checksum == encryptionChecksum;
        }


        private fun getInput(): List<EncryptedRoom> {
            val input = File("./src/day4/input.txt").readText().split("\n");
            return input.map {

                val encryption = it.split("-").toMutableList();
                val idAndChecksum = encryption.removeAt(it.split("-").size - 1);

                EncryptedRoom(
                        encryption.joinToString(" ","",""),
                        idAndChecksum.split("\\[|\\]".toRegex())[0].toInt(),
                        idAndChecksum.split("\\[|\\]".toRegex())[1]
                );

            };

        }
    }

}
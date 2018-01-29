package day5

import java.security.MessageDigest

class Day5 {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val doorId = "ffykfhsq";

            var found = true;
            var start = 0;
            var password = "";
            while (found){

                val md5Hash = md5Second(doorId + (++start).toString());

                if(md5Hash.substring(0,5) == "00000"){
                    password += md5Hash.substring(5,6);
                    println(password);
                }

                if (password.length == 8){
                    println(password.toLowerCase());
                    found = false;
                }

            }

        }

        private fun md5Second(input: String): String {
            val HEX_CHARS = "0123456789ABCDEF"
            val bytes = MessageDigest
                    .getInstance("MD5")
                    .digest(input.toByteArray())
            val result = StringBuilder(bytes.size * 2)

            bytes.forEach {
                val i = it.toInt()
                result.append(HEX_CHARS[i shr 4 and 0x0f])
                result.append(HEX_CHARS[i and 0x0f])
            }

            return result.toString()
        }

    }
}
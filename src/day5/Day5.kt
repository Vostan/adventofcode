package day5

import com.sun.deploy.util.StringUtils
import java.security.MessageDigest
import kotlin.reflect.jvm.internal.impl.utils.StringsKt

class Day5 {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val doorId = "ffykfhsq";

            var found = true;
            var start = 0;
            val password = arrayListOf<Char>('#','#','#','#','#','#','#','#');

            while(found) {

                val md5Hash = md5Second(doorId + (++start).toString());

                if(md5Hash.substring(0,5) == "00000"){
                    val position:Int? = md5Hash[5].toString().toIntOrNull();
                    val code:Char = md5Hash[6];

                    if(position != null
                            && position <= 7
                            && password[position] == '#'){
                        password[position] = code;
                    }

                    password.map { print(it.toLowerCase()) };
                    println();
                }

                if (!password.contains('#')){
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
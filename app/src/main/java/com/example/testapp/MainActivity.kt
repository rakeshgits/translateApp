package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var unicodeMap: MutableMap<String,String> = mutableMapOf()
    var unicodeVowelMap: MutableMap<String,String> = mutableMapOf()
    var unicodeHalfConsonantMap: MutableMap<String,String> = mutableMapOf()
    var unicodeMatraMap:MutableMap<String,String> = mutableMapOf()

    var halfConsonant = "\u094D"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addDevnagriUnicodeMap()
        addDevnagariMatraMap()
        addDevnagriVowelUnicodeMap()
        addUnicodeHalfConsonents()
        tv_output.text = unicodeMap.get(".")


        editText_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var textString = s.toString()
                var finalString = ""
                var isConsonant = false
                var isVowel = false
                var subLetter  = ""
                var i = 0
                while (i < textString.length){
                    if (textString.get(i) == ' '){
                        finalString += " "
                        i++
                        continue
                    }
                    var availableString = ""
                    isConsonant = false
                    isVowel = false

                    availableString = "${textString.getOrNull(i) ?: ""}${textString.getOrNull(i+1) ?: ""}"
                    while (availableString.length > 0){
                        if (unicodeHalfConsonantMap.containsKey(availableString)){
                            isConsonant = true
                            break
                        }else if (unicodeVowelMap.containsKey(availableString)){
                            isVowel = true
                            break
                        }else if (unicodeHalfConsonantMap.containsKey(availableString.toLowerCase())){
                            availableString = availableString.toLowerCase()
                            isConsonant = true
                            break
                        }else if (unicodeVowelMap.containsKey(availableString.toLowerCase())){
                            availableString = availableString.toLowerCase()
                            isVowel = true
                            break
                        }
                        availableString = availableString.substring(0,availableString.length - 1)
                    }
                    i += availableString.length

                    if (isConsonant){
                        if (textString.getOrNull(i) == 'a' || textString.getOrNull(i) == 'e' || textString.getOrNull(i) == 'i' || textString.getOrNull(i) == 'o' || textString.getOrNull(i) == 'u'){
                            availableString += "a"
                            finalString += unicodeMap.get(availableString)
                            var matraString = "${textString.getOrNull(i) ?: ""}${textString.getOrNull(i+1) ?: ""}"
                            while (matraString.length > 0){
                                if (unicodeMatraMap.containsKey(matraString)){
                                    break
                                }else if (unicodeMatraMap.containsKey(matraString.toLowerCase())){
                                    matraString = matraString.toLowerCase()
                                    break
                                }
                                matraString = matraString.substring(0,matraString.length-1)
                            }
                            i += matraString.length
                            if (matraString != "a"){
                                finalString += unicodeMatraMap.get(matraString)
                            }
                        }else{
                            if (i == textString.length || textString.getOrNull(i) == ' '){
                                availableString += "a"
                                finalString += unicodeMap.get(availableString)

                            }else{
                                finalString += unicodeHalfConsonantMap.get(availableString)
                            }
                        }
                    }else{
                        finalString += unicodeVowelMap.get(availableString)
                    }
                }
                tv_output.text = finalString
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    fun addDevnagariMatraMap(){
        unicodeMatraMap.put("a","\u093E")
        unicodeMatraMap.put("aa","\u093E")
        unicodeMatraMap.put("e","\u093F")
        unicodeMatraMap.put("ee","\u0940")
        unicodeMatraMap.put("u","\u0941")
        unicodeMatraMap.put("uu","\u0942")
        unicodeMatraMap.put("i","\u0947")
        unicodeMatraMap.put("ai","\u0948")
        unicodeMatraMap.put("o","\u094B")
        unicodeMatraMap.put("ou","\u094C")
    }

    fun addDevnagriVowelUnicodeMap() {
        unicodeVowelMap.put("a", "\u0905")
        unicodeVowelMap.put("aa", "\u0906")
        unicodeVowelMap.put("e", "\u0907")
        unicodeVowelMap.put("ee", "\u0908")
        unicodeVowelMap.put("u", "\u0909")
        unicodeVowelMap.put("uu", "\u090A")
        unicodeVowelMap.put("e", "\u090F")
        unicodeVowelMap.put("ai", "\u0910")
        unicodeVowelMap.put("o", "\u0913")
        unicodeVowelMap.put("ou", "\u0914")
    }

    fun addDevnagriUnicodeMap(){
//        unicodeMap.put("a","\u0905")
//        unicodeMap.put("aa","\u0906")
//        unicodeMap.put("e","\u0907")
//        unicodeMap.put("ee","\u0908")
//        unicodeMap.put("u","\u0909")
//        unicodeMap.put("uu","\u090A")
//        unicodeMap.put("e","\u090F")
//        unicodeMap.put("ai","\u0910")
//        unicodeMap.put("o","\u0913")
//        unicodeMap.put("ou","\u0914")

        unicodeMap.put("ka","\u0915")
        unicodeMap.put("kha","\u0916")
        unicodeMap.put("ga","\u0917")
        unicodeMap.put("gha","\u0918")
        unicodeMap.put("nga","\u0919")

        unicodeMap.put("ca","\u091A")
        unicodeMap.put("cha","\u091B")
        unicodeMap.put("ja","\u091C")
        unicodeMap.put("jha","\u091D")
        unicodeMap.put("Nya","\u091E")

        unicodeMap.put("Ta","\u091F")
        unicodeMap.put("Tha","\u0920")
        unicodeMap.put("Da","\u0921")
        unicodeMap.put("Dha","\u0922")
        unicodeMap.put("Na","\u0923")

        unicodeMap.put("ta","\u0924")
        unicodeMap.put("tha","\u0925")
        unicodeMap.put("da","\u0926")
        unicodeMap.put("dha","\u0927")
        unicodeMap.put("na","\u0928")

        unicodeMap.put("pa","\u092A")
        unicodeMap.put("pha","\u092B")
        unicodeMap.put("ba","\u092C")
        unicodeMap.put("bha","\u092D")
        unicodeMap.put("ma","\u092E")

        unicodeMap.put("ya","\u092F")
        unicodeMap.put("ra","\u0930")
        unicodeMap.put("la","\u0932")
        unicodeMap.put("va","\u0935")
        unicodeMap.put("ha","\u0939")

        unicodeMap.put("sa","\u0938")
        unicodeMap.put("sha","\u0936")
        unicodeMap.put("Sha","\u0937")



        unicodeMap.put(".","\u0964")

    }

    fun addUnicodeHalfConsonents(){
        // Half Consonants
        unicodeHalfConsonantMap.put("k","\u0915\u094D")
        unicodeHalfConsonantMap.put("kh","\u0916\u094D")
        unicodeHalfConsonantMap.put("g","\u0917\u094D")
        unicodeHalfConsonantMap.put("gh","\u0918\u094D")
        unicodeHalfConsonantMap.put("ng","\u0919\u094D")

        unicodeHalfConsonantMap.put("c","\u091A\u094D")
        unicodeHalfConsonantMap.put("ch","\u091B\u094D")
        unicodeHalfConsonantMap.put("j","\u091C\u094D")
        unicodeHalfConsonantMap.put("jh","\u091D\u094D")
        unicodeHalfConsonantMap.put("Ny","\u091E\u094D")

        unicodeHalfConsonantMap.put("T","\u091F\u094D")
        unicodeHalfConsonantMap.put("Th","\u0920\u094D")
        unicodeHalfConsonantMap.put("D","\u0921\u094D")
        unicodeHalfConsonantMap.put("Dh","\u0922\u094D")
        unicodeHalfConsonantMap.put("N","\u0923\u094D")

        unicodeHalfConsonantMap.put("t","\u0924\u094D")
        unicodeHalfConsonantMap.put("th","\u0925\u094D")
        unicodeHalfConsonantMap.put("d","\u0926\u094D")
        unicodeHalfConsonantMap.put("dh","\u0927\u094D")
        unicodeHalfConsonantMap.put("n","\u0928\u094D")

        unicodeHalfConsonantMap.put("p","\u092A\u094D")
        unicodeHalfConsonantMap.put("ph","\u092B\u094D")
        unicodeHalfConsonantMap.put("b","\u092C\u094D")
        unicodeHalfConsonantMap.put("bh","\u092D\u094D")
        unicodeHalfConsonantMap.put("m","\u092E\u094D")

        unicodeHalfConsonantMap.put("y","\u092F\u094D")
        unicodeHalfConsonantMap.put("r","\u0930\u094D")
        unicodeHalfConsonantMap.put("l","\u0932\u094D")
        unicodeHalfConsonantMap.put("v","\u0935\u094D")
        unicodeHalfConsonantMap.put("h","\u0939\u094D")

        unicodeHalfConsonantMap.put("s","\u0938\u094D")
        unicodeHalfConsonantMap.put("sh","\u0936\u094D")
        unicodeHalfConsonantMap.put("Sh","\u0937\u094D")
    }
}

package net.oriserver.aether.aether.chat;

import java.util.HashMap;
import java.util.Map;

public class RomanjiToJapanese {
    private final Map<String, String> romanjiToHiraganaMap;

    RomanjiToJapanese(){
        romanjiToHiraganaMap = new HashMap<String,String>();

        romanjiToHiraganaMap.put("a", "あ");
        romanjiToHiraganaMap.put("i", "い");
        romanjiToHiraganaMap.put("u", "う");
        romanjiToHiraganaMap.put("e", "え");
        romanjiToHiraganaMap.put("o", "お");

        romanjiToHiraganaMap.put("ka", "か");
        romanjiToHiraganaMap.put("ki", "き");
        romanjiToHiraganaMap.put("ku", "く");
        romanjiToHiraganaMap.put("ke", "け");
        romanjiToHiraganaMap.put("ko", "こ");

        romanjiToHiraganaMap.put("sa", "さ");
        romanjiToHiraganaMap.put("si", "し");
        romanjiToHiraganaMap.put("su", "す");
        romanjiToHiraganaMap.put("se", "せ");
        romanjiToHiraganaMap.put("so", "そ");

        romanjiToHiraganaMap.put("ta", "た");
        romanjiToHiraganaMap.put("ti", "ち");
        romanjiToHiraganaMap.put("tu", "つ");
        romanjiToHiraganaMap.put("te", "て");
        romanjiToHiraganaMap.put("to", "と");

        romanjiToHiraganaMap.put("na", "な");
        romanjiToHiraganaMap.put("ni", "に");
        romanjiToHiraganaMap.put("nu", "ぬ");
        romanjiToHiraganaMap.put("ne", "ね");
        romanjiToHiraganaMap.put("no", "の");

        romanjiToHiraganaMap.put("ha", "は");
        romanjiToHiraganaMap.put("hi", "ひ");
        romanjiToHiraganaMap.put("hu", "ふ");
        romanjiToHiraganaMap.put("he", "へ");
        romanjiToHiraganaMap.put("ho", "ほ");

        romanjiToHiraganaMap.put("ma", "ま");
        romanjiToHiraganaMap.put("mi", "み");
        romanjiToHiraganaMap.put("mu", "む");
        romanjiToHiraganaMap.put("me", "め");
        romanjiToHiraganaMap.put("mo", "も");

        romanjiToHiraganaMap.put("ya", "や");
        romanjiToHiraganaMap.put("yi", "い");
        romanjiToHiraganaMap.put("yu", "ゆ");
        romanjiToHiraganaMap.put("ye", "いぇ");
        romanjiToHiraganaMap.put("yo", "よ");

        romanjiToHiraganaMap.put("ra", "ら");
        romanjiToHiraganaMap.put("ri", "り");
        romanjiToHiraganaMap.put("ru", "る");
        romanjiToHiraganaMap.put("re", "れ");
        romanjiToHiraganaMap.put("ro", "ろ");

        romanjiToHiraganaMap.put("wa", "わ");
        romanjiToHiraganaMap.put("wi", "うぃ");
        romanjiToHiraganaMap.put("wo", "を");
        romanjiToHiraganaMap.put("we", "うぇ");
        romanjiToHiraganaMap.put("nn", "ん");

        romanjiToHiraganaMap.put("ga", "が");
        romanjiToHiraganaMap.put("gi", "ぎ");
        romanjiToHiraganaMap.put("gu", "ぐ");
        romanjiToHiraganaMap.put("ge", "げ");
        romanjiToHiraganaMap.put("go", "ご");

        romanjiToHiraganaMap.put("za", "ざ");
        romanjiToHiraganaMap.put("zi", "じ");
        romanjiToHiraganaMap.put("zu", "ず");
        romanjiToHiraganaMap.put("ze", "ぜ");
        romanjiToHiraganaMap.put("zo", "ぞ");

        romanjiToHiraganaMap.put("da", "だ");
        romanjiToHiraganaMap.put("di", "ぢ");
        romanjiToHiraganaMap.put("du", "づ");
        romanjiToHiraganaMap.put("de", "で");
        romanjiToHiraganaMap.put("do", "ど");

        romanjiToHiraganaMap.put("ba", "ば");
        romanjiToHiraganaMap.put("bi", "び");
        romanjiToHiraganaMap.put("bu", "ぶ");
        romanjiToHiraganaMap.put("be", "べ");
        romanjiToHiraganaMap.put("bo", "ぼ");

        romanjiToHiraganaMap.put("pa", "ぱ");
        romanjiToHiraganaMap.put("pi", "ぴ");
        romanjiToHiraganaMap.put("pu", "ぷ");
        romanjiToHiraganaMap.put("pe", "ぺ");
        romanjiToHiraganaMap.put("po", "ぽ");

        romanjiToHiraganaMap.put("kya", "きゃ");
        romanjiToHiraganaMap.put("kyi", "きぃ");
        romanjiToHiraganaMap.put("kyu", "きゅ");
        romanjiToHiraganaMap.put("kye", "きぇ");
        romanjiToHiraganaMap.put("kyo", "きょ");

        romanjiToHiraganaMap.put("sha", "しゃ");
        romanjiToHiraganaMap.put("shi", "し");
        romanjiToHiraganaMap.put("shu", "しゅ");
        romanjiToHiraganaMap.put("she", "しぇ");
        romanjiToHiraganaMap.put("sho", "しょ");

        romanjiToHiraganaMap.put("cha", "ちゃ");
        romanjiToHiraganaMap.put("chi", "ち");
        romanjiToHiraganaMap.put("chu", "ちゅ");
        romanjiToHiraganaMap.put("che", "ちぇ");
        romanjiToHiraganaMap.put("cho", "ちょ");

        romanjiToHiraganaMap.put("nya", "にゃ");
        romanjiToHiraganaMap.put("nyi", "にぃ");
        romanjiToHiraganaMap.put("nyu", "にゅ");
        romanjiToHiraganaMap.put("nye", "にぇ");
        romanjiToHiraganaMap.put("nyo", "にょ");

        romanjiToHiraganaMap.put("hya", "ひゃ");
        romanjiToHiraganaMap.put("hyi", "ひぃ");
        romanjiToHiraganaMap.put("hyu", "ひゅ");
        romanjiToHiraganaMap.put("hye", "ひぇ");
        romanjiToHiraganaMap.put("hyo", "ひょ");

        romanjiToHiraganaMap.put("mya", "みゃ");
        romanjiToHiraganaMap.put("myi", "みぃ");
        romanjiToHiraganaMap.put("myu", "みゅ");
        romanjiToHiraganaMap.put("mye", "みぇ");
        romanjiToHiraganaMap.put("myo", "みょ");

        romanjiToHiraganaMap.put("rya", "りゃ");
        romanjiToHiraganaMap.put("ryi", "りぃ");
        romanjiToHiraganaMap.put("ryu", "りゅ");
        romanjiToHiraganaMap.put("rye", "りぇ");
        romanjiToHiraganaMap.put("ryo", "りょ");

        romanjiToHiraganaMap.put("gya", "ぎゃ");
        romanjiToHiraganaMap.put("gyi", "ぎぃ");
        romanjiToHiraganaMap.put("gyu", "ぎゅ");
        romanjiToHiraganaMap.put("gye", "ぎぇ");
        romanjiToHiraganaMap.put("gyo", "ぎょ");

        romanjiToHiraganaMap.put("ja", "じゃ");
        romanjiToHiraganaMap.put("ji", "じ");
        romanjiToHiraganaMap.put("ju", "じゅ");
        romanjiToHiraganaMap.put("je", "じぇ");
        romanjiToHiraganaMap.put("jo", "じょ");

        romanjiToHiraganaMap.put("bya", "びゃ");
        romanjiToHiraganaMap.put("byi", "びぃ");
        romanjiToHiraganaMap.put("byu", "びゅ");
        romanjiToHiraganaMap.put("bye", "びぇ");
        romanjiToHiraganaMap.put("byo", "びょ");

        romanjiToHiraganaMap.put("pya", "ぴゃ");
        romanjiToHiraganaMap.put("pyi", "ぴぃ");
        romanjiToHiraganaMap.put("pyu", "ぴゅ");
        romanjiToHiraganaMap.put("pye", "ぴぇ");
        romanjiToHiraganaMap.put("pyo", "ぴょ");

        romanjiToHiraganaMap.put("zya", "じゃ");
        romanjiToHiraganaMap.put("zyi", "じぃ");
        romanjiToHiraganaMap.put("zyu", "じゅ");
        romanjiToHiraganaMap.put("zye", "じぇ");
        romanjiToHiraganaMap.put("zyo", "じょ");

        romanjiToHiraganaMap.put("xa", "ぁ");
        romanjiToHiraganaMap.put("xi", "ぃ");
        romanjiToHiraganaMap.put("xu", "ぅ");
        romanjiToHiraganaMap.put("xe", "ぇ");
        romanjiToHiraganaMap.put("xo", "ぉ");

        romanjiToHiraganaMap.put("xtu", "っ");

        romanjiToHiraganaMap.put("xya", "ゃ");
        romanjiToHiraganaMap.put("xyi", "ぃ");
        romanjiToHiraganaMap.put("xyu", "ゅ");
        romanjiToHiraganaMap.put("xye", "ぇ");
        romanjiToHiraganaMap.put("xyo", "ょ");

        romanjiToHiraganaMap.put("fa", "ふぁ");
        romanjiToHiraganaMap.put("fi", "ふぃ");
        romanjiToHiraganaMap.put("fu", "ふ");
        romanjiToHiraganaMap.put("fe", "ふぇ");
        romanjiToHiraganaMap.put("fo", "ふぉ");

        romanjiToHiraganaMap.put("va", "ヴぁ");
        romanjiToHiraganaMap.put("vi", "ヴぃ");
        romanjiToHiraganaMap.put("vu", "ヴ");
        romanjiToHiraganaMap.put("ve", "ヴぇ");
        romanjiToHiraganaMap.put("vo", "ヴぉ");

        romanjiToHiraganaMap.put("la", "ぁ");
        romanjiToHiraganaMap.put("li", "ぃ");
        romanjiToHiraganaMap.put("lu", "ぅ");
        romanjiToHiraganaMap.put("le", "ぇ");
        romanjiToHiraganaMap.put("lo", "ぉ");

        romanjiToHiraganaMap.put("qa", "くぁ");
        romanjiToHiraganaMap.put("qi", "くぃ");
        romanjiToHiraganaMap.put("qu", "く");
        romanjiToHiraganaMap.put("qe", "くぇ");
        romanjiToHiraganaMap.put("qo", "くぉ");

        romanjiToHiraganaMap.put("ca", "か");
        romanjiToHiraganaMap.put("ci", "し");
        romanjiToHiraganaMap.put("cu", "く");
        romanjiToHiraganaMap.put("ce", "せ");
        romanjiToHiraganaMap.put("co", "こ");

        romanjiToHiraganaMap.put("kwa", "くぁ");

        romanjiToHiraganaMap.put("twa", "とぁ");
        romanjiToHiraganaMap.put("twi", "とぃ");
        romanjiToHiraganaMap.put("twu", "とぅ");
        romanjiToHiraganaMap.put("twe", "とぇ");
        romanjiToHiraganaMap.put("two", "とぉ");

        romanjiToHiraganaMap.put("tha", "てゃ");
        romanjiToHiraganaMap.put("thi", "てぃ");
        romanjiToHiraganaMap.put("thu", "てゅ");
        romanjiToHiraganaMap.put("the", "てぇ");
        romanjiToHiraganaMap.put("tho", "てょ");

        romanjiToHiraganaMap.put("tsa", "つぁ");
        romanjiToHiraganaMap.put("tsi", "つぃ");
        romanjiToHiraganaMap.put("tsu", "つ");
        romanjiToHiraganaMap.put("tse", "つぇ");
        romanjiToHiraganaMap.put("tso", "つぉ");

        romanjiToHiraganaMap.put("gwa", "ぐぁ");
        romanjiToHiraganaMap.put("gwi", "ぐぃ");
        romanjiToHiraganaMap.put("gwu", "ぐぅ");
        romanjiToHiraganaMap.put("gwe", "ぐぇ");
        romanjiToHiraganaMap.put("gwo", "ぐぉ");

        romanjiToHiraganaMap.put("dya", "ぢゃ");
        romanjiToHiraganaMap.put("dyi", "ぢぃ");
        romanjiToHiraganaMap.put("dyu", "ぢゅ");
        romanjiToHiraganaMap.put("dye", "ぢぇ");
        romanjiToHiraganaMap.put("dyo", "ぢょ");

        romanjiToHiraganaMap.put("dwa", "どぁ");
        romanjiToHiraganaMap.put("dwi", "どぃ");
        romanjiToHiraganaMap.put("dwu", "どぅ");
        romanjiToHiraganaMap.put("dwe", "どぇ");
        romanjiToHiraganaMap.put("dwo", "どぉ");

        romanjiToHiraganaMap.put("dha", "でゃ");
        romanjiToHiraganaMap.put("dhi", "でぃ");
        romanjiToHiraganaMap.put("dhu", "でゅ");
        romanjiToHiraganaMap.put("dhe", "でぇ");
        romanjiToHiraganaMap.put("dho", "でょ");

        romanjiToHiraganaMap.put("wha", "うぁ");
        romanjiToHiraganaMap.put("whi", "うぃ");
        romanjiToHiraganaMap.put("whu", "う");
        romanjiToHiraganaMap.put("whe", "うぇ");
        romanjiToHiraganaMap.put("who", "うぉ");

        romanjiToHiraganaMap.put("qwa", "くぁ");
        romanjiToHiraganaMap.put("qwi", "くぃ");
        romanjiToHiraganaMap.put("qwu", "くぅ");
        romanjiToHiraganaMap.put("qwe", "くぇ");
        romanjiToHiraganaMap.put("qwo", "くぉ");

        romanjiToHiraganaMap.put("lya", "ゃ");
        romanjiToHiraganaMap.put("lyi", "ぃ");
        romanjiToHiraganaMap.put("lyu", "ゅ");
        romanjiToHiraganaMap.put("lye", "ぇ");
        romanjiToHiraganaMap.put("lyo", "ょ");
    }

    public String setJapanese(String romanji){
        StringBuilder result = new StringBuilder();
        String temp = "";
        for(int i =0; i< romanji.length(); i++){
            char c = romanji.charAt(i);
            if(!(97<=c&&c<=122)){
                result.append(temp);
                result.append(c);
                temp = "";
                continue;
            }
            temp += String.valueOf(romanji.charAt(i));
            if(romanjiToHiraganaMap.containsKey(temp)){
                result.append(romanjiToHiraganaMap.get(temp));
                temp = "";
            }
            if(temp.length()==2){
                if(temp.charAt(0)==temp.charAt(1)){
                    if(temp.charAt(0)=='n'){
                        result.append("ん");
                        temp = "";
                    }
                    else{
                        result.append("っ");
                        temp = ""+c;
                    }
                }
            }else if(temp.length()==3){
                result.append(temp.charAt(0));
                i-=2;
                temp = "";
            }
        }
        result.append(temp);
        String rs = result.toString().replace("n","ん");
        rs = rs.replace("-","ー");
        return rs;
    }
}

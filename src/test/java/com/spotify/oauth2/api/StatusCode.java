package com.spotify.oauth2.api;

public enum StatusCode {
    CODE_200(200, ""),
    CODE_201(201, ""),
    CODE_400(400, "Missing required field: name"),
    CODE_401(401, "Invalid access token");

    //Her enum sabiti iki bilgi taşıyor:
    public final int code;
    public final String msg;

    //Constructor bunları atar: (private, no public keyword)
    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

//public final int code;public final String msg; bu degerler:
//public final degerler oldugu icin direkt erisilebilir, getter setter ihtiyaci yok.
//private degerler olsaydi getter setter a bu degerlere ulasmak icin ihtiyac olurdu
/*    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
}*/

/*
Bu enum şunları sağlar:
✔ Magic number'ları kaldırır
✔ Kod okunabilirliğini artırır
✔ Tip güvenliği sağlar
✔ Status code + mesajı birlikte tutar
✔ Test framework’ünde tekrar kullanılabilir
*/

}



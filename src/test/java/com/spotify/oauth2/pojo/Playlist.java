
package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/*
How we understand this is a final class?
-because of @Value
@Value, Lombok’un immutable (değiştirilemez) sınıflar için kullandığı bir anotasyondur ve arka planda şunları otomatik olarak yapar:
-Class’ı final yapar
-Tüm field’ları private final yapar
-Setter üretmez
-Getter’ları üretir
-equals, hashCode, toString üretir
-Tüm field’ları alan bir constructor üretir
*/

//1
//before the usage of lombok, i set this page with getter and setters with the help of jsonschema
//if i use the pojos without lombok, i can do setDescription with this way
//to avoid the repeatable requestPlayList object over and over the pojo class, remove void as return type
//and instead use the Same class , like PlayList
//public void setDescription(String description) {
//    this.description = description;
//}
//public PlayList setDescription(String description) {
//    this.description = description;
//    return this;//=> with this, this method returns the OBJECT of the same class,
//in this framework there is lombok
//when you create your pojo class you can consider this way,
//ddo thge same thing for setName and setPublic


//lombok
//Lombok = kodu kısaltır
//Delombok = kısaltılmış kodu açar

//try to use it with Builder and without Builder
//@Getter
//@Setter
/*
without lombok
public class User {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

with lombok
import lombok.Data;
@Data
public class User {
    private String name;
    private int age;
}
*/

//@com.fasterxml.jackson.databind.annotation.JsonDeserialize(builder = Playlist.PlaylistBuilder.class)
@Data
/*
Tek başına yazıldığında birden fazla Lombok anotasyonunu otomatik olarak ekler.
@Data şunları üretir:
@Getter  ==>after usage @Data  @Getter and @Setter it is unnecessary
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor (final alanlar için)
ama, @Data builder pattern üretmez.
 */

@Builder
//makes readable and controllable object
//@Builder
//public class User {
//    private String name;
//    private int age;
//}
//        User user = User.builder()
//        .name("Ali")
//        .age(25)
//        .build();
//Immutable (değişmez) nesne oluşturmayı kolaylaştırır
//Constructor karmaşasını azaltır
//Çok parametreli nesnelerde okunabilirliği artırır
//Zincirleme (fluent) kullanım sağlar

/*
| Özellik                  | @Getter / @Setter             | @Builder           |
| ------------------------ | ----------------------------- | ------------------ |
| Amaç                     | Alan erişimi                  | Nesne oluşturma    |
| Pattern                  | Encapsulation                 | Builder Pattern    |
| Method Türü              | getX(), setX()                | builder(), build() |
| Nesne Üretir mi?         | ❌                             | ✅                |
| Immutable için uygun mu? | ❌ (setter varsa mutable olur) | ✅                |
 */
@Jacksonized //use it with @Builder, it helps to identify jackson annotations

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Playlist {
    @JsonProperty("collaborative")
    private final
    Boolean collaborative;
    @JsonProperty("description")
    private final
    String description;
    @JsonProperty("external_urls")
    private final
    ExternalUrls externalUrls;
    @JsonProperty("followers")
    private final
    Followers followers;
    @JsonProperty("href")
    private final
    String href;
    @JsonProperty("id")
    private final
    String id;
    @JsonProperty("images")
    private final
    List<Object> images;
    @JsonProperty("name")
    private final
    String name;
    @JsonProperty("owner")
    private final
    Owner owner;
    @JsonProperty("primary_color")
    private final
    Object primaryColor;
    @JsonProperty("public")
    private final
    Boolean _public;
    @JsonProperty("snapshot_id")
    private final
    String snapshotId;
    @JsonProperty("tracks")
    private final
    Tracks tracks;
    @JsonProperty("type")
    private final
    String type;
    @JsonProperty("uri")
    private final
    String uri;

}

/*
ALL BRIEF ABOUT LOMBOK ANNOTATIONS
1️⃣ @Getter
Alanlar için sadece okuma metodu üretir.
✔ getX() üretir
❌ setX() üretmez
✔ Immutable tasarıma uygundur

2️⃣ @Setter
Alanlar için değiştirme metodu üretir.
✔ setX() üretir
❌ Immutable değildir
✔ Mutable nesne oluşturur

3️⃣ @Builder
Nesneyi Builder Pattern ile oluşturmak.
✔ Okunabilir nesne oluşturma
✔ Çok parametreli constructor sorununu çözer
✔ Immutable ile uyumlu
❌ Getter/Setter üretmez

4️⃣ @Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
✔ Hızlı DTO üretimi
✔ Tüm temel methodları otomatik üretir
❌ Immutable değildir
❌ Builder içermez

5️⃣ @Value
@Value, @Data’nın immutable versiyonu gibidir.
Şunları üretir:
Getter
toString
equals & hashCode
Tüm alanlar private final
All-args constructor

Setter ÜRETMEZ ❌
@Value
public class User {
    String name;
    int age;
}

Bu sınıf:

final class olur
Alanlar final olur
Değiştirilemez
✔ Gerçek immutable yapı sağlar
✔ Thread-safe tasarıma uygundur

🔥 Büyük Karşılaştırma Tablosu
| Özellik            | @Getter | @Setter | @Builder     | @Data               | @Value   |
| ------------------ | ------- | ------- | ------------ | -------------       | -------- |
| Getter üretir      | ✅       | ❌       | ❌            | ✅              | ✅        |
| Setter üretir      | ❌       | ✅       | ❌            | ✅              | ❌        |
| toString üretir    | ❌       | ❌       | ❌            | ✅              | ✅        |
| equals/hashCode    | ❌       | ❌       | ❌            | ✅              | ✅        |
| Constructor üretir | ❌       | ❌       | ❌            | Final alanlar   | All-args |
| Immutable          | ✅       | ❌       | ✅            | ❌              | ✅        |
| Mutable            | ❌       | ✅       | Duruma bağlı | ✅               | ❌        |

🧠 Mimari Kullanım Önerisi
✔ Basit DTO (request/response)
→ @Data

✔ Immutable Domain Model
→ @Value
veya
→ @Getter @Builder

✔ Çok parametreli nesne
→ @Builder

✔ Mutable Entity
→ @Getter @Setter

⚡ Kritik Fark: @Data vs @Value
|               | @Data | @Value |
| ------------- | ----- | ------ |
| Setter        | Var   | Yok    |
| Alanlar final | Hayır | Evet   |
| Class final   | Hayır | Evet   |
| Mutable mi?   | Evet  | Hayır  |

💡 Profesyonel Tavsiye
Clean architecture / DDD → @Value veya @Getter @Builder
REST DTO → @Data
JPA Entity → Dikkatli kullanım (özellikle @Data riskli olabilir)
 */
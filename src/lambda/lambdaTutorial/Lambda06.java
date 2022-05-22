package lambda.lambdaTutorial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda06 {

    public static void main(String[] args) throws IOException {
        //TASK 01 --> haluk.txt dosyasini okuyunuz.(Console'a yazdiriniz)
        System.out.println("\nTASK 1 --> haluk.txt dosyasini okuyunuz --> TASK-1 ");

       // Path'in kolay yolu Atayarak yapma
        //1.yol'un path variable atamasi
        Path haluk = Path.of("src/lambda/lambdaTutorial/haluk.txt");
        // 2.yol'un variable'ı obje ile çağırma
        Stream <String> akis=Files.lines(haluk); // haluk.txt datalari akis stream'ine atandi

        // 1.yol....
        Files.lines(haluk).forEach(System.out::println);
        // 2.yol
        akis.forEach(System.out::println);

            // ------------UZUN YOLU -------------//
        //Files.lines(Paths.get("src/lambda/lambdaTutorial/haluk.txt")).//Files class'dan lines() method call edilerek data
                // çekilecek dosya yolu(path)
                // paremetre girilerek path'deki dosyanın dataları akısa alındı
                      //  forEach(System.out::println);//akısadaki datalar(her satırdaki string)yazıldı)
           //---------------------------------------//

        //TASK 02 --> haluk.txt dosyasini buyuk harflerle okuyunuz.(Console'a buyuk harflerle yazdiriniz)
        System.out.println("\nTASK 2 --> haluk.txt dosyasini buyuk harflerle okuyunuz -->  TASK-2 ");

        Files.lines(haluk).
                map(String::toUpperCase).//akısdaki datalar buyuk harfe update edldi
                forEach(System.out::println);//akısadaki datalar(her satırdaki string)yazıldı)

        //TASK 03 --> haluk.txt dosyasindaki ilk satiri kucuk harflerle yazdiriniz.
        System.out.println("\nTASK 3 --> haluk.txt dosyasindaki ilk satiri kucuk harflerle okuyunuz 01 --> TASK-3  ");

        // akis.limit(1).map(t-> t.toLowerCase()).forEach(System.out::println);;

       //1.yol limit()
        Files.lines(haluk).map(String::toLowerCase).limit(1).forEach(System.out::println);
        //2. yol findFirst();
        System.out.println(Files.lines(haluk).
                map(String::toLowerCase).
                findFirst());

        //TASK 04 --> haluk.txt dosyasinda "basari" kelimesinin kac satirda gectiginiz yazdiriniz
        System.out.println("\nTASK 4 --> haluk.txt dosyasinda basari kelimesinin kac satirda gectiginiz yazdiriniz --> TASK-4 ");

        System.out.println(Files.lines(haluk).
                map(String::toLowerCase).
                filter(t -> t.contains("basari")).
                count());


        //TASK 05 --> haluk.txt dosyasindaki farkli kelimeleri  yazdiriniz.
        System.out.println("\nTASK 5 --> haluk.txt dosyasindaki farkli kelimeleri  yazdiriniz. --> TASK-5 ");
        /*
Stream.flatMap, adıyla tahmin edilebileceği gibi, bir map ve flat işleminin birleşimidir. Bu, ilk önce elemanlarınıza bir
 fonksiyon uyguladığınız ve daha sonra düzleştirdiğiniz anlamına gelir.
  Stream.map yalnızca akışı düzleştirmeden bir işlevi uygular.

   Bir akışın düzleştirme'in neyi içerdiğini anlamak için, "iki seviye" olan [ [1,2,3],[4,5,6],[7,8,9] ] gibi bir yapı düşünün.
   Bunun düzleştirilmesi, "bir seviye" yapısında dönüştürülmesi anlamına gelir: [ 1,2,3,4,5,6,7,8,9 ].
   flatMap yöntemi, bir akışın her bir değerini başka bir akışla değiştirmenizi sağlar
   ve ardından oluşturulan tüm akışları tek bir akışa birleştirir.

 */
        // 1.yol... toSet ile
        Files.lines(haluk).
                map(t -> t.split(" ")).
                flatMap(Arrays::stream). // MD arraydaki elmanlar tek eleman akisa alındı
                collect(Collectors.toSet()). // set attigimizda zaten set benzersiz elemanlari attiği için daha clean oluyor.
                forEach(t -> System.out.println(t)); // dönüşler karışık olarak yazdirilir toSet ile olduğu için
        System.out.println("----------------------------------------");
        // 2.yol... distinct ile
        Files.lines(haluk).
                map(t -> t.split(" ")).
                flatMap(Arrays::stream). // MD arraydaki elmanlar tek eleman akisa alındı
                distinct().collect(Collectors.toList()).
                forEach(t -> System.out.println(t)); // dönüşler karışık olarak yazdirilir List ile olduğu için


        //TASK 06 --> haluk.txt dosyasindaki tum kelimeleri natural order  yazdiriniz.
        System.out.println("\nTASK 6 --> haluk.txt dosyasindaki tum kelimeleri natural order  yazdiriniz. --> TASK - 6 ");
        Files.lines(haluk).
                map(t -> t.split(" ")). // satirlardaki kelimeler array'e alindi
                flatMap(Arrays::stream). //  arryadaki elemanlar akisa alindi
                sorted(). //  harf sirasi yapildi
                forEach(System.out::println); // print yapildi


        //TASK 07 --> haluk.txt dosyasinda "basari" kelimesinin kac kere gectigini buyuk harf kucuk harf bagımsız yaziniz.
        System.out.println("\nTASK 7 --> haluk.txt dosyasinda basari kelimesinin kac kere gectigini  yazdiriniz. --> TASK - 7 ");
        System.out.println(Files.lines(haluk).
                map(t-> t.toLowerCase().split(" ")).
                flatMap(Arrays::stream).
                filter(t -> t.equals("basari")). //filtreleyip equals ile kontrolünü yaptik.
                count());

        //TASK 08 --> haluk.txt dosyasinda "a" harfi gecen kelimelerin sayisini ekrana yazdiran programi yaziniz
        System.out.println("\nTASK 8 --> haluk.txt dosyasinda a harfi gecen kelimelerin sayisini ekrana yazdiran programi yazdiriniz. --> TASK - 8 ");
        System.out.println(Files.lines(haluk).//txt dosyasıne erişldi
                map(t -> t.split(" ")).//satırlar akısa alındı
                flatMap(Arrays::stream).//her satırdaki her kelime akısa alındı
                filter(t -> t.contains("a")).//a bulunduran kelimeler fitrelenedi
                count());//a bulunduran kelimeler sayıldı


        //TASK 09 --> haluk.txt dosyasinda icinde "a" harfi gecen kelimeleri yazdiriniz
        System.out.println("\nTASK 9 --> haluk.txt dosyasinda a harfi gecen kelimeler yazdiriniz. --> TASK - 9 ");

        Files.lines(haluk).//txt dosyasıne erişldi
                map(t -> t.split(" ")).//satırlar akısa alındı
                flatMap(Arrays::stream).//her satırdaki her kelime akısa alındı
                filter(t -> t.contains("a")).//a bulunduran kelimeler fitrelenedi
                // collect(Collectors.toList())); burada bu sefer list 'e atmadan direk akiştakileri yazdirdik.
                forEach(System.out::println);

        System.out.println("--------------------------------");

        Files.lines(haluk).//txt dosyasıne erişldi
                map(t -> t.split(" ")).//satırlar akısa alındı
                flatMap(Arrays::stream).//her satırdaki her kelime akısa alındı
                filter(t -> t.contains("a")).//a bulunduran kelimeler fitrelenedi
                collect(Collectors.toSet()).
                forEach(System.out::println);


        //TASK 10 --> haluk.txt dosyasinda kac /farklı harf kullanildigini yazdiriniz
        System.out.println("\nTASK 10 --> haluk.txt dosyasinda kac /farklı harf kullanildigini  yazdiriniz. --> TASK - 10 ");

        System.out.println(Files.lines(haluk).//txt dosyasıne erişldi
                map(t -> t.replaceAll("\\W", "").
                           replaceAll("\\d", "").
                           split("")).//satırlar akısa alındı
                flatMap(Arrays::stream).//her satırdaki her kelime akısa alındı
                distinct().
                count());


        //TASK 11 --> haluk.txt dosyasinda kac farkli kelime kullanildigini yazdiriniz
        System.out.println("\nTASK 11 --> haluk.txt dosyasinda kac farkli kelime kullanildigini  yazdiriniz. --> TASK - 11 ");
        System.out.println(Files.lines(haluk).
                map(t -> t.replaceAll("[.!,:)\\-]", "").

                        split(" ")).//kelime akısı saglanır
                        flatMap(Arrays::stream).
                distinct().
                count());
        //TASK 12 --> haluk.txt dosyasinda  farkli kelimeleri print ediniz
        System.out.println("\nTASK 12 --> haluk.txt dosyasinda kac farkli kelime kullanildigini  yazdiriniz. --> TASK - 12 ");
        Files.lines(haluk).
                map(t -> t.replaceAll("[.!,:)\\-]", "").
                        split(" ")).//kelime akısı saglanır
                flatMap(Arrays::stream).
                distinct().forEach(System.out::println);

    }
}

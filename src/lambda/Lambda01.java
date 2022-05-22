package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lambda01 {
    public static void main(String[] args) {
        /*
   1) Lambda "Functional Programming"-->mathod(action) kullanma pr dili.
      Lambda --> mathod create  etme değil mevcut method'ları(library)secip kullanmaktır...
      Lambda  kendi başına tanımlanabilen parametre alıp gönderebilen fonksiyonlardır.
      Lambda'lar sayesinde daha az kod ve hızlı geliştirme sağlanabilmektedir.
      Java 8 ile gelen bu özellik, Java 8’in en önemli özelliğidir.

      "Functional Programming" de "Nasil yaparim?" degil "Ne yaparim?" dusunulur.
   2) "Structured Programming" de "Ne yaparim?" dan cok "Nasil Yaparim?" dusunulur
   3) "Functional Programming" hiz, code kisaligi, code okunabilirligi
       ve hatasiz code yazma acilarindan cok faydalidir.
   4) Lambda sadece collections'larda(List, Queue ve Set) ve array'lerde kullanilabilir ancak map'lerde kullanılmaz.
      Lambda kullanmak hatasız code kullanmaktır.

          Collections Nedir?
          Çoğu yazılım tek tek öğeler yerine öğelerden oluşan toplulukları depolar ve onlar üzerinde işlem
          yapar. Array'ler onlardan birisidir. Java Collections Framework, arraylerle yapılan işleri daha kolay
          yaptığı gibi, daha fazlasını da yapar.
          Java'da bir koleksiyon (collection - bazen container, ambar diye adlandırılır) nesnelerden oluşan bir
          topluluğu bir arada tutan bir yapıdır. 'Collections Framework' ise arayüzler ve onların kurgularından
          (implementations) oluşur.
*/
        List<Integer> sayi = new ArrayList<>(Arrays.asList(34, 22, 16, 11, 35, 20, 63, 21, 65, 44, 66, 64, 81, 38, 15));
        printELStuructured(sayi);
        System.out.println();
        printELFunctional(sayi);
        System.out.println();
        printELFunctional1(sayi);
        System.out.println();
        printELFunctional2(sayi);
        System.out.println();
        printCiftELSturctured(sayi);
        System.out.println();
        printCiftELFunctional(sayi);
        System.out.println();
        printCiftELFunctional1(sayi);

        System.out.println("----------");
        printOtuzKucukCiftFunctional(sayi);
        System.out.println("----------");
        printOtuzKucukCiftELFunctional1(sayi);
    }

    //Task : "Structured Programming" kullanarak list elemanlarini ayni satirda aralainda bosluk olacak sekilde prnt ediniz.
    public static void printELStuructured(List<Integer> sayi) {
        for (Integer w : sayi) {
            System.out.print(w + " ");
        }
    }

    //Task; "Functional Programming" kullanarak list elemanlarini ayni satirda aralainda bosluk olacak sekilde prnt ediniz
    public static void printELFunctional(List<Integer> sayi) {
        sayi.stream().forEach(t -> System.out.print(t + " "));
    }

    /*
 stream() : datalari yukaridan asagiya akis sekline getirir. Stream bir interface olduğundan dolayı doğrudan nesne almaz.
 forEach() :datanin parametresine gore her bir elemanı isler
 t-> : Lambda operatoru
  Lambda Expression-->(parameter list) -> {action body}
      Parameter list: Fonksiyonun parametreleri tanımlanır. Hiç parametre geçirmeden boşta olabilir.
      -> Arrow-token: Argüman listesi ile expression gövdesini birbirine bağlamak için kullanılır.
      Body: Expressionları ve statementları içerir.

     Bir kod bloğundan oluşan bir body...
     Bu tip lambda body, block body olarak bilinir. Blok gövdesi, lambda gövdesinin birden çok ifade içermesine izin verir.
     Bu ifadeler parantez içine alınır ve parantezlerden sonra noktalı virgül eklemeniz gerekir.

         () -> {
          double pi = 3.1415;
             return pi;
         };
  Lambda Expression  yapisi cok tavsiye edilmez daha cok METHOD REFERENCE kullanilir

 */

    public static void printELFunctional1(List<Integer> sayi) {
        sayi.stream().forEach(System.out::print); // method reference--> System.out yapisinda print methodu refere et.
    }

    public static void yazdir(int a) { // verilen integer degeri ayni satirda bosluk birakarak yazdirma aksiyonu yapan seed(tohum) method create edildi
        System.out.print(a + " ");
    }

    public static void printELFunctional2(List<Integer> sayi) {
        sayi.stream().forEach(Lambda01::yazdir); // method reference--> System.out yapisinda print methodu refere et.
    }

    //Task : Structured Programming ile list elemanlarinin  cift olanalrini ayni satirda aralarina bosluk birakarak print ediniz
    public static void printCiftELSturctured(List<Integer> sayi) {
        for (Integer w : sayi) {
            if (w % 2 == 0) {
                System.out.print(w + " ");
            }
        }
    }

    //Task :Functional Programming ile list elemanlarinin  cift olanalrini ayni satirda aralarina bosluk birakarak print ediniz
    public static void printCiftELFunctional(List<Integer> sayi) {
        sayi.stream().filter(t -> t % 2 == 0).forEach(Lambda01::yazdir);

    }

    public static boolean ciftBul(int a) { // seed (tohum method kendisine verilen int degerin cift olmasini kotrol eder.
        return a % 2 == 0;
    }


    public static void printCiftELFunctional1(List<Integer> sayi) {
        sayi.
                stream().// list elemanlari akisa alindi
                filter(Lambda01::ciftBul).//ciftbul method refere edilerek akisdaki elemanlar filtrelendi(cif sayi)
                forEach(Lambda01::yazdir); // filtre den gelen elemanlar yazdir() method refere edilerek print edildi
    }

    public static void printOtuzKucukCiftFunctional(List<Integer> sayi) {
        sayi.stream().filter(t -> t % 2 == 0 && t<34).forEach(Lambda01::yazdir);

    }
   public static boolean otuzDortKucuk(int a) { // seed (tohum method kendisine verilen int degerin 34'ten Kucuk olmasini kotrol eder.
       return a < 34 ;
    }
    public static void printOtuzKucukCiftELFunctional1(List<Integer> sayi) {
        sayi.
                stream().// list elemanlari akisa alindi
                filter(Lambda01::ciftBul).//ciftbul method refere edilerek akisdaki elemanlar filtrelendi(cif sayi)
                filter(Lambda01::otuzDortKucuk). //otuzDortKucuk method refere edilerek akisdaki elemanlar filtrelendi(34'ten kucuk)
                // filter(t-> t<34).
                forEach(Lambda01::yazdir); // filtre den gelen elemanlar yazdir() method refere edilerek print edildi
    }
}

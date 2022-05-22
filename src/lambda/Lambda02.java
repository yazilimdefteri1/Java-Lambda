package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Lambda02 {
    public static void main(String[] args) {

        List<Integer> sayi = new ArrayList<>(Arrays.asList(4, 2, 6, 11, -5, 7, 3, 15));
        ciftKarePrint(sayi);//16 4 36
        System.out.println("\n   ***   ");
        tekKupBirFazlaPrint(sayi);//1332 -124 344 28 3376
        System.out.println("\n   ***   ");
        ciftKarekokPrint(sayi);
        System.out.println("\n   ***   ");
        maxElBul(sayi);
        System.out.println("\n   ***   ");
        ciftKareMaxBul(sayi);
        System.out.println("\n   ***   ");
        elTopla(sayi);
        System.out.println("\n   ***   ");
        ciftCarp(sayi);


    }

    // Task : Functional Programming ile listin cift elemanlarinin  karelerini ayni satirda aralarina bosluk bırakarak print ediniz

    public static void ciftKarePrint(List<Integer> sayi) {
        sayi.
                stream().
                filter(Lambda01::ciftBul).
                map(t -> t * t).//map()--> Stream içerisindeki elemanları başka tiplere dönüştürmek veya üzerlerinde işlem yapmak (update) için Map kullanılmaktadır.
                forEach(Lambda01::yazdir);
    }
    // Task : Functional Programming ile listin tek elemanlarinin  kuplerinin bir fazlasini ayni satirda aralarina bosluk birakarak print edi

    public static void tekKupBirFazlaPrint(List<Integer> sayi) {
        sayi.
                stream().//sayilar akısa  alındı
                filter(t -> t % 2 != 0).//tek elemnalr fitrelendi
                map(t -> (t * t * t) + 1).//tek elemanları küplerinin 1 fazlasına udate edildi
                forEach(Lambda01::yazdir);//print edildi

    }

    // Task : Functional Programming ile listin cift elemanlarinin   karekoklerini ayni satirda aralarina bosluk birakarak yazdiriniz
    public static void ciftKarekokPrint(List<Integer> sayi) {
        sayi.
                stream().
                filter(Lambda01::ciftBul).
                map(Math::sqrt).//meth ref--> double deger return eder
                //forEach(Lambda01::yazdir)--> yazdir() method int.  çalıştığı için map den çıkan dataları çalıştırmaz
                        forEach(t -> System.out.print(t + " "));
    }
    // Task : list'in en buyuk elemanini yazdiriniz

    public static void maxElBul(List<Integer> sayi) {
        Optional<Integer> maxSayi = sayi.
                stream().
                reduce(Math::max); // akisa giren elemanlarin action sonrasi tek eleman haline getir.
        System.out.println(maxSayi);
        System.out.println("halukca : " + sayi.stream().reduce(Math::max));
        /*
 reduce()-->azaltmak ... bir cok datayi tek bir dataya(max min carp top vs islemlerde) cevirmek icin kullanilir.
 kullanımı yaygındır pratiktir.
 Bir Stream içerisindeki verilerin teker teker işlenmesidir. Teker teker işleme sürecinde, bir önceki adımda elde edilen sonuç
 bir sonraki adıma girdi olarak sunulmaktadır. Bu sayede yığılmlı bir hesaplama süreci elde edilmiş olmaktadır.
 reduce metodu ilk parametrede identity değeri, ikinci parametrede ise BinaryOperator türünden bir obj kullanılır.
 reduce işleminde bir önceki hesaplanmış değer ile sıradaki değer bir işleme tabi tutulmaktadır.
 İşleme başlarken bir önceki değer olmadığı için bu değer identity parametresinde tanımlanmaktadır.

 */
    }
        // Task : List'in cift elemanlarin karelerinin en buyugunu print ediniz

        public static void ciftKareMaxBul(List<Integer> sayi){
            System.out.println(sayi.
                    stream().
                    filter(Lambda01::ciftBul).
                    map(t -> t * t).
                    reduce(Math::max)); //36

            System.out.println("daha hizli specific integer class : "+sayi.
                    stream().
                    filter(Lambda01::ciftBul).
                    map(t -> t * t).
                    reduce(Integer::max));//36  specific class daha hizli run olur
        }
    // Task : List'teki tum elemanlarin toplamini yazdiriniz.
//Lambda Expression...
    public static void elTopla(List<Integer> sayi) {

        int toplam=sayi.stream().reduce(0,(a,b)->a+b); //Lambda Expression...
        /*
        a ilk degerini her zaman atanna değerden alir
        b degerini her zaman stream()' dan akisdan alir
        a ilk degerinden sonraki her degeri action(islem'dan alir.
         */
        System.out.println("Lambda Exp..:"+toplam);
        //Method Ref...
      Optional<Integer> topla= sayi.stream().reduce(Integer::sum);
        System.out.println("Method Ref.:"+topla);
        System.out.println("Method Ref : "+sayi.stream().reduce(Integer::sum)); // method ref

    }
// Task : List'teki cift elemanlarin carpimini  yazdiriniz.

    public static void ciftCarp(List<Integer> sayi){

        //Method Ref..
        System.out.println("Method Ref.: " +sayi.
                stream().
                filter(Lambda01::ciftBul).
                reduce(Math::multiplyExact));
        //Lambda Expression..
        System.out.println("Lambda exp. : " +sayi.
                stream().
                filter(Lambda01::ciftBul).
                reduce(1, (a, b) -> (a * b)));
    }


}

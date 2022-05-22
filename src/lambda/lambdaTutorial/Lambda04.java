package lambda.lambdaTutorial;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda04 {

    /*
    TASK :
    fields --> Universite (String)
               bolum (String)
               ogrcSayisi (int)
               notOrt (int)
               olan POJO clas craete edip main method içinde 5 farklı obj'den List create ediniz.
     */
    public static void main(String[] args) {

        Universite u01 = new Universite("bogazici", "matematik", 571, 93);
        Universite u02 = new Universite("istanbul teknik", "matematik", 622, 81);
        Universite u03 = new Universite("istanbul", "hukuk", 1453, 71);
        Universite u04 = new Universite("marmara", "bilgisayar muh", 1071, 77);
        Universite u05 = new Universite("yıldız teknik", "gemi", 333, 74);
        List<Universite> unv = new ArrayList<>(Arrays.asList(u01, u02, u03, u04, u05));

        System.out.println("Task1 : "+notOrt74BykUnv(unv));
        System.out.println("\n    ***   ");
        System.out.println("Task2 : "+ogrcSayisi110AzMi(unv));
        System.out.println("\n    ***   ");
        System.out.println("Task3 : "+matBolumVarmi(unv));
        System.out.println("\n    ***   ");
        System.out.println("Task4 : "+ogrSayiBkSirala(unv));
        System.out.println("\n    ***   ");
        ogrSayiBkSiralaVoid(unv);
        System.out.println("\n    ***   ");
        System.out.println(notOrtBkSiraliIlkUc(unv));
        System.out.println("\n    ***   ");
        System.out.println("Task6 : "+ogrSayisiEnazOlan2(unv));
        System.out.println("\n    ***   ");
        System.out.println("Task7 - mapToInt ile : "+notOrt63BykUnivOgrcSayisiToplaToInt(unv));
        System.out.println("\n    ***   ");
        System.out.println("Task7 - Reduce ile :  "+notOrt63BykUnivOgrcSayisiTopla(unv));
        System.out.println("\n    ***   ");
        System.out.println("Task8 - mapToDouble ve Average ile :  "+ogrcSayisi333BykNotOrtalamaAl(unv));
        System.out.println("\n    ***   ");
        System.out.println("Task10 - max ile :  "+ogrcSayisi571BykMaxNotOrtlamasi(unv));
        System.out.println("\n    ***   ");
        System.out.println("Task11 - min ile :  "+ogrcSayisi1071AzMinNotOrt(unv));


    }

    //task 01--> notOrt'larinin 74' den buyuk oldg kontrol eden pr create ediniz.
    public static boolean notOrt74BykUnv(List<Universite> unv) {

        return unv.
                stream().
                allMatch(t -> t.getNotOrt() > 74);
    }

    //task 02-->ogrc sayilarinin   110 den az olmadigini  kontrol eden pr create ediniz.
    public static boolean ogrcSayisi110AzMi(List<Universite> unv) {
        return unv.
                stream().
                allMatch(t -> t.getOgrSayisi() > 110);
    }

    //task 03-->universite'lerde herhangi birinde "matematik" olup olmadigini  kontrol eden pr create ediniz.

    public static boolean matBolumVarmi(List<Universite> unv) {
        return unv.
                stream().
                anyMatch(t -> t.getBolum().toLowerCase().contains("mat"));
    }

    //task 04-->universite'leri ogr sayilarina gore b->k siralayiniz.

    public static List<Universite> ogrSayiBkSirala(List<Universite> unv) {
        return unv.
                stream().
                sorted(Comparator.comparing(Universite::getOgrSayisi).reversed()).
                collect(Collectors.toList());
    }

    public static void ogrSayiBkSiralaVoid(List<Universite> unv) {
        System.out.println(unv.
                stream().
                sorted(Comparator.comparing(Universite::getOgrSayisi).reversed()).
                collect(Collectors.toList()));//collect()->akısdaki elamanları istenen sarta gore toplar
        //Collectors.toList()->collect'e toplanan elemanlarilist'e cevirir
    }

    //task 05-->universite'leri notOrt gore  b->k siralayip ilk 3 'unu print ediniz.

    public static List<Universite> notOrtBkSiraliIlkUc(List<Universite> unv) {
        return unv.
                stream(). // akisa alindi
                        sorted(Comparator.comparing(Universite::getNotOrt).reversed()).// notOrt a göre b->k siralandi
                        limit(3). // akisin ilk 3 elemani alindi
                        collect(Collectors.toList()); // akisin ilk 3 elemani list'e assign edildi
    }
    //task 06--> ogrc sayisi en az olan 2. universite'yi  print ediniz//

    public static List<Universite> ogrSayisiEnazOlan2(List<Universite> unv) {
        return unv.
                stream().
                sorted(Comparator.comparing(Universite::getOgrSayisi)).
                limit(2).
                skip(1).
                collect(Collectors.toList());
    }

    //task 07--> notOrt 63 'den buyuk olan universite'lerin ogrc sayilarini toplamini print ediniz.
    public static int notOrt63BykUnivOgrcSayisiToplaToInt(List<Universite> unv) {
        return unv.
                stream().
                filter(t -> t.getNotOrt() > 63).
                mapToInt(t -> t.getOgrSayisi()).
                sum(); // En kisa cincik hali
        // mapToInt() --> bu method akısdaki elemanların data type'nı
        // parameterisindeki() degere göre Int data type update eder
        // mapToInt() --> bu method akısdaki elemanların data type'nı
        // parameterisindeki() degere göre Int data type update eder


        //reduce(Integer::sum);
        //reduce(Math::addExact);
        //reduce(0, (t, u) -> t + u);
    }
        public static int notOrt63BykUnivOgrcSayisiTopla(List<Universite> unv){
            return unv.
                    stream().
                    filter(t -> t.getNotOrt() > 63).
                    map(t -> t.getOgrSayisi()).
                    //reduce(Integer::sum);
                   //reduce(Math::addExact);
                   reduce(0, (t, u) -> t + u);
    }

    //task 08--> Ogrenci sayisi 130'dan buyuk olan universite'lerin notOrt'larinin ortalamasini bulunuz.

        public static OptionalDouble ogrcSayisi333BykNotOrtalamaAl(List<Universite> unv) {
            return unv.
                    stream().
                    filter(t -> t.getOgrSayisi() > 333).
                    mapToDouble(t -> t.getNotOrt()).
                    average(); // akisdaki elemanlarin ortalamasini alindi
            // mapToDouble() --> bu method akısdaki elemanların data type'nı
            // parameterisindeki degere göre dooble data type update eder
        }

    //task 09-->"matematik" bolumlerinin sayisini  print ediniz.

    public static int mathBolmSayisi(List<Universite> unv){
         return (int) unv.
                stream().
                filter(t -> t.getBolum().contains("matematik")).
                count(); // akisdaki eleman sayisini return eder
    }

    //task 10-->Ogrenci sayilari 571'den fazla olan universite'lerin en buyuk notOrt'unu bulunuz.

    public static OptionalInt ogrcSayisi571BykMaxNotOrtlamasi(List<Universite> unv) {
        return unv.
                stream().
                filter(t -> t.getOgrSayisi() > 571). // unv obj akisi filtrelendi
                mapToInt(t -> t.getNotOrt()). // akisdaki unv objesi notOrt akisi olarak update edildi.
                max();  // akisin en buyuk degerini return eder.
    }
    //task 11-->Ogrenci sayilari 150'dan az olan universite'lerin en kucuk notOrt'unu bulunuz.

    public static OptionalInt ogrcSayisi1071AzMinNotOrt(List<Universite> unv) {
        return unv.
                stream().
                filter(t-> t.getOgrSayisi()<1071).// unv obj akisi filtrelendi
                mapToInt(t-> t.getNotOrt()).// akisdaki unv objesi notOrt akisi olarak update edildi.
                min();// akisin en kucuk degerini return eder.
    }


}






























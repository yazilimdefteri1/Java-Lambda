package lambda.lambdaTutorial;

import java.util.*;


import static java.util.Arrays.compare;
import static java.util.Arrays.stream;


public class Anagram {

    public static void main(String[] args) {
        String kelime1 = "l";
        String kelime2 = "l";
        List<String> menü = new ArrayList<>();
        menü.add(kelime1);
        menü.add(kelime2);
        System.out.println(menü.
                stream().equals(menü.stream().skip(1)));


    }

    }






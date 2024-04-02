package com.example.springbootdemo.others.testinitial;

import java.util.ArrayList;
import java.util.List;

/**
 * Brackets
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class Brackets {

    // ([{}]) } ()
    public boolean isValid(String line) {
        List<Character> simbols = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(' || line.charAt(i) == '[' || line.charAt(i) == '{') {
                simbols.add(line.charAt(i));
            } else if (line.charAt(i) == ')' || line.charAt(i) == ']' || line.charAt(i) == '}') {
                if (simbols.isEmpty())
                    return false;
                if ((line.charAt(i) == ')' && simbols.get(simbols.size() - 1) == '(')
                        || (line.charAt(i) == ']' && simbols.get(simbols.size() - 1) == '[')
                        || (line.charAt(i) == '}' && simbols.get(simbols.size() - 1) == '{')) {
                    simbols.remove(simbols.size() - 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Brackets brackets = new Brackets();
        System.out.println();
        System.out.println(brackets.isValid("(())()"));
        System.out.println(brackets.isValid("Tengase que (obviamente) puede haber otros simbolos"));
        System.out.println(brackets.isValid(":)"));
        System.out.println(brackets.isValid("a (( ) ) () () b"));
        System.out.println(brackets.isValid("a (( ) ) () () b ))"));
        System.out.println(brackets.isValid("([{}])()"));
        System.out.println(brackets.isValid("([{}]) } ()"));
        System.out.println("([{}]) } ()");
    }
}

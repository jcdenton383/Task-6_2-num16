package ru.vsu.cs.course1.hash.demo;

import ru.vsu.cs.course1.hash.SimpleHashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Program {
    static final String filePath = "input.txt";

    /**
     * Основная функция программы
     *
     * @param args Параметры командной строки
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {


        class Test {
            int a, b;

            public Test(int a, int b) {
                this.a = a;
                this.b = b;
            }

            @Override
            public int hashCode() {
                // return  a + b;
                return Objects.hash(a, b);
            }

            @Override
            public boolean equals(Object other) {
                if (!(other instanceof Test)) {
                    return false;
                }
                Test testOther = (Test) other;
                return this.a == testOther.a && this.b == testOther.b;
            }
        }


        SimpleHashMap<String, Integer> taskMap = new SimpleHashMap<String, Integer>(10000);

        taskMap.put("0", 1);   //required to be able to iterate through map, otherwise cycle does not start
        Scanner scan = new Scanner(System.in);
        System.out.print("Input string: ");
        String s = scan.nextLine();




        int pos = 0;
        boolean capitalize = true;
        StringBuilder sb = new StringBuilder(s);
        while (pos < sb.length()) {
            if (sb.charAt(pos) == '.') {
                capitalize = true;
            } else if (capitalize && !Character.isWhitespace(sb.charAt(pos))) {
                sb.setCharAt(pos, Character.toLowerCase(sb.charAt(pos)));
                capitalize = false;
            }
            pos++;
        }
        s = sb.toString().replaceAll("\\p{Punct}", "");
        s = s.replaceAll("[0-9]","");    //NOTE: IN LAVLINSKY' LECTURE THIS BREAKS IT WITH ++String index out of range: 1++  probably cause he used a SHITTON of weird escape chars, just a long string of words should work fine
        s = s.replaceAll("[ ]{2,}"," ").trim();
        //in fact that was just numbers leaving whitespace which broke iterator
        System.out.println(s);


//-----------

        //-------------

        for (String word : s.split(" ")) {
            if (word.substring(0, 1).toUpperCase().equals(word.substring(0, 1))) {
                // result.add(word);
                Integer integer = taskMap.get(word); // get returns null if there is no such element

                if (integer == null)
                    taskMap.put(word, 1);

                else {
                    taskMap.put(word, integer + 1);
                }
            }
        }

        taskMap.remove("0");   //removing 0 element used to initialize

            for (Map.Entry<String, Integer> entry : taskMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }



        }



    /*private static String readFileUsingBufferedReader(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }*/
    }

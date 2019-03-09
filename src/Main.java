import Factory.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que desea implementar? \n1.Hashmap \n2.Treemap \n3.LinkedHashmap");
        int op = scanner.nextInt();

        Map map = MapFactory.getMap("Hashmap");
        if(op == 2) {
            Map map = MapFactory.getMap("Treemap");
        }
        else if(op == 3){
            Map map = MapFactory.getMap("LinkedHashmap");
        }

        try {
            FileReader file = new FileReader("cards_desc.txt");
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            while (line != null) {
                String[] card = line.split("|");
                map.put(card[0],card[1]);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
    }
}

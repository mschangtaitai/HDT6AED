import Factory.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Main {
    public static void main(String[] args) {
        MapFactory<String,String> factory = new MapFactory<>();
        Map<String,String> allCards;
        Map<String,String> myCards;
        Map<String,Integer> numCards;
        String[] cards;
        String chain;
        ArrayList<String> separetedCards = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que desea implementar? \n1.Hashmap \n2.Treemap \n3.LinkedHashmap");
        int op = scanner.nextInt();

        allCards = factory.createMap(op);
        myCards = factory.createMap(op);
        numCards = factory.createCantMap(op);

        try {
            FileReader file = new FileReader("cards_desc.txt");
            BufferedReader reader = new BufferedReader(file);
            while ((chain = reader.readLine()) != null) {
                cards = chain.split("\\|");
                separetedCards.add(cards[0]);
                separetedCards.add(cards[1]);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }

        for (int x = 0; x < separetedCards.size(); x += 2){
            allCards.put(separetedCards.get(x), separetedCards.get(x + 1));
        }

        Boolean cent = true;
        while(cent) {
            System.out.println("1.Agregar una carta a coleccion\n2.Mostrar el tipo de una carta\n3.Mostrar mi coleccion\n4.Mostrar mi coleccion en orden\n5.Mostrar nombre y tipo de todas las cartas\n6.Mostrara todas las cartas en orden\n7.Salir");
            op = scanner.nextInt();
            switch (op) {

                case 1:
                    System.out.println("Que carte desea agregar?: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.println(name);
                    if (allCards.containsKey(name)) {
                        myCards.put(name, allCards.get(name));
                        if (numCards.containsKey(name)) {
                            int numOfCards = numCards.get(name);
                            numCards.put(name, numOfCards += 1);
                        } else {
                            numCards.put(name, 1);
                        }
                    } else {
                        System.out.println("Esa carta no existe...");
                    }
                    break;

                case 2:
                    System.out.println("Ingrese el nombre de la carta para saber su tipo : ");
                    scanner.nextLine();
                    String cardName = scanner.nextLine();
                    if (allCards.containsKey(cardName)) {
                        System.out.println("La carta " + cardName + " es de tipo " + allCards.get(cardName));
                    } else {
                        System.out.println("La carta " + cardName + " no existe");
                    }
                    break;

                case 3:
                    System.out.println("Mis cartas: ");
                    for (Map.Entry<String, String> pair : myCards.entrySet()) {
                        System.out.println(pair.getKey() + " | " + pair.getValue() + " Cantidad: " + numCards.get(pair.getKey()));
                    }
                    break;

                case 4:
                    System.out.println("Mis cartas ordenadas: ");
                    Map<String, String> sortedMap = myCards
                            .entrySet()
                            .stream()
                            .sorted(comparingByValue())
                            .collect(
                                    toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                            LinkedHashMap::new));
                    System.out.println(sortedMap);
                    Iterator<Map.Entry<String, String>> iterator = myCards.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> pair = iterator.next();
                        System.out.println(pair.getKey() + " | " + pair.getValue() + " Cantidad: " + numCards.get(pair.getKey()));
                    }
                    break;

                case 5:
                    System.out.println("Todas las cartas: ");
                    Iterator<Map.Entry<String, String>> cardMap = allCards.entrySet().iterator();
                    while (cardMap.hasNext()) {
                        Map.Entry<String, String> pair = cardMap.next();
                        System.out.println("Nombre: " + pair.getKey() + "\tTipo : " + pair.getValue());
                    }
                    break;

                case 6:
                    System.out.println("Todas las cartas ordenadas: ");
                    Map<String, String> sortedGeneralMap = allCards
                            //access the entry Set
                            .entrySet()
                            //as a stream
                            .stream()
                            //sort with the comparingByValue Method
                            .sorted(comparingByValue())
                            .collect(
                                    //collect all the entries and set them in the LinkedHashMap object
                                    toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                            LinkedHashMap::new));
                    for (Map.Entry<String, String> entry : sortedGeneralMap.entrySet()) {
                        System.out.println(entry.getKey() + "/" + entry.getValue());
                    }
                    break;

                case 7:
                    cent = false;
            }
        }

    }
}


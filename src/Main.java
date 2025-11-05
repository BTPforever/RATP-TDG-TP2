import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static HashMap<String, Station> stations = new HashMap<>();
    static HashSet<Arc> arcs = new HashSet<>();
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String cheminFichier = "src/sections_metro_ratp.txt";


        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] parties = ligne.split(";");
                String nomStation1 = parties[1].trim();
                String nomStation2 = parties[2].trim();
                if (!stations.containsKey(nomStation1)) {
                    stations.put(nomStation1, new Station(nomStation1));
                }
                if (!stations.containsKey(nomStation2)) {
                    stations.put(nomStation2, new Station(nomStation2));
                }
                arcs.add(new Arc(parties[0], stations.get(nomStation1), stations.get(nomStation2)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Station station : stations.values()) {
            station.addArcs(arcs);
        }

        Scanner s = new Scanner(System.in);
        boolean fini = false;

        while (!fini) {
            System.out.println("Menu principal :");
            System.out.println();
            System.out.println("----------------");
            System.out.println();
            System.out.println("Que voulez-vous faire ?");
            System.out.println();
            System.out.println("0. Quitter");
            System.out.println("1. Afficher les stations");
            System.out.println("2. Trouver le plus cours chemin entre deux stations");

            switch (s.nextInt()) {
                case 0: {
                    fini = true;
                    break;
                }
                case 1: {
                    System.out.println("Il y a " + stations.size() + " stations");
                    for (Station station : stations.values()) {
                        System.out.println(station);
                    }
                    break;
                }
                case 2: {
                    System.out.println("Stations de départ :");
                    s.nextLine();
                    String stationDépart = s.nextLine();
                    System.out.println("Stations d'arrivée :");
                    String stationArrivée = s.nextLine();
                    System.out.println(Station.DFS(stations, stationDépart, stationArrivée));
                    break;
                }
                default: {
                    System.out.println("Valeur entrée inconnue.");
                    break;
                }
            }
        }
    }
}
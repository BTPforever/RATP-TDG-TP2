import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Station {
    private String nom;
    private HashSet<String> idLignes = new HashSet<>();
    private HashSet<Arc> arcsSortants = new HashSet<>();

    public Station(String nomStation) {
        Scanner s = new Scanner(System.in);
        nom = nomStation;

        String cheminFichier = "src/sections_metro_ratp.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] parties = ligne.split(";");
                if (parties[1].trim().equals(nom)) {
                    idLignes.add(parties[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addArcs(HashSet<Arc> arcs) {
        for (Arc arc : arcs) {
            if (arc.getStationDépart() == this) {
                arcsSortants.add(arc);
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public HashSet<String> retourneLignes() {
        return idLignes;
    }

    public int getArcs() {
        int i = 0;
        for (Arc arcSortant : arcsSortants) {
            System.out.println(arcSortant);
            i++;
        }
        return i;
    }

    public HashSet<Arc> retourneArcs() {
        return arcsSortants;
    }

    public HashSet<Station> retourneStationsArrivée() {
        HashSet<Station> stationsArrivée = new HashSet<>();
        for (Arc arcSortant : arcsSortants) {
            stationsArrivée.add(arcSortant.getStationArrivée());
        }
        return stationsArrivée;
    }

    public static int DFS(HashMap<String, Station> stations, LinkedList<Arc> pile, HashMap<String, Arc> arcsDécouverts) {
        int longueur = 0;
        int i = 0;
        int max = 0;
        int max2 = 0;
        //Station stationDépart = stations.get("Maison Blanche");
        //Station stationArrivée = stations.get("Saint Jacques");
        //Station stationDépart = stations.get("Tolbiac");
        //Station stationArrivée = stations.get("Dupleix");
        //Station stationDépart = stations.get("La Defense");
        //Station stationArrivée = stations.get("Bastille");
        Station stationDépart = stations.get("Republique");
        Station stationArrivée = stations.get("Nation");

        Station stationActuelleBase = stationDépart;
        Station stationActuelle = stationDépart;

        max =1;

        //pile.add(stationDépart.retourneArcs().get(0));

        while (stationActuelle!=stationArrivée) {
            System.out.println();
            System.out.println("-----------------------");
            System.out.println("-----------------------");
            System.out.println();
            //max = stationActuelleBase.retourneArcs().size();
            for (Arc arc : stationActuelle.retourneArcs()) {
                if (!arcsDécouverts.containsKey(arc.getStationArrivée().getNom())) {
                    arcsDécouverts.put(arc.getStationArrivée().getNom(), arc);
                    pile.add(arc);
                    max2++;
                    /*if (i<max) {
                        max2++;
                    }*/
                }
            }
            for (Arc arc : pile) {
                System.out.println(arc);
            }
            System.out.println();
            System.out.println(max2);
            System.out.println();
            stationActuelle = pile.poll().getStationArrivée();
            i++;
            if (i==max) {
                i=0;
                longueur++;
                //stationActuelleBase = pile.peek().getStationArrivée();
                max = max2;
                max2 = 0;
            }
            if (stationActuelle==stationArrivée) {
                return longueur;
            }
            System.out.println(stationActuelle);
            System.out.println(longueur);
            System.out.println();
            System.out.println(i);
            System.out.println(max);
            System.out.println(max2);
            System.out.println();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /*public static int DFS(HashMap<String, Station> stations, LinkedList<Arc> pile, HashMap<String, Arc> arcsDécouverts) {
        int longueur = 0;
        int i = 0;
        int max = 0;
        int max2 = 0;
        //Station stationDépart = stations.get("Maison Blanche");
        //Station stationArrivée = stations.get("Saint Jacques");
        //Station stationDépart = stations.get("Tolbiac");
        //Station stationArrivée = stations.get("Dupleix");
        //Station stationDépart = stations.get("La Defense");
        //Station stationArrivée = stations.get("Bastille");
        Station stationDépart = stations.get("Republique");
        Station stationArrivée = stations.get("Nation");

        Station stationActuelleBase = stationDépart;
        Station stationActuelle = stationDépart;

        HashSet<String> lignesActuelle = stationActuelle.retourneLignes();

        max =1;

        //pile.add(stationDépart.retourneArcs().get(0));

        while (stationActuelle!=stationArrivée) {
            System.out.println();
            System.out.println("-----------------------");
            System.out.println("-----------------------");
            System.out.println();
            //max = stationActuelleBase.retourneArcs().size();
            for (Arc arc : stationActuelle.retourneArcs()) {
                if (!arcsDécouverts.containsKey(arc.getStationArrivée().getNom()) && lignesActuelle.contains(arc.getIdLigne())) {
                    arcsDécouverts.put(arc.getStationArrivée().getNom(), arc);
                    pile.add(arc);
                    max2++;
                }
                else {

                }
            }
            for (Arc arc : pile) {
                System.out.println(arc);
            }
            System.out.println();
            System.out.println(max2);
            System.out.println();
            stationActuelle = pile.poll().getStationArrivée();
            i++;
            if (i==max) {
                i=0;
                longueur++;
                //stationActuelleBase = pile.peek().getStationArrivée();
                max = max2;
                max2 = 0;
            }
            if (stationActuelle==stationArrivée) {
                return longueur;
            }
            System.out.println(stationActuelle);
            System.out.println(longueur);
            System.out.println();
            System.out.println(i);
            System.out.println(max);
            System.out.println(max2);
            System.out.println();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }*/

    @Override
    public String toString() {
        return "Station " + nom;
    }
}
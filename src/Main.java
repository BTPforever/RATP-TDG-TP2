import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static HashMap<String, Station> stations = new HashMap<>();
    static HashSet<Arc> arcs = new HashSet<>();
    static LinkedList<Arc> pile = new LinkedList<>();
    static HashMap<String, Arc> arcsDécouverts = new HashMap<>();
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
        int i = 0;
        for (Station station : stations.values()) {
            station.addArcs(arcs);
            int p = station.getArcs();
            i+=p;
        }
        System.out.println(i);

        System.out.println(Station.DFS(stations, pile, arcsDécouverts));

        /*int longueur = 0;
        Station stationDépart = stations.get("Porte de St Cloud");
        Station stationArrivée = stations.get("Marcel Sembat");
        //Station stationArrivée = stations.get("Michel Ange Molitor");

        Station stationActuelle = stationDépart;

        while (stationActuelle!=stationArrivée) {
            for (Arc arc : stationActuelle.retourneArcs()) {
                arcsDécouverts.put(arc.getStationArrivée().getNom(), arc);
            }
            pile.addAll(stationActuelle.retourneStationsArrivée());
            for (int j = 0 ; j<pile.size() ; j++) {
                stationActuelle = pile.poll();
                return longueur;
            }
            System.out.println(stationActuelle);
            System.out.println(longueur);
            longueur++;
        }*/
        System.out.println(stations.get("Denfert Rochereau").retourneArcs());
    }
}
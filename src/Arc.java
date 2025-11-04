public class Arc {
    private String idLigne;
    private Station stationDépart;
    private Station stationArrivée;

    public Arc(String idLigne, Station stationDépart, Station stationArrivée) {
        this.idLigne = idLigne;
        this.stationDépart = stationDépart;
        this.stationArrivée = stationArrivée;
    }

    public String getIdLigne() {
        return idLigne;
    }

    public Station getStationDépart() {
        return stationDépart;
    }

    public Station getStationArrivée() {
        return stationArrivée;
    }

    @Override
    public String toString() {
        return "Arc sur la ligne " + idLigne + " de la station " + stationDépart + " à la station " + stationArrivée;
    }
}
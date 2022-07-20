package civitas;

public class OperacionInmobiliaria {
    private GestionesInmobiliarias gestion;
    private int numPropiedad;
    
    public OperacionInmobiliaria(GestionesInmobiliarias gest, int ip) {
        gestion = gest;
        numPropiedad = ip;
    }
    
    public GestionesInmobiliarias getGestion(){
        return gestion;
    }
    
    public int getNumPropiedad(){
        return numPropiedad;
    }
}

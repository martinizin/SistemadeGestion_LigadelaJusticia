import java.util.ArrayList;

public class GestionHeroes {
    private ArrayList<Heroes> heroes = new ArrayList<>();

    public void registrarHeroe(Heroes heroe) throws Exception {
        for (Heroes h : heroes) {
            if (h.getId().equals(heroe.getId())) {
                throw new Exception("El ID ya está registrado.");
            }
        }
        heroes.add(heroe);
    }

    public void modificarHeroe(String id, double nuevoPagoMensual) {
        for (Heroes heroe : heroes) {
            if (heroe.getId().equals(id)) {
                heroe.setPagoMensual(nuevoPagoMensual);
                return;
            }
        }
    }

    public ArrayList<Heroes> listarHeroes() {
        return heroes;
    }

    public String calcularAporteImpuestos(String id) {
        for (Heroes heroe : heroes) {
            if (heroe.getId().equals(id)) {
                double fondoHeroes = heroe.getPagoMensual() * 0.1;
                double impuesto = calcularImpuestoAnual(heroe.getPagoMensual() * 12);
                double neto = heroe.getPagoMensual() - fondoHeroes - impuesto / 12;
                return String.format(
                        "Nombre: %s\nSuperpoder: %s\nPago Mensual: %.2f\nFondo Héroes: %.2f\nImpuesto: %.2f\nNeto: %.2f",
                        heroe.getNombre(), heroe.getSuperpoder(), heroe.getPagoMensual(), fondoHeroes, impuesto, neto
                );
            }
        }
        return "Héroe no encontrado.";
    }


    private double calcularImpuestoAnual(double anual) {
        if (anual <= 60000) return 0;
        if (anual <= 120000) return 0.12 * (anual - 60000);
        if (anual <= 240000) return 0.25 * (anual - 120000);
        return 0.35 * (anual - 240000);
    }
}

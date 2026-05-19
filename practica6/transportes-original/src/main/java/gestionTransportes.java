import java.util.ArrayList;
import java.util.List;

/**
 * Métricas iniciales de la clase gestionTransportes.
 *
 * Métodos considerados: buscaConductor, anhadeConductor y conductores.
 *
 * WMC = buscaConductor(3) + anhadeConductor(2) + conductores(1) = 6
 * WMCn = 6 / 3 = 2.00
 *
 * CCog = buscaConductor(3) + anhadeConductor(1) + conductores(0) = 4
 * CCogn = 4 / 3 = 1.33
 *
 * CBO = 1: Conductor.
 * DIT = 0.
 * NOC = 0.
 */
public class gestionTransportes {

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();
	
	public Conductor buscaConductor(String DNI) {	
		// WMC buscaConductor:
	    // CC = 1 base + 1 por el for + 1 por el if = 3
	    //
	    // CCog buscaConductor:
	    // for: +1
	    // if dentro del for: +2
	    // CCog = 3
		for(Conductor c: cs) 
			if (c.dni().equals(DNI))
				return c;
		
		return null;
	}
	
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {
		// WMC anhadeConductor:
	    // CC = 1 base + 1 por el if = 2
	    //
	    // CCog anhadeConductor:
	    // if: +1
	    // CCog = 1
		if (buscaConductor(dni) != null)
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}

	public List<Conductor> conductores() {
		return cs;
	}
	
}

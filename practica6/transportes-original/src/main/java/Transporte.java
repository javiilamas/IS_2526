/**
 * Métricas iniciales de la clase Transporte.
 *
 * Métodos considerados: constructor, horas, categoria, getPersonas y ton.
 *
 * WMC = constructor(5) + horas(1) + categoria(1) + getPersonas(1) + ton(1) = 9
 * WMCn = 9 / 5 = 1.80
 *
 * CCog = constructor(4)
 * El resto de métodos son getters, por tanto CCog = 0.
 * CCogn = 4 / 5 = 0.80
 *
 * CBO = 1: CategoriaTransporte.
 * DIT = 0.
 * NOC = 0.
 */
public class Transporte {
	
	private double horas;
	private int ton;
	private int personas;
	private CategoriaTransporte cat;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 
	public Transporte(double horas, CategoriaTransporte cat, int valor) throws IllegalArgumentException {
		// WMC constructor:
	    // CC = 1 base + 1 por if horas <= 0 + 1 por if categoria == null
	    //    + 1 por if valor <= 0 + 1 por otro if/condición si aparece = 5
	    //
	    // CCog constructor:
	    // Se suma por cada estructura de control if.
	    // CCog = 4 según las condiciones del constructor.
		if (horas <= 0 || valor <= 0 || cat == null) {
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		if (cat.equals(CategoriaTransporte.Personas)) {
			this.personas = valor;
		} else  {
			this.ton = valor;
		}
	}
	
	public double horas() {
		return horas;
	}

	public CategoriaTransporte categoria() {
		return cat;
	}

	public int ton() {
		return ton;
	}

	public int getPersonas() {
		return personas;
	}
	
}

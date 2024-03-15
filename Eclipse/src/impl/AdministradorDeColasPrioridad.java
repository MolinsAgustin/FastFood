package impl;

import apis.AdministradorDeColasTDA;
import apis.ColaPrioridadTDA;
import apis.ConjuntoTDA;
import apis.DiccionarioMultipleTDA;
import apis.DiccionarioSimpleTDA;

public class AdministradorDeColasPrioridad implements AdministradorDeColasTDA {

	private int cantPuestos;
	private DiccionarioSimpleTDA puestos;
	private ColaPrioridadTDA colaClientes;
	private DiccionarioMultipleTDA clienteXpuesto;
	private DiccionarioSimpleTDA DicclientesPrioridadId;
	private ConjuntoTDA identificadoresCola;
	private int identificador;
	
	
	@Override
	public void inicializar(int cantidad) {
		cantPuestos = cantidad;
		identificador = 0;
		puestos = new DicSimpleL();
		colaClientes = new ColaPrioridadLD();
		clienteXpuesto = new DicMultipleL();
		identificadoresCola = new ConjuntoLD();
		DicclientesPrioridadId = new DicSimpleL();
	}

	@Override
	public int acolar(int demora) {
		// TODO Auto-generated method stub
		identificador++;
		colaClientes.acolarPrioridad(identificador, demora);
		DicclientesPrioridadId.agregar(identificador, demora);
		identificadoresCola.agregar(identificador);
		return identificador;
	}

	@Override
	public void acolar(int idElemento, int demora) {
		// TODO Auto-generated method stub
		if(!identificadoresCola.pertenece(idElemento)) {
			colaClientes.acolarPrioridad(idElemento, demora);
		}
	}

	@Override
	public int desacolar(int puesto) {
		int clave=-1;
		if(puesto > 0 & puesto <= cantPuestos & !colaClientes.colaVacia()) {
			clave = colaClientes.primero();
			puestos.agregar(puesto, clave);
			clienteXpuesto.agregar(puesto, clave);
			colaClientes.desacolar();
		}
		return clave;
	}

	@Override
	public int cantPuestos() {
		// TODO Auto-generated method stub
		return cantPuestos;
	}

	@Override
	public int próximo() {
		if(!colaClientes.colaVacia())return colaClientes.primero();
		return -1;
	}

	@Override
	public int posiciónXelemento(int idElemento) {
	    int pos = 1;
	    int i = -1;
	    ColaPrioridadTDA colaPrioridad_aux = new ColaPrioridadLD();
	    
	    while (!colaClientes.colaVacia()) {
	        int clave = colaClientes.primero();
	        int valor = colaClientes.prioridad();
	        colaClientes.desacolar();

	        if (clave == idElemento) {
	            i = pos;
	            break;
	        }

	        colaPrioridad_aux.acolarPrioridad(clave, valor);
	        pos++;
	    }

	    while (!colaPrioridad_aux.colaVacia()) {
	        int clave = colaPrioridad_aux.primero();
	        int valor = colaPrioridad_aux.prioridad();
	        colaPrioridad_aux.desacolar();
	        colaClientes.acolarPrioridad(clave, valor);
	    }

	    return i;
	}

	@Override
	public ConjuntoTDA atendidosXpuesto(int idPuesto) {
		// TODO Auto-generated method stub
		return clienteXpuesto.recuperar(idPuesto);
	}

	@Override
	public ColaPrioridadTDA programacion() {
		// TODO Auto-generated method stub
		ColaPrioridadTDA ColaP_copia = new ColaPrioridadLD();
		ColaPrioridadTDA colaPrioridad_aux = new ColaPrioridadLD();
		ColaP_copia.inicializarCola();
		colaPrioridad_aux.inicializarCola();
		
	    while (!colaClientes.colaVacia()) {
	        int elemento = colaClientes.primero();
	        int prioridad = colaClientes.prioridad();
	        colaClientes.desacolar();

	        colaPrioridad_aux.acolarPrioridad(elemento, prioridad);
	        colaClientes.acolarPrioridad(elemento, prioridad);
	        ColaP_copia.acolarPrioridad(elemento, prioridad);
	    }
		
	    while (!colaPrioridad_aux.colaVacia()) {
	        int elemento = colaPrioridad_aux.primero();
	        int prioridad = colaPrioridad_aux.prioridad();
	        colaPrioridad_aux.desacolar();
	        colaClientes.acolarPrioridad(elemento, prioridad);
	    }
		
		return ColaP_copia;
	}
		

	@Override
	public DiccionarioSimpleTDA elementos() {
		// TODO Auto-generated method stub
	    DiccionarioSimpleTDA dicClientesPosicion = new DicSimpleL();
	    dicClientesPosicion.inicializarDiccionario();
	    
	    ColaPrioridadTDA colaPrioridad_aux = new ColaPrioridadLD();
	    colaPrioridad_aux.inicializarCola();

	    int pos = 0;

	    while (!colaClientes.colaVacia()) {
	        int elemento = colaClientes.primero();
	        int prioridad = colaClientes.prioridad();
	        colaClientes.desacolar();

	        colaPrioridad_aux.acolarPrioridad(elemento, prioridad);
	        dicClientesPosicion.agregar(elemento, ++pos);
	    }

	    while (!colaPrioridad_aux.colaVacia()) {
	        int elemento = colaPrioridad_aux.primero();
	        int prioridad = colaPrioridad_aux.prioridad();
	        colaPrioridad_aux.desacolar();
	        colaClientes.acolarPrioridad(elemento, prioridad);
	    }

	    return dicClientesPosicion;
	}

	@Override
	public int Prioridad() {
		// TODO Auto-generated method stub
		return colaClientes.prioridad();
	}

}

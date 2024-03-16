//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///											   TRABAJO PRACTICO PROGRAMACION 2									   ///
///										GRUPO NXX - VIERNES NOCHE - 2DO CUATRIMESTRE							   ///
///										    REPETTO	FRANCO	- MOLINS CRISTIAN									   ///
/// 	UADE 2023																								   ///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


package tpo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;


import apis.AdministradorDeColasTDA;
import impl.AdministradorDeColasPrioridad;

public class testTPO {
	
	static int puestos = 10;
	//private static int horarioTicket = horaTicket();
	
	
	public static void cargarColaPrioridad(AdministradorDeColasTDA administradorFF, int cantidadClientes) {
	    int cont = 1;
	    int horaInicio = 8;
	    int minutosASumar = 1;
	    int horarioAnterior = 800;
        System.out.println(" -- Creando tickets para cada cliente y su pedido...\n");
	    while (cont <= cantidadClientes) {
	        int prioridad = getRandomNumber(1, 5);
	        int horarioTicket = generarHorarioTicket(horarioAnterior, minutosASumar);

	        if (prioridad == 1) {
	            System.out.println("ID Ticket: " + cont + " | Ticket: PDL" + administradorFF.acolar(-200) + "  -->  [" + 200 + "]");
	        } else if (prioridad == 2) {
	            System.out.println("ID Ticket: " + cont + " | Ticket: PAS" + administradorFF.acolar(-140) + "  -->  [" + 140 + "]");
	        } else if (prioridad == 3) {
	            System.out.println("ID Ticket: " + cont + " | Ticket: AM" + administradorFF.acolar(-40) + "  -->  [" + 40 + "]");
	        } else if (prioridad == 4) {
	            System.out.println("ID Ticket: " + cont + " | Ticket: RDP" + administradorFF.acolar(-horarioTicket) + "  -->  [" + horarioTicket + "]");
	        }

	        cont++;
	        horarioAnterior = horarioTicket;
	    }
	}

    public static void simular(AdministradorDeColasTDA administradorFF, int cantidadPuestos) {
        boolean fin = false;
        int id = 0;
        while (fin != true) {
            for (int i = 1; i <= cantidadPuestos; i++) {
            	id = administradorFF.desacolar(i);
                if(id != -1) {
                    System.out.println("ID del Ticket: " + id + "\t||  Puesto: " + i + "\t||  Hora del llamado: " + obtenerHoraActual());
                } else {
                    fin = true;
                }
                
                generarDemora(2000);
            }
            
        }
    }


    ///////////////////////// MAIN ////////////////////////////////
    
    public static void main(String[] args) {
    	
        AdministradorDeColasTDA administradorFF = new AdministradorDeColasPrioridad();
        System.out.println("////////////////////////// FAST FOOD ////////////////////////// \n");
        administradorFF.inicializar(puestos); 
        cargarColaPrioridad(administradorFF, 100);
        System.out.println("\n\n -- Generando lamados y asignando a puestos...");
        simular(administradorFF, puestos);
        System.out.println("////////////////////////////////////////////////////////////// \n");

   
}
    
    ///////////////////////// COMPLEMENTOS ////////////////////////////////
    
    
    /*Numero Aleatorio*/
	public static int getRandomNumber(int minimo, int maximo) {
		return (int) ((Math.random() * (maximo - minimo)) + minimo);
	}
	
	private static void generarDemora(int milisegundos) {
		 try {
             Thread.sleep(milisegundos);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
	}
	
	/*Obtener Hora Actual*/
    public static String obtenerHoraActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime horaActual = LocalTime.now();
        return horaActual.format(formatter);
    }
    
    /*A partir de un horario hardcodeado sumar +1 minuto respetando que es un horario*/
    public static int generarHorarioTicket(int horarioAnterior, int minutosASumar) {
        int minutos = horarioAnterior % 100 + minutosASumar;
        int horaInicio = horarioAnterior / 100;
        
        if (minutos >= 60) {
            horaInicio += minutos / 60;
            minutos %= 60;
        }
        if (horaInicio >= 24) {
            horaInicio %= 24;
        }

        return horaInicio * 100 + minutos;
    }
    
    
    
}


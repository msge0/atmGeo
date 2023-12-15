package org.generation;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class OpcionesATM {
	
	ArrayList<String> transs = new ArrayList<>();
	double balance = 10_000.00;
	Scanner sc = new Scanner(System.in);
	double amount; 
	int attemps = 0;
	
	
	public void Menu() {
		System.out.println("Bienvenidx, por favor seleccione una de las siguentes opciones: ");
	      System.out.println(" 1) Retirar dinero");
	      System.out.println(" 2) Hacer depositos");
	      System.out.println(" 3) Consultar saldo");
	      System.out.println(" 4) Quejas");
	      System.out.println(" 5) Ver ultimos movimientos");
	      System.out.println(" 9) Salir");
	      System.out.println();
	      System.out.println("Ingrese la opcion: ");
	      
	      int choice = sc.nextInt();
	      sc.nextLine();
	      
	      switch (choice) {
	        case 1:
	          retirar();
	          break;
	        case 2:
	          menuDeposits();
	          break;
	        case 3:
	          getBalance();
	          break;
	        case 4:
	          complaints();
	          break;
	        case 5:
	          transHistory();
	          break;
	        case 9:
	          exit();
	          break;
	        default:
	          invalidOption();
	      }
	}
	      public void menuDeposits() {
	    	  System.out.println(" 1) Depositar a cuenta de Cheques ");
		      System.out.println(" 2) Depositar a TC");
		      
		      System.out.print("Ingrese la opcion: ");
		      int choice = sc.nextInt();
		      
		      switch(choice) {
		      case 1: 
		    	  checkingAcc();
		    	  Menu();
		    	  break;
		      case 2: 
		    	  CC(); 
		    	  Menu();
		    	  break;
		    	  default:
		    		  System.out.println("Opcion invalida");
		    		  menuDeposits();
		      }
	      }
	      
	      public void retirar() {
			  System.out.println("Su saldo es de: $" + balance); 
				System.out.print("Ingrese cantidad a retirar: ");
				amount = sc.nextDouble();
				sc.nextLine();
				
				if(amount > 6000) {
					System.out.println("La cantidad maxima a retiar por dia es de $6,000");
					retirar();
				}else if(amount % 50 != 0) {
					System.out.println("Solo se puede retirar en multiplos de $50");
					retirar();
				}else if(balance <= 0) {
					System.out.println("Fondos insuficientes");
					retirar();
				}else{
					balance -= amount; 
					transs.add(timestamp() + " Retiro de $:" + amount);
					
					System.out.print("Desea donar $200 para la graduación de ch34? (S/N): ");
						char answer = sc.nextLine().toUpperCase().charAt(0);
						
						if(answer == 's') {
							balance -= 200; 
							transs.add(timestamp() + "Retiro de $:" + amount);
							System.out.println("Gracias por su donacion. Retiro exitoso");
						}else {
							System.out.println("Retiro exitoso");
						}
						Menu();
						}}

				  public void checkingAcc() {
				    System.out.print("Ingrese la cantidad a depositar en cuenta de cheques: ");
				    amount = sc.nextDouble();
				    sc.nextLine(); 

				    if (amount > 0 && amount % 50 == 0) {
				      balance += amount;
				      transs.add(timestamp() + " Depósito en cuenta de cheques de $" + amount);
				      System.out.println("Depósito exitoso.");
				    } else {
				      System.out.println("Cantidad inválida para depositar. Asegúrese de que sea múltiplo de $50.");
				      checkingAcc();
				    }
				    
				  }

				  public void CC() {
				    System.out.print("Ingrese la cantidad a depositar en tarjeta de crédito: ");
				    amount = sc.nextDouble();
				    sc.nextLine(); 

				    if (amount > 0) {
				      balance -= amount;
				      transs.add(timestamp() + " Pago a tarjeta de crédito de $" + amount);
				      System.out.println("Depósito en tarjeta de crédito exitoso.");
				    } else {
				      System.out.println("Cantidad inválida para depositar en tarjeta de crédito.");
				      CC();
				    }
				  }

				  public void getBalance() {
				    System.out.println("Saldo disponible: $" + balance);
				    Menu();
				  }

				  public void complaints() {
				    System.out.println("No disponible por el momento, intente más tarde");
				  Menu();
				  }

				  public void transHistory() {
				    System.out.println("Últimos movimientos:");

				    int count = 0;
				    for (String movimiento : transs) {
				      System.out.println(movimiento);
				      count++;

				      if (count >= 5) {
				        break;
				      }
				    }
				 Menu();
				  }

				  public void exit() {
				    System.out.println("Gracias por utilizar nuestro cajero automático. ¡Hasta luego!");
				    System.exit(0);
				  }

				  public void invalidOption() {
				    attemps++;

				    if (attemps >= 3) {
				      System.out.println("Demasiados intentos inválidos. Saliendo del sistema.");
				      System.exit(0);
				    }

				    System.out.println("Opción inválida. Por favor, vuelva a intentar.");
				    Menu();
				  }

				  public String timestamp() {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				    LocalDateTime date = LocalDateTime.now();
				    return formatter.format(date);
				  }
	
}

/* Esse programa foi criado para estudos por Felipe Mello
 * Não objetivando nenhum tipo de ataque
 */



package br.com.ping;

import java.util.Scanner;

public class TestePingueAtaque {
	
	public static void main (String  [] args){
			
			System.out.println("##################################################\n"
							 + "#                      EXXUS                     #\n"
							 + "##################################################\n\n\n"
							 + "Desenvolvido por Felipe Mello @Drinith\n");
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.print("DIGITE O NÚMERO DE VEZES QUE DESEJA PINGAR UM ENDEREÇO: ");
			
			int numeroPing = scan.nextInt();
			
			System.out.print("DIGITE O ENDEREÇO PARA ONDE VAI PINGAR:  ");
			
			String valorIP = scan.next();
			
			
			Thread t = null;  
			
			for (int i = 0; i < numeroPing; i++) {
						
				
				Ping p = new Ping();
				
				p.endereco = valorIP;
				t = new Thread(p);
				t.start();
			
			}
			
			synchronized (t) {
				try{
	                  System.out.println("Aguardando o b completar...");
	                  t.wait();
	              }catch(InterruptedException e){
	                  e.printStackTrace();
	              }
			}
		
			for (String string : Ping.ListaPing) {
				
				System.out.println(string);
				
			}
			
		}
}

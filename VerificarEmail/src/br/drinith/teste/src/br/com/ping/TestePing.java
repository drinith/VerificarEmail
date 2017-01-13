package br.com.ping;
import java.util.Scanner;


public class TestePing {

	public static void main (String  [] args){
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("DIGITE A PARTE DE REDE DO ENDERECO DE CLASS C QUE DESEJA PINGAR \n "
				+ "Ex 192.168.12 \n "
				+ "DIGITE :");
		
		String valorIP = scan.nextLine()+".";
		
		
		for (int i = 0; i < 255; i++) {
					
			
			Ping p = new Ping();
			
			p.endereco = valorIP+i;
			Thread t = new Thread(p);
			t.start();
		
		}
		
		System.out.notify();

		
	}
}
	


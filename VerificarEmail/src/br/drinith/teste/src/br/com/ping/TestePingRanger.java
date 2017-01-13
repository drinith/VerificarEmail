package br.com.ping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class TestePingRanger {

	public static void main (String [] args){
		
		int oct1=0;
		int oct2=0;
		int oct3=0;
		int oct4=0;
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
				
		System.out.println("##############################################\n"
						+"####################EXXUS#####################\n"
						+"##############################################\n"
						+"Desenvolvido por Felipe Mello felpmel@gmail.com\n\n\n");
		
		
		System.out.print("DIGITE O IP DO INICIO DO RANGER \n "
				+ "Ex 192.168.1.0 \n "
				+ "DIGITE :");
		
		String valorIP = scan.nextLine();
		
		Ping ping = new Ping();
		ArrayList<Integer> list1 = ping.splitOctetosIP(valorIP);
		
		System.out.print("DIGITE O IP DO FINAL DO RANGER \n "
				+ "Ex 192.168.2.0 \n "
				+ "DIGITE :");
		
		valorIP = scan.nextLine();
		ArrayList<Integer> list2 = ping.splitOctetosIP(valorIP);

		int vetorRanger[] = new int[4] ;
		
		for (int i = 0; i < list1.size(); i++) {
			vetorRanger[i]=list2.get(i)-list1.get(i);
		}
		
		oct1=vetorRanger[0];
		oct2=vetorRanger[1];
		oct3=vetorRanger[2];
		oct4=vetorRanger[3];		
		
		
		if (vetorRanger[0]>0){
			oct2=255;
			oct3=255;
			oct4=255;
		}
		if (vetorRanger[0]==0 && vetorRanger[1]>0){
			oct3=255;
			oct4=255;
		}
		if (vetorRanger[0]==0 && vetorRanger[1]==0 && vetorRanger[2]>0){
			oct4=255;
		}
		
		
		
		
		for (int i = 0; i <= oct1; i++) {
			for (int j = 0; j <= oct2; j++) {
				for (int k = 0; k <= oct3; k++) {
					for (int l = 0; l <= oct4; l++) {
						Ping p = new Ping();
						p.endereco = (list1.get(0)+i)+"."+(list1.get(1)+j)+"."+(list1.get(2)+k)+"."+(list1.get(3)+l);
						Thread t = new Thread(p);
						t.start();
												
					}
				}
			}
		}
		
	
		
		/*
		for (int i = 0; i < 255; i++) {
					
			
			Ping p = new Ping();
			
			p.endereco = valorIP+i;
			Thread t = new Thread(p);
			t.start();
		
		}
		
		System.out.notify();
		*/
	}
	
	
}

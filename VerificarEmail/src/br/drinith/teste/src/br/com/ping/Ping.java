package br.com.ping;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;



public class Ping implements Runnable{

	
	public String endereco;
	
	public static ArrayList<String> ListaPing = new ArrayList<>();
	
	
	public void run(){
		
		
		try {

			System.out.println((this.endereco+" "+ this.pingar()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public boolean pingar () throws UnknownHostException, IOException{
		
		InetAddress.ListaPing.add(this.endereco+ InetAddress.getByName(this.endereco).isReachable(3000));
		
		
		return InetAddress.getByName(this.endereco).isReachable(3000) ;
		
	}
	
	
	public ArrayList<Integer> splitOctetosIP(String _ip){
		
		ArrayList<Integer> octeto = new ArrayList<Integer>();	
		String ip []  = _ip.split("\\.");
		
		for (String string : ip) {
			octeto.add(Integer.parseInt(string));
		}
		
		return octeto;
	}
	

	
}

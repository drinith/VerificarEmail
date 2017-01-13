package br.drinith.teste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import br.drinith.teste.src.br.com.ping.Ping;

public class TesteVerificarEmail {


	public static void main(String args[]) {
	    String testData[] = { "teste@gmail.com" };
	    System.out.println(testData.length);
	    
	    VerificarEmail vEmail = new VerificarEmail();
	    
	    Thread t = null;  
		
		for (int i = 0; i < testData.length; i++) {
					
			VerificarEmail vEmail = new VerificarEmail();			
			
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
	    
	    
	    for (int ctr = 0; ctr < testData.length; ctr++) {
	        System.out.println(testData[ctr] + " is valid? " + isAddressValid(testData[ctr]));
	    }
	    return;
	}
	
}

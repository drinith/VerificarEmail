package br.drinith.teste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

public class TesteVerificarEmailThread {


	public static void main(String args[]) throws IOException {
		
		String nome;
		String email;
		String path = "C:\\Users\\Instrutor\\Desktop\\";
		CsvReader cvs = new CsvReader(path+"cadastro.csv");
		
		cvs.readHeaders();
		
	    List <Email> listEmail = new ArrayList<Email>();
	    
	    while (cvs.readRecord())
		{
			 nome = cvs.get("Nome");
			 email = cvs.get("E-mail");
			
			listEmail.add(new Email(nome,email));
			
			// perform program logic here
			
		}

		cvs.close();
	    
	 
	    VerificarEmailThread vEmail;
  
	    Thread[] t = new Thread[listEmail.size()];
		for (int i = 0; i < listEmail.size(); i++) {
				
			vEmail = new VerificarEmailThread();
			vEmail.email = listEmail.get(i);
			t[i] = new Thread(vEmail);
			t[i].start();
			
		
			System.out.println("Contagem "+i);
		}
		for (Thread thread : t) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	    
	    for (int ctr = 0; ctr < VerificarEmailThread.listEmail.size(); ctr++) {
	        System.out.println(VerificarEmailThread.listEmail.get(ctr).endereco +
	        		" is valid? " + VerificarEmailThread.listEmail.get(ctr).existe);
	    }
	   
	}
	
}

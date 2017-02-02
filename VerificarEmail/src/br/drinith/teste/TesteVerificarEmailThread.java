package br.drinith.teste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

public class TesteVerificarEmailThread {


	public static void main(String args[]) throws IOException {
		
		VerificarEmailThread vEmail ;
		String nome ;
		String email;
		CsvReader cvs = new CsvReader("C:\\Users\\Felipe\\Desktop\\CadastroTotal.csv");
		
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
	    
	 
		Thread t= null;
  
		
		for (int i = 0; i <10; i++) {
				
			vEmail = new VerificarEmailThread();
			vEmail.email = listEmail.get(i);
		    t = new Thread(vEmail);
			t.start();
			
			System.out.print(i);
			try {
				t.join();
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

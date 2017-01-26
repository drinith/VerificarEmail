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
	    
	 
	    
  
		
		for (int i = 0; i < 50; i++) {
				
			vEmail = new VerificarEmailThread();
			vEmail.email = listEmail.get(i);
			Thread t = new Thread(vEmail);
			t.start();
			
			
		}
		
	
		
	    
	    
	    for (int ctr = 0; ctr < VerificarEmailThread.listEmail.size(); ctr++) {
	        System.out.println(VerificarEmailThread.listEmail.get(ctr).endereco +
	        		" is valid? " + VerificarEmailThread.listEmail.get(ctr).existe);
	    }
	   
	}
	
}

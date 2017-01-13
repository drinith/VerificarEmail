//Ipv4.java
package br.com.ping;

public class Ipv4{
	private int bitmask;
	private int oct[]   = new int[4];
	private int mask[]  = new int[4];
	private String boct[]  = new String[4]; // Ip em Binario
	private String bmask[] = new String[4]; // Mask em Binario
	private String ipbin="",maskbin=""; // Ip e Mask em binario sem pontuacao.
	private String host; // Parte binaria do ip referente ao host.
	private String net; // Parte binária do ip referente a rede.
	
	//Array relacionando as mascaras e o bitmask.
	public static final String BITMASK[] = {
		"0.0.0.0","128.0.0.0","192.0.0.0",
		"224.0.0.0","240.0.0.0","248.0.0.0",
		"252.0.0.0","254.0.0.0","255.0.0.0",
		"255.128.0.0","255.192.0.0","255.224.0.0",
		"255.240.0.0","255.248.0.0","255.252.0.0",
		"255.254.0.0","255.255.0.0","255.255.128.0",
		"255.255.192.0","255.255.224.0","255.255.240.0",
		"255.255.248.0","255.255.252.0","255.255.254.0",
		"255.255.255.0","255.255.255.128","255.255.255.192",
		"255.255.255.224","255.255.255.240","255.255.255.248",
		"255.255.255.252","255.255.255.254","255.255.255.255"
	};
	
	public static final String NUM_HOSTS[] = { 
		"4294967294","2147483646","1073741822",
		"536870910","268435454","134217726",
		"67108862","33554430","16777214",
		"8388606","4194302","2097150","1048574",
		"524286","262142","131070","65534","32766",
		"16382","8190","4094","2046","1022","510",
		"254","126","62","30","14","6","2","0","1"
	};
	
	//Construtor da classe.
	public Ipv4(String addr, String mask){
		//Checando se o ip está em formato valido.
		String tmp[] = addr.split("[.]");
		if(tmp.length < 4)
			throw new RuntimeException("Formato de Host Invalido. Ex: w.x.y.z");
			
		//Armazenando os octetos do endereço.
		for(int i=0;i<4;i++){
			oct[i] = Integer.parseInt(tmp[i]);
			boct[i] = zfill(Integer.toString(oct[i], 2),8);
			ipbin += boct[i];
		}
		
		//Trabalhando com a mascara.
		bitmask = calcBitMask(mask);
		
		//Armazenando os octetos da mascara.
		tmp = BITMASK[bitmask].split("[.]");
		for(int i=0;i<4;i++){
			this.mask[i] = Integer.parseInt(tmp[i]);
			bmask[i] = zfill(Integer.toString(this.mask[i],2),8);
			maskbin += bmask[i];
		}
		
		//Armazena em binario sem os pontos a parte do ip referente a rede
		net  = ipbin.substring(0,bitmask);
		host = ipbin.substring(bitmask);
		
	}
	
	//Completa a string numerica com zeros a esquerda.
	private String zfill(String num, int tam){
		if(num.length()<tam){
			int dif = tam-num.length();
			for(int i=0;i < dif;i++)num="0"+num;
		}
		return num;
	}
	
	//Coloca a pontuacao em um ip binario
	private String binPutDot(String ipbin){
		StringBuffer ip = new StringBuffer(ipbin);
		ip = ip.insert(8,".");
		ip = ip.insert(17,".");
		ip = ip.insert(26,".");
		return ip.toString();
	}
	
	//Converte um ip em binario com pontuacao para decial
	private String ipToDecimal(String ip){
		String o[] = ip.split("[.]");
		ip="";
		for(int i=0;i < 4; i++)
			ip += String.valueOf(Integer.parseInt(o[i],2)) + ".";
		ip = ip.substring(0,ip.length()-1);
		return ip;
	}
	
	//Calcula o bitmask da mascara de rede.
	private int calcBitMask(String m){
		if(m.length()<=2){
			int mask = Integer.parseInt(m);
			if(mask<0 || mask > 32)
				throw new RuntimeException("Bitmask de Tamanho Inválido");
			return mask;
		}
		
		for(int i=0;i<BITMASK.length;i++)
			if(BITMASK[i].equals(m))return i;
		
		return 0;
	}
	
	//Retorna o bitmask
	public String getBitMask(){return String.valueOf(bitmask);}
	
	//Retorna a máscara em decimal
	public String getMask(){
		String tmp = "";
		for(int i=0; i < mask.length;i++)
			tmp += String.valueOf(mask[i]) +".";
		tmp = tmp.substring(0,tmp.length()-1);
		return tmp;
	}
	
	//Retorna a máscara em binário.	
	public String getBinMask(){
		String tmp = "";
		for(int i=0; i < mask.length;i++)
			tmp += bmask[i];
		return binPutDot(tmp);
	}
	
	//Retorna a rede a qual pertence o ip.
	public String getNetwork(){
		StringBuffer ip = new StringBuffer(net);
		int dif = 32 - ip.length();
		for(int i=0;i < dif; i++)ip.append("0");
		ip = ip.insert(8,".");
		ip = ip.insert(17,".");
		ip = ip.insert(26,".");
		return ipToDecimal(ip.toString());
	}
	
	//Retorna o broadcast da rede a qual pertence o ip.
	public String getBroadcast(){
		StringBuffer ip = new StringBuffer(net);
		int dif = 32 - ip.length();
		for(int i=0;i < dif; i++)ip.append("1");
		return ipToDecimal(binPutDot(ip.toString()));
	}
	
	//Retorna o primeiro ip da rede
	public String getMinIp(){
		String h = "";
		int dif = 32 - net.length();
		for(int i=0;i < dif; i++)h+="0";
		
		//Converte pra decimal e soma 1 ao host
		int s = Integer.parseInt(h,2) + 1;
		//Converte novamente pra binario
		String host = Integer.toString(s,2);
		//Completa os zeros caso necessario
		host = zfill(host,dif);
		//Coloca os pontos e converte pra decimal
		return ipToDecimal(binPutDot(net + host));		
	}
	
	//Retorna o primeiro ip da rede
	public String getMaxIp(){
		String h = "";
		int dif = 32 - net.length();
		for(int i=0;i < dif; i++) h+="1";
		
		//Converte pra decimal e soma 1 ao host
		int s = Integer.parseInt(h,2) - 1;
		//Converte novamente pra binario
		String host = Integer.toString(s,2);
		//Completa os zeros caso necessario
		host = zfill(host,dif);
		//Coloca os pontos e converte pra decimal
		return ipToDecimal(binPutDot(net + host));		
	}
	
	//Retorna true se o ip passado no construtor for rede.
	public boolean isNetwork(){
		String h = "";
		int dif = 32 - net.length();
		for(int i=0;i < dif; i++)h+="0";
		return h.equals(host);
	}
	
	//Retorna true se o ip passado no construtor for broadcast.
	public boolean isBroadcast(){
		String h = "";
		int dif = 32 - net.length();
		for(int i=0;i < dif; i++)h+="1";
		return h.equals(host);
	}
	
	//Retorna o proximo ip.
	public String nextIp(){
		if(isBroadcast())return "0.0.0.0";
		int dif = 32 - net.length();
		//Converte pra decimal e soma 1 ao host
		int s = Integer.parseInt(host,2) + 1;
		//Converte novamente pra binario
		String host = Integer.toString(s,2);
		//Completa os zeros caso necessario
		host = zfill(host,dif);
		//Coloca os pontos e converte pra decimal
		host = ipToDecimal(binPutDot(net + host));
		
		//Se o proximo ip for o broadcast, retorn 0.
		if(host.equals(getBroadcast()))return "0.0.0.0";
		
		return host;
	}

	// Retorna a Quantidade de Hosts da Rede.
	public String getNumHosts(){return NUM_HOSTS[bitmask];}
	
	//Ferramenta de linha de comando para testes da lib.
	public static void main(String args[]){
		java.util.Scanner in = new java.util.Scanner(System.in);
		System.out.println("-----------------------------------");
		System.out.println("--- Ipcalc - Leandro Costa 2008 ---");
		System.out.println("-- ");
		System.out.print("-- Host|Net  :");
		String h = in.nextLine();
		System.out.print("-- [Bit]Mask :");
		String m = in.nextLine();
		
		Ipv4 i = new Ipv4(h,m);
		System.out.println("-----------------------------------");
		System.out.println("-- Network  : " + i.getNetwork() + "/" + i.getBitMask());
		System.out.println("-- Broadcast: " + i.getBroadcast());
		System.out.println("-- Mascara  : " + i.getMask());
		System.out.println("-- Ip Min   : " + i.getMinIp());
		System.out.println("-- Ip Max   : " + i.getMaxIp());
		System.out.println("-- Next Ip  : " + i.nextIp());
		System.out.println("-- isNet    : " + i.isNetwork());
		System.out.println("-- isBrd    : " + i.isBroadcast());
		System.out.println("-- N. Hosts : " + i.getNumHosts());
	}
}

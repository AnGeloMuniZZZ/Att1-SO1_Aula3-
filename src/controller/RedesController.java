package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	public RedesController() {
		super();
	}

	private String OS() {

		return System.getProperty("os.name");
	}

	public void IP() {
		try {
			Process p;
			InputStream dados;
			InputStreamReader leitor;
			BufferedReader buffer;
			String linha;
			if (OS().contains("Windows")) {
				p = Runtime.getRuntime().exec("ipconfig");
				dados = p.getInputStream();
				leitor = new InputStreamReader(dados);
				buffer = new BufferedReader(leitor);
				linha = buffer.readLine();
				StringBuffer textoIP = new StringBuffer();
				while (linha != null) {
					if (linha.contains("Adaptador") || linha.contains("IPv4")) {
						textoIP.append(linha);
						textoIP.append("\n");
					}
					linha = buffer.readLine();
				}
				String[] Ipv4 = textoIP.toString().split("\n");
				for (int i = 0; i < Ipv4.length; i++) {
					if (i == (Ipv4.length - 1)) {
					} else {
						if (Ipv4[i + 1].contains("IP")) {
							System.out.println(Ipv4[i]);
							System.out.println(Ipv4[i + 1]);
						}
					}
				}
				dados.close();
				leitor.close();
				buffer.close();
			} else {
				p = Runtime.getRuntime().exec("ifconfig");
				dados = p.getInputStream();
				leitor = new InputStreamReader(dados);
				buffer = new BufferedReader(leitor);
				linha = buffer.readLine();
				StringBuffer textoInet = new StringBuffer();
				while (linha != null) {
					textoInet.append(linha);
					textoInet.append("\n");
					linha = buffer.readLine();
				}
				String[] inet = textoInet.toString().split("\n");
				for (int i = 0; i < inet.length; i++) {
					if (inet[i].contains("inet ")) {
						System.out.println(inet[i]);
						System.out.println(inet[i+2]);
					}
				}
				dados.close();
				leitor.close();
				buffer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void PING() {
		try {
			Process p;
			InputStream dados;
			InputStreamReader leitor;
			BufferedReader buffer;
			String linha;
			if (OS().contains("Windows")) {
				p = Runtime.getRuntime().exec("ping -4 -n 10 www.google.com.br");
				dados = p.getInputStream();
				leitor = new InputStreamReader(dados);
				buffer = new BufferedReader(leitor);
				linha = buffer.readLine();
				String ping = null;
				while(linha != null) {
					if(linha.contains("dia")) {
						ping = linha;
					}
					linha = buffer.readLine();
				}
				String[] media = ping.split(",");
				System.out.println(media[2]);
			} else {
				p = Runtime.getRuntime().exec("ping -4 -c 10 www.google.com.br");
				dados = p.getInputStream();
				leitor = new InputStreamReader(dados);
				buffer = new BufferedReader(leitor);
				linha = buffer.readLine();
				StringBuffer ping = new StringBuffer();
				while(linha != null) {
					if(linha.contains("avg")) {
						ping.append(linha);
					}
					linha = buffer.readLine();
				}
				String[] media = ping.toString().split("=");
				String[] Tmedia = media[0].split("/");
				String[] Nmedia = media[1].split("/");
				System.out.print(Tmedia[1] + ": ");
				System.out.println(Nmedia[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

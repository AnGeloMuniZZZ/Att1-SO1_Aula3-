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
			if (this.OS().contains("Windows")) {
				p = Runtime.getRuntime().exec("ipconfig");
			} else {
				p = Runtime.getRuntime().exec("ifconfig");
			}
			InputStream dados = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(dados);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			dados.close();
			leitor.close();
			buffer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

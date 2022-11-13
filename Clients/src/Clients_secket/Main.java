package Clients_secket;

public class Main {

	public static void main(String[] args) {
		final int port = 9634;
		final String adresse_ip_serveur = "127.0.0.1";
		new Clients().connection(port, adresse_ip_serveur);
	}

}

import java.util.ArrayList;
import java.util.Scanner;

import control.*;
import model.*;
import network.*;
import view.*;

public class Teste {
	
	public static void Simulacao(Scanner scan) {
		Controller c = new Controller();
		ActorPlayer actor = c.getActorPlayer();
		
		System.out.print("Digite seu nome: ");
		String name = scan.nextLine();
		
		System.out.println("CASO DE USO CONECTAR");
		actor.connect(Main.server, name);
		
		System.out.println("CASO DE USO INICIAR PARTIDA / RECEBER SOLICITAÇÃO DE INICIO");
		System.out.print("Iniciar Partida: ");
		if (scan.nextBoolean()) {
			actor.startGame();
		}
		scan.next();
	}
		
	
}

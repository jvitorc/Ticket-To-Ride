package network;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import control.Controller;

public class ActorNetGames implements OuvidorProxy {

	private Controller controller;
	private Proxy proxy;
	
	public ActorNetGames(Controller controller) {
		this.controller = controller;
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	
	// CASO DE USO CONECTAR 
	public boolean connect(String server, String name) {
		try {
			proxy.conectar(server, name);
			return true;
		} catch (JahConectadoException | NaoPossivelConectarException | ArquivoMultiplayerException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	// INICIA CASO DE USO RECEBER SOLICITÇÃO DE INICIO
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		// TODO Auto-generated method stub
		controller.startNewGame(posicao);
	}

	// DESCONECTAR POR ERRO
	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub
		controller.disconnectNETWORK();
	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub
		
	}

	
	// INICIA CASO DE USO RECEBER JOGADA
	@Override
	public void receberJogada(Jogada jogada) {
		// TODO Auto-generated method stub
		Action action = (Action) jogada;
		controller.setPlayed(action);
	}

	// DESCONECTAR POR ERRO
	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		controller.disconnectNETWORK();
	}

	
	// NÃO PRECISA
	@Override
	public void tratarPartidaNaoIniciada(String message) {
		
	}

	// CASO DE USO INICIAR PARTIDA
	public boolean startGame(int quantity) {
		try {
			proxy.iniciarPartida(quantity);
			return true;
		} catch (NaoConectadoException e) {
			return false;
		}
		
	}

	// CASO DE USO FINALIZAR TURNO
	public boolean sendAction(Jogada action) {
		try {
			proxy.enviaJogada(action);
			return true;
		} catch (NaoJogandoException e) {
			return false;
		}
	}

	// CASO DE USO DESCONECTAR
	public void disconnect() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			e.printStackTrace();
		}
	}


	public String getOtherPlayerName(int position) {
		return proxy.obterNomeAdversario(position);		
	}

}

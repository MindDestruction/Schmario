//package Client;

public class MainClient extends Client {

    public MainClient(String pServerIP, int pServerPort ){
        super(pServerIP, pServerPort);
    }

    public void processMessage(String pMessage){
        if (pMessage.substring(0, 3).equals("pos")) {
            Game.connection_player2Move(Integer.parseInt(pMessage.substring(5, 8)), Integer.parseInt(pMessage.substring(10, 13)));
        }
    }

    public void sendMessageToServer(){
        String test="Hallo Welt";
        if(this.isConnected()) {
            this.send(test);
        }else{
            System.out.println("Not connected to server");
        }
    }
}

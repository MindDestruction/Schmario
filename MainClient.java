//package Client;

public class MainClient extends Client {

    public MainClient(String pServerIP, int pServerPort ){
        super(pServerIP, pServerPort);
    }

    public void processMessage(String pMessage){
        System.out.println(pMessage);
        System.out.println(pMessage.substring(0, 2));
        if (pMessage.substring(0, 2).equals("pos")) {
            Game.connection_player2Move(Integer.parseInt("" + pMessage.charAt(5)), Integer.parseInt("" + pMessage.charAt(8)));
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

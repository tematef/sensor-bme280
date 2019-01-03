import core.PortListener;

public class Application {

    public static void main(String[] args) {
        PortListener portListener = new PortListener();
        portListener.listen();
    }
}

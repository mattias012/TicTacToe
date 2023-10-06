import java.util.Random;

public class Computer extends Player {

    private final Random random = new Random();

    public Computer(String playerName, String playerType) {
        super(playerName, playerType);
    }
}

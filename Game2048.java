import java.util.Scanner;

public class Game2048 {

    public static void main(String[] args) {
        GameModel model = new GameModel(4); // Create a 4x4 game board
        model.init();                       // Initialize the board
        show(model);                        // Display the initial board

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Use W/A/S/D to move Up/Left/Down/Right, R to restart, Q to quit:");

            String input = scanner.nextLine().toUpperCase();
            switch (input) {
                case "W":
                    model.move(Direction.UP);
                    break;
                case "A":
                    model.move(Direction.LEFT);
                    break;
                case "S":
                    model.move(Direction.DOWN);
                    break;
                case "D":
                    model.move(Direction.RIGHT);
                    break;
                case "R":
                    model.init();
                    break;
                case "Q":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid input. Please use W/A/S/D, R, or Q.");
            }

            show(model); // Display the board after the move
        }
    }

    // Method to display the game board
    private static void show(GameModel model) {
        for (int x = 0; x < model.getSize(); x++) {
            for (int y = 0; y < model.getSize(); y++) {
                int number = model.getBoardCell(x, y);
                System.out.print(number == 0 ? ".    " : String.format("%-5d", number));
            }
            System.out.println();
        }
        System.out.println();
    }
}

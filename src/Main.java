import view.CLIView;

/**
 * Main class to launch the Pizza Ordering System.
 */
public class Main {
    public static void main(String[] args) {
        CLIView view = new CLIView();
        view.showMenu();
    }
}
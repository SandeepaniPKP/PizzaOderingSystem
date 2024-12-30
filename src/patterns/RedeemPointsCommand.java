package patterns;

import controllers.UserController;
import models.User;

public class RedeemPointsCommand implements Command {
    private UserController userController;
    private String userName;
    private int points;

    public RedeemPointsCommand(UserController userController, String userName, int points) {
        this.userController = userController;
        this.userName = userName;
        this.points = points;
    }

    @Override
    public void execute() {
        User user = userController.getUser(userName);
        if (user != null) {
            ((User) user).redeemPoints(points);
            System.out.println("Redeemed " + points + " points for user: " + userName);
        } else {
            System.out.println("User not found.");
        }
    }
}

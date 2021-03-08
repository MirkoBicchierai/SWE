package agentManager;

import java.util.Scanner;

public class LoginMenu implements Menu{

    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(User activeUser) {
        Scanner in = new Scanner(System.in);
        Scanner inLog = new Scanner(System.in);
        String name;
        String psw;
        boolean quit = false;
        int menuItem;
        do {
            System.out.println("agentManager.Menu option:");
            System.out.println("1. Login");
            System.out.println("2. Debug");
            System.out.println("0. Quit");
            System.out.print("Choose menu item: ");
            try {
                menuItem = Integer.parseInt(in.next());
            }catch (Exception e){
                menuItem = -1;
            }
            switch (menuItem) {

                case 1:
                    while (activeUser == null) {

                        System.out.println("Inserire Nome Utente:");
                        name = inLog.nextLine();
                        System.out.println("Inserire Password:");
                        psw = inLog.nextLine();
                        activeUser = Program.getInstance().login(name, psw);

                    }
                    quit = true;
                    break;

                case 2:
                    quit = true;
                    Program.getInstance().debug();
                    break;

                case 0:
                    quit = true;
                    Program.getInstance().close();
                    break;

                default:
                    System.err.println("Invalid choice.");
            }
        } while (!quit);
    }
}

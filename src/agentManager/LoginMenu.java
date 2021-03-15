package agentManager;

import java.util.Scanner;

public final class LoginMenu implements Menu{

    @Override
    public void showMenu() {
        Scanner in = new Scanner(System.in);
        Scanner inLog = new Scanner(System.in);
        boolean quit = false;
        int menuItem;

        do {
            System.out.println("Menu option:");
            System.out.println("1. Login");
            System.out.println("0. Quit");
            System.out.print("Choose menu item: ");
            try {
                menuItem = Integer.parseInt(in.next());
            }catch (Exception e){
                menuItem = -1;
            }

            System.out.println(menuItem);
            switch (menuItem) {

                case 1:
                    while (Program.getInstance().getActiveUser() == null) {

                        System.out.println("Insert Username:");
                        String name = inLog.nextLine();
                        System.out.println("Insert Password:");
                        String psw = inLog.nextLine();
                        Program.getInstance().login(name, psw);

                    }
                    quit = true;
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

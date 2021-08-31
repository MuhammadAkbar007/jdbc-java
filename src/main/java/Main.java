import util.DbUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        for (; ; ) {

            DbUtil.mainMenu();

            Scanner scanner = new Scanner(System.in);
            int opt = scanner.nextInt();
            switch (opt) {
                case 0:
                    System.exit(0);
                case 1:
                    DbUtil.regionMenu();
                    break;
                case 2:
                    DbUtil.districtMenu();
                    break;
                case 3:
                    DbUtil.productMenu();
                    break;
                case 4:
                    DbUtil.companyMenu();
                    break;
                case 5:
                    DbUtil.categoryMenu();
                    break;
                default:
                    System.out.println("\uD83D\uDEA7 Wrong input ! ❌");
                    break;
            }

        }

    }
}
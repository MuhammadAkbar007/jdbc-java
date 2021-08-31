package util;

import config.DbConfig;

import java.sql.*;
import java.util.Scanner;

public class DbUtil {

    public static Statement statement;

    //Region operations
    public static void addRegion() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type region name: ");
        String name = scanner.next();

        Connection connection = DbConfig.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{? = call add_region(?)}");
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.setString(1, name);
            callableStatement.execute();

            System.out.println(callableStatement.getString(2) + "☑️");

            connection.close();
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void editRegion() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type region name you want to edit: ");
        String oldName = scanner.next();
        System.out.print("Type new name: ");
        String newName = scanner.next();

        Connection connection = DbConfig.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{? = call edit_region(?, ?)}");
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.setString(1, oldName);
            callableStatement.setString(2, newName);
            callableStatement.execute();

            System.out.println(callableStatement.getString(3) + "☑️");
            connection.close();
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void getRegionList() {

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from region");

            for (int n = 1; resultSet.next(); ++n) {
                System.out.println(n + ". " + resultSet.getString("name"));
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteRegion() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type region name you want to delete: ");
        String name = scanner.next();

        Connection connection = DbConfig.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{? = call delete_region(?)}");
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.setString(1, name);
            callableStatement.execute();

            System.out.println(callableStatement.getString(2) + "☑️");

            connection.close();
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //District operations
    public static void addDistrict() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type district name: ");
        String name = scanner.next();
        System.out.print("Type region id of this district: ");
        int id = scanner.nextInt();

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into district(name, region_id) values('" + name + "', " + id + ")");
            System.out.println(name + " inserted to district table ! ☑️");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void editDistrict() {

        Scanner scanner = new Scanner(System.in);
        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.print("Type name of district you want to edit: ");
        String oldName = scanner.next();
        System.out.print("Type new name of district: ");
        String newName = scanner.next();
        try {
            statement.executeUpdate("update district set name = '" + newName + "' where name = " + "'" + oldName + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(oldName + " changed to " + newName + " ☑️");
    }

    public static void getDistrictList() {

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from district");

            for (int n = 1; resultSet.next(); ++n) {
                System.out.println(n + ". " + resultSet.getString("name"));
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteDistrict() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type district name you want to delete: ");
        String name = scanner.next();

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("delete from district where name = " + "'" + name + "'");
            System.out.println(name + " successfully deleted ! ☑️");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Product operations
    public static void addProduct() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type product name: ");
        String name = scanner.next();
        System.out.print("Type maker of this product: ");
        String maker = scanner.next();
        System.out.print("Type model of this product: ");
        String model = scanner.next();
        scanner = new Scanner(System.in);
        System.out.print("Type company id of this product: ");
        int company = scanner.nextInt();
        System.out.print("Type category id of this product: ");
        int category = scanner.nextInt();

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into product(maker, model, company_id, category_id, name)" +
                    "values('" + maker + "', '" + model + "', '" + company + "', '" + category + "', '" + name + "')");
            System.out.println(name + " inserted to product table ! ☑️");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void editProduct() {

        Scanner scanner = new Scanner(System.in);
        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.print("Type name of product you want to edit: ");
        String oldName = scanner.next();
        System.out.print("Type new name of product: ");
        String newName = scanner.next();
        try {
            statement.executeUpdate("update product set name = '" + newName + "' where name = " + "'" + oldName + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(oldName + " changed to " + newName + " ☑️");
    }

    public static void getProductList() {

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");

            System.out.println("№. name       || model || maker || company id || category id");
            for (int n = 1; resultSet.next(); ++n) {
                System.out.println(n + ". " + resultSet.getString("name") + " || " + resultSet.getString("model") + " || "
                        + resultSet.getString("maker") + " || " + resultSet.getString("company_id") + " || "
                        + resultSet.getString("category_id"));
            }
            System.out.println();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type product name you want to delete: ");
        String name = scanner.next();

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("delete from product where name = " + "'" + name + "'");
            System.out.println(name + " successfully deleted ! ☑️");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Company operations
    public static void addCompany() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type company name: ");
        String name = scanner.next();

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into company(name) values('" + name + "')");
            System.out.println(name + " inserted to company table ! ☑️");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void editCompany() {

        Scanner scanner = new Scanner(System.in);
        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.print("Type name of company you want to edit: ");
        String oldName = scanner.next();
        System.out.print("Type new name of company: ");
        String newName = scanner.next();
        try {
            statement.executeUpdate("update company set name = '" + newName + "' where name = " + "'" + oldName + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(oldName + " changed to " + newName + " ☑️");
    }

    public static void getCompanyList() {

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from company");

            for (int n = 1; resultSet.next(); ++n) {
                System.out.println(n + ". " + resultSet.getString("name"));
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteCompany() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type company name you want to delete: ");
        String name = scanner.next();

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("delete from company where name = " + "'" + name + "'");
            System.out.println(name + " successfully deleted ! ☑️");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Category operations
    public static void addCategory() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type category name: ");
        String name = scanner.next();

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into category(name) values('" + name + "')");
            System.out.println(name + " inserted to category table ! ☑️");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void editCategory() {

        Scanner scanner = new Scanner(System.in);
        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.print("Type name of category you want to edit: ");
        String oldName = scanner.next();
        System.out.print("Type new name of category: ");
        String newName = scanner.next();
        try {
            statement.executeUpdate("update category set name = '" + newName + "' where name = " + "'" + oldName + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(oldName + " changed to " + newName + " ☑️");
    }

    public static void getCategoryList() {

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from category");

            for (int n = 1; resultSet.next(); ++n) {
                System.out.println(n + ". " + resultSet.getString("name"));
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type category name you want to delete: ");
        String name = scanner.next();

        Connection connection = DbConfig.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("delete from category where name = " + "'" + name + "'");
            System.out.println(name + " successfully deleted ! ☑️");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //menus
    public static void mainMenu() {

        System.out.println("************ DataBase Via Java ************");
        System.out.println("1.Region table \uD83C\uDFDB");
        System.out.println("2.District table \uD83C\uDFD8");
        System.out.println("3.Product table \uD83D\uDD79");
        System.out.println("4.Company table \uD83C\uDFE2");
        System.out.println("5.Category table \uD83D\uDEA5");
        System.out.println("0.Exit \uD83D\uDC4E");
        System.out.print("✍️");
    }

    public static void regionMenu() {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("1.Add region ➕");
            System.out.println("2.Edit region \uD83D\uDCDD");
            System.out.println("3.Get region list \uD83D\uDCDC");
            System.out.println("4.Delete region \uD83D\uDDD1");
            System.out.println("5.Main menu \uD83D\uDC48");
            System.out.print("✍️");

            int opt2 = scanner.nextInt();
            switch (opt2) {
                case 1:
                    addRegion();
                    break;
                case 2:
                    editRegion();
                    break;
                case 3:
                    getRegionList();
                    break;
                case 4:
                    deleteRegion();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\uD83D\uDEA7 Wrong input ! ❌");
                    break;
            }

        }

    }

    public static void districtMenu() {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("1.Add district ➕");
            System.out.println("2.Edit district \uD83D\uDCDD");
            System.out.println("3.Get district list \uD83D\uDCDC");
            System.out.println("4.Delete district \uD83D\uDDD1");
            System.out.println("5.Main menu \uD83D\uDC48");
            System.out.print("✍️");

            int opt3 = scanner.nextInt();
            switch (opt3) {
                case 1:
                    addDistrict();
                    break;
                case 2:
                    editDistrict();
                    break;
                case 3:
                    getDistrictList();
                    break;
                case 4:
                    deleteDistrict();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\uD83D\uDEA7 Wrong input ! ❌");
                    break;
            }

        }

    }

    public static void productMenu() {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("1.Add product ➕");
            System.out.println("2.Edit product \uD83D\uDCDD");
            System.out.println("3.Get product list \uD83D\uDCDC");
            System.out.println("4.Delete product \uD83D\uDDD1");
            System.out.println("5.Main menu \uD83D\uDC48");
            System.out.print("✍️");

            int opt4 = scanner.nextInt();
            switch (opt4) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    getProductList();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\uD83D\uDEA7 Wrong input ! ❌");
            }

        }

    }

    public static void companyMenu() {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("1.Add company ➕");
            System.out.println("2.Edit company \uD83D\uDCDD");
            System.out.println("3.Get company list \uD83D\uDCDC");
            System.out.println("4.Delete company \uD83D\uDDD1");
            System.out.println("5.Main menu \uD83D\uDC48");
            System.out.print("✍️");

            int opt5 = scanner.nextInt();
            switch (opt5) {
                case 1:
                    addCompany();
                    break;
                case 2:
                    editCompany();
                    break;
                case 3:
                    getCompanyList();
                    break;
                case 4:
                    deleteCompany();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\uD83D\uDEA7 Wrong input ! ❌");
            }

        }

    }

    public static void categoryMenu() {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("1.Add category ➕");
            System.out.println("2.Edit category \uD83D\uDCDD");
            System.out.println("3.Get category list \uD83D\uDCDC");
            System.out.println("4.Delete category \uD83D\uDDD1");
            System.out.println("5.Main menu \uD83D\uDC48");
            System.out.print("✍️");

            int opt6 = scanner.nextInt();
            switch (opt6) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    editCategory();
                    break;
                case 3:
                    getCategoryList();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\uD83D\uDEA7 Wrong input ! ❌");
            }

        }

    }

}

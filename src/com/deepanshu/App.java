package com.deepanshu;

import java.sql.*;
import java.util.Scanner;

public class App {
    Connection con;
    Statement stml;

    static Scanner sc =new Scanner(System.in);
    App() throws SQLException,ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/oop_jdbc", "root", "QWErty@123");
        stml=con.createStatement();
    }
    void display() throws SQLException,Exception
    {

        ResultSet rs=stml.executeQuery("select * from students;");
        while(rs.next())
        {
            System.out.print(rs.getInt(1)+" ");
            System.out.print(rs.getString(2)+" ");
            System.out.println(rs.getInt(3));
        }
    }
    void insert() throws SQLException,Exception
    {
        System.out.println("Enter SAP -");
        int sap=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Name -");
        String name=sc.nextLine();
        System.out.println("Enter Percentage - ");
        int percentage=sc.nextInt();
        sc.nextLine();
        String query="insert into students values("+sap+",'"+name+"',"+percentage+");";
        stml.executeUpdate(query);
        System.out.println("Updated Successfully...");
    }
    static void mainmenu()
    {
        System.out.println("************ Main - Menu *************");
        System.out.println("1. Insert");
        System.out.println("2. Display");
        System.out.println("3. Delete");
        System.out.println("4. Update");
        System.out.println("5. Exit");
    }
    boolean choose(int choice) throws SQLException,Exception
    {
        switch(choice)
        {
            case 1:
                insert();
                break;
            case 2:
                display();
                break;
            case 3:
                delete();
                break;
            case 4:
                update();
                break;
            case 5:
                return false;
            default:
                System.out.println("Invalid Choice...Try Again....");
        }
        return true;
    }
    void update() throws SQLException,NumberFormatException
    {
        System.out.print("Enter SAP ID ->");
        int sap=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new Percentage ->");
        int per=sc.nextInt();
        sc.nextLine();
        stml.executeUpdate("update students set Percentage="+per+" where SAP_ID="+sap+";");
        System.out.println("Value Updated....");
    }
    void delete() throws Exception,SQLException
    {
        System.out.print("Enter SAP ID ->");
        int sap=sc.nextInt();
        sc.nextLine();
        stml.executeUpdate("delete from students where SAP_ID="+sap+";");
        System.out.println("deleted successfully...");
    }
    public static void main(String[] args) throws Exception {
        try{
            App obj=new App();
            while(true)
            {
                try{
                    System.out.println();
                    mainmenu();
                    System.out.print("Enter your Choice ->");
                    int choice=sc.nextInt();
                    sc.nextLine();
                    boolean check=obj.choose(choice);
                    if(!check)
                        return;
                    System.out.println("Press any key to continue....");
                    sc.nextLine();
                }
                catch(SQLException e)
                {
                    System.out.println(e.getMessage());
                    break;
                }
                catch(Exception e)
                {
                    System.out.print("Technical fault occured -> ");
                    System.out.println(e.getMessage());
                }
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("Connectivity problem with database.....");
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println("Connectivity Problem with Mysql......");
        }
        finally
        {
            System.out.println("sorry for incovinence.....");
        }
    }
}

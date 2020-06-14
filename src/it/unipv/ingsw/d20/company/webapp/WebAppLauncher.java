package it.unipv.ingsw.d20.company.webapp;

public class WebAppLauncher {
    public static void main(String[] argv) throws Exception {
       Operators.add("Admin", "admin", "admin", "Remote Operator");
       System.out.println(Operators.get("admin").getUsername());
       System.out.println("admin registrato");
       new ApplicationServer(8080, new WelcomeServlet()).start();
    }
    
}

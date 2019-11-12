public class CreateDataBases {

    public static void main(String[] args) {
        System.out.println("Hallo und herzlich willkommen - Erstellen der Datenbanken");
        DBConnect con1 = new DBConnect("w0136ee0.kasserver.com", "d03037fa", "d03037fa", "fpcQdPhv5v4UoQ6H");
        con1.connectDB();
        CustomerDAO cuDAO = new CustomerDAO(con1.getConnection());
        //cuDAO.createTableCustomer();
        BookDAO boDAO = new BookDAO(con1.getConnection());
        //boDAO.createTableBook();

    }
}

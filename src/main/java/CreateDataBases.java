public class CreateDataBases {

    public static void main(String[] args) {
        System.out.println("Hallo und herzlich willkommen - Erstellen der Datenbanken");
        DBConnect con1 = new DBConnect("w0136ee0.kasserver.com", "d03037fa", "d03037fa", "fpcQdPhv5v4UoQ6H");
        con1.connectDB();
        CustomerDAO cuDAO = new CustomerDAO(con1.getConnection());
        //cuDAO.createTableCustomer();
        BookDAO boDAO = new BookDAO(con1.getConnection());
        //boDAO.createTableBook();
        AuthorDAO auDAO = new AuthorDAO(con1.getConnection());
        //auDAO.createTableAuthor();
        CategoryDAO caDAO = new CategoryDAO(con1.getConnection());
        //caDAO.createTablCategory();
        LoanedDAO loDAO = new LoanedDAO(con1.getConnection());
        //loDAO.createTableLoaned();
//        auDAO.createRecordAuthor("Johann Wolfgang", "von Goethe", 1749);
//        auDAO.createRecordAuthor("Friedrich", "Schiller", 1759);
//        auDAO.createRecordAuthor("Michael", "Amon", 1954);
//        auDAO.createRecordAuthor("Jeff", "Kinney",1971 );
//        auDAO.createRecordAuthor("Doris", "Chevron", 1951);
//        auDAO.createRecordAuthor("Madeleine", "Mueller", 1968);
//        auDAO.createRecordAuthor("Fred", "Vargas", 1959);
        con1.closeDB();



    }
}

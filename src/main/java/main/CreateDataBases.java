package main;

import daos.*;
import entities.BookAuthorCategory;

import java.time.LocalDateTime;

public class CreateDataBases {

    public static void main(String[] args) {
        System.out.println("Hallo und herzlich willkommen - Erstellen der Datenbanken");
        DBConnector dbConnector;
        dbConnector = DBConnector.getInstance();
        dbConnector.connect("w0136ee0.kasserver.com", "d03037fa", "d03037fa", "fpcQdPhv5v4UoQ6H");

        CustomerDAO cuDAO = new CustomerDAO();
        //cuDAO.createTableCustomer();
        BookDAO boDAO = new BookDAO();
        //boDAO.createTableBook();
        AuthorDAO auDAO = new AuthorDAO();
        //auDAO.createTableAuthor();
        CategoryDAO caDAO = new CategoryDAO();
        //caDAO.createTablCategory();
        LoanedDAO loDAO = new LoanedDAO();
        //loDAO.createTableLoaned();
        BACDAO bacDAO = new BACDAO();
        //bacDAO.createTableBAC();

//        auDAO.createRecordAuthor("Johann Wolfgang", "von Goethe", 1749); //1
//        auDAO.createRecordAuthor("Friedrich", "Schiller", 1759);         //2
//        auDAO.createRecordAuthor("Michael", "Amon", 1954);               //3
//        auDAO.createRecordAuthor("Jeff", "Kinney",1971 );                //4
//        auDAO.createRecordAuthor("Doris", "Chevron", 1951);              //5
//        auDAO.createRecordAuthor("Madeleine", "Mueller", 1968);          //6
//        auDAO.createRecordAuthor("Fred", "Vargas", 1959);                //7
//        auDAO.createRecordAuthor("Debbi", "Harry", 1968);                //8
//        auDAO.createRecordAuthor("Alyse", "Wax", 1970);                  //9
//        auDAO.createRecordAuthor("Güldane", "Altekrüger", 1980);         //10

//        caDAO.createRecordCategory("Cookbook");                          //1
//        caDAO.createRecordCategory("Crime & Thriller");                  //2
//        caDAO.createRecordCategory("Romance");                           //3
//        caDAO.createRecordCategory("Autobiography");                     //4
//        caDAO.createRecordCategory("History");                           //5
//        caDAO.createRecordCategory("Graphic Novel");                     //6
//        caDAO.createRecordCategory("Classic Fantasy");                   //7
//        caDAO.createRecordCategory("Christmas");                         //8
//        caDAO.createRecordCategory("Children");                          //9
//        caDAO.createRecordCategory("Classic");                           //10

//        boDAO.createRecordBook("Gregs Tagebuch Band  1 Von Idioten umzingelt!", 4, 9, 9783833936586L,10, "Baumhaus", "2016", "2007", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  1 Von Idioten umzingelt!", 4, 9, 9783833936586L,10, "Baumhaus", "2016", "2007", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  2 Gibt`s Probleme?", 4, 9, 9783833936333L,10, "Baumhaus", "2008", "2008", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  2 Gibt`s Probleme?", 4, 9, 9783833936333L,10, "Baumhaus", "2008", "2008", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  3 Jetzt reicht`!s", 4, 9, 9783833936340L,10, "Baumhaus", "2009", "2009", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  3 Jetzt reicht`!s", 4, 9, 9783833936340L,10, "Baumhaus", "2009", "2009", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  4 Ich war`s nicht!", 4, 9, 9783833936357L,10, "Baumhaus", "2010", "2010", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  4 Ich war`s nicht!", 4, 9, 9783833936357L,10, "Baumhaus", "2010", "2010", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  5 Geht's noch!", 4, 9, 9783833936364L,10, "Baumhaus", "2011", "2011", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  5 Geht's noch!", 4, 9, 9783833936364L,10, "Baumhaus", "2011", "2011", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  6 Keine Panik!", 4, 9, 9783833936371L,10, "Baumhaus", "2011", "2011", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  6 Keine Panik!", 4, 9, 9783833936371L,10, "Baumhaus", "2011", "2011", 218, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  7 Dumm gelaufen!", 4, 9, 9783833910843L,10, "Baumhaus", "2012", "2012", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  7 Dumm gelaufen!", 4, 9, 9783833910843L,10, "Baumhaus", "2012", "2012", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  8 Echt übel!", 4, 9, 9783833936494L,10, "Baumhaus", "2013", "2013", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  8 Echt übel!", 4, 9, 9783833936494L,10, "Baumhaus", "2013", "2013", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  9 Böse Falle!", 4, 9, 9783833911017L,10, "Baumhaus", "2014", "2014",  224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band  9 Böse Falle!", 4, 9, 9783833911017L,10, "Baumhaus", "2014", "2014",  224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 10 So ein Mist!", 4, 9, 9783833936517L,10, "Baumhaus", "2015", "2015", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 10 So ein Mist!", 4, 9, 9783833936517L,10, "Baumhaus", "2015", "2015", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 11 Alles Käse!", 4, 9, 9783833936524L,10, "Baumhaus", "2016", "2016", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 11 Alles Käse!", 4, 9, 9783833936524L,10, "Baumhaus", "2016", "2016", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 12 Und Tschüss!", 4, 9, 9783833936562L,10, "Baumhaus", "2017", "2017", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 12 Und Tschüss!", 4, 9, 9783833936562L,10, "Baumhaus", "2017", "2017", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 13 Eiskalt erwischt!", 4, 9, 9783833936593L,10, "Baumhaus", "2018", "2018", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 13 Eiskalt erwischt!", 4, 9, 9783833936593L,10, "Baumhaus", "2018", "2018", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 14 Voll daneben!", 4, 9, 9783833906077L,10, "Baumhaus", "2019", "2019", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Gregs Tagebuch Band 14 Voll daneben!", 4, 9, 9783833906077L,10, "Baumhaus", "2019", "2019", 224, "deutsch", 9, 3   );
//        boDAO.createRecordBook("Die Wahlverwandtschaften", 1, 10, 9783866472297L, 0, "Anaconda Verlag", "2008", "1750", 320, "deutsch", 10, 2);
//        boDAO.createRecordBook("Die Leiden des jungen Werther", 1, 10, 9783868203813L, 0, "Nikol Verlagsgesellschaft", "2017", "1760", 160, "deutsch", 10, 2);
//        boDAO.createRecordBook("Kabale und Liebe", 2, 10,9783804419186L, 0, "C. Bange", "2017", "1755", 100, "deutsch", 10, 3 );
//        boDAO.createRecordBook("Face it",8, 4,  9783453271623L, 18, "Heyne", "2019", "2019", 432, "deutsch", 4, 1);
//        boDAO.createRecordBook("World of IT", 9, 2, 9781419740473L, 18, "Abrams & Chronicle Books", "2019", "2019", 224, "engllish", 2, 4);
//        boDAO.createRecordBook("Abnehmen mit Brot und Kuchen Teil 1", 10, 1, 9783000579141L, 0, "DplusA Verlag", "2018", "2015", 128, "deutsch", 1, 1);
//        boDAO.createRecordBook("Abnehmen mit Brot und Kuchen Teil 1", 10, 1, 9783000579141L, 0, "DplusA Verlag", "2018", "2015", 128, "deutsch", 1, 1);

//        cuDAO.createRecordCustomer("abcdef", "ulrike_klaus@gmx.at", "Ulrike", "Klaus", LocalDateTime.of(1973, 2, 27, 15, 32), "Rebhalde", "51", 6832, "Röthis", 4568123587459658L, 547, 2020, 12);
//        cuDAO.createRecordCustomer("abcdeg", "fabian.sinz@gmx.at", "Fabian", "Sinz", LocalDateTime.of(1985, 3, 3, 3, 30), "Dorfweg", "7", 6850, "Dornbirn", 9547853214687411L, 242, 2020, 12);
//        cuDAO.createRecordCustomer("abcdeh", "emoeke.sowieso@huch.ch", "Emöke", "Sowieso", LocalDateTime.of(1995, 4, 4, 4, 40), "Kirchstraße", "4", 4444, "Bad Ragaz", 4563214569874123L, 363, 2020, 4);
//        cuDAO.createRecordCustomer("abcedi", "max@muster.de", "Max", "Muster", LocalDateTime.of(2010, 10, 10, 10, 10), "Waldweg", "27", 6900, "Bregenz", 0, 0, 0, 0);
//        cuDAO.createRecordCustomer("abcdej", "netterNachbar@gmx.at", "Heinz", "Nachbar", LocalDateTime.of(1970, 7, 10, 0, 0), "Rebhalde", "51",  6832, "Röthis", 0, 0, 0, 0);

//        loDAO.createRecordLoanedWithReturn(1, 1, LocalDateTime.of(2019, 10, 10, 14, 0), LocalDateTime.of(2019, 10, 20, 15, 0));
//        loDAO.createRecordLoanedWithReturn(1, 3, LocalDateTime.of(2019, 10, 10, 14, 0), LocalDateTime.of(2019, 10, 20, 15, 0));
//        loDAO.createRecordLoanedWithReturn(1, 5, LocalDateTime.of(2019, 10, 10, 14, 0), LocalDateTime.of(2019, 10, 20, 15, 0));
//        loDAO.createRecordLoanedWithReturn(1, 7, LocalDateTime.of(2019, 10, 12, 14, 0), LocalDateTime.of(2019, 10, 22, 15, 0));
//        loDAO.createRecordLoanedWithReturn(1, 9, LocalDateTime.of(2019, 10, 12, 14, 0), LocalDateTime.of(2019, 10, 22, 15, 0));
//        loDAO.createRecordLoanedWithReturn(1, 11, LocalDateTime.of(2019, 10, 12, 14, 0), LocalDateTime.of(2019, 10, 22, 15, 0));
//        loDAO.createRecordLoanedWithReturn(1, 13, LocalDateTime.of(2019, 10, 12, 14, 0), LocalDateTime.of(2019, 10, 22, 15, 0));
//        loDAO.createRecordLoanedWithReturn(1, 15, LocalDateTime.of(2019, 10, 12, 14, 0), LocalDateTime.of(2019, 10, 22, 15, 0));
//        loDAO.createRecordLoanedWithReturn(2, 29, LocalDateTime.of(2019, 11, 10, 15, 11), LocalDateTime.of(2019, 11, 12, 15, 11));
//        loDAO.createRecordLoanedWithoutReturn(3, 29, LocalDateTime.of(2019, 11, 12, 15, 15));
//        loDAO.createRecordLoanedWithoutReturn(3, 30, LocalDateTime.of(2019, 11, 12, 15, 15));
//        loDAO.createRecordLoanedWithoutReturn(1, 17, LocalDateTime.of(2019, 11, 12, 15, 15));
//        loDAO.createRecordLoanedWithoutReturn(4, 27, LocalDateTime.now());
//        loDAO.createRecordLoanedWithExtraTime(5, 6, LocalDateTime.of(2019, 11, 5, 15, 11), LocalDateTime.of(2019, 11, 13, 8, 26));
//        loDAO.createRecordLoanedWithExtraTime(2, 8, LocalDateTime.of(2019, 11, 9, 15, 11), LocalDateTime.of(2019, 11, 13, 8, 26));
/*
        for (BookAuthorCategory bac : boDAO.createLinkedListBAC("select * from ((book inner join author on book.idAuthor=author.idAuthor) inner join category on book.idCategory = category.idCategory) order by book.idBook ASC")){
            bacDAO.createRecordBAC(bac.getTitle(), bac.getIdAuthor(), bac.getFirstName(), bac.getLastName(), bac.getBirthYear(), bac.getIdCategory(), bac.getDescription(), bac.getIsbn(), bac.getFsk(), bac.getPublisher(), bac.getEdition(), bac.getFirstEdition(),
                    bac.getAmountPages(), bac.getLanguage(), bac.getIdRow(), bac.getIdColumn());
        }
*/
         dbConnector.close();
    }
}

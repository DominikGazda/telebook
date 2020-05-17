package pl.gazda.app;

import pl.gazda.model.Contact;
import pl.gazda.model.Database;
import pl.gazda.model.TeleBook;
import java.util.Map;

public class TeleBookController {
    private TeleBook book = new TeleBook();
    private Database database = new Database();

    public Map<String, Contact> createDatabase(){
        database.connectToDatabase();
        Database.createContactFromDatabase();
        return book.getTelebook();
    }


}

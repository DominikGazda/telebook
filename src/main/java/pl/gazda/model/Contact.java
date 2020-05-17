package pl.gazda.model;

import java.util.Objects;

public class Contact {

    private int id;
    private String number;
    private String name;


    public Contact(int id, String name,String number){
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return number == contact.number &&
                Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    public String toString(){
        return id+" "+name+" "+number;
    }
}

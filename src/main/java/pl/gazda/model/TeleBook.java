package pl.gazda.model;

import java.util.*;
import java.util.stream.Collectors;

public class TeleBook {

    private static Map<String, Contact> telebook = new HashMap<>();


    public Map<String, Contact> getTelebook() {
        return telebook;
    }

    public static void addNewContactToMap(Contact contact) {
        if(contact.getName() == null || contact.getNumber() == null ){
            throw new NullPointerException("Wartość nie moze być null");
        }
        if(contact.getName().isEmpty() || contact.getNumber().isEmpty())
            throw new IllegalArgumentException("Nie podałeś wartości");

        telebook.put(contact.getName(), contact);
    }

    public static void deleteContact(String name) {
        if(telebook.remove(name) == null){
            throw new IllegalArgumentException("Nie ma takiego rekordu");
        }
        else
            System.out.println("Pomyślnie usunięto");

    }

    public static List<Contact> getContactByNameOrNumber(String value) {
        List<Contact> list = telebook.values().stream()
                .filter(p -> p.getName().contains(value) || p.getNumber().contains(value))
                .collect(Collectors.toList());
        return list;
    }

    public static void showList(List<Contact> list) {
        System.out.println("Znaleziono następujące rekordy: ");
        list.forEach(System.out::println);
    }

}



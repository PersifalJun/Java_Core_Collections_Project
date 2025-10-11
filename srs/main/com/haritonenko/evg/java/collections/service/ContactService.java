package collections.service;
import collections.exceptions.NoSuchElementsException;
import collections.model.Contact;
import collections.repository.ContactRepository;
import lombok.*;
import java.util.Scanner;

@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private Scanner scanner = new Scanner(System.in);

    public Contact create(){
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Введите телефон: ");
        String phone = scanner.nextLine();
        System.out.println("Введите email: ");
        String email = scanner.nextLine();
        System.out.println("Введите группу: ");
        String group = scanner.nextLine();

        return Contact.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .group(group)
                .build();

    }
    public void add(Contact contact){
        contactRepository.addContacts(contact);
    }

    public void remove(String name){
        try{
            contactRepository.removeContactFromAllCollections(name);
        }
        catch(NullPointerException ex){
            System.out.println("Удаляемый объект null!");
        }
    }
    public void view(){
        try{
            contactRepository.viewingAllContactsFromList();
        }
        catch(NoSuchElementsException ex){
            System.out.println("Список контактов пуст!");
        }
    }
    public String searchByName(String name){
        String foundName = null;
        try{
            foundName =  contactRepository.searchContactByName(name);
        }
        catch(NoSuchElementsException ex){
            System.out.println("Список контактов пуст!");
        }
        return foundName;
    }
    public void searchByGroup(){
        try{
            contactRepository.showContactListByGroup();
        }
         catch(NoSuchElementsException ex){
            System.out.println("Список контактов пуст!");
        }
    }
}

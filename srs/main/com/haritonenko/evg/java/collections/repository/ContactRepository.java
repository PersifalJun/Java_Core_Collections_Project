package collections.repository;
import collections.model.Contact;
import java.util.*;


public class ContactRepository{
    private Set<Contact> contactsSet;
    private List<Contact> contactsList;
    private Map<String,Contact> contactsMap;
    private Iterator<Contact> listIterator;
    private Iterator<Contact> setIterator;
    private Map<String,List<Contact>> contactsGroupMap;
    private final Scanner scanner;
    private final String nothingWasFound = "Ничего не найдено!";


    public ContactRepository(){
        this.contactsSet = new HashSet<>();
        this.contactsList = new ArrayList<>();
        this.contactsMap = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.contactsGroupMap = new HashMap<>();
    }



    public boolean containsContact(Contact contact){return contactsSet.contains(contact);}

    public void addContacts(Contact contact){
        if(!containsContact(contact)){
            contactsSet.add(contact);
            contactsList.add(contact);
            contactsMap.put(contact.getEmail(),contact);
            System.out.println("Контакт добавлен!");
        }
        else{
            System.out.println("Вы добавляете дубликат!");
        }

    }



    public void removeContactFromAllCollections(String name){
        boolean deleted = false;
        setIterator = contactsSet.iterator();
        listIterator = contactsList.iterator();
        while(listIterator.hasNext()){
            if(listIterator.next().getName().equals(name)){
                deleted = true;
                listIterator.remove();
            }
        }
        while(setIterator.hasNext()){
            Contact contact = setIterator.next();
            if(contact.getName().equals(name)){
                setIterator.remove();
                contactsMap.remove(contact.getEmail());
                System.out.println("Контакт удален!");
            }
        }
        if(!deleted){
            System.out.println("Нет контактов для удаления!");
        }

    }

    public String searchContactByName(String name){
        setIterator = contactsSet.iterator();
        while(setIterator.hasNext()){
            Contact contact = setIterator.next();
            if(contact.getName().equals(name)) {
                System.out.println(contact.getName());
                return contact.getName();

            }

        }
        System.out.println(nothingWasFound);
        return null;

    }


    public void viewingAllContactsFromList(){
        listIterator = contactsList.iterator();
        System.out.println("Все контакты: ");
        if(!(contactsList.isEmpty())){
            while(listIterator.hasNext()){
                System.out.println(listIterator.next());
                System.out.println("-".repeat(50));
            }
        }
        else{
            System.out.println("В списке нет контактов!");
        }
    }



    public Map<String,List<Contact>> addContactsInGroupMap(String neededGroup){
        List<Contact> listByGroup = new ArrayList<>();
        listIterator = contactsList.listIterator();
        while(listIterator.hasNext()){
            Contact contact = listIterator.next();
            if(contactsMap.get(contact.getEmail()).getGroup().equals(neededGroup)){
                listByGroup.add(contact);
            }
            contactsGroupMap.put(contact.getGroup(),listByGroup);
        }
        return contactsGroupMap;
    }



    public void showContactListByGroup() {
        boolean found = false;
        System.out.println("Введите группу для просмотра списка контактов: ");
        String neededGroup = scanner.nextLine();
        addContactsInGroupMap(neededGroup);
        for (Map.Entry<String, List<Contact>> entry : contactsGroupMap.entrySet()) {
            listIterator = entry.getValue().iterator();
            while (listIterator.hasNext()) {
                Contact contact = listIterator.next();
                if (contact.getGroup().equals(neededGroup)) {
                    System.out.println(contact);
                    found = true;
                    System.out.println("-".repeat(50));
                }
            }
        }
        if(!found){
            System.out.println(nothingWasFound);
        }
    }

}

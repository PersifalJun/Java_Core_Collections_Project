
import collections.model.Contact;
import collections.repository.ContactRepository;
import collections.service.ContactService;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ContactRepository contactRepository = new ContactRepository();
    private static ContactService contactService = new ContactService(contactRepository);
    private static Contact contact;


    public static void main(String[] args) {

        System.out.println("Выберите действие:" +"\n1-Добавить контакт"+
                "\n2-Удалить контакт"+"\n3-Посмотреть все контакты"+
                "\n4-Найти контакт по имени" +"\n5-Посмотреть контакты по группе" + "\n0-Выход");


        boolean running = true;
        Integer choice = null;

        while(running){
            try{
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch(NullPointerException ex){
                ex.printStackTrace();
            }

            switch(choice){

                case 0 :
                    System.out.println("Выход");
                    running = false;
                    break;

                case 1 :
                    contact = contactService.create();
                    contactService.add(contact);
                    break;

                case 2 :
                    System.out.println("Введите имя контакта для удаления: ");
                    String nameToDelete = scanner.nextLine();
                    contactService.remove(nameToDelete);
                    break;


                case 3 :
                    contactService.view();
                    break;


                case 4 :
                    System.out.println("Введите имя контакта для просмотра: ");
                    String nameToWatch = scanner.nextLine();
                    System.out.println("Данный контакт: "+ contactService.searchByName(nameToWatch));
                    break;


                case 5 :
                    contactService.searchByGroup();
                    break;
                default :
                    System.out.println("Вы ввели несуществующий пункт меню!");
            }

        }

    }
}

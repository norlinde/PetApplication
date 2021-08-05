import javax.swing.*;

import owner.OwnerController;
import pet.PetController;

public class Menu {
  PetController petController = new PetController();
  OwnerController ownerController = new OwnerController();
  public void start(){
    String userChoice = JOptionPane.showInputDialog(null,
        String.format("Welcome to pet manager"
            + "\n1. View All Pets" + "    2. Add Pet"
            + "\n3. View Pet Profile" + "    4. Remove pet from Owner"
            + "\n5. Assign Pet to Owner" + "    6. View All Owners"
            + "\n7. View Owner Profile" + "    8. Add Owner"
            + "\n9. Remove Owner" + "    10. Add Pet Type"
            + "\n11. Remove Pet Type" + "    12. View All Pet Types"
            + "\n13. Exit" ));

    switch (userChoice){
      case "1":
        petController.showAllPets();
        break;
      case "2":
        petController.addPet();
        break;
        case "3":
        petController.viewPet();
        break;
      case "4":
        petController.removePetFromOwner();
        break;
      case "5":
        petController.assignPetToOwner();
        break;
        case "6":
          ownerController.showAllOwners();
        break;
      case "7":
        ownerController.viewOwner();
        break;
        case "8":
        ownerController.createOwner();
        break;
      case "9":
        ownerController.removeOwner();
        break;
      case "10":
        petController.createPetType();
        break;
      case "11":
        petController.removePetType();
        break;
      case "12":
        petController.viewAllPetTypes();
        break;
      case "13":
        System.exit(0);
        break;
    }

    start();
  }
  public void start2() {

    String[] menu = {"View All Pets", "Add Pet", "View Pet Profile", "Remove Pet from Owner",
            "Assign Pet to Owner", "View All Owners", "View Owner Profile", "Add Owner",
            "Remove Owner", "Add Pet Type", "Remove Pet Type", "View All Pet Types", "Exit"};
    ImageIcon pet = new ImageIcon("pets.jfif");
    String chooseOption = (String) JOptionPane.showInputDialog(
            null,
            "Choose option:",
            "Welcome to the Pet Application!",
            JOptionPane.INFORMATION_MESSAGE,
            pet,
            menu,
            menu[0]
    );

    if (chooseOption == menu[0]) {
      petController.showAllPets();
    } else if (chooseOption == menu[1]) {
      petController.showAllPets();
    } else if (chooseOption == menu[2]) {
     petController.addPet();
    } else if (chooseOption == menu[3]) {
      petController.viewPet();
    } else if (chooseOption == menu[4]) {
      petController.removePetFromOwner();
    } else if (chooseOption == menu[5]) {
      petController.assignPetToOwner();
    } else if (chooseOption == menu[6]) {
      ownerController.showAllOwners();
    } else if (chooseOption == menu[7]) {
      ownerController.viewOwner();
    } else if (chooseOption == menu[8]) {
      ownerController.createOwner();
    } else if (chooseOption == menu[9]) {
      ownerController.removeOwner();
    } else if (chooseOption == menu[10]) {
      petController.createPetType();
    } else if (chooseOption == menu[11]) {
      petController.removePetType();
    } else if (chooseOption == menu[12]) {
      petController.viewAllPetTypes();
    } else {
      System.exit(0);
    }
    start2();
  }
}

package pet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import meal.MealDBService;
import meal.PetMeal;
import toys.PetToy;
import toys.ToyDBService;

public class PetController {
  PetDBService petDBService = new PetDBService();
  MealDBService mealDBService = new MealDBService();
  ToyDBService toyDBService = new ToyDBService();
  Pet pet = new Pet();
  private String petListTitle = "id \t name \t age \t type \t owner\n";
  private String petTypesListTitle = "id \t type\n";

  public void showAllPets() {
    ArrayList<Pet> pets = new ArrayList<>();
    try {
      pets = petDBService.getAllPets();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    String message = petListTitle +
            pets.stream()
                    .map(Pet::toString)
                    .collect(Collectors.joining("\n"));
    JOptionPane.showMessageDialog(null, message);
  }

  public void viewPet() {
    String petId = JOptionPane.showInputDialog(null, "Enter pet Id");
    Pet pet = null;
    ArrayList<PetMeal> petMeals = new ArrayList<PetMeal>();
    ArrayList<PetToy> petToys = new ArrayList<PetToy>();

    try {
      pet = petDBService.getPet(Integer.parseInt(petId));
      petToys = toyDBService.findToysByPetType(pet.getType());
      petMeals = mealDBService.findMealsByPetType(pet.getType());
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    String message = createPetProfileUI(pet, petMeals, petToys);
    JOptionPane.showMessageDialog(null, message);
  }

  private String createPetProfileUI(Pet pet, ArrayList<PetMeal> petMeals, ArrayList<PetToy> petToys) {
    String petInfo = String.format("\n%s's Profile" + "\nSpecial Id: %o" + "\nAge: %o" + "\nType: %s",
            pet.getName(), pet.getId(), pet.getAge(), pet.getType().getValue());

    String ownerInfo = "\n\nOwner Information" + "\nname: " + pet.getOwner().getName();

    StringBuilder foodInfo = new StringBuilder("\n\nFood Information\n");
    for (PetMeal meal : petMeals) {
      foodInfo.append(meal.getId()).append(" \t ")
              .append(meal.getName()).append(" \t ")
              .append(meal.getWeight()).append("\n");
    }

    StringBuilder toyInfo = new StringBuilder("\n\nToy Information\n");
    for (PetToy toy : petToys) {
      toyInfo.append(toy.getId()).append(" \t ")
              .append(toy.getMaterial()).append(" \t ")
              .append(toy.getPrice()).append("\n");
    }
    return petInfo + ownerInfo + foodInfo + toyInfo;
  }

  public void removePetFromOwner() {
    try {
      int petId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet Id"));
      petDBService.removeOwnerFromPet(petId);
      JOptionPane.showMessageDialog(null, "Sad to see you go :(");
    } catch (Exception exception) {
      exception.printStackTrace();
      JOptionPane.showMessageDialog(null,
              "Error occured while removing pet");
    }
  }

  public void assignPetToOwner() {
    try {
      int petId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet Id"));
      int ownerId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Owner Id"));
      petDBService.addOwnerToPet(ownerId, petId);
      JOptionPane.showMessageDialog(null, "Pet adoption complete :)");
    } catch (Exception exception) {
      exception.printStackTrace();
      JOptionPane.showMessageDialog(null,
              "Error occurred while removing pet");
    }

  }

  public void createPetType() {
    try {
      PetType petType = collectPetTypeInfo();
      petDBService.add(petType);
      JOptionPane.showMessageDialog(null, petType.getValue() + " Added successfully");
    } catch (Exception exception) {
      exception.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error occurred while adding owner");
    }
  }

  private PetType collectPetTypeInfo() {
    String value = JOptionPane.showInputDialog(null, "Enter Pet Type Name");
    return new PetType(value);
  }

  public void removePetType() {
    try {
      int petTypeId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Pet Type Id"));
      petDBService.delete(petTypeId);
      JOptionPane.showMessageDialog(null, " Pet Type deleted successfully");
    } catch (Exception exception) {
      exception.printStackTrace();
      JOptionPane.showMessageDialog(null,
              "Error occurred while deleting owner. Make sure they don't have any pets assigned and owner exist on the system");
    }

  }

  public Object viewAllPetTypes() {
    ArrayList<PetType> petTypes = new ArrayList<>();
    try {
      petTypes = petDBService.getAllPetTypes();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    String message = petTypesListTitle +
            petTypes.stream()
                    .map(PetType::toString)
                    .collect(Collectors.joining("\n"));
    JOptionPane.showMessageDialog(null, message);
    return null;
  }


  public void addPet() {
    try {
      String name = JOptionPane.showInputDialog(null, "Pet's name: ");
      int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Pet's age: "));
      int petType = Integer.parseInt(JOptionPane.showInputDialog("Enter Pet's type ID:", viewAllPetTypes()));
      petDBService.addPet(name, age, petType);
      JOptionPane.showMessageDialog
              (null, "New pet by name " + "| "+ name+ " |"+ " has been added");


    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error. New pet was not added");
    }
  }

}



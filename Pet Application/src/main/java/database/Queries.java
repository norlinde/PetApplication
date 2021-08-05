package database;

import owner.Owner;
import pet.PetType;

public class Queries {
  public static final String SELECT_PETS = "SELECT pets.id, pets.name as pet_name,  pets.age as pet_age, owners.id as owner_id, owners.name as owner_name,pettypes.id as pet_type_id, pettypes.type as pet_type_value FROM owners RIGHT JOIN pets ON pets.ownerId = owners.id INNER JOIN pettypes ON pets.pet_type_id = pettypes.id ORDER BY id asc;";
  public static final String SELECT_PETS_BY_OWNER_ID = "SELECT pets.id, pets.name as pet_name,  pets.age as pet_age, pettypes.id as pet_type_id, pettypes.type as pet_type_value FROM owners RIGHT JOIN pets ON pets.ownerId = owners.id INNER JOIN pettypes ON pets.pet_type_id = pettypes.id WHERE pets.ownerId = ? ORDER BY id asc;";
  public static final String SELECT_PET = "SELECT pets.id, pets.name as pet_name,  pets.age as pet_age, owners.id as owner_id, owners.name as owner_name, pettypes.id as pet_type_id, pettypes.type as pet_type_value FROM pets LEFT JOIN owners ON pets.ownerId = owners.id INNER JOIN pettypes ON pets.pet_type_id = pettypes.id \n where pets.id = ? LIMIT 1;";
  public static final String SELECT_MEALS_BY_FIELD_NAME = "SELECT meals.id, meals.name, meals.weight, \n"
          + "pettypes.id as pet_type_id, pettypes.type as pet_type_value \n"
          + "FROM meals\n"
          + "INNER JOIN pettypes ON meals.pet_type_id = pettypes.id\n"
          + "WHERE meals.pet_type_id = ?;";
  public static final String SELECT_TOYS_BY_FIELD_NAME = "SELECT toys.id, toys.material, toys.price, pettypes.id as pet_type_id, pettypes.type as pet_type_value FROM toys INNER JOIN pettypes ON toys.pet_type_id = pettypes.id WHERE toys.pet_type_id = ?;";
  public static final String INSERT_OWNER = "INSERT INTO owners(name) VALUES(?);";
  public static final String DELETE_OWNER = "DELETE FROM owners WHERE id = ?";
  public static final String SELECT_OWNERS = "SELECT * FROM OWNERS";
  public static final String SELECT_OWNER = "SELECT * FROM OWNERS WHERE id= ?";
  public static final String REMOVE_PET_OWNER = "UPDATE pets SET ownerId = null where id = ?";
  public static final String SET_PET_OWNER = "UPDATE pets SET ownerId = ? where id = ?";


  public static final String INSERT_PETTYPE = "INSERT INTO pettypes(type) VALUES(?);";
  public static final String DELETE_PETTYPE = "DELETE FROM pettypes WHERE id = ?";
  public static final String SELECT_PETTYPES = "SELECT * FROM pettypes";
  public static final String INSERT_PET = "INSERT INTO pets(name, pets.pet_type_id, age ) VALUES(?,?,? )";

}
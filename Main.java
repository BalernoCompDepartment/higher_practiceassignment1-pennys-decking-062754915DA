import java.io.*;
import java.util.Scanner;

class Deck {
double length;
double width;
double price;
String name;
}

class Main {
  public static void main(String[] args) {
  Deck [] arrayOfDecks = new Deck [6];
  
  arrayOfDecks = readFromFile();

  int choice = getValidChoice();

while (!(choice==4)) {
  if (choice == 1) { 
displayLowest(arrayOfDecks);
  }
if (choice == 2) {
displayDeckLength(arrayOfDecks);
}
if (choice == 3) {
displayDeckArea(arrayOfDecks);
}

  choice = getValidChoice();
  System.out.println("");
}

  System.exit(0);
  }

public static Deck [] readFromFile () {
 String nameOfFile = "Decks.csv";
 Scanner fileScanner = null;

  Deck [] arrayOfDecks = new Deck [6];

  int index = 0;

  try{
    fileScanner = new Scanner(new BufferedReader(new FileReader(nameOfFile)));
    fileScanner.useDelimiter("[\\n\\r,]+");
    while(fileScanner.hasNext()) {
      arrayOfDecks[index] = new Deck();
      arrayOfDecks[index].name = fileScanner.next();
      arrayOfDecks[index].length = fileScanner.nextDouble();
      arrayOfDecks[index].width = fileScanner.nextDouble();
      arrayOfDecks[index].price = fileScanner.nextDouble();

      index = index + 1;
    }

  } 
  catch (FileNotFoundException error) {
    System.out.println("Error, file not found.");
  }
  finally{
    if(fileScanner!=null) {
      fileScanner.close();
    }
  }
   return arrayOfDecks;
}

//here!
public static int getValidChoice(){
  int integer;
  integer = Keyboard.getInt("Enter 1 to find the cheapest garden deck. \n Enter 2 to display the garden decks over a certain length. \n Enter 3 to display the number of garden decks that are available over a certain area. \n Enter 4 to quit.");
  while(integer < 1 || integer > 4){
    System.out.println("Error, please select a number between 1 and 4.");
    integer = Keyboard.getInt("Enter 1 to find the cheapest garden deck. \n Enter 2 to display the garden decks over a certain length. \n Enter 3 to display the number of garden decks that are available over a certain area. \n Enter 4 to quit.");
  }
return integer;
}



public static void displayLowest (Deck [] list) {
  int lowestIndex = 0;
  for (int index = 1; index < list.length; index ++) {
    if (list[index].price < list[lowestIndex].price) {
      lowestIndex = index;
    }
  }
  System.out.println("The lowest priced deck is £" + list[lowestIndex].price + ", named " + list[lowestIndex].name + ".");
}

public static void displayDeckLength (Deck [] list) {
  double minDeckLength = Keyboard.getReal("Please enter the minimum deck length specified to the nearest decimal place.");
  String listOfNames = "";
  for (int index = 0; index < list.length; index ++) {
    if (list[index].length > minDeckLength) {
      listOfNames = listOfNames + list[index].name;
    }
    System.out.println("A deck with a length above " + minDeckLength + "m is: " + list[index].name + ".");
  }

}

public static void displayDeckArea (Deck [] list) {
  double minDeckArea = Keyboard.getReal("Please enter the minimum deck area specified to the nearest decimal place.");
  int counter = 0;

  for (int index = 0; index < list.length; index ++) {
    if (list[index].length * list[index].width > minDeckArea) {
      counter = counter + 1;
    }
    
  } 
System.out.println("There are " + counter + " decks with the area above " + minDeckArea + "m^2.");
}

}
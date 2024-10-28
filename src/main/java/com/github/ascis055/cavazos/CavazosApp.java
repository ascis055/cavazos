package com.github.ascis055.cavazos;

import java.util.*;
import org.json.simple.*;

public class CavazosApp {

  private static String[] commandArray;

  public static void main(String[] args) {
    String fileName;
    Scanner scan = new Scanner(System.in);
    Character command;

    if (args.length != 1) {
      System.out.println("Supply JSON file name as a command line argument");
      return;
    }

    fileName = args[0];

    // read coammands
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    commandArray = getCommandArray(commandJSONArray);

    // loop until user quits
    do {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);

      executeCommand(scan, command);
    } while (command != 'q');

    scan.close();
  }

  //
  // menu functions
  //
  private static void printMenuLine() {
    System.out.println(
      "----------------------------------------------------------"
    );
  }

  private static void printMenuCommand(Character command, String desc) {
    System.out.printf("%s\t%s\n", command, desc);
  }

  // print the menu
  public static void printMenu() {
    printMenuLine();
    System.out.println("General Cavazos Commander App");
    printMenuLine();

    printMenuCommand('i', "Issue a command");
    printMenuCommand('l', "List all of the commands");
    printMenuCommand('u', "Undo the last command that was issued");
    printMenuCommand('r', "Redo the last command that was issued");
    printMenuCommand('q', "Quit");

    printMenuLine();
  }

  // execute command (initially empty)
  private static Boolean executeCommand(Scanner scan, Character command) {
    switch (command) {
      case 'q':
	System.out.println("Thank you for using General Cavazos Commander App");
        break;

      case 'l':
	print(commandArray);
	break;
    }

    return true;
  }

  // get first character from input line
  // return value:
  //     Character received: character value
  //     Empty line: '_'
  //     Exception (end-of-input or error): 'q'
  private static Character menuGetCommand(Scanner scan) {
    String rawInput;

    try {
        rawInput = scan.nextLine();
    }
    catch (Exception e) {
        System.out.println();
        return 'q';
    }
    if (rawInput.length() > 0) {
      rawInput = rawInput.toLowerCase();
      return rawInput.charAt(0);
    } else
        return '_';
  }

  // randomly issue commands from General Cavazos
  public static void randomCommand(String[] commandArray, int numCommand) {
    Random rand = new Random();
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < numCommand; i++) {
      int randIndex = rand.nextInt(commandArray.length);
      System.out.printf("%04d\t%s\n", i, commandArray[randIndex]);
    }
  }

  // print command array
  public static void print(String[] commandArray) {
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < commandArray.length; i++) {
      System.out.printf("%04d\t%s\n", i, commandArray[i]);
    }
  }

  // get array of commands
  public static String[] getCommandArray(JSONArray commandArray) {
    String[] arr = new String[commandArray.size()];

    // get names from json object
    for (int i = 0; i < commandArray.size(); i++) {
      String command = commandArray.get(i).toString();
      arr[i] = command;
    }
    return arr;
  }
}

import java.util.*;

class HangmanBC{
   //create array of incorrect guesses
   static char[] guessedWrong = new char[7];
   //initialize number of wrong guesses
   static int numWrongGuesses = 0;
   
   public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
   String secretWord = "corgi";
   GameIntro();
   char[] printWord = blankWord(secretWord.length());
   
   //LOOP FOR UP TO 7 WRONG GUESSES
   while(numWrongGuesses<7){
      char guess = getGuess();
      int location = secretWord.indexOf(guess);
      if(location!=-1){
         printWord = incompleteWord(guess, secretWord, printWord);
         System.out.println(printWord);
         boolean completed = wordComplete(secretWord, printWord);
         if(completed){
            System.out.println("You win!");
            break;
         }
      }//if location
      
      else{ //else guessed wrong
         if(Character.isLetter(guess)){
         guessedWrong[numWrongGuesses] = guess;
         System.out.println("That letter is not found! ");
         numWrongGuesses+=1;
         if(numWrongGuesses==7){
         System.out.println("You lost!");
         }
         }
      }//else not found
   
   /*
   String guessS = convert2String(guess);

  */
   }//BIG WHILE LOOP
   
   }//main
   
   static void GameIntro(){
      System.out.println("This is a game of Hangman, please try to guess");
      System.out.println("the correct word by making guesses of single");
      System.out.println("letters, one at a time. You can guess until");
      System.out.println("you win, or until hangman completes (7 wrong),");
      System.out.println("whichever happens first.");
   }//intro
   
   
   //function prompt user for one letter, return as char
   static char getGuess(){
      System.out.println("Please enter a letter.");
      Scanner sc = new Scanner(System.in);
      String letter = sc.next();
      char guess = letter.charAt(0);
      for(int i=0;i<guessedWrong.length; i++){
         if(guessedWrong[i]==guess && Character.isLetter(guess)){
         System.out.println("You already guessed " + guess + ", try again.");
         numWrongGuesses-=1; //subtracts 1 because duplication not counted against user
         }
      }
      if(!(Character.isLetter(guess))){ //checks if character is not a letter
         System.out.println("Please only enter letters.");
      }
      if(Character.isUpperCase(guess)){ //check if uppercase
         char guess2 = Character.toLowerCase(guess); //convert to lowercase
         return(guess2);
      }
      return(guess);
   }//get guess
   
   //function converting char guess to string
   /*static String convert2String(char c){
      String s = "";
      s += c;
      return(s);
   }//convert2string */
   
   //function to print blank word with # characters
   static char[] blankWord(int len){
      int a = len;
      char[] poundSigns = new char[a];
      for(int i=0;i<len;i++){
         poundSigns[i] = '#';
      }//for
      return(poundSigns);
   }//function
   
   //function print out incomplete word between guesses
   static char[] incompleteWord(char guess, String word, char[] ps){
      int len = word.length();
      char[] leftOver = new char[len];
      int i = word.indexOf(guess);
      ps[i] = guess;
      return(ps);
   }//function
      
   //function to check if word complete
   static boolean wordComplete(String s, char[] blanks){
      String b = new String(blanks);
      return(s.equals(b));
   }//function showing hangman character

}//class
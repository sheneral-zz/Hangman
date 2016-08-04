import java.util.*;
import java.io.*;

class HangmanImproved{
   //initialize number of wrong guesses
   static int numWrongGuesses = 0;
   //initialize 
   static String secretWord;
   //create char array of guessed - length is most wrong plus length of word
   static char[] guessed; //=new char[7+secretWord.length()];
   static int numGuesses = 0;
   static String[] names = new String[30];
   
   public static void main(String[] args){
      
      String fileName = "babyGirlNames.txt";
      
      //reference one  line at a time
      String line = null;
      
      try{
         FileReader fileReader = new FileReader(fileName);
         
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         
         for(int i=0;i<30 && (line = bufferedReader.readLine())!= null;i++){
            //System.out.println(line);
            names[i]=line;
         }//while
         
         //close files
         bufferedReader.close();
      }//try
      
      catch(FileNotFoundException ex){
         System.out.println("Unable to open file " + fileName);
      }//catch
      
      catch(IOException ex){
         System.out.println("Error reading file " + fileName);
      }//catch
      
      secretWord = names[randomNum()].toLowerCase();
      
      //TO NANETTE: UN-COMMENT BELOW IF WANT TO TEST :)
      //System.out.println(secretWord); 
      
      guessed = new char[7+secretWord.length()];
      
      Scanner sc = new Scanner(System.in);
      GameIntro();
      char[] printWord = blankWord(secretWord.length());
      
      //LOOP FOR UP TO 7 WRONG GUESSES
      while(numWrongGuesses<7) {
         char guess = getGuess();
         guessed[numGuesses] = guess;
         numGuesses += 1;
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
            System.out.println("That letter is not found! ");
            numWrongGuesses+=1;
            showHangman(numWrongGuesses);
            if(numWrongGuesses==7){
               System.out.println("You lost!");
               System.out.println("The correct word is " + secretWord);
            }
         }//else not found
      
      }//BIG WHILE LOOP
      
   }//main
   
   static void GameIntro(){
      System.out.println("This is a game of Hangman, please try to guess");
      System.out.println("the correct word by making guesses of single");
      System.out.println("letters, one at a time. You can guess until");
      System.out.println("you win, or until hangman completes (7 wrong),");
      System.out.println("whichever happens first.");
   }//intro
   
   //function picking a random letter
   static int randomNum(){
      int chooseNum = (int)(Math.random()*30.0);
      return(chooseNum);
   }
   
   //function prompt user for one letter, return as char
   static char getGuess(){
      boolean validGuess = false;
      Scanner sc = new Scanner(System.in);
      char guess;
      System.out.println("Please enter a letter.");
      do{ //asks the user for a new guess until a valid guess is entered
         String letter = sc.next();
         guess = Character.toLowerCase(letter.charAt(0));
         if(!(Character.isLetter(guess))){ //checks if character is not a letter
            System.out.println("Please only enter letters.");
         }//if
         else if(alreadyGuessed(guess)){
            System.out.println("You already guessed " + guess + ", try again.");
         }//else if
         else {
            validGuess = true;
         }//else
      } while(! validGuess);
      return(guess);
   }//get guess
   
   //function to check if guess is already been guessed
   static boolean alreadyGuessed(char guess){
      for(int i=0;i<numGuesses;i++){
         if(guessed[i]==guess){
            return true;
         }
      }//for
      return(false);
   }//function boolean
   
   
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
      for(int i=0;i<word.length();i++){
         if(word.charAt(i)==guess){
         ps[i] = guess;
         }
      }
      return(ps);
   }
   
   //function to check if word complete
   static boolean wordComplete(String s, char[] blanks){
      String b = new String(blanks);
      return(s.equals(b));
   }
   
   //function showing hangman character
   static void showHangman(int n){
      char[][] display = {{'+','-','-', '-'},
                       {'|',' ',' ',' '},
                       {'|',' ',' ',' '},
                       {'|',' ',' ',' '},
                       {'|',' ',' ',' '},
                       {'|',' ',' ',' '},
                    };
      
      switch(n){
         case 7:
            display[4][3]='\\';
         case 6:
            display[4][1]='/';
         case 5:
            display[3][2]='|';
         case 4:
            display[2][3]='\\';
         case 3:
            display[2][2]='|';
         case 2:
            display[2][1]='/';
         case 1:
            display[1][2]='O';
         default:
            for(char[] row:display){
              for(char col:row){
                 System.out.print(col);
              }
              System.out.println();
            }
         }//switch
      
      }//hangman

}//class
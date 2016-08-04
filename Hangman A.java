import java.util.*;

class HangmanA{
   //create array of incorrect guesses
   static char[] guessedWrong = new char[7];
   //initialize number of wrong guesses
   static int numWrongGuesses = 0;
   //create array of correct guesses
   static char[] guessedRight = new char[20];
   static String guessedRightS = new String(guessedRight);
   
   public static void main(String[] args){
   Scanner sc = new Scanner(System.in);
   String secretWord = "delete";
   GameIntro();
   char[] printWord = blankWord(secretWord.length());
   int located = 0; //initialize beginning search for indexOf method
   
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
         guessedRight[location]=guess;

      }//if location
      
      else{ //else guessed wrong
         if(Character.isLetter(guess)){
         guessedWrong[numWrongGuesses] = guess;
         System.out.println("That letter is not found! ");
         numWrongGuesses+=1;
         showHangman(numWrongGuesses);
         if(numWrongGuesses==7){
         System.out.println("You lost!");
         System.out.println("The correct word is " + secretWord);
         }
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
   
   //function prompt user for one letter, return as char
   static char getGuess(){
      System.out.println("Please enter a letter.");
      Scanner sc = new Scanner(System.in);
      String letter = sc.next();
      char guess = Character.toLowerCase(letter.charAt(0));
      for(int i=0;i<guessedWrong.length; i++){
         if(guessedWrong[i]==guess && Character.isLetter(guess)){
            System.out.println("You already guessed " + guess + ", try again.");
            numWrongGuesses-=1; //subtracts 1 because duplication not counted against user
         }
      }//for loop
      if(!(Character.isLetter(guess))){ //checks if character is not a letter
         System.out.println("Please only enter letters.");
      }
      return(guess);
   }//get guess
   
   
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
         case 1: 
            display[1][2]='O';
            for (char[] row:display){//slice from display
               for (char col:row){
                  System.out.print(col);
               }//inner loop
               System.out.println();
            }//outter loop
            break;
         case 2: 
            display[1][2]='O';
            display[2][1]='/';
            for(char[] row:display){
               for(char col:row){
               System.out.print(col);
               }
            System.out.println();
            }
            break;
         case 3: 
            display[1][2]='O';
            display[2][1]='/';
            display[2][2]='|';
            for(char[] row:display){
               for(char col:row){
               System.out.print(col);
               }
            System.out.println();
            }
            break;
         case 4:
            display[1][2]='O';
            display[2][1]='/';
            display[2][2]='|';
            display[2][3]='\\';
            for(char[] row:display){
               for(char col:row){
               System.out.print(col);
               }
            System.out.println();
            }
            break;
         case 5:
            display[1][2]='O';
            display[2][1]='/';
            display[2][2]='|';
            display[2][3]='\\';
            display[3][2]='|';
            for(char[] row:display){
               for(char col:row){
               System.out.print(col);
               }
            System.out.println();
            }
            break;
         case 6:
            display[1][2]='O';
            display[2][1]='/';
            display[2][2]='|';
            display[2][3]='\\';
            display[3][2]='|';
            display[4][1]='/';
            for(char[] row:display){
               for(char col:row){
               System.out.print(col);
               }
            System.out.println();
            }
            break;
         case 7:
            display[1][2]='O';
            display[2][1]='/';
            display[2][2]='|';
            display[2][3]='\\';
            display[3][2]='|';
            display[4][1]='/';
            display[4][3]='\\';
            for(char[] row:display){
               for(char col:row){
               System.out.print(col);
               }
            System.out.println();
            }
            break;
         }//switch
      
      }//hangman

}//class
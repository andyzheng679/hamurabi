package hammurabi;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    void playGame() {
        // declare local variables here: grain, population, etc.
        // statements go after the declations


        //start values
        int startPeople = 100;
        int grainInStorage = 2800;
        int acresOfLand = 100;
        int landValue = 19; //19 bushels/acre

//        int year = 0;
//        while (year < 10){
//            //
//            //
//            //
//            year++;
//        }
        //System.out.println(askHowManyAcresToBuy(landValue, grainInStorage));
        //System.out.println(askHowManyAcresToSell(100));
        System.out.println(askHowMuchGrainToFeedPeople(grainInStorage));

    }

    //other methods go here

    int askHowManyAcresToBuy(int price, int bushels) {
        System.out.println("Land cost " + price + " bushels per acre");
        System.out.println("You have " + bushels + " bushels of grain");
        System.out.println("How many acres of land do you want to buy; ");

        int acresToBuy = 0; //set it to 0 bc you will ask for it in the loop
        boolean validInput = false; //to start the while loop

        while(!validInput){
            try{
                acresToBuy = scanner.nextInt();
                if (acresToBuy * price > bushels){
                    System.out.println("You don't have enough to buy " + acresToBuy + ". Enter another amount: ");
                }else{
                    validInput = true;
                }
            }catch (InputMismatchException e){
                System.out.println("\"" + scanner.next() + "\" isn't a number! Enter a number: ");
            }
        }
        return acresToBuy;
    }

    int askHowManyAcresToSell(int acresOwned){
        System.out.println("Enter how many acres you want to see: ");
        int acresToSell = 0;
        boolean validInput = false;

        while(!validInput){
            try {
                acresToSell = scanner.nextInt();
                if(acresToSell > acresOwned){
                    System.out.println("You only have " + acresOwned + " , enter another amount: ");
                }else {
                    validInput = true;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number! Enter a number: ");
            }
        }
        return acresToSell;
    }

    int askHowMuchGrainToFeedPeople(int bushels){
        System.out.println("You have " + bushels + " bushels of grain in storage.");
        System.out.println("It takes at least 20 bushels of grain per person per year to survive.");
        System.out.println("How many bushels you want to give: ");

        int bushelsToGive = 0;
        boolean validInput = false;

        while (!validInput){
            try {
                bushelsToGive = scanner.nextInt();
                if(bushelsToGive > bushels){
                    System.out.println("You only have " + bushels + " bushles of grain in storage, enter another amount: ");
                }else{
                    validInput = true;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number! Enter a number: ");
            }
        }
        return bushelsToGive;
    }








}
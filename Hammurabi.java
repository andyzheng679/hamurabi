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
//        Each person needs at least 20 bushels of grain per year to survive
//        Each person can farm at most 10 acres of land
//        It takes 2 bushels of grain to farm an acre of land
//        The market price for land fluctuates yearly



//        int year = 0;
//        while (year < 10){
//            //
//            //
//            //
//            year++;
//        }
        //System.out.println(askHowManyAcresToBuy(landValue, grainInStorage));
        //System.out.println(askHowManyAcresToSell(100));
        //System.out.println(askHowMuchGrainToFeedPeople(grainInStorage));
        System.out.println(askHowManyAcresToPlant(acresOfLand, startPeople, grainInStorage));

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
    //print out the setup, let user know what they have rn, create two vars, one to hold user input and one the turn on/off while loop
    //set bool to false bc we for now, set var that holds user input to 0 bc no user input yet
    //using a while loop bc user can answer forever, condiditon, use ! to change the bool from false to true,
    //once they give a valid input, change bool to true so the !bool will become false and stop the loop
    //try & catch, inside try, set user input to var, if statement next,
    //as long as user input * price is greater than bushels(amount you have), print dont have enough and let them know how much they have
    //else set bool to true

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
    //start the set up, two var, user input and bool for the while loop, set user input var to 0; and bool to false
    //while loop, !bool, try catch, set user input var to next input, if statement, if input is greater than arcesOwned (number of arces you have)
    //let user know that they only have xyz amount and prompt another input
    //else bool = true

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
    //start set up, tell user how many bushel in storage, how many it takes to feed a person and how many to give
    //set two vars, user input and while loop bool, set user input to 0 and bool to false
    // while !bool, try catch, set user input var to next input, if statement,
    //if user input is greater than bushels you have, let user know that they only have xyz bushels and ask for another input

    int askHowManyAcresToPlant(int acresOwned, int population, int bushels){
        System.out.println("You have " + acresOwned + " acres, " + population + " people, " + bushels + " bushels of grain.");
        System.out.println("Each person can farm at most 10 acres, it takes 2 bushels of grain to farm an acre.");
        System.out.println("How many acres do you want to plant: ");

        int acresToPlant = 0;
        boolean validInput = false;

        while (!validInput){
            try {
                acresToPlant = scanner.nextInt();
                if(acresToPlant > acresOwned){
                    System.out.println("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres");
                }else if (acresToPlant > population * 10){
                    System.out.println("O Great Hammurabi, surely you jest! We have only " + population + " people");
                }else if (acresToPlant * 2 > bushels){
                    System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels of grains");
                }else{
                    validInput = true;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number! Enter a number: ");
            }
        }
        return acresToPlant;
    }
    // start set up, sell user how many acres owned, population rn, and number of bushels in storage
    //let user know what they need to do stuff, 1 person farm 10 acres, 2 bush to farm 1 acre, then ask for num of acres to plant
    //two var, user input and bool for while loop, set user input to 0 and bool to false
    //while !bool, try catch, set user input to next input, if statement
    //if input is greater than acres owned, let they know they only have xyz
    //if input is greater than population * 10 (pop * 10 bc 1 person can farm 10 acres) let user know how many people they have
    //if input * 2 is greater than bushels (input * 2 bc it take 2 bush per acres to farm) let user know how many bush they have
    //you check these in this order bc if input is more than the number of acres owned, cant do anything,
    //if input > pop * 10, not enough people to plant
    //if input * 2 > bush, not enough bush to plant

    int plagueDeaths(int population){
        int randomNum = rand.nextInt(100);
        if(randomNum < 15){
            return population / 2;
        }else {
            return 0;
        }
    }
    //






}
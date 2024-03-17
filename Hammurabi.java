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

    //start values
    int startPeople = 100;
    int grainInStorage = 2800;
    int acresOfLand = 1000;
    int landValue = 19; //19 bushels/acre
    int numOfBushelsToFarmAcres = 2;

//        Each person needs at least 20 bushels of grain per year to survive
//        Each person can farm at most 10 acres of land
//        It takes 2 bushels of grain to farm an acre of land
//        The market price for land fluctuates yearly


    void playGame() {
        // declare local variables here: grain, population, etc.
        // statements go after the declations

//        O great Hammurabi!
//                You are in year 1 of your ten year rule.
//                In the previous year 0 people starved to death.
//        In the previous year 5 people entered the kingdom.
//        The population is now 100.

//        We harvested 3000 bushels at 3 bushels per acre.                          //all of this is pt 2, dont print at start of game
//                Rats destroyed 200 bushels, leaving 2800 bushels in storage.
//                The city owns 1000 acres of land.
//                Land is currently worth 19 bushels per acre.
        int year = 1;
        int population = startPeople;
        int deathsPerStarvation = 0;  //starvationDeaths method will update this
        int numOfPeopleEnterKingdom = 0; //immigrants method will update this



        while(year <= 10){
            System.out.println("O great Hammurabi!");
            System.out.println("You are in year " + year + " of your ten year rule.");
            System.out.println("In the previous year " + deathsPerStarvation + " people starved to death.");
            System.out.println("In the previous year " + numOfPeopleEnterKingdom + " people entered the kingdom.");
            System.out.println("The population is now " + population + ".");

            //asking user for input
            int acresBought =askHowManyAcresToBuy(landValue, grainInStorage);
            acresOfLand += acresBought;
            grainInStorage -= acresBought * landValue;

            int acresSold = 0;
            if(acresBought == 0){
                acresSold = askHowManyAcresToSell(acresOfLand);
                acresOfLand -= acresSold;
                grainInStorage += acresSold * landValue;
            }

            int grainsUsedToFeed = askHowMuchGrainToFeedPeople(grainInStorage);
            grainInStorage -= grainsUsedToFeed;

            int numOfAcresPlanted = askHowManyAcresToPlant(acresOfLand, population, grainInStorage);
            grainInStorage -= numOfAcresPlanted * numOfBushelsToFarmAcres;


            //negative outcomes
            int deathByPlague = plagueDeaths(population);
            population -= deathByPlague;
            if(deathByPlague > 0){
                System.out.println("Kingdom got hit by the plague, lost half of your population");
            }



            int starvationDeathsVar = starvationDeaths(population, grainsUsedToFeed);
            deathsPerStarvation = starvationDeathsVar;
            population -= starvationDeathsVar;


            if(uprising(population, starvationDeathsVar) == true){
                System.out.println("Uprising, you have been over thrown.");
                break;
            }

            int hungryRats = (grainEatenByRats(grainInStorage));
            grainInStorage -= hungryRats;

            //other stuff
            numOfPeopleEnterKingdom = immigrants(population, acresOfLand, grainInStorage);
            population += numOfPeopleEnterKingdom;

            int harvesting = harvest(numOfAcresPlanted, numOfBushelsToFarmAcres);
            grainInStorage += harvesting;

            landValue = newCostOfLand();

            //print out pt 2
            System.out.println("We harvested " + harvesting + " bushels at " + (harvesting/numOfAcresPlanted) + " bushels per acre. ");
            System.out.println("Rats destroyed " + hungryRats + " bushels, leaving " + grainInStorage + " bushels in storage.");
            System.out.println("The city owns " + acresOfLand + " acres of land.");
            System.out.println("Land is currently worth " + landValue + " bushels per acre.");
            System.out.println("                                ");
            System.out.println("                                ");
            System.out.println("                                ");
            System.out.println("                                ");
            System.out.println("                                ");

//            We harvested 3000 bushels at 3 bushels per acre.                          //all of this is pt 2, dont print at start of game
//                Rats destroyed 200 bushels, leaving 2800 bushels in storage.
//                The city owns 1000 acres of land.
//                Land is currently worth 19 bushels per acre.
            year++;




        }

        finalSummary(year - 1, population, acresOfLand, grainInStorage);




        //System.out.println(askHowManyAcresToBuy(landValue, grainInStorage));
        //System.out.println(askHowManyAcresToSell(100));
        //System.out.println(askHowMuchGrainToFeedPeople(grainInStorage));
        //System.out.println(askHowManyAcresToPlant(acresOfLand, startPeople, grainInStorage));
        //System.out.println(plagueDeaths(startPeople));
        //System.out.println(starvationDeaths(startPeople, 2000));
        //System.out.println(starvationDeaths(startPeople, 1500));
        //System.out.println(uprising(startPeople, 20));
        //System.out.println(uprising(startPeople, 50));
        //System.out.println(harvest(1, numOfBushelsToFarmAcres));
        //System.out.println(grainEatenByRats(grainInStorage));
        //System.out.println(newCostOfLand());
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
        System.out.println("Enter how many acres you want to sell: ");
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
    //make a var to store rand int, use Random class .nextInt, set bound to 100 (bc its 0-99, 100 choices, represents percentages)
    //if rand var is less than 15 (15%) return half pop
    //else return 0 (bc didnt lose anyone)

    int starvationDeaths(int population, int bushelsFedToPeople){ //bushelsFedToPeople is askHowMuchGrainToFeedPeople
        int numOfPeopleFed = bushelsFedToPeople / 20;
        if(numOfPeopleFed >= population){
            return 0;
        }else{
            return population - numOfPeopleFed;
        }
    }
    //bushelsFedToPeople is askHowMuchGrainToFeedPeople, create var to store people that were fed, each person need 20 bush per year
    //if number of people fed is greater or equals to pop, return 0 bc no one starved, min pop got feed, user can feed more
    //else, take pop and subtract the num of people that were fed, we're returning the number of people that died

    boolean uprising(int population, int howManyPeopleStarved){ //how howManyPeopleStarved = starvationDeaths
        double percentageOfPeopleDied = ((double) howManyPeopleStarved / population) * 100;

        if(percentageOfPeopleDied > 45){
            return true;
        }else {
            return false;
        }
    }

    //how howManyPeopleStarved = starvationDeaths, create a double var for % of people died
    //you need a double bc for int, it disregards the fraction part, 50/100 is .50, for an int, it will be 0
    // howManyPeopleStarved / pop * 100, you need to type cast before division bc of ^^^^^^^^^^^^^^^^^^^^^^
    //if var is greater than 45, return true, else false

    int immigrants(int population, int acresOwned, int grainInStorage){
        int newImmigrants = (20 * acresOwned + grainInStorage) / (100 * population) + 1;
        return newImmigrants;
    }
    //this was in the read me

    int harvest(int acres, int bushelsUsedAsSeed){  //acres = askHowManyAcresToPlant, bushelsUsedAsSeed = numOfBushelsToFarmAcres
        int randBushelsPerAcres = rand.nextInt(6) + 1;
        int bushelsUsed = acres * bushelsUsedAsSeed;
        grainInStorage -= bushelsUsed;
        return randBushelsPerAcres * acres;
    }
    ///acres = askHowManyAcresToPlant, bushelsUsedAsSeed = numOfBushelsToFarmAcres("fields" It takes 2 bushels of grain to farm an acre of land)
    //create var to hold rand num, bound is 6, but add 1 to result, 0-5 to 1-6
    //create var to hold acres * bushelsUsedAsSeed bc for every bush we plant, we need to subtract bush in storage
    //set grainInStorage field to -= bushelsUsed, return randBushelsPerAcres * acres to see the yield of bush you get per acres

    int grainEatenByRats(int bushels){
        int randPercentage = rand.nextInt(100); //0-99

        if(randPercentage < 40){
            int eatPercent = rand.nextInt(21) + 10; //10-30
            return bushels * eatPercent / 100;
        }
        return  0;
    }
    //create var to store percentage, bound 100, 0-99
    //if statement, if var less than 40, rat infestation
    //if rat infestation, 10-30% of grains in storage will be eaten, set another var to find percentage, bound 21, but plus 10 to result, bc 10-30
    //return bushels * 10-30% var / 100, return 0 if the if statement isnt true

    int newCostOfLand(){
        int randNum = rand.nextInt(7);
        int landPrice = randNum + 17;

        return landPrice;
    }
    // create rand num var, bound 7 bc 0-6
    //create a land price var, = ran num var + 17 bc land cost between 17-23

    void finalSummary(int year, int population, int acresOfLand, int grainInStorage){
        System.out.println("10 year rule comes to an end");
        System.out.println("Year of rule: " + year);
        System.out.println("Population: " + population);
        System.out.println("Acres of land owned: " + acresOfLand);
        System.out.println("Grain in Storage: " + grainInStorage);

        if(population < startPeople / 2){
            System.out.println("Half of the starting population died, your rule sucks");
        }
    }





}
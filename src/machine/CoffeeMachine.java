package machine;

import java.util.Scanner;

enum CoffeeType {
    ESPRESSO,
    LATTE,
    CAPPUCCINO
}

enum CoffeeType {
    ESPRESSO,
    LATTE,
    CAPPUCCINO
}

class Coffee {
    int money;
    int water;
    int milk;
    int coffeeBeans;

    public int getMoney() {
        return money;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }
}

class Espresso extends Coffee {

    public Espresso() {
        this.money = 4;
        this.water = 250;
        this.milk = 0;
        this.coffeeBeans = 16;
    }
}
class Cappuccino extends Coffee {

    public Cappuccino() {
        this.money = 6;
        this.water = 200;
        this.milk = 100;
        this.coffeeBeans = 12;
    }
}
class Latte extends Coffee {

    public Latte() {
        this.money = 7;
        this.water = 350;
        this.milk = 75;
        this.coffeeBeans = 20;
    }
}

class CoffeeFactory {
    public Coffee createCoffee (CoffeeType type) {
        Coffee coffee = null;

        switch (type) {
            case ESPRESSO:
                coffee = new Espresso();
                break;
            case CAPPUCCINO:
                coffee = new Cappuccino();
                break;
            case LATTE:
                coffee = new Latte();
                break;
        }
        return coffee;
    }
}


public class CoffeeMachine {

    private final CoffeeFactory coffeeFactory;

    public CoffeeMachine (CoffeeFactory coffeeFactory) {
        this.coffeeFactory = coffeeFactory;
    }

    private int money = 550;
    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int disposableCups = 9;

    public void takeMoney () {

        System.out.println();
        System.out.printf("I gave you $%d\n", this.getMoney());
        System.out.println();
        this.money = 0;

    }

    public void fillCoffeeMachine () {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        this.setWater(this.getWater() + scanner.nextInt());
        System.out.println("Write how many ml of milk you want to add:");
        this.setMilk(this.getMilk() + scanner.nextInt());
        System.out.println("Write how many grams of coffee beans you want to add:");
        this.setCoffeeBeans(this.getCoffeeBeans() + scanner.nextInt());
        System.out.println("Write how many disposable cups of coffee you want to add:");
        this.setDisposableCups(this.getDisposableCups() + scanner.nextInt());
        System.out.println();

    }

    public void buyCoffee () {

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        switch (scanner.nextLine()) {
            case "1":
                orderCoffee(CoffeeType.ESPRESSO);
                break;
            case "2":
                orderCoffee(CoffeeType.LATTE);
                break;
            case "3":
                orderCoffee(CoffeeType.CAPPUCCINO);
                break;
            case "back":
                return;
        }

        System.out.println();

    }

    public void orderCoffee(CoffeeType type) {

        Coffee coffee = coffeeFactory.createCoffee(type);
        if ((this.getWater() - coffee.getWater()) >= 0) {
            this.setWater(this.getWater() - coffee.getWater());
        } else {
            System.out.println("Sorry, not enough water!");
            return;
        }
        if ((this.getMilk() - coffee.getMilk()) >= 0) {
            this.setMilk(this.getMilk() - coffee.getMilk());
        } else {
            System.out.println("Sorry, not enough milk!");
            return;
        }
        if ((this.getCoffeeBeans() - coffee.getCoffeeBeans()) >= 0) {
            this.setCoffeeBeans(this.getCoffeeBeans() - coffee.getCoffeeBeans());
        } else {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }
        if ((this.getDisposableCups() - 1) >= 0) {
            this.setDisposableCups(this.getDisposableCups() - 1);
        } else {
            System.out.println("Sorry, not enough coffee disposable cups!");
            return;
        }

        this.setMoney(this.getMoney() + coffee.getMoney());
        System.out.println("I have enough resources, making you a coffee!");

    }

    public void infoCoffeeMachine() {

        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", this.getWater());
        System.out.printf("%d ml of milk\n", this.getMilk());
        System.out.printf("%d g of coffee beans\n", this.getCoffeeBeans());
        System.out.printf("%d disposable cups\n", this.getDisposableCups());
        System.out.printf("$%d of money\n", this.getMoney());
        System.out.println();

    }

    public int getDisposableCups() {
        return disposableCups;
    }

    public void setDisposableCups(int disposableCups) {
        this.disposableCups = disposableCups;
    }

    public int getMoney() {
        return money;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine(new CoffeeFactory());
        Scanner scanner = new Scanner(System.in);
        String action = "";
        coffeeMachine.infoCoffeeMachine();
        while (!action.equals("exit")) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.nextLine();
            switch (action) {
                case "buy":
                    coffeeMachine.buyCoffee();
                    break;
                case "fill":
                    coffeeMachine.fillCoffeeMachine();
                    break;
                case "take":
                    coffeeMachine.takeMoney();
                    break;
                case "remaining":
                    coffeeMachine.infoCoffeeMachine();
                    break;
            }

        }

    }
}

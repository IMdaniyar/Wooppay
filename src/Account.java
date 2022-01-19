class Account
{
    private int money;
    private final String name, password;

    Account(String name, String password, int initialSum)
    {
        money = initialSum;
        this.password = password;
        this.name = name;
    }

    int availableMoney()
    {
        return money;
    }

    public int addMoney(int amountToAdd)
    {
        return money += amountToAdd;
    }

    int withdraw(int amountToTake) throws Exception
    {
        if (hasEnaughMoney(amountToTake))
        {
            money -= amountToTake;
            return amountToTake;
        }
        throw new Exception("Account of " + name + " has not enaugh money");

    }

    boolean verify(String name, String password)
    {
        return this.name.equals(name) && this.password.equals(password);
    }

    String getName()
    {
        return name;
    }

    boolean hasEnaughMoney(int amountToTake)
    {
        return money >= amountToTake;
    }
}
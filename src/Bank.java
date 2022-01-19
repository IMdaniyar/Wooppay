import java.util.HashMap;
import java.util.Map;

public class Bank implements BankService
{
    private static Map<String, Account> registeredAccounts;

    Bank()
    {
        registeredAccounts = new HashMap<>();
    }

    public static BankService requestBankService()
    {
        return new Bank();
    }

    @Override
    public void register(String name, String password, int initialAmount)
    {
        registeredAccounts.put(name, new Account(name, password, initialAmount));
        System.out.println("User " + name + " registerred succesfully");
    }

    @Override
    public AccountService logIn(String name, String password) throws Exception
    {
        if(!registeredAccounts.containsKey(name)) throw new Exception("Account of " + name + " is not registerred");
        if(registeredAccounts.get(name).verify(name, password))
        {
            System.out.println("User " + name + " logged in succesfully");
            return new LoggedInUser(registeredAccounts.get(name));
        }
        throw new Exception("Wrong credentials");
    }

    private class LoggedInUser implements AccountService
    {
        private Account loggedAcount;

        LoggedInUser(Account account)
        {
            this.loggedAcount = account;
        }


        @Override
        public int withdraw(int amount) throws Exception
        {
            int withdrawedAmount = loggedAcount.withdraw(amount);
            System.out.println("User " + loggedAcount.getName() + "withdrawed " + Integer.toString(withdrawedAmount) + "$");
            return withdrawedAmount;
        }

        @Override
        public boolean transfer(String to, int amount) throws Exception
        {
            if(registeredAccounts.containsKey(to))
            {
                Account transferTo = registeredAccounts.get(to);
                transferTo.addMoney(loggedAcount.withdraw(amount));
                System.out.println("User " + loggedAcount.getName() + " has transferred " + Integer.toString(amount) + "$ to " + transferTo.getName());
                return true;
            }
            throw new Exception("Can't transfer money to " + to + ". Reason: No such user");
        }

        @Override
        public int getAvailableMoney()
        {
            return loggedAcount.availableMoney();
        }

        @Override
        public String getName()
        {
            return loggedAcount.getName();
        }
    }

}
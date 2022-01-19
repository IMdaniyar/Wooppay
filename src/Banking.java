import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Banking
{

    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        BankService bankService = Bank.requestBankService();
        bankService.register("Daniyar", "6565", 10000);
        bankService.register("Bibigul", "8585", 10000);
        try
        {
            AccountService daniyar = bankService.logIn("Daniyar", "6565");
            AccountService bibigul = bankService.logIn("Bibigul", "8585");
            System.out.println(daniyar.getName() + " has " + daniyar.getAvailableMoney() + "$");
            System.out.println(bibigul.getName() + " has " + daniyar.getAvailableMoney() + "$");
            bibigul.transfer(daniyar.getName(), in.nextInt());
            System.out.println(daniyar.getName() + " has " + daniyar.getAvailableMoney() + "$");
            System.out.println(bibigul.getName() + " has " + bibigul.getAvailableMoney() + "$");
            daniyar.transfer(bibigul.getName(), in.nextInt());
        } catch (Exception e)
        {
            System.err.println("\u001B[31m" + e.getMessage() + "\u001B[00m");
        }
    }
}

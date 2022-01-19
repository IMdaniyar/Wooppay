public interface AccountService
{
    int withdraw(int amount) throws Exception;
    boolean transfer(String to, int amount) throws Exception;
    int getAvailableMoney();
    String getName();
}
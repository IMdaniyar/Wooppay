public interface BankService
{
    public void register(String name, String password, int initialAmount);
    public AccountService logIn(String name, String password) throws Exception;
}
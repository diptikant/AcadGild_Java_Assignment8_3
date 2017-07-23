package Java_Assignment83;
/*
 * This class will help you to master the concepts of Multithreading and Synchronization

 */


public class Java_Assignment8_3 implements Runnable
{
	Account acc = new Account(); //Account instance
	
	public static void main(String[] args) 
	{
		Java_Assignment8_3 ts = new Java_Assignment8_3();
        Thread t1 = new Thread(ts, "person 1");
        Thread t2 = new Thread(ts, "person 2");
        Thread t3 = new Thread(ts, "person 3");
        t1.start();
        t2.start();
        t3.start();

	}
	
	 @Override
	 public void run()  //Run method
	 {
        for (int i = 0; i < 3; i++) 
        {
            makeWithdraw(100);
            if (acc.getBal() < 0) 
            {
                System.out.println("account is overdrawn!");
            }
            deposit(200);
        }
	    
	 }

    // synchronized makeWithdraw method
    private synchronized void makeWithdraw(int bal) 
    {
        if (acc.getBal()>=bal) 
        {
            System.out.println(Thread.currentThread().getName()+" "+ "is try to withdraw");
            try 
            {
                Thread.sleep(100);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            acc.withdraw(bal);
            System.out.println(Thread.currentThread().getName()+" "+ "is complete the withdraw"+" Balance is: " +acc.getBal());
        }
        else
        {        
            System.out.println(Thread.currentThread().getName()+ " "+"doesn't have enough money for withdraw ");
        }
    }

 // synchronized deposit method
    private synchronized void deposit(int bal)
    {
        if (bal>0) 
        {
            System.out.println(Thread.currentThread().getName()+" "+ " is try to deposit");
            try 
            {
                Thread.sleep(100);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            acc.deposit(bal);
            System.out.println(Thread.currentThread().getName()+" "+ "is complete the deposit"+" Balance is: " +acc.getBal());
        }
        else
        {        
            System.out.println(Thread.currentThread().getName()+ " "+"doesn't have enough money for deposit");
        }
    }

}

class Account //Account class
{
    int balance= 1000;

    public int getBal()
    {
        return balance;
    }

    public void withdraw(int bal)
    {
        balance= balance-bal;
    }

    public void deposit(int bal)
    {
        balance= balance+bal;
    }
}

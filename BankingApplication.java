/**
* <h1>BankingApplication</h1>
* This program shows deposit and withdraw functionality for the Online Banking Application
* using synchronized method in thread class
* @author  Vikas
* @version 1.0
* @since   2017-05-30 
*/
package assignment8.session3;
/*As we can see since both deposit method and withdraw methods are synchronized so there is no 
 data inconsistency only one person can operate on the account at a particular time*/
public class BankingApplication implements Runnable{
	//this is an account on which 2 persons are operating at the same time
	//2 different persons we have taken as thread
	//this Account will be having initial balance and withdraw and deposit methods
    Account1 acc = new Account1();

    public static void main(String[] args) {
    	BankingApplication ts = new BankingApplication();
        Thread t1 = new Thread(ts, "person 1");//creating first thread
        Thread t2 = new Thread(ts, "person 2");//creating second thread
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
            withdraw(100);//withdraw 100 rupees
            deposit(200);//deposit 200 rupees
    }

    //if withdrawing balance is less than the available balance, then amount can be withdrawn
    //as this is a synchronized method only one person can withdraw at a particular time
    private synchronized void withdraw(int bal){
        if (acc.getBal()>=bal) {
            System.out.println(Thread.currentThread().getName()+" "+ "is trying to withdraw amount of rupees: "+bal);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            acc.withdraw(bal);
            System.out.println(Thread.currentThread().getName()+" "+ "has successfully withdrawn amount of rupees: "+bal);
            System.out.println("Current available balance is rupees: "+acc.balance);
        }else{        
            System.out.println(Thread.currentThread().getName()+ " "+"doesn't have enough money to withdraw ");
        }
    }
    
    //if depositing balance is greater than 0, then amount can be deposited
    //as this is a synchronized method only one person can deposit at a particular time
    private synchronized void deposit(int bal){
        if (bal>0) {
        	System.out.println(Thread.currentThread().getName()+" "+ "is trying to deposit amount of rupees: "+bal);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            acc.deposit(bal);
            System.out.println(Thread.currentThread().getName()+" "+ "has successfully deposited amount of rupees: "+bal);
            System.out.println("Current available balance is rupees: "+acc.balance);
        }else{        
            System.out.println(Thread.currentThread().getName()+ " "+"deposit amount should be greater than 0");
        }
    }
}

//this is the account on which a person is performing withdraw and deposit operations
class Account1 {
    int balance= 1000;

    public int getBal(){
        return balance;
    }

    public void withdraw(int bal){
        balance= balance-bal;
    }

    public void deposit(int bal){
        balance= balance+bal;
    }
}
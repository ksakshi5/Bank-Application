import java.util.Scanner;
import java.util.ArrayList;
class BankApplication
{ 
	// GLOBAL VARIABLES 

	static String name;     // null --> DEFAULT VALUES 
	static int pin;         // 0
	static double balance;  // 0.0
	static long contact;    // 0
	static final long ACCOUNT = 1234_1345_1324L;
	static final String IFSC_CODE = "LCF000001";
	static ArrayList<String> transactions = new ArrayList<>();	


	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		
		welcomeLoop:       // WE CAN GIVE NAME TO LOOP  
		for(;;)
		{
			System.out.println("\n====================================");
			System.out.println("              WELCOME               ");
			System.out.println("*-*-*-*-*-*-*VYOMI BANK*-*-*-*-*-*-*");
			System.out.println("         Banking Beyond Limits      ");
			System.out.println("====================================");
			System.out.println("1. LOGIN");
			System.out.println("2. OPEN NEW ACCOUNT");
			System.out.print("Please select an option : ");
			int opt = sc.nextInt();

			switch(opt)
			{
				case 1 : 
				{
					if(name == null)
					{
						System.out.println("\nNO DATA FOUND.PLEASE CREATE AN ACCOUNT FIRST. \n");
						continue;
					}



					// LOOP RUNS 3 TIMES AND APP TERMINATES IF CREDENTIALS IS WRONG --> MAXIMUM 3 SECURE LOGIN ATTEMPTS 
					

					for(int i=1, attempt=3; i<=3; i++)
					{
						System.out.println("\n*** SECURE LOGIN *** (Attempt " +i+ " of 3)");
						System.out.print("Registerd Contact Number : ");
						long userContact = sc.nextLong();
						System.out.print("Enter Pin : ");
						int userPin = sc.nextInt();

						if(contact==userContact && pin==userPin)
						{
							homePage:
							for(;;)
							{
								System.out.println("\n===========CUSTOMER DASHBOARD==============");
								System.out.println("1. Deposit Funds");
								System.out.println("2. Withdraw Funds");
								System.out.println("3. Check Balance");
								System.out.println("4. View Transactions History");
								System.out.println("5. Logout Securely");
								System.out.print("\nChoose an action  : ");
								int opt1 = sc.nextInt();

								switch(opt1)

								{
									case 1 :
									{
										System.out.println("\n***DEPOSIT FUNDS***");
										System.out.print("Enter Deposit Amount : ");
										double depAmt = sc.nextDouble();
										balance += depAmt ;
										transactions.add("Credit | Amount : " + depAmt);
										System.out.println("\nAMOUNT CREDITED SUCCESSFULLY. \n");
										break;
									}
									case 2 :
									{
										System.out.println("\n***WITHDRAW FUNDS***");
										System.out.print("Enter Withdrawal amount : ");
										double withDrawAmt = sc.nextDouble();
										System.out.print("Confirm pin : ");
										int userPin1 = sc.nextInt();

										if(userPin1 == pin)
										{
											if(withDrawAmt <= balance)
											{
												balance -= withDrawAmt;
												transactions.add("DEBIT  | AMOUNT : " + withDrawAmt);
												System.out.println("\nAMOUNT DEBITED SUCCESSFULLY \n");
											}
											else
											{
												System.out.println("\nTRANSACTION FAILED: INSUFFICIENT FUNDS. \n");
											}
										}
										else 
										{
											System.out.println("\nAUTHENTICATION FAILED: INVALID PIN. \n");
										}
										break;
									}
									case 3 :
									{
										System.out.println("\n ***CHECK ACCOUNT BALANCE***");
										System.out.print("Enter pin to continue : ");
										int userPin2 = sc.nextInt();

										if(userPin2 == pin)
										{
											System.out.println("Dear " +name+ " , your availble balance is : " +balance+ " Rs.");
										}
										else
										{
											System.out.println("\nACCESS DENIED: INVALID PIN. \n");
										}
										break;
									}
									case 4 :
									{
										System.out.println("\n ***TRANSACTION HISTORY***");
										for(String ele : transactions)
										{
											System.out.println(ele) ;
										}
										break;
									}
									case 5 :
									{
										System.out.println("\nYOU HAVE BEEN LOGGED OUT SAFELY. THANK YOU FOR BANKING WIHT US!");
										continue welcomeLoop;
									}
									default :
									{
										System.out.println("\nINVALID SELECTION. PLEASE CHOOSE A VALID OPTION.");
									}

								}
							}
						}
						else
						{
							System.out.println("\nINVALID CREDENTIALS\n");
							System.out.println(" ATTEMPT LEFT : "+ --attempt);
						}

					}
					System.out.println("\nMAXIMUM LOGIN ATTEMPTS EXCEEDED. YOUR ACCOUNT HAS BEEN BLOCKED FOR 24 HOURS. \n");
					System.exit(0);   // JVM TERMINATE
					break;
				}




				case 2 :
				{
					if(name != null)
					{
						System.out.println("\n ACCOUNT ALREADY EXIST WITH THIS SYSTEM.");
						continue welcomeLoop;
					}
					System.out.println("\n=======NEW ACCOUNT REGISTRATION=======");
					System.out.print("Enter Full Name : ");
					name = new Scanner(System.in).nextLine();
					System.out.print("Create 4-digit PIN : ");
					pin = sc.nextInt();
					System.out.print("Initial Deposit Amount : ");
					balance = sc.nextDouble();
					System.out.print("Contact Number : ");
					contact = sc.nextLong();
					transactions.add("CREDIT | ACCOUNT OPENING : " + balance);
					System.out.println("\n ACCOUNT CREATED SUCCESSFULLY. WELCOME TO VYOMI BANK!!! \n");				
					break;
				}
				default :
				{
					System.out.println("\nINVALID OPTION. PLEASE SELECT 1 OR 2.");
				}

			}
		}
	}
}































import java.util.*;

public class Borrower
{
	int borrower_number;
	String borrower_name;
	String borrow_date;
	
	public Borrower(int borrower_number, String borrower_name, String borrow_date)
	{
		this.borrower_number = borrower_number;
		this.borrower_name = borrower_name;
		this.borrow_date = borrow_date;
	}
	
	public void setBorrowerNumber(int borrower_number)
	{
		this.borrower_number = borrower_number;
	}
	
	public void setBorrowerName(String borrower_name)
	{
		this.borrower_name = borrower_name;
	}
	
	public void setBorrowDate(String borrow_date)
	{
		this.borrow_date = borrow_date;
	}
	
	//getter method
	public int getBorrowerNumber()
	{
		return borrower_number;
	}
	
	public String getBorrowerName()
	{
		return borrower_name;
	}
	
	public String getBorrowDate()
	{
		return borrow_date;
	}
}
import java.util.*;

public class VideoData
{
	int video_number;
	String video_title;
	boolean available_for_borrowing;
	int borrower_number;
	String borrower_name;
	
	public VideoData(int video_number, String video_title, boolean available_for_borrowing, int borrower_number, String borrower_name)
	{
		this.video_number = video_number;
		this.video_title = video_title;
		this.available_for_borrowing = available_for_borrowing;
		this.borrower_number = borrower_number;
		this.borrower_name = borrower_name;
	}
	
	public void setVideoNumber(int video_number)
	{
		this.video_number = video_number;
	}
	
	public void setVideoTitle(String video_title)
	{
		this.video_title = video_title;
	}
	
	public void setAvailableBorrow(boolean available_for_borrowing)
	{
		this.available_for_borrowing = available_for_borrowing;
	}
	
	public void setBorrowerNumber(int borrower_number)
	{
		this.borrower_number = borrower_number;
	}
	
	public void setBorrowerName(String borrower_name)
	{
		this.borrower_name = borrower_name;
	}
	
	//getter method
	public int getVideoNumber()
	{
		return video_number;
	}
	
	public String getVideoTitle()
	{
		return video_title;
	}
	
	public boolean getAvailableBorrow()
	{
		return available_for_borrowing;
	}
	
	public int getBorrowerNumber()
	{
		return borrower_number;
	}
	
	public String getBorrowerName()
	{
		return borrower_name;
	}
	
}
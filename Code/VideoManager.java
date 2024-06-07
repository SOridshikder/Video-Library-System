import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VideoManager
{
	// Create an ArrayList to store video objects
       public static ArrayList<VideoData> video_Data = new ArrayList<>();
	   public static ArrayList<Borrower> borrower_Data = new ArrayList<>();
	   int current_search_index = 0;
	   int curr_Idx = 0;
	   //int next_Idx = 0;
	   //int prev_idx = 0;
	
	public void setInitialVideoList()
	{
		VideoData video1 = new VideoData(1, "video1", true, 1, "Mr. X" ); //true indicate that the video was borrowed
		VideoData video2 = new VideoData(2, "video2", false, 5, "Mr. Y" ); 
		VideoData video3 = new VideoData(3, "video3", false, 0, "");
		VideoData video4 = new VideoData(4, "video4", false, 5, "Mr. ppp");
		 
		video_Data.add(video1);
		video_Data.add(video2);
		video_Data.add(video3);
		video_Data.add(video4);
		
		String formattedDateTime = (" 2024-06-01 23:25:30");
		Borrower borrowUser = new Borrower(1,"Mr.X", formattedDateTime," ");
		borrower_Data.add(borrowUser);
	}
	
	public void showVideoSub(int idx)
	{
		
		idx = idx-1;
		if(video_Data.get(idx).getAvailableBorrow() == false)
		{
			System.out.println("This video has no borrower details!!!! ");
			return;
		}
		System.out.println("-------------------Number" + ( idx + 1)
						+ "-------------------");
						
		System.out.println("Video number: " + (idx + 1));
		System.out.println("Video Title: " + video_Data.get(idx).getVideoTitle() );
		System.out.println("Video Borrower number: " + video_Data.get(idx).getBorrowerNumber() );
		int borrower_idx = video_Data.get(idx).getBorrowerNumber();
		System.out.println("Video Borrower name: " + borrower_Data.get(borrower_idx-1).getBorrowerName());
		System.out.println("Video Borrow date: " + borrower_Data.get(borrower_idx-1).getBorrowDate());
		
	}
	
	public void videoInfo()
	{
		System.out.println("");
		System.out.println("----------------------All video List --------------------------");
		System.out.println("Total " + video_Data.size() + " of videos are available");
		System.out.println("");
		System.out.println(".............................................................");
		for (int i = 0; i <video_Data.size(); i++) 
		{
			if (video_Data.size()> 0) 
			{
				boolean available = video_Data.get(i).getAvailableBorrow();	
				if(!available)
				{
					System.out.println("Video number and title : " + (i + 1) + "  -  " + video_Data.get(i).getVideoTitle() + "" + "  Available for borrow");
				}
				else
				{
					System.out.println("Video number and title : " + (i + 1) + "  -  " + video_Data.get(i).getVideoTitle() + "" + "  Not Available for borrow");
				}
				System.out.println("........................................................");
			}
		}
		System.out.println("");
		System.out.println("Enter the corresponding number to show the video details if borrowed");
		System.out.println("If not interested then press 0");
		int choice = checkForValidInput(1, video_Data.size());
		if (choice>0 && choice<=video_Data.size())
		{
			showVideoSub(choice);
		}
		
	}
	
	public void addNewVideo()
	{
		System.out.print("");
		System.out.print("Please enter the Video Title to be added:");//Video title
		Scanner scanner = new Scanner(System.in);
		String video_title = scanner.nextLine();
		
		//check if the same video file name already exist or not>....
		for (int i = 0; i <video_Data.size(); i++) 
		{
			if (video_Data.size()> 0) 
			{
				if(video_Data.get(i).getVideoTitle().equalsIgnoreCase(video_title ))
				{
					System.out.println(" this video name already exist!!!");
						//System.out.println("----New video added successfully!!!----");
					System.out.println("");
					System.out.println("Continue adding new videos? then press 1 or if you want  to show the video then press 2");
					System.out.println("If not interested then press 0");
					int choice = checkForValidInput(1, 2);
					if (choice==1)
					{
						addNewVideo();
					}
					else if (choice==2)
					{
						videoInfo();
					}  
					return;
				}
			}
		}
		video_title = video_title;
		VideoData video = new VideoData(video_Data.size(), video_title, false, 0, "" );
		video_Data.add(video);
		
		System.out.println("----New video added successfully!!!----");
		System.out.println("");
		System.out.println("Continue adding new videos? then press 1 or if you want  to show the video then press 2");
		System.out.println("If not interested then press 0");
		int choice = checkForValidInput(1, 2);
		if (choice==1)
		{
			addNewVideo();
		}
		else if (choice==2)
		{
			videoInfo();
		}  
	}
	
	
	public void deleteVideo()
	{
		System.out.print("Please enter the Video Title to be deleted:");//Video title
		Scanner scanner = new Scanner(System.in);
		String video_title = scanner.nextLine();
		 
		boolean isFound = isVideoAlreadyExist(video_title);
		if(isFound)
		{
			if(video_Data.get(current_search_index).getAvailableBorrow()==false)
			{
				video_Data.remove(current_search_index);
				System.out.println(" Video deleted successfully!!!");
			}
			else
			{
				System.out.println("Sorry this video can't be deleted because already borrowed!!!");
			}
		}
		if(!isFound)
		{
			System.out.println("Could not found the video you entered!!!");
		}
		
		System.out.println("");
		System.out.println("Continue deleting videos? then press 1 or if you want  to show the video then press 2");
		System.out.println("If not interested then press 0");
		int choice = checkForValidInput(1, 2);
		if (choice==1)
		{
			deleteVideo();
		}
		else if (choice==2)
		{
			videoInfo();
		}
	}
	
	
	public void borrowVideo()
	{
		System.out.print("Please enter the Video Title to be Borrowed:");//Video title
		Scanner scanner = new Scanner(System.in);
		String video_title = scanner.nextLine();
		boolean isFound = isVideoAlreadyExist(video_title);
		int i;
		if(isFound)
		{
			i = current_search_index;
			if(video_Data.get(i).getAvailableBorrow()==false)
			{
				System.out.print("Please enter the Borrower name:");
				String borrower_name = scanner.nextLine();
				
				// Get the current date and time
				LocalDateTime currentDateTime = LocalDateTime.now();
				
				 // Define a formatter to display the date and time in a readable format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				
				// Format the current date and time
				String formattedDateTime = currentDateTime.format(formatter);
				
				Borrower borrowUser = new Borrower(borrower_Data.size(),borrower_name, formattedDateTime,"");
				borrower_Data.add(borrowUser);
				
				// Print the current date and time
				//System.out.println("Current Date and Time: " + formattedDateTime);
				
				video_Data.get(i).setAvailableBorrow(true);
				video_Data.get(i).setBorrowerNumber(borrower_Data.size());
				video_Data.get(i).setBorrowerName(borrower_name);
				System.out.println(" Video Borrowed successfully!!!");
				
			}
			
			else
			{
				System.out.println(" Sorry!!! this video already borrowed by " + video_Data.get(i).getBorrowerName());
			}
		}
		
		if(!isFound)
		{
			System.out.println("Could not found the video you entered!!!");
		}
		System.out.println("");
		System.out.println("Continue borrowing videos? then press 1 or if you want  to show the video then press 2");
		System.out.println("If not interested then press 0");
		int choice = checkForValidInput(1, 2);
		if (choice==1)
		{
			borrowVideo();
		}
		else if (choice==2)
		{
			videoInfo();
		}
	}
	
	public void updateVideoRecord()
	{
		System.out.print("Please enter the Video Title you want to update:");//Video title
		Scanner scanner = new Scanner(System.in);
		String video_title = scanner.nextLine();
		boolean isVideoFound = isVideoAlreadyExist(video_title);
		if(isVideoFound)
		{
			System.out.print("Please enter the updated Video file name:");//Video title
			Scanner scanner1 = new Scanner(System.in);
			String update_video_title = scanner1.nextLine();
			boolean isExistUpdateName = isVideoAlreadyExist(update_video_title);
			if(!isExistUpdateName)
			{
				int i = current_search_index;
				video_Data.get(i).setVideoTitle(update_video_title);
				System.out.print("Video file name updated successfully!!!:");
			}
			else
			{
				System.out.print("This Video file name can not be updated because this file name already exist!!!:");
			}
		}
		else
		{
			System.out.println("Could not found the video you entered!!!");
		}
		System.out.println("");
		System.out.println("Continue modifying videos? then press 1 or if you want  to show the video then press 2");
		System.out.println("If not interested then press 0");
		int choice = checkForValidInput(1, 2);
		if (choice==1)
		{
			updateVideoRecord();
		}
		else if (choice==2)
		{
			videoInfo();
		}
	}
	
	public boolean isVideoAlreadyExist(String video_title)
	{
		boolean isFound = false;
		for (int i = 0; i <video_Data.size(); i++) 
		{
			if (video_Data.size()> 0) 
			{
				if(video_Data.get(i).getVideoTitle().equalsIgnoreCase(video_title))
				{
					current_search_index = i;
					isFound = true;
					return true;
				}
			}
		}
		if(!isFound)
		{
			//System.out.println("Could not found the video you entered!!!");
			return false;
		}
		return false;
	}
	
	public void serchVideoRecord()
	{
		System.out.print("Please enter the Video Title you want to search:");//Video title
		Scanner scanner = new Scanner(System.in);
		String video_title = scanner.nextLine();
		boolean isVideoFound = isVideoAlreadyExist(video_title);
		if(isVideoFound)
		{
			int idx = current_search_index;
			
			boolean available = video_Data.get(idx).getAvailableBorrow();
			if(!available)
			{
				System.out.println("-----------------------Number" + ( idx + 1)
						+ "--------------------------");
						
			System.out.println("Video number: " + (idx+1));
			System.out.println("Video Title: " + video_Data.get(idx).getVideoTitle());
				System.out.println("Availability of borrow status:  Available for borrow");
			}
			else
			{

				showVideoSub(idx+1);
			}
		}
		else
		{
			System.out.println("Could not found the video you entered!!!");	
		}
		
		System.out.println("");
		System.out.println("Continue searching videos? then press 1 or if you want  to show the video then press 2");
		System.out.println("If not interested then press 0");
		int choice = checkForValidInput(1, 2);
		if (choice==1)
		{
			serchVideoRecord();
		}
		else if (choice==2)
		{
			videoInfo();
		}
	}
	
	public void showAvailableVideo()
	{
		for (int i = 0; i <video_Data.size(); i++) 
		{
			if (video_Data.size()> 0) 
			{

				//System.out.println("Video number and title : " + (i + 1) + "-" + video_Data.get(i).getVideoTitle() + "");
				
				boolean available = video_Data.get(i).getAvailableBorrow();
				if(!available)
				{
					System.out.println("Video number and title : " + (i + 1) + "-" + video_Data.get(i).getVideoTitle() + "" + "  Available for borrow");
				}
			}
		}
	}
	
		public void showNavigationBar()
	{
		System.out.println("-----------------------------------------------------Navigation Bar----------------------------------------");
		
		System.out.println(" If you want to Show current video then press 1");
		System.out.println(" If not interested then press 0");
		
		if(video_Data.size()>0)
		{
			int input = checkForValidInput(1, 1);
			if(input==1)
			{
				showCurrentVideo();
			}
		}
	}
	
	public void showCurrentVideo()
	{
		System.out.println("-----------------------Currently playing---------------------");
		System.out.println("Video number and title : " + (curr_Idx + 1) + "-" + video_Data.get(curr_Idx).getVideoTitle());
		System.out.println();
		System.out.println(" Do you want to show next or previous video?");
		System.out.println(" Show Next video:      1");
		System.out.println(" Show Previous video:  2");
		System.out.println(" If not interested then press 0");
		
		int choice = checkForValidInput(1, 2);
		if (choice==1)
		{
			showVideoByStatus(1);
		}
		else if(choice==2)
		{
			showVideoByStatus(2);
		}
	}
	
	public void showVideoByStatus(int video_stage)
	{
		if(video_stage==1)
		{
			if(curr_Idx<video_Data.size()-1)
			{
				curr_Idx = curr_Idx+1;
				
			}
			else
			{
				curr_Idx = 0;
			}
			showCurrentVideo();
		}
		
		else if(video_stage==2)
		{
			if(curr_Idx>0)
			{
				curr_Idx = curr_Idx-1;
				
			}
			else
			{
				curr_Idx = video_Data.size()-1;
				System.out.println(" No more previos video");
			}
			showCurrentVideo();
		}
	}
	
	public int checkForValidInput(int input_min, int input_max)
	{
		Scanner scan = new Scanner(System.in);
		int input=0;
        boolean valid = false;
        while(! valid) //Error Handling For Menu Input
        {
            System.out.print("Please Enter Your Option->");
            try
            {
             input=scan.nextInt();
             if(input>=input_min && input<=input_max)
             {
                valid = true;
				return input;
             }
			 else if(input==0)
			 {
				 return input;
			 }
             else 
             {
               System.out.println("Out of Range");
             }
            }
            catch(InputMismatchException e)
            {
            System.out.println("Not a valid number.");
            scan.next();
            } 
        }
		return input;
	}
}


import java.util.*;
public class VideoLibrarySystemMain
{
	VideoManager videoManager = new VideoManager();
    public static void main(String[] args) 
    {
		VideoLibrarySystemMain main = new VideoLibrarySystemMain();
		main.initializeVideoData();
		System.out.println("********************************************************"); 
		System.out.println("           Welcome To The Video Library System          "); 
		System.out.println("********************************************************"); 
		main.displayMainMenu();
	}
	
	
	public void initializeVideoData()
	{
		videoManager.setInitialVideoList();
	}		
	
	public void displayMainMenu() 
    {
		System.out.println("");
		System.out.println("Select From The Following Options: ");  
        System.out.println("---------------------------------------------------------");
		System.out.println("Press 0 to show all video list");
        System.out.println("Press 1 to Add new video to the system");
        System.out.println("Press 2 to Borrow video");
        System.out.println("Press 3 to Modify video record");
        System.out.println("Press 4 to Delete video record");
		System.out.println("Press 5 to Search video record");
		System.out.println("Press 6 to Report of available videos");
        System.out.println("Press 7 to Navigate The Program");
		System.out.println("Press 8 to Return Any Book");
		System.out.println("Press 9 to Exit The Program");
        System.out.println(  "---------------------------------------------------------");
        Scanner scan = new Scanner(System.in);
        int input=0;
        boolean valid = false;
        while(! valid) //Error Handling For Menu Input
        {
            System.out.print("Please Enter Your Option->");
            try
            {
             input=scan.nextInt();
             if(input>=0 && input<=8)
             {
                valid = true;
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
        switch (input){
			case 0: //show all video0
			videoManager.videoInfo();
            returnMethod();
            break;
            case 1: //Add new video
            videoManager.addNewVideo();
			returnMethod();
            break;
            case 2://Borrow video
            videoManager.borrowVideo();
			returnMethod();
            break;
            case 3://update Video Record
            videoManager.updateVideoRecord();
			returnMethod();
            break;
			case 4://delete Video
			videoManager.deleteVideo();
			returnMethod();
			break;
			case 5://Search Video
			videoManager.serchVideoRecord();
			returnMethod();
			break;
			case 6://Report of Available Video
			videoManager.showAvailableVideo();
			returnMethod();
			break;
			case 7://Navigation bar ------additional task
			videoManager.showNavigationBar();
			returnMethod();
			break;
            case 8:
            //System.exit(0);
            break; 
            
        } 
				
    }
	
	 public void returnMethod()
	 {
		
		Scanner scanner = new Scanner(System.in);
        int i=0;
        boolean valid = false;
        while(! valid) //Error Handling For Menu Input
        {
            System.out.print("Press 1 to return to the function main menu->");
            try
            {
             i=scanner.nextInt();
             if(i==1)
             {
                valid = true;
             }
             else 
             {
               System.out.println("Out of Range");
             }
            }
            catch(InputMismatchException e)
            {
            System.out.println("Not a valid number.");
            scanner.next();
            } 
        }  
		displayMainMenu();
	}
}
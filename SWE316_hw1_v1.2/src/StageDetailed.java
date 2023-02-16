
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
public class StageDetailed extends StageManager{
	private Date date ;
	private LocalTime time;
	private String Lang="EN"; 
	public StageDetailed(String DocumentN, String ID,Date date,LocalTime time) {
		super(DocumentN, ID);
		this.date=date;
		this.time=time;
	}

	
	
	protected Date getDate() {
		return date;
	}



	protected void setDate(Date date) {
		this.date = date;
	}



	protected LocalTime getTime() {
		return time;
	}



	protected void setTime(LocalTime time) {
		this.time = time;
	}



	protected SimpleDateFormat setTime() {
		return new SimpleDateFormat("hh:mm a");
	}
	
	
	protected String getLang() {
		return Lang;
	}
	protected void  setLang(String Lang) {
		this.Lang= Lang; 
	}
	


	

}

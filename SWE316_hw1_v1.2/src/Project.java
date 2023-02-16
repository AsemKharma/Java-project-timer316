import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
public class Project extends info {
	private String NodeID;
	private String CostumerProjectID;
	private String Currency="SAR";
	private String CreatedOn;
	private int StageLevel=1;
	private int CustomerID;
	private Date StartDate;
	private Date EndDate;
	String changedon;

	private ArrayList<Stage_properties> stages =new  ArrayList<Stage_properties>();
	Project(String NodeID,String CostumerProjectID,int StageNumber,Date StartDate,Date EndDate,int CustomerID,String Currency ,String CreatedOn,String Changedon){
		this.NodeID=NodeID;
		this.CostumerProjectID=CostumerProjectID;
		this.StageLevel=StageNumber;
		this.StartDate=StartDate;
		this.EndDate=EndDate;
		this.CustomerID=CustomerID;
		this.CreatedOn=CreatedOn;
		this.changedon=Changedon;
		this.Currency=Currency;
		
	} 
	public String getNodeID() {
		return NodeID;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public ArrayList<Stage_properties> getStages() {
		return stages;
	}
	public int getStageLevel() {
		return StageLevel;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public String getCostumerProjectID() {
		return CostumerProjectID;
	}
	public String getCurrency() {
		return Currency;
	}
	public String getCreatedOn() {
		return CreatedOn;
	}
	public int getStageNumber() {
		return StageLevel;}

	public void AddStage(Stage_properties stage) {
		
		stages.add(stage);
	}
public void replaceStage(int stagenum,StageManager stage) {
		
     //later to be added
	}
	
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}


	public  void ReadExcel()  throws Exception, IOException {

		File fileStage= new File("./data/Stages.xls");		
		FileInputStream fileinp1 = new FileInputStream(fileStage);
		HSSFWorkbook workbook1 = new HSSFWorkbook(fileinp1);
		
		File fileDetails= new File("./data/Stages_Detailed.xls");		
		FileInputStream fileinp2 = new FileInputStream(fileDetails);
		HSSFWorkbook workbook2 = new HSSFWorkbook(fileinp2); 
		
		String ObjectVlaue, DocNum,  FieldName, ChangeInd, LanguageKey;
		int  TextFlag, NewStage, OldStage;
		Date date;
		LocalTime time;
		
		HSSFSheet sheet1 = workbook1.getSheetAt(0);	
		HSSFSheet sheet2 = workbook2.getSheetAt(0);	
		



		int rowIndex = 1, numOfRows = sheet1.getPhysicalNumberOfRows();
		
		//System.out.println(row.getRowNum()+"|||"+rowIndex);
		//StageManager stage;
	//	boolean finished = false;
		Boolean FOUND = false;

		while(rowIndex<numOfRows&&(!FOUND)) {

			ObjectVlaue = sheet1.getRow(rowIndex).getCell(0).getStringCellValue();
			while(ObjectVlaue.equals(NodeID)) {
				if(ObjectVlaue.equals("005056AB1EC01EDBBFD057AF0C4A4B8D"))
				System.out.println(ObjectVlaue);
				
				FOUND = true;
				ObjectVlaue = sheet1.getRow(rowIndex).getCell(0).getStringCellValue();	
				int docNum = (int) sheet1.getRow(rowIndex).getCell(1).getNumericCellValue();
				DocNum = Integer.toString(docNum);
				FieldName = sheet1.getRow(rowIndex).getCell(2).getStringCellValue();
				ChangeInd = sheet1.getRow(rowIndex).getCell(3).getStringCellValue();
				TextFlag = (int) sheet1.getRow(rowIndex).getCell(4).getNumericCellValue();
				NewStage = (int) sheet1.getRow(rowIndex).getCell(5).getNumericCellValue();
				
				if(!ChangeInd.equals("J")) 
					OldStage = (int) sheet1.getRow(rowIndex).getCell(6).getNumericCellValue();
				else
					OldStage = -1;
				
				date = sheet2.getRow(rowIndex).getCell(2).getDateCellValue();
				time = sheet2.getRow(rowIndex).getCell(3).getLocalDateTimeCellValue().toLocalTime();				
				LanguageKey = sheet2.getRow(rowIndex).getCell(4).getStringCellValue();
				rowIndex++;
			
				SStage stageFile = new SStage(DocNum, ObjectVlaue, FieldName, ChangeInd, TextFlag,
						 NewStage,OldStage); 
				StageDetailed stageDetatilFile = new StageDetailed(DocNum,ObjectVlaue,date,time);
				stages.add(new Stage_properties(stageFile, stageDetatilFile));
				
					if(rowIndex<numOfRows)
						ObjectVlaue = sheet1.getRow(rowIndex).getCell(0).getStringCellValue();
					else {
						ObjectVlaue = "123";
					}
					}
			
			
			
			if(!FOUND)
				rowIndex++;
		}
		workbook1.close();
		fileinp1.close();
		workbook2.close();
		fileinp2.close();
		return;
	}

}
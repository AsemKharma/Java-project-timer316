import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;


import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileInputStream;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


import javafx.application.Application;
import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


import java.util.*;
public class main extends Application {

	ArrayList<Project> Projects = new ArrayList<Project>();
	static Pane timepane=new Pane();
	static Pane duration= new Pane(); 
	static Pane datepane=new Pane();
	static Pane labelpane=new Pane();
	static BorderPane rework=new BorderPane();

	
	static Pane primePane = new Pane();
	boolean read = false;
	static Label t1 = new Label();
	static Line mainLine=new Line(0,30 , 900, 30);
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		if(!read) {readExcel();}
		System.out.println("id is "+Projects.get(4).getCustomerID());
		
		//to choose project.
		ListView<String> list = new ListView<String>();
		

		t1.setLayoutX(350);
		t1.setLayoutY(100);
		t1.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(2), Insets.EMPTY)));
		list.getItems().add(String.format(" %22s %25s ","Project ID", "Stage"));
		
		

		
		


		for(int i =0 ; i<Projects.size() ; i++) {
			list.getItems().add(String.format("%-2s %20s  %20s",(i+1)+"",Projects.get(i).getCostumerProjectID(),Projects.get(i).getStageNumber() ));
		}
////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
		

		

         //sample for testing 
		
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		list.setOnMouseClicked(e-> listclicked((list.getSelectionModel().getSelectedIndex()),(list.getSelectionModel().getSelectedItem())));
		
				
		
		primePane.getChildren().addAll(list,t1,timepane,labelpane,duration,datepane,rework);
		primePane.setStyle("-fx-background-color: #A6E0FF");
		
		
		
		Scene scene = new Scene(primePane, 1400, 800);
		primaryStage.setTitle("316Project");
		
		

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	
	
	
	
	
	
	public static void TimeLine(Project project) {
		try {
			timepane.getChildren().clear();
			duration.getChildren().clear();
			datepane.getChildren().clear();
			labelpane.getChildren().clear();
			rework.getChildren().clear();
			
//			datepane.setStyle("-fx-background-color: WHITE");
//			duration.setStyle("-fx-background-color: ORANGE");
//			timepane.setStyle("-fx-background-color: ORANGE");

			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			Object[] startendtime=DaysFinder(project.getStages(),format);
			
			
			Date starttimeline= new SimpleDateFormat("dd/MM/yyyy").parse("01"+format.format(startendtime[1]).substring(2, 10));
			Label starttimelinelabel=new Label(format.format(starttimeline));
			starttimelinelabel.setLayoutX(-20);
			starttimelinelabel.setLayoutY(0);
			starttimelinelabel.setRotate(90);
			starttimelinelabel.setTextFill(Color.CRIMSON);
			datepane.getChildren().add(starttimelinelabel);
			
			
			
			String xtemp=format.format(startendtime[2]);
			Date endtimeline= new SimpleDateFormat("dd/MM/yyyy").parse("01"+xtemp.substring(2, 4)+(Integer.parseInt(xtemp.substring(4,5))+1)+xtemp.substring(5,10));
		//	System.out.println(format.format(starttimeline)+" the end is "+format.format(endtimeline));
			Label endtimelinelabel=new Label(format.format(endtimeline));
			endtimelinelabel.setLayoutX(870);
			endtimelinelabel.setLayoutY(0);
			endtimelinelabel.setRotate(90);
			endtimelinelabel.setTextFill(Color.CRIMSON);
			
			
			
			
			
			long daysnum=0;
			daysnum=(long) ((endtimeline.getTime()-starttimeline.getTime())/(1000 * 60 * 60 * 24));
					
			
			
			//int offdays= (int) ((((Date)startendtime[1]).getTime()-starttimeline.getTime())/1000 * 60 * 60 * 24);
			
			
			
			duration.setLayoutX(250);
			duration.setLayoutY(500);
			

			labelpane.setLayoutX(250);
			labelpane.setLayoutY(650);
			
			timepane.setLayoutX(250);
			timepane.setLayoutY(650);
			
			datepane.setLayoutX(250);
			datepane.setLayoutY(710);
			
			Line mainLine=new Line(0,30 , 900, 30);
			timepane.getChildren().add(mainLine);
			
			ArrayList<Integer> days=new ArrayList<Integer>();
			ArrayList<Label> labels=new ArrayList<Label>();
			ArrayList<VBox> vboxes=new ArrayList<VBox>();
			ArrayList<Date> dates=new ArrayList<Date>();

			for(Stage_properties x:project.getStages()) {
							days.add((int) (((x.getDetails().getDate()).getTime()-(starttimeline).getTime())/(1000 * 60 * 60 * 24)));
							
			

			
			if(!(dates.contains(x.getDetails().getDate()))) {
			dates.add(x.getDetails().getDate());}


			
			
			Label label=new Label(	" "+(x.getStage().getNewStage())+" ");
			label.setPadding(new Insets(-2));
			
			if (x.getStage().getNewStage()>=help(x.getStage().getOldStage())) {
							label.setStyle("-fx-background-color: GREEN");
			}
			else {label.setStyle("-fx-background-color: RED");}
			labels.add(label);
			
			
			}
			
			
		//	System.out.println(project.getStages().size());
			System.out.println(days);

			
			
			
			//turning dates into labels
			ArrayList<Label> dateslabels=new ArrayList<Label>();
			for(Date x: dates) {
							
							Label labelinstance=new Label(format.format(x));
							labelinstance.setRotate(90);
							dateslabels.add(labelinstance);
							}
			
			
			
			
			int minnum=days.get(0);
			int maxnum=days.get(0);
			for(Integer x: days) {
							if (x> maxnum) {maxnum=x;}
							if(x<minnum) {minnum=x;}
			}
			float factorone= (float) (900.0/daysnum);

			Label durLabel=new Label("Duration is: "+(maxnum-minnum)+"days");
			durLabel.setLayoutX((factorone*((minnum+maxnum)/2))-40);
			Line duline=new Line(factorone*minnum ,20,factorone*maxnum,20);
			Line endline=new Line(factorone*maxnum,20,factorone*maxnum,30);
			Line startline=new Line(factorone*minnum,20,factorone*minnum,30);
			duration.getChildren().addAll(duline, durLabel,startline,endline);

			duline.setStyle("-fx-stroke: red;");
			endline.setStyle("-fx-stroke: red;");
			startline.setStyle("-fx-stroke: red;");
			durLabel.setStyle("-fx-stroke: red;");
			
			
			


			
			
			//rework pane
			Integer[] awarding =beforeAfterAwarding(labels);
			rework.setLayoutX(950);
			rework.setLayoutY(100);
			rework.setStyle("-fx-background-color: #EED8AE");//.setStyle("-fx-background-color: GREEN")
			
			Label lab1=new Label("                 rework                  ");
			lab1.setTextFill(Color.WHITE);
			lab1.setStyle("-fx-background-color: BROWN");
			rework.setTop(lab1);
			
			
			Label lab2=new Label();
			lab2.setText("Before Award     After Award");
			lab2.setStyle("-fx-background-color: DARKBLUE");
			lab2.setTextFill(Color.WHITE);
			rework.setBottom(lab2); 
		    
			Label lab3=new Label();
			lab3.setText( "             "+awarding[0]+"       ");
			lab3.setTextFill(Color.BLACK);
		    rework.setLeft(lab3); 
		    
		    Label lab4=new Label();
		    lab4.setText( "             "+awarding[1]+"      ");
		    rework.setRight(lab4); 
		    
			//rework pane
			
			
			
			
			
			float factor= (float) (900.0/daysnum);
			
for(int i=0;i<=daysnum;i++) {
				
				if (days.contains(i)) {
					Line lines=new Line(i*factor,-40 , i*factor, 30);
					timepane.getChildren().add(lines);
					
					

					
					
					
					
					
					
					///Date adding 
					Label inst=dateslabels.get(0);
					inst.setLayoutX(i*factor-30);
					inst.setLayoutY(0);
					dateslabels.remove(0);
					
					
					datepane.getChildren().add(inst);
					///Date adding 
				}
				
				else {
					Line lines=new Line(i*factor,20 , i*factor, 30);
					timepane.getChildren().add(lines);}
				}
datepane.getChildren().add(endtimelinelabel);







//create boxes for lables to be added 



ArrayList<Integer> count=new ArrayList<Integer>(); 


int holder=0;
for(Integer x:days) {
	if (holder==x) {
		continue;
	}
	else {
		holder=x;
		int occurrences = Collections.frequency(days, holder);
		count.add(occurrences);
		
	}
	
}

for (Integer x: count) {
	VBox tembox=new VBox();
	for (int i=0; i<x;i++) {
		
		tembox.getChildren().add(labels.get(0));
		
		labels.remove(0);
		
	}
	vboxes.add(tembox);
}
//create boxes for lables to be added








// adding lables to the UI
	
for(int i=0;i<=daysnum;i++) {
	
	if (days.contains(i)) {
		Pane  tempo=vboxes.get(0);
		vboxes.remove(0);
		tempo.setLayoutX(i*factor+2);
		tempo.setLayoutY(-40);
		labelpane.getChildren().add(tempo);
	    }
	}


	
	

			
	
		}
		catch (Exception e) {
			System.out.println(e);
		}
}	

	
	private static int help(Object o) {
if (o == null) {
	return 0;
}
else return (int)o;}

	private static Object[] DaysFinder(ArrayList<Stage_properties> stages, SimpleDateFormat format) {
		float daysnum=0;
		
		Date start=stages.get(0).getDetails().getDate();
		Date end=stages.get(0).getDetails().getDate();
		for(Stage_properties x: stages) {
			if (x.getDetails().getDate().getTime()>end.getTime()) {
				end=x.getDetails().getDate();
			}
			if(x.getDetails().getDate().getTime()<start.getTime()) {
				start=x.getDetails().getDate();
			}}
		
		
		
		daysnum=end.getTime()-start.getTime();
		daysnum=daysnum/(1000 * 60 * 60 * 24);
		Object[] returned={daysnum,start,end};
		return returned;
	}
	
	
	private static Integer[] beforeAfterAwarding(ArrayList<Label> labels) {
		int before=0;
		int after=0;
		boolean cond=true;
		
		
		for (Label x: labels) {
			String color=x.getStyle().substring(22).strip();
			
			System.out.println(cond+" if euqals"+color.equals("RED"));
			if (cond && color.equals("RED")) {
				before=before+1;
				System.out.println("first if:   "+x.getText()+"the style is"+color);
			}
			else if(color.equals("RED")) {
				after=after+1;
				System.out.println("second if:   "+x.getText()+"the style is"+color);}
			
			
			if(Integer.parseInt(x.getText().strip()   )==5) {
				System.out.println("cond change");
				cond=false;
			}
			
		}
				
		
		Integer[] returned={before,after};
		return  returned;
				
	}
	
	public void listclicked(int s1,String s2) {
		System.out.println(s1);
        t1.setText("Selected Project:    "+s2); // most get proj ID
        //String projectNum = s1.substring(18, 20);

          //  int number = Integer.parseInt(s1+1);
         TimeLine(Projects.get(s1-1));
	}

	public void readExcel()throws Exception, IOException {
		
		
		File file= new File("./data/Projects.xls");
		
		FileInputStream fileinp = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(fileinp); 
		
		//DataFormatter form = new DataFormatter();
		
		String NodeID, ProjectID,  Currency, CreatedOn, ChangedOn;
		int  Customer, Stage;
		Date StartDate, EndDate;
		HSSFSheet sheet = workbook.getSheetAt(0);	
		//NodeID = sheet.getRow(row).getCell(0).getStringCellValue();	

			
		Iterator<Row> rows = sheet.rowIterator();
		HSSFRow row = (HSSFRow) rows.next();
		row = (HSSFRow) rows.next();
		int rowIndex = 1;
	
		while(true) {		
		NodeID = sheet.getRow(rowIndex).getCell(0).getStringCellValue();
		ProjectID = sheet.getRow(rowIndex).getCell(1).getStringCellValue();				
		Stage = (int)sheet.getRow(rowIndex).getCell(2).getNumericCellValue();			


		Cell cell = row.getCell(3,MissingCellPolicy.RETURN_BLANK_AS_NULL);
		if(cell != null) 			
			StartDate = sheet.getRow(rowIndex).getCell(3).getDateCellValue();		
			else 
				StartDate = null;
	
	    cell = row.getCell(4,MissingCellPolicy.RETURN_BLANK_AS_NULL);
		if(cell != null) 
			EndDate = sheet.getRow(rowIndex).getCell(4).getDateCellValue();		
		else EndDate = null;
		
	    cell = row.getCell(5,MissingCellPolicy.RETURN_BLANK_AS_NULL);
		if(cell != null) 
			Customer = (int)sheet.getRow(rowIndex).getCell(5).getNumericCellValue();	
		else Customer = -1;
		
		
			Currency = sheet.getRow(rowIndex).getCell(6).getStringCellValue();
			CreatedOn = sheet.getRow(rowIndex).getCell(7).getStringCellValue();
			ChangedOn = sheet.getRow(rowIndex).getCell(8).getStringCellValue();
			ChangedOn = "";
			
	
		Project newProj = new Project(NodeID, ProjectID, Stage, StartDate, EndDate, Customer, Currency,CreatedOn, ChangedOn);
		newProj.ReadExcel();
		Projects.add(newProj);
		if(!rows.hasNext()) {
			
			workbook.close();
			fileinp.close();
			return;}
		else {
			row = (HSSFRow) rows.next();}
				
		rowIndex++;
		}
	}

}


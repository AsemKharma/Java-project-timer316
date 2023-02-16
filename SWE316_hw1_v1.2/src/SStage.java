public class SStage extends StageManager{
	private String FieldName;
	private String ChangeIndicator;
	private int TextFlag;
	private int OldStage;
	private int newStage; 
	SStage(){
		this("", "","","",0,0,0);

	}
	 SStage(String DocumentN, String ID, String fieldName, String changeIndicator, int textFlag,
			 int newStage,int oldStage) {
		super(DocumentN, ID);
		setFieldName(fieldName);
		setChangeIndicator(changeIndicator);
		setTextFlag(textFlag);
		setOldStage(oldStage);
		setNewStage(newStage);
	}

	protected String getFieldName() {
		return FieldName;
	}
	protected void setFieldName(String fieldName) {
		FieldName = fieldName;
	}
	protected String getChangeIndicator() {
		return ChangeIndicator;
	}
	protected void setChangeIndicator(String changeIndicator) {
		ChangeIndicator = changeIndicator;
	}
	protected int getTextFlag() {
		return TextFlag;
	}
	protected void setTextFlag(int textFlag) {
		TextFlag = textFlag;
	}
	protected int getOldStage() {
		return OldStage;
	}
	protected void setOldStage(int oldStage) {
		OldStage = oldStage;
	}
	protected int getNewStage() {
		return newStage;
	}
	protected void setNewStage(int newStage) {
		this.newStage = newStage;
	}
	
	
}

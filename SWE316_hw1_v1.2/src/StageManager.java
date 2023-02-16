public abstract class StageManager extends info{
	private String DocumentNumber;
	
	public StageManager(String DocumentNumber ,String ID) {
		setDocumentN(DocumentNumber);
		super.setID(ID);
	}
	 public String getDocumentN() {
		 return DocumentNumber;
	 }
	 public void setDocumentN(String Document) {
		 this.DocumentNumber= Document;
	 }
	 

}

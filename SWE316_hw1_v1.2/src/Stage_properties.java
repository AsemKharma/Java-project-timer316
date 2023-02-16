

public class Stage_properties {
	private SStage stage;
	private StageDetailed details;
	public Stage_properties(SStage stage,StageDetailed details ) {
		this.stage=stage;
		this.details=details;
		
		
		
	}
	protected SStage getStage() {
		return stage;
	}
	protected void setStage(SStage stage) {
		this.stage = stage;
	}
	protected StageDetailed getDetails() {
		return details;
	}
	protected void setDetails(StageDetailed details) {
		this.details = details;
	}
	
	
	

}

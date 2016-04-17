package pullapi;

public class ObjectRelations {
	
	private String commonName;
	private String objectName;
	private String legacyId;
	private String platform;
	private String objectType;
	
	public ObjectRelations() {
		// Empty constructor
	}
	
	/**
	 * Prints object's contents to system out in Java
	 */
	public void printJava() {
		
		System.out.println("\tCommon Name: " + commonName);
		System.out.println("\tObject Name: " + objectName);
		System.out.println("\tLegacy Id: " + legacyId);
		System.out.println("\tPlatform: " + platform);
		System.out.println("\tObject Type: " + objectType + "\n");
	}
	
	/**
	 * Accessor for the common name of object relations (video)
	 * @return string of common name
	 */
	public String getCommonName() {
		return commonName;
	}
	
	/**
	 * Mutator for the common name of object relations (video)
	 * @param commonName string of common name
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	/**
	 * Accessor for the object name of object relations (video)
	 * @return string of the object name of object relations (video)
	 */
	public String getObjectName() {
		return objectName;
	}
	
	/**
	 * Mutator for the object name of object relations (video)
	 * @param objectName string of object name of object relations (video)
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * Accessor for the legacy id of object relations (video)
	 * @return string of the legacy id of object relations (video)
	 */
	public String getLegacyId() {
		return legacyId;
	}
	
	/**
	 * Mutator for the legacy id of object relations (video)
	 * @param legacyId string of the legacy id of object relations (video)
	 */
	public void setLegacyId(String legacyId) {
		this.legacyId = legacyId;
	}
	
	/**
	 * Accessor for the platform of object relations (video)
	 * @return string of the platform of object relations (video)
	 */
	public String getPlatform() {
		return platform;
	}
	
	/**
	 * Mutator for the platform of object relations (video)
	 * @param platform string of the platform of object relations (video)
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	/**
	 * Accessor for the object type of object relations (video)
	 * @return string of object type of object relations (video)
	 */
	public String getObjectType() {
		return objectType;
	}
	
	/**
	 * Mutator for the object type of object relations (video)
	 * @param objectType string of object type of object relations (video)
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
}

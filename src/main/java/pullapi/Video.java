package pullapi;

public class Video extends Article {

	private String thumbnail;
	
	/* Metadata */
	private String name;
	private String description;
	private String publishDate;
	private String title;
	private String longTitle;
	private int duration;
	private String url;
	private String slug;
	private String ageGate;
	private String classification;
	private String subClassification;
	private String networks;
	private String state;
	private boolean noAds;
	private boolean prime;
	private boolean subscription;
	private boolean downloadable;
	private String origin;
	private String genre;
	
	private ObjectRelations[] objRelations;
	
	public Video() {
		// Empty Constructor
	}
	
	public void printJava() {
		
		System.out.println("Thumbnail: " + thumbnail);
		System.out.println("Name: " + name);
		System.out.println("Description: " + description);
		System.out.println("Publish Date: " + publishDate);
		System.out.println("Title: " + title);
		System.out.println("Long Title: " + longTitle);
		System.out.println("Duration: " + duration);
		System.out.println("Url: " + url);
		System.out.println("Slug: " + slug);
		System.out.println("Age Gate: " + ageGate);
		System.out.println("Classification: " + classification);
		System.out.println("Sub Classification: " + subClassification);
		System.out.println("Networks: " + networks);
		System.out.println("State: " + state);
		System.out.println("No Ads: " + noAds);
		System.out.println("Prime: " + prime);
		System.out.println("Subscription: " + subscription);
		System.out.println("Downloadable: " + downloadable);
		System.out.println("Origin: " + origin);
		System.out.println("Genre: " + genre + "\n");
		
		for (int index = 0; index < objRelations.length; index++) {
			
			objRelations[index].printJava();
		}
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLongTitle() {
		return longTitle;
	}
	
	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getSlug() {
		return slug;
	}
	
	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	public String getAgeGate() {
		return ageGate;
	}
	
	public void setAgeGate(String ageGate) {
		this.ageGate = ageGate;
	}
	
	public String getClassification() {
		return classification;
	}
	
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	public String getSubClassification() {
		return subClassification;
	}
	
	public void setSubClassification(String subClassification) {
		this.subClassification = subClassification;
	}
	
	public String getNetworks() {
		return networks;
	}
	
	public void setNetworks(String networks) {
		this.networks = networks;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public boolean areAds() {
		return noAds;
	}
	
	public void setAds(boolean ads) {
		this.noAds = ads;
	}
	
	public boolean isPrime() {
		return prime;
	}
	
	public void setPrime(boolean prime) {
		this.prime = prime;
	}
	
	public boolean isSubscription() {
		return subscription;
	}
	
	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}
	
	public boolean isDownloadable() {
		return downloadable;
	}
	
	public void setDownloadable(boolean downloadable) {
		this.downloadable = downloadable;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void initObjRelations(int objRelSize) {
		
		objRelations = new ObjectRelations[objRelSize];
		
		for (int index = 0; index < objRelSize; index++) {
			objRelations[index] = new ObjectRelations();
		}
	}
	
	public ObjectRelations[] getObjRelations() {
		return objRelations;
	}
	
	public void setObjRelations(ObjectRelations[] objRelations) {
		this.objRelations = objRelations;
	}
	
}

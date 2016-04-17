package pullapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IGNAPI {
	
	/**
	 * Use as request type for articles
	 */
	public static final int ARTICLES = 0;
	/**
	 * Use as request type for videos
	 */
	public static final int VIDEOS = 1;
	
	/* Private Constants */
	private static final int PULL_SIZE = 2;
	private static final int MAX_PULL_SIZE = 20;
	private static final int START_INDEX = 30;
	private static final int NULL_INT = 0;
	private static final String NULL_STR = "null";
	private static final String ENCODE_TYPE = "UTF-8";
	private static final String ERROR = "Could not get request";

	public static void main(String[] args) {
		
		Article[] articles;
		Video[] videos;
		
		articles = pullArticles(START_INDEX, PULL_SIZE);
		
		if (articles != null) {
			
			for (int index = 0; index < PULL_SIZE; index++) {
				articles[index].printJava();
			}
		}
		
		videos = pullVideos(START_INDEX, PULL_SIZE);
		
		if (videos != null) {
			
			for (int index = 0; index < PULL_SIZE; index++) {
				videos[index].printJava();
			}
		}
		
	}
	
	/**
	 * Pull array of Videos from IGN API
	 * @see Video Class
	 * @param startIndex the index to start pagination from
	 * @param count the amount of items to pull. Max of 20
	 * @return array of video objects
	 */
	public static Video[] pullVideos(int startIndex, int count) {
		
		Video[] pulledItems = new Video[count];
		
		for (int index = 0; index < count; index++) {
			pulledItems[index] = new Video();
		}
		
		String result = getRequest(VIDEOS, startIndex, count);
		
		if (result.isEmpty()) {
			return null;
		}
		
		Gson gson = new Gson();
		JsonObject pullItem = gson.fromJson(result,  JsonElement.class)
				.getAsJsonObject();
		JsonArray pullArray = pullItem.getAsJsonArray("data");

		// Parses an 'array' of json objects from request
		for (int index = 0; index < count; index++) {
			
			JsonObject obj = pullArray.get(index).getAsJsonObject();
			JsonObject metaData = obj.getAsJsonObject("metadata");
			JsonArray jsonObjRelations = obj.getAsJsonArray("objectRelations");
			ObjectRelations[] objRelations = null;
			int objRelSize = jsonObjRelations.size();
			
			pulledItems[index].initObjRelations(objRelSize);
			objRelations = pulledItems[index].getObjRelations();
			
			try {
				pulledItems[index].setThumbnail(obj.get("thumbnail").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setThumbnail(NULL_STR);
			}
			try {
				pulledItems[index].setName(metaData.get("name").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setName(NULL_STR);
			}
			try {
				pulledItems[index].setDescription(metaData.get("description").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setDescription(NULL_STR);
			}
			try {
				pulledItems[index].setPublishDate(metaData.get("publishDate").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setPublishDate(NULL_STR);
			}
			try {
				pulledItems[index].setTitle(metaData.get("title").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setTitle(NULL_STR);
			}
			try {
				pulledItems[index].setLongTitle(metaData.get("longTitle").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setLongTitle(NULL_STR);
			}
			try {
				pulledItems[index].setDuration(metaData.get("duration").getAsInt());
			} catch (NullPointerException npe) {
				pulledItems[index].setDuration(NULL_INT);
			}
			try {
				pulledItems[index].setUrl(metaData.get("url").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setUrl(NULL_STR);
			}
			try {
				pulledItems[index].setSlug(metaData.get("slug").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setSlug(NULL_STR);
			}
			try {
				pulledItems[index].setAgeGate(metaData.get("ageGate").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setAgeGate(NULL_STR);
			}
			try {
				pulledItems[index].setClassification(metaData.get("classification").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setClassification(NULL_STR);
			}
			try {
				pulledItems[index].setSubClassification(metaData.get("subClassification").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setSubClassification(NULL_STR);
			}
			try {
				pulledItems[index].setNetworks(metaData.get("networks").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setNetworks(NULL_STR);
			}
			try {
				pulledItems[index].setState(metaData.get("state").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setState(NULL_STR);
			}
			try {
				pulledItems[index].setAds(metaData.get("noads").getAsBoolean());
			} catch (NullPointerException npe) {
				pulledItems[index].setAds(false);
			}
			try {
				pulledItems[index].setPrime(metaData.get("prime").getAsBoolean());
			} catch (NullPointerException npe) {
				pulledItems[index].setPrime(false);
			}
			try {
				pulledItems[index].setSubscription(metaData.get("subscription").getAsBoolean());
			} catch (NullPointerException npe) {
				pulledItems[index].setSubscription(false);
			}
			try {
				pulledItems[index].setDownloadable(metaData.get("downloadable").getAsBoolean());
			} catch (NullPointerException npe) {
				pulledItems[index].setDownloadable(false);
			}
			try {
				pulledItems[index].setOrigin(metaData.get("origin").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setOrigin(NULL_STR);
			}
			try {
				pulledItems[index].setGenre(metaData.get("genre").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setGenre(NULL_STR);
			}

			// Parses the object relations json values
			for (int objRelIndex = 0; objRelIndex < objRelSize; objRelIndex++) {
				
				JsonObject objectRel = jsonObjRelations.get(objRelIndex).getAsJsonObject();
				try {
					objRelations[objRelIndex].setCommonName(objectRel.get("commonName").getAsString());
				} catch (Exception e) {
					objRelations[objRelIndex].setCommonName(NULL_STR);
				}
				try {
					objRelations[objRelIndex].setObjectName(objectRel.get("objectName").getAsString());
				} catch (Exception e) {
					objRelations[objRelIndex].setObjectName(NULL_STR);
				}
				try {
					objRelations[objRelIndex].setLegacyId(objectRel.get("legacyId").getAsString());
				} catch (Exception e) {
					objRelations[objRelIndex].setLegacyId(NULL_STR);
				}
				try {
					objRelations[objRelIndex].setPlatform(objectRel.get("platform").getAsString());
				} catch (Exception e) {
					objRelations[objRelIndex].setPlatform(NULL_STR);
				}
				try {
					objRelations[objRelIndex].setObjectType(objectRel.get("objectType").getAsString());
				} catch (Exception e) {
					objRelations[objRelIndex].setObjectType(NULL_STR);
				}
			}
			pulledItems[index].setObjRelations(objRelations);
			
		}
		
		return pulledItems;
		
	}

	/**
	 * Pull an array of articles from IGN API
	 * @param startIndex the index to start pagination from
	 * @param count the number of items to pull
	 * @return array of articles
	 */
	public static Article[] pullArticles(int startIndex, int count) {
		
		Article[] pulledItems = new Article[count];
		
		for (int index = 0; index < count; index++) {
			pulledItems[index] = new Article();
		}
		
		String result = getRequest(ARTICLES, startIndex, count);
		
		if (result.isEmpty()) {
			return null;
		}
		
		Gson gson = new Gson();
		JsonObject pullItem = gson.fromJson(result, JsonElement.class)
				.getAsJsonObject();
		JsonArray pullArray = pullItem.getAsJsonArray("data");
		
		for (int index = 0; index < count; index++) {
			
			JsonObject obj = pullArray.get(index).getAsJsonObject();			
			JsonObject metaData = obj.getAsJsonObject("metadata");
			
			try {
				pulledItems[index].setThumbnail(obj.get("thumbnail").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setThumbnail(NULL_STR);
			}
			try {
				pulledItems[index].setHeadline(metaData.get("headline").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setHeadline(NULL_STR);
			}
			try {
				pulledItems[index].setNetworks(metaData.get("networks").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setNetworks(NULL_STR);
			}
			try {
				pulledItems[index].setState(metaData.get("state").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setState(NULL_STR);
			}
			try {
				pulledItems[index].setSlug(metaData.get("slug").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setSlug(NULL_STR);
			}
			try {
				pulledItems[index].setSubHeadline(metaData.get("subHeadline").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setSubHeadline(NULL_STR);
			}
			try {
				pulledItems[index].setPublishDate(metaData.get("publishDate").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setPublishDate(NULL_STR);
			}
			try {
				pulledItems[index].setArticleType(metaData.get("articleType").getAsString());
			} catch (NullPointerException npe) {
				pulledItems[index].setArticleType(NULL_STR);
			}
		}
		
		return pulledItems;
	}

	/**
	 * Gets a request for the IGN API
	 * uses the ign-apis.herokuapp.com request
	 * @param reqType the type of request (articles or videos)
	 * @param startIndex index to start pagination from
	 * @param count count of items to pull (max of 20)
	 * @return string of the response in json format
	 */
	public static String getRequest(int reqType, int startIndex, int count) {
		
		String strBase = "http://ign-apis.herokuapp.com/";
		String strType = null;
		String strStartIndex = "startIndex=";
		String strAnd = "\u0026";
		String strCount = "count=";
		String strUrl = null;
		URL url = null;


		if (count > MAX_PULL_SIZE) {
			return ERROR;
		}
		
		if (reqType == ARTICLES) {
			strType = "articles?";
		} else if (reqType == VIDEOS) {
			strType = "videos?";
		} else {
			return ERROR;
		}
		
		strStartIndex += startIndex;
		strCount += count;
		strUrl = strBase + strType + strStartIndex + strAnd + strCount;
		
		try {
			url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);		/* in milliseconds */
			conn.setConnectTimeout(15000);	/* in milliseconds */
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			
			conn.connect();
			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				return "";
			}
			InputStream is = conn.getInputStream();

			return readResponse(is);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	private static String readResponse(InputStream stream) throws IOException {
		
		StringBuilder buf = new StringBuilder();
		InputStreamReader reader = null;
		reader = new InputStreamReader(stream, ENCODE_TYPE);
		
		int i;
		char c;
		
		while ((i = reader.read()) != -1) {
			c = (char) i;
			buf.append(c);
		}
		
		return buf.toString();
	}

}

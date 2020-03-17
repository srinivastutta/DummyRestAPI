package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil extends ExcelUtil{
/**
 * This method used to covert POJO Object to JSON String
 * @param obj
 * @return JsonString
 */
	public static String getSerializedJason(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);
			System.out.println("JSON BODY PAYLOAD ====> "+jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return jsonString;
	}
}

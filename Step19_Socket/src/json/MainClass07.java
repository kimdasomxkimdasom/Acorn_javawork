package json;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainClass07 {
	public static void main(String[] args) {
		String source="{\"info\":{\"num\":99, \"name\":\"kim\"}, \"nums\":[10,20,30]}";
		JSONObject obj=new JSONObject(source);
		//"info"라는 키값으로 JSONObject 빼내기
		JSONObject info=obj.getJSONObject("info");
		// "nums"라는 키값으로 JSONArray 빼내기
		JSONArray nums=obj.getJSONArray("nums");
		
		// JSONObject 에서 값 빼내기 
		// "info"에 .찍어서 "num"의 99 뺴내기
		int a=info.getInt("num");
	
		// JSONArray 에서 값 빼내기 
		// "nums"에 .찍어서 "nums"의 10빼내기 -> 배열의 0번째 방 
		int b=nums.getInt(0);
		
		// JSONObject 에서 값 빼내기 
		int c=info.getInt("num");
		String d=info.getString("name");
		// JSONArray 에서 갑 ㅅ빼내기
		for(int i=0; i<nums.length(); i++){
			int tmp=nums.getInt(i);
		}
	}
}

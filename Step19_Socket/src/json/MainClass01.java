package json;

import org.json.JSONArray;
/*
 * {"오늘 할일":["은행가기","게임하기","친구만나기"]}
 * 
 * {"약속1":{"날짜":"2020년 12월 24일","친구":["김","이","박"]} }
 */
public class MainClass01 {
	public static void main(String[] args) {
		JSONArray arr=new JSONArray();
		arr.put(10);
		arr.put(20);
		arr.put(30);
		//JSONArray객체 안에 있는 정보를 JSON 표기법에 맞는 문자열로 얻어내기
		String result1=arr.toString();
		System.out.println("result1:"+result1);
		
		JSONArray arr2=new JSONArray();
		arr.put("kim");
		arr.put("gura");
		arr.put("babo");
		String result2=arr.toString();
		System.out.println("result2:"+result2);
	}
}

package json;

import org.json.JSONArray;

public class MainClass05 {
	public static void main(String[] args) {
		/*
		 * 인터넷으로 부터 다운받은 문자열이 json형식인 경우가
		 * 
		 */
		String info="[10,20,30,40,50]";
		//위의 문자열에서 숫자만 순서대로 추출해서 콘솔창에 순서대로 출력 할 수 있을까?
		JSONArray nums=new JSONArray();
		/*
		 *  nums에 들어있는 참조값을 이용해서 (JSONArray 객체의 메소드를 활용해서)
		 *  
		 *  0번쨰 인덱스 : 10
		 *  1번째 인덱스 : 20
		 *  .
		 *  .
		 *  4번째 인덱스 : 50
		 *  
		 *  위와 같은 형식으로 콘솔창에 출력해보세요.
		 */
		//for문을 이용해서

		for(int i=0; i<5; i++) {
			//int tmp=(int)nums.get(i);
			System.out.println(i +"번째 인덱스 :"+nums.getInt(i));
		}
	}
}

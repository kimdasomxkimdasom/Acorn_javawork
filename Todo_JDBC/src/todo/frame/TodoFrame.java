package todo.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import todo.dao.TodoDao;
import todo.dto.TodoDto;

public class TodoFrame extends JFrame
					implements ActionListener,PropertyChangeListener{
	//필드
	JTextField text_content;
	DefaultTableModel model;
	JTable table;
	
	//생성자
	public TodoFrame(String title) {
		super(title);
		//프레임의 레이아웃 법칙 지정하기
		setLayout(new BorderLayout());
		//상단 페널
		JPanel topPanel=new JPanel();
		topPanel.setBackground(Color.ORANGE);
		//패널을 상단에 배치하기
		add(topPanel, BorderLayout.NORTH);
		//패널에 추가할 UI객체를 생성해서
		JLabel label_content=new JLabel("할일");
		JLabel label_regdate=new JLabel("등록일");
		//아래 메소드에서 필요한 값을 필드에 저장하기
		text_content=new JTextField(10);
		JButton btn_add=new JButton("추가");
		//패널에 순서대로 추가하기
		topPanel.add(label_content);
		topPanel.add(text_content);
		topPanel.add(btn_add);
		
		//버튼에 Action command 지정 **
		btn_add.setActionCommand("add");
		//버튼에 리스너 등록 **
		btn_add.addActionListener(this);
		
		//추가한 정보를 프레임에 보이게 목록 기능 추가
		//회원 목록을 출력할 테이블
		table=new JTable();
		//칼럼명을 String[]에 순서대로 준비하기
		String[] colNames= {"번호","할일","등록일"};
		
		//테이블에 연결할 기본 모델 객체
		model=new DefaultTableModel(colNames,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				System.out.println(row+"|"+column);
				//번호만 수정 불가하게 하려면 여기를 어떻게 코딩하면 될까요?
				//return false; //<-- 이렇게만 적으면 모든 로우/칼럼이 수정 불가 
				
				if(column == 0) { //0번쨰 칼럼은
					return false; //수정 불가하게
				}else { //나머지 칼럼은
					return true; //수정 가능하게
				}
			}
		};
		
		//모델을 테이블에 연결하기
		table.setModel(model);
		//테이블의 내용이 scroll될 수 있도록 스크롤 페널로 감싼다.
		JScrollPane scPane=new JScrollPane(table);
		//스크롤 페널을 프레임의 중앙에 배치하기
		add(scPane,BorderLayout.CENTER);
		//회원 목록을 테이블에 출력하기
		printTodo();
		
		//삭제버튼을 만들고
		JButton btn_delete=new JButton("삭제");
		btn_delete.addActionListener(this);
		btn_delete.setActionCommand("delete");
		
		//삭제 버튼을 상단 페널에 추가
		topPanel.add(btn_delete);
			
		//테이블의 값이 바뀌는지 감시할 리스너 등록하기
		table.addPropertyChangeListener(this);
	
	}
	
	//할일목록을 테이블에 출력하는 메소드
	public void printTodo() {
		
		//회원 목록 불러오기
		TodoDao dao=new TodoDao();
		List<TodoDto> list=dao.selectAll(); //List에 담기 
		
		//기존에 출력된 내용 초기화
		model.setRowCount(0); // 0개의 row로 강제로 초기화 하고
				
		for(TodoDto tmp:list) {
			Vector<Object> row=new Vector<>();
			row.add(tmp.getNum());
			row.add(tmp.getContent());
			row.add(tmp.getRegdate());
			model.addRow(row);
		}
	}
	
	//메인 메소드
	public static void main(String[] args) {
		TodoFrame f=new TodoFrame("회원정보 관리");
		f.setBounds(100, 100, 800, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	@Override // implements ActionListener 하고 Override 하기
	public void actionPerformed(ActionEvent e) {
		//눌러진 버튼의 action command를 읽어온다.
		String command=e.getActionCommand();
		if(command.equals("add")) { //만일 추가버튼을 눌렀을때 
			addTodo();
		}else if(command.equals("delete")) {//만일 삭제버튼을 눌렀을때
			deleteTodo();
		}
	}
	
	//-----메소드구역------//
	
	//회원정보를 삭제하는 메소드
	private void deleteTodo() {
		//선택된 row의 인덱스를 읽어온다.
		int selectedIndex=table.getSelectedRow();
		if(selectedIndex == -1) {
			JOptionPane.showMessageDialog(this, "삭제할 row를 선택해라");
			return; //메소드를 여기서 끝내라
		}
		//선택한 row의 0번째 칼럼의 값(번호)을 읽어와서 int로 casting하기
		int num=(int)table.getValueAt(selectedIndex, 0);
		//삭제하기 전에 한번 확인하기
		int result=JOptionPane.showConfirmDialog(this, num+" 번 회원을 정말 삭제 하겠습니까?");
		//만일yes를 눌렀을떄
		if(result == JOptionPane.YES_OPTION);
			//MemberDao객체를 이용해서 삭제하기
			new TodoDao().delete(num);
			//UI업데이트 (목록 다시 출력하기)
			printTodo();
		
		//TodoDao 객체를 이용해서 삭제하기
		new TodoDao().delete(num);
		//UI 업데이트 (목록 다시 출력하기)
		printTodo();
	}
	
	//회원정보를 추가하는 메소드
	private void addTodo() {	
		//1. 입력한 할일을 읽어와서 
		//getText()메소드를 이용해서 문자열 읽어오기
		String content=text_content.getText(); 
		//String regdate=text_regdate.getText();
		
		//2.TodoDto 객체에 담고
		TodoDto dto=new TodoDto();
		dto.setContent(content);
		//dto.setRegdate(regdate);
		
		//3.TodoDao 객체를 이용해서 DB에 저장
		TodoDao dao=new TodoDao();
		//boolean을 추가해서 작업의 성공 여부 
		boolean isSuccess=dao.insert(dto);
		//실제로 저장되었는지 확인
		if(isSuccess) {
			JOptionPane.showMessageDialog(this, content+" 할일 추가 성공");
			//테이블에 다시 목록 불러오기
			printTodo();
		}else {
			JOptionPane.showMessageDialog(this, "할일 추가 실패!");
		}
	}
	
	//필드
	//table 칼럼이 수정중인지의 여부
	boolean isEditing=false;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("change!");
		System.out.println(evt.getPropertyName());
		//만일 table 칼럼에서 발생한 이벤트라면
		if(evt.getPropertyName().equals("tableCellEditor")) {
			if(isEditing) {
				//수정된 row 를 읽어와서 DB 에 반영한다.
				int selectedIndex=table.getSelectedRow();
				int num=(int)model.getValueAt(selectedIndex, 0);
				String content=(String)model.getValueAt(selectedIndex, 1);
				String regdate=(String)model.getValueAt(selectedIndex, 2);
				TodoDto dto=new TodoDto(num, content, regdate);
				new TodoDao().update(dto);
			}
			//isEditing 의 값을 반대로 바꿔준다. true => false, false => true
			isEditing=!isEditing;
		}
	}
	
}



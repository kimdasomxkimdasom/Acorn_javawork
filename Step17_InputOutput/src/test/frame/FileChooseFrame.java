package test.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileChooseFrame extends JFrame implements ActionListener{
	//필요한 필드 정의하기 (모든 메소드에서 접근 가능한 자원_
	JButton selectBtn, selectBtn2;
	
	//생성자
	public FileChooseFrame(String title) {
		super(title);
		setLayout(new BorderLayout());
		//버튼의 참조값을 필드에 저장해 놓는다 (다른 메소드에서 참조해서 사용하려고)
		this.selectBtn=new JButton("파일 선택하기");
		add(selectBtn, BorderLayout.NORTH);
		selectBtn.addActionListener(this);
		
		this.selectBtn2=new JButton(".txt파일 선택하기");
		add(selectBtn, BorderLayout.SOUTH);
		selectBtn2.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//c:/myFolder에서 시작되는 JFileChooser 객체 생성
		JFileChooser fc=new JFileChooser("c:/myFolder"); // ()처음 시작하는 위치를 선정해주는곳 (입력 안해도 됨)
		//파일을 선택해서 열 수 잇는 다이얼로그 띄우고 어떤 작업을 했는지 결과 int로 받기 
		int result=fc.showOpenDialog(this);
		
		if(result==JFileChooser.APPROVE_OPTION) {//파일을 선택하고 확인을 눌렀을때 
			//선택한 파일을 Acces 할 수 있는 File 객체의 참조값 얻어오기
			File selectedFile=fc.getSelectedFile();
			//선택한 파일의 이름 얻어오기
			String fileName=selectedFile.getName();
			//메시지 다이얼로그 띄우기
			JOptionPane.showMessageDialog(this, fileName+" 파일을 선택했네요?");
		}else if(result==JFileChooser.CANCEL_OPTION) {//취소 버튼을 눌렀을때 
			JOptionPane.showMessageDialog(this, "취소 버튼을 눌렀네요?");
		}else if(result == JFileChooser.ERROR_OPTION) {
			JOptionPane.showMessageDialog(this, "에러");
		}
		
		System.out.println("actionPerformed() 메소드가 종료 됩니다.");
		
	}
	//메인메소드
	public static void main(String[] args) {
		FileChooseFrame f=new FileChooseFrame("파일을 선택하는 프레임");
		f.setBounds(100, 100, 500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
}

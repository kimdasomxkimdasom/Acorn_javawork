package test.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import test.mypac.SubThread;

public class MainFrame03 extends JFrame implements ActionListener {
	//생성자
	public MainFrame03(String title) {
		super(title);
		setLayout(new BorderLayout());
		JButton btn=new JButton("알림 띄우기");
		btn.addActionListener(this);
		
		add(btn, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(this,"알림 입니다! ! !!");
		
		//익명의 local inner class를 이용해서 Thread객체를 생성하고 
		Thread t=new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("무언가 10초(오랜시간)이 걸리는 작업을 합니다.");
					Thread.sleep(10000);
					System.out.println("시간이 오래걸리는 작업이 끝났습니다.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};//<-익명의 클래스 : 클래스명은 몰라, 하지만 thread는 상속 받았어  
		  //{};중괄호 안에 run()메소드를  override하기 
		
		//스레드를 시작시킨다.
		t.start();
		System.out.println("actionPerformed() 메소드가 리턴 됩니다.");
	}
	
	public static void main(String[] args) {
		MainFrame03 f=new MainFrame03("메인프레임 01");
		f.setBounds(100, 100, 500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

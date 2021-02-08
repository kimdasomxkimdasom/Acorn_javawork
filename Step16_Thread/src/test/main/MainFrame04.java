package test.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import test.mypac.SubThread;

public class MainFrame04 extends JFrame implements ActionListener {
	// 생성자
	public MainFrame04(String title) {
		super(title);
		setLayout(new BorderLayout());
		JButton btn = new JButton("알림 띄우기");
		btn.addActionListener(this);

		add(btn, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(this, "알림 입니다! ! !!");

		// 익명의 local inner class를 이용해서 Thread객체를 생성하고
		new Thread() {
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
		}.start(); // 스레드를 시작시킨다
					// 변수값에 담지 않고 바로 .찍어서 사용 (일회용으로)
					// thread는 어차피 일회용 개념이기때문에 변수에 담지않고 일회용으로 사용

		System.out.println("actionPerformed() 메소드가 리턴 됩니다.");
	}

	public static void main(String[] args) {
		MainFrame04 f = new MainFrame04("메인프레임 01");
		f.setBounds(100, 100, 500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

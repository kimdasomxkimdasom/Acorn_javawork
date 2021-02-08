package test.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import test.mypac.DownloadTask;
import test.mypac.SubThread;

public class MainFrame08 extends JFrame implements ActionListener {
	// 생성자
	public MainFrame08(String title) {
		super(title);
		setLayout(new BorderLayout());
		JButton btn = new JButton("다운로드 알림 띄우기");
		btn.addActionListener(this);

		add(btn, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(this, "다운로드 알림 입니다! ! !!");
		//람다식 
		new Thread(()->{
			System.out.println("다운로드를 시작합니다...");
			try {
				for(int i=1; i<=100; i++) {
					System.out.println(i+" % ");
					Thread.sleep(100);
				}	
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //<-- 런타임 인셉션을 상속 받지 않는 인센셥은 제대로 잡아줘야한다. (try~catch)
			System.out.println("다운로드를 완료 했습니다.");
		}).start();
		
		System.out.println("actionPerformed() 메소드가 리턴 됩니다.");
	}

	public static void main(String[] args) {
		MainFrame08 f = new MainFrame08("메인프레임 08");
		f.setBounds(100, 100, 500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

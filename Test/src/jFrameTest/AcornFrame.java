package jFrameTest;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AcornFrame extends JFrame implements ActionListener{
	
	JButton sendBtn;
	
	public AcornFrame(String title) {
		super(title);
		setLayout(new BorderLayout());
		
		sendBtn=new JButton("전송");
		
		JPanel topPanel=new JPanel();
		topPanel.add(sendBtn);
		add(topPanel, BorderLayout.NORTH);
		
		sendBtn.addActionListener(this);
	}
	
	public static void main(String[] args) {
		AcornFrame t=new AcornFrame("acorn");
		t.setBounds(100, 100, 400, 400);
		t.setDefaultCloseOperation(EXIT_ON_CLOSE);
		t.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JOptionPane.showMessageDialog(this.sendBtn, "전송합니다");
	}
}

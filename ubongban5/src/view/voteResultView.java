package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dto.VoteDto;

public class voteResultView  extends JFrame implements ActionListener{

	//완료된 투표의 목록을 출력하게 하는 부분
	
	public voteResultView(List<VoteDto> list) {
		String str[] = new String[list.size()];
		JLabel jlabel = new JLabel("지난 투표결과");
		jlabel.setFont(new Font("아리따-돋움(TTF)-SemiBold", Font.PLAIN, 18));
		add(jlabel).setBounds(120, 10, 130, 30);
		
		
		TextArea resultView = new TextArea();

		
		add(resultView).setBounds(10, 50, 500, 300);


		
		for (int i = 0; i < list.size(); i++) {
			str[i] = "투표안건 : " + list.get(i).getVotecontent() + "\n찬성 : " + list.get(i).getAgreement() + "\t반대 : " + list.get(i).getDisagreement() + "\t기권 : " 
					  + list.get(i).getAbstain() + "\t투표인원 : " + list.get(i).getVotecount()+"\n\n ";
			resultView.append(str[i] + "\n");
			resultView.setFont(new Font("아리따-돋움(TTF)-Medium", Font.PLAIN,11));
		}
				
		setLayout(null);
		setBounds(100, 100, 550, 500);
		getContentPane().setBackground(Color.WHITE);	
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

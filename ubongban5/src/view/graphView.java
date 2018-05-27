package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.VoteDto;
import singleton.Singleton;

public class graphView extends JFrame implements ActionListener{

	VoteDto votedto = new VoteDto();

	   //투표의 현재 상황을 그래프로 보여줌
	   JTextField agreeCount = new JTextField(3);
	   JTextField DisagreeCount= new JTextField(3);
	   JTextField Abstain = new JTextField(3);
	   JTextField title = new JTextField();
	   
	   public graphView(VoteDto votedto) {   //찬성, 반대 숫자를 2개 받으면 됨.

	      this.votedto = votedto;
	      
          JLabel votecontent = new JLabel(" <투표안건 : " + votedto.getVotecontent()+" > ");
          add(votecontent).setBounds(100, 10, 290, 35);
          votecontent.setFont(new Font("돋움", Font.BOLD, 15));
          
	      add(new JLabel("찬성")).setBounds(390, 150, 50, 30);
	      add(agreeCount).setBounds(420, 150, 30, 30);
	      agreeCount.setEditable(false);
	      agreeCount.setBorder(null);
	      agreeCount.setFont(new Font(null, Font.PLAIN, 13));
	      
	      add(new JLabel("반대")).setBounds(390, 190, 50, 30);
	      add(DisagreeCount).setBounds(420, 190, 30, 30);
	      DisagreeCount.setEditable(false);
	      DisagreeCount.setBorder(null);
	      DisagreeCount.setFont(new Font(null, Font.PLAIN, 13));
	      
	      add(new JLabel("기권")).setBounds(390, 230, 100, 30);
	      add(Abstain).setBounds(420, 230, 30, 30);
	      Abstain.setEditable(false);
	      Abstain.setBorder(null);
	      Abstain.setFont(new Font(null, Font.PLAIN, 13));
	      
	      DrawingPanel drawingPanel = new DrawingPanel();
	      add(drawingPanel).setBounds(10, 10, 500, 300);
	      DrawActionListener drawActionListener = new DrawActionListener(votedto.getAgreement(), votedto.getDisagreement(), votedto.getAbstain(), drawingPanel);
	      agreeCount.setText(Integer.toString(votedto.getAgreement()));
	      DisagreeCount.setText(Integer.toString(votedto.getDisagreement()));
	      Abstain.setText(Integer.toString(votedto.getAbstain()));
//	      (new DrawActionListener(votedto.getAgreement(), votedto.getDisagreement(), votedto.getAbstain(), drawingPanel));

	      
	      setLayout(null);
	      setBounds(100, 0, 550, 400);
	      getContentPane().setBackground(Color.WHITE);
	      setVisible(true);
	      //setDefaultCloseOperation(EXIT_ON_CLOSE);
            
         }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
         
      }

      //그래피를 그리는 패널 클래스

      class DrawingPanel extends JPanel{
          
         int agreement, disagreement, abstain;
         public void paint(Graphics g){
             g.clearRect(0,0,getWidth(),getHeight());
             g.drawLine(50,250,350,250);
          
             for(int cnt = 1 ;cnt<11;cnt++){
                g.drawString(cnt *3 +"",25,255-20*cnt);
                g.drawLine(50, 250-20*cnt, 350,250-20*cnt);
             }
          
             g.drawLine(50,20,50,250);
             g.drawString("찬성",100,270);
             g.drawString("반대",200,270);
             g.drawString("기권",300,270);
             g.setColor(Color.GREEN);
          
             if (agreement>0)
                g.fillRect(110,250-agreement*6,10,agreement*6);
             if(disagreement>0)
                g.fillRect(210,250-disagreement*6,10,disagreement*6);
             if(abstain>0)
                g.fillRect(310,250-abstain*6,10,abstain*6);
          
          }
          
          void setScores(int agreement, int disagreement, int abstain){
             this.agreement=agreement;
             this.disagreement=disagreement;
             this.abstain = abstain;
          }
      }

      //버튼 눌렀을때 동작하는 리스너
      class DrawActionListener{
         // JTextField text1,text2,text3;
         int agreement; 
         int disagreement; 
         int abstain;
          DrawingPanel drawingPanel;
          
          public DrawActionListener(int agreement, int disagreement, int abstain, DrawingPanel drawingPanel){
             this.agreement=agreement;
             this.disagreement=disagreement;
             this.abstain=abstain;
             this.drawingPanel = drawingPanel;
          
             try
             {
         //          int agreement = agreement;
         //          int disagreement = disagreement;
         //          int abstain = abstain;
               
                drawingPanel.setScores(agreement, disagreement, abstain);
                drawingPanel.repaint();
             }
             catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(drawingPanel,"잘못된 숫자 입력입니다","에러메시지",JOptionPane.ERROR_MESSAGE);
             }
          
          }
      }





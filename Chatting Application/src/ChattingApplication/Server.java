package ChattingApplication;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;



public class Server implements ActionListener {

	JPanel p1;
	JTextField t1;
	JButton b1;
	static JPanel a1;
	static JFrame f1 = new JFrame();
	static Box vertical = Box.createVerticalBox();
	static ServerSocket skt;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	boolean typing;
	
	Server()
	{   
		
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70);
		f1.add(p1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chattingApplication/3.png"));
		Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);		
		JLabel li = new JLabel(i3);  //directly image is not inserted label is required
		li.setBounds(5,17,30,30);
		p1.add(li);
		
		li.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent ae)
			{
				System.exit(0);
			}
		});
		
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chattingApplication/elon.png"));
		Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);		
		JLabel l2 = new JLabel(i6);
		l2.setBounds(40,5,60,60);
		p1.add(l2);
		
		JLabel l3 = new JLabel("Elon Musk");
		l3.setBounds(110,16,100,18);
		l3.setFont(new Font("SAN_SERIF",Font.BOLD,18));
		l3.setForeground(Color.white);
		p1.add(l3);
		
		JLabel l4 = new JLabel("Active Now");
		l4.setBounds(110,35,100,20);
		l4.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
		l4.setForeground(Color.white);
		p1.add(l4);
		

	       Timer t = new Timer(1, new ActionListener(){
	           public void actionPerformed(ActionEvent ae){
	               if(!typing){
	                   l4.setText("Active Now");
	               }
	           }
	       });
		
	       t.setInitialDelay(2000);
		
		
		a1=new JPanel();
		a1.setBounds(5,75,440,570);
		a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		f1.add(a1);
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chattingApplication/video.png"));
		Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);		
		JLabel l5 = new JLabel(i9);  
		l5.setBounds(290,20,30,30);
		p1.add(l5);
		
		ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chattingApplication/phone.png"));
		Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		ImageIcon i13 = new ImageIcon(i12);		
		JLabel l6 = new JLabel(i13); 
		l6.setBounds(350,20,35,30);
		p1.add(l6);
		
		ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chattingApplication/3icon.png"));
		Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
		ImageIcon i16 = new ImageIcon(i15);		
		JLabel l7 = new JLabel(i16);  
		l7.setBounds(410,20,13,25);
		p1.add(l7);
		
		
		
		t1 = new JTextField();
		t1.setBounds(5,655,310,40);
		t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		f1.add(t1);
		
		 t1.addKeyListener(new KeyAdapter(){
	           public void keyPressed(KeyEvent ke){
	               l4.setText("typing...");
	               
	               t.stop();
	               
	               typing = true;
	           }
	           
	           public void keyReleased(KeyEvent ke){
	               typing = false;
	               
	               if(!t.isRunning()){
	                   t.start();
	               }
	           }
	       });
		
		b1=new JButton("Send");
		b1.setBounds(320,655,123,40);
		b1.setBackground(new Color(7,94,84));
		b1.setForeground(Color.white);
		b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		b1.addActionListener(this);
		f1.add(b1);
		
		f1.getContentPane().setBackground(Color.WHITE);
		f1.setSize(450,700);
		f1.setLayout(null);
		f1.setLocation(200,50);
		f1.setUndecorated(true);
		f1.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		String out= t1.getText();
		
		JPanel p2= FormatLabel(out);
		
		a1.setLayout(new BorderLayout());
		
		JPanel right = new JPanel(new BorderLayout());
		
		right.add(p2,BorderLayout.LINE_END);
		
		vertical.add(right);
		
		vertical.add(Box.createVerticalStrut(15));
		
		a1.add(vertical,BorderLayout.PAGE_START);
	
		
		FormatLabel(out);
		dout.writeUTF(out);
		t1.setText("");
		}
		catch(Exception e)
		{
			
		}
		
	}
	

	public static JPanel FormatLabel(String out) {
		
		JPanel p3= new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
		
		JLabel l1 = new JLabel("<html><p style=\"width : 150px\">"+out+"</p></html>");
		l1.setFont(new Font("Tahoma",Font.PLAIN,16));
		l1.setBackground(new Color(37,211,102));
		l1.setOpaque(true);
		l1.setBorder(new EmptyBorder(15,15,15,50));
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		JLabel l2 = new JLabel();
		l2.setText(sdf.format(cal.getTime()));
		
		p3.add(l1);
		p3.add(l2);
		return p3;
	}

	public static void main(String[] args) {
		
		
		new Server().f1.setVisible(true);
       
		String MsgInput ="";
		
		try
		{
		   skt= new ServerSocket(6001); 
			 
			 while(true)
			 {
				 s=skt.accept(); 
				 din= new DataInputStream(s.getInputStream());
				 dout = new DataOutputStream(s.getOutputStream());
				 
				 while(true)
				 {
					 MsgInput = din.readUTF();
					 JPanel p2 = FormatLabel(MsgInput);
					 JPanel left = new JPanel(new BorderLayout());
					 left.add(p2, BorderLayout.LINE_START);
					 vertical.add(left);
					 f1.validate();
				 }
			 }
			
		}catch(Exception e)
		{
			
		}
	
	
	}

}

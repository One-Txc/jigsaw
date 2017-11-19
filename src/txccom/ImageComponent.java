package txccom;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import txc.xxy.model.SudokuExt;
import txc.xxy.service.SudokuService;
public class ImageComponent extends JComponent {

	private class imgObj{
		public int x;				//x坐标
		public int y;				//y坐标
		public Image img;			//图片
		public Rectangle2D imgBox;
	}

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private SudokuExt startModel;
	
	private JPanel panel;
	private JButton recoverButton;		//复位按钮
	private JButton helpButton;			//帮助按钮
	private Rectangle2D[] imgBox;		//图片盒子---用来区分
	private Image[] image;				//图片
	private static int[] XX;
	private static int[] YY;



	
	public ImageComponent(){
		setLayout(new BorderLayout());
		int [][] jieshu = {
				{1,4,7},
				{2,5,8},
				{3,6,9}
		};
		startModel = new SudokuExt(jieshu, null, null);

		panel = new JPanel();
		recoverButton = new JButton("recover");
		recoverButton.setVisible(true);
		helpButton = new JButton("help");
		helpButton.setVisible(true);
		panel.add(helpButton);
		panel.add(recoverButton);

		image = new Image[9];
		imgBox = new Rectangle2D[9];
		XX = new int [9];
		YY = new int [9];
		for(int i=0; i<9; i++)
		{
			if(i<9) XX[i] = 300;
			if(i<6) XX[i] = 200;
			if(i<3) XX[i] = 100;
			
			if(i%3==0) YY[i] = 100;
			if(i%3==1) YY[i] = 200;
			if(i%3==2) YY[i] = 300;		
		}

		String osName = System.getProperty("os.name");
		if("Linux".equals(osName)){
			//linux 系统处理
			image[0] = new ImageIcon("image/pintu/1.gif").getImage();
			image[1] = new ImageIcon("image/pintu/2.gif").getImage();
			image[2] = new ImageIcon("image/pintu/3.gif").getImage();
			image[3] = new ImageIcon("image/pintu/4.gif").getImage();
			image[4] = new ImageIcon("image/pintu/5.gif").getImage();
			image[5] = new ImageIcon("image/pintu/6.gif").getImage();
			image[6] = new ImageIcon("image/pintu/7.gif").getImage();
			image[7] = new ImageIcon("image/pintu/8.gif").getImage();
		}else {
			//windos 系统处理
			image[0] = new ImageIcon("image\\pintu\\1.gif").getImage();
			image[1] = new ImageIcon("image\\pintu\\2.gif").getImage();
			image[2] = new ImageIcon("image\\pintu\\3.gif").getImage();
			image[3] = new ImageIcon("image\\pintu\\4.gif").getImage();
			image[4] = new ImageIcon("image\\pintu\\5.gif").getImage();
			image[5] = new ImageIcon("image\\pintu\\6.gif").getImage();
			image[6] = new ImageIcon("image\\pintu\\7.gif").getImage();
			image[7] = new ImageIcon("image\\pintu\\8.gif").getImage();
		}

		
		add(panel, BorderLayout.SOUTH);
		recoverAction action = new recoverAction();
		recoverButton.addActionListener(action);
		helpButton.addActionListener(new aotuAction());
		addMouseListener(new MouseHandler());
		repaint();
		
		//随机打乱图片模型
		daluan();
		repaint();
	}













	/**
	
	  * @Title: daluan
	  * @Description: 随机打乱图片位置
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	  */
	private void daluan() {
		String[] fangxiang = {"上移","下移","右移","左移"};
		for(int i=0;i<999;i++){
			int x = (int)(1+Math.random()*4);
			x = x%4;
			yidong(null,fangxiang[x]);
			System.out.print(x);
		}
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		for(int i=0;i<8;i++){
		      g.drawImage(image[i], XX[i], YY[i], 100, 100, null);
		      imgBox[i] = new Rectangle2D.Double(XX[i], YY[i],100,100);
		      g2.draw(imgBox[i]);
		}
		imgBox[8] = new Rectangle2D.Double(XX[8], YY[8],100,100);
	    g2.draw(imgBox[8]);
		
	}

	 public int dedao(Point2D p){
		 for (int i=0;i<8;i++){
			 if(imgBox[i].contains(p)) return i;
		 }
		 return 100;
	 }
	 public void yidong(Point2D p,int ii){
		 if (ii==100) return;
	      int xuhao = ii;
	  
	      if((XX[xuhao]-100)==XX[8]&&YY[xuhao]==YY[8]||XX[xuhao]==XX[8]&&(YY[xuhao]-100)==YY[8]||(XX[xuhao]+100)==XX[8]&&YY[xuhao]==YY[8]||XX[xuhao]==XX[8]&&(YY[xuhao]+100)==YY[8])
    	  {
	    	  //变换格子模型
	    	  startModel.ChangeModel(xuhao+1);
	    	  
	    	  int tempX;
    	      int tempY;
    	      
    	      tempX = XX[xuhao];
    	      tempY = YY[xuhao];
    	      
    	      XX[xuhao] = XX[8];
    	      YY[xuhao] = YY[8];
    	      XX[8] = tempX;
    	      YY[8] = tempY;
    	  }       
	      repaint();	 
	 }
	 
	 public void yidong(Point2D p,String fangxiang){
		 int xuhao = 999;
		 if(fangxiang.equals("上移")){
			 if((startModel.getX()+1)>=3){
				 return;
			 }
			xuhao = (startModel.cloneGezi())[startModel.getX()+1][startModel.getY()];
		 }else if(fangxiang.equals("下移")){
			 if((startModel.getX()-1)<0){
				 return;
			 }
			 xuhao = (startModel.cloneGezi())[startModel.getX()-1][startModel.getY()];
		 }else if(fangxiang.equals("左移")){
			 if((startModel.getY()+1)>=3){
				 return;
			 }
			xuhao = (startModel.cloneGezi())[startModel.getX()][startModel.getY()+1];
		 }else if(fangxiang.equals("右移")){
			 if((startModel.getY()-1)<0){
				 return;
			 }
			xuhao = (startModel.cloneGezi())[startModel.getX()][startModel.getY()-1];
		 }
    	  //变换格子模型
    	  startModel.ChangeModel(xuhao);
    	  xuhao--;
    	  
    	  int tempX;
	      int tempY;
	      
	      tempX = XX[xuhao];
	      tempY = YY[xuhao];
	      
	      XX[xuhao] = XX[8];
	      YY[xuhao] = YY[8];
	      XX[8] = tempX;
	      YY[8] = tempY;  
	     repaint();	 
	 }
	 
	 
	 public void swap(Point2D p,int ii){
		 if (ii==100) return;
	      int xuhao = ii;
	    	  int tempX;
    	      int tempY;
    	      
    	      tempX = XX[xuhao];
    	      tempY = YY[xuhao];
    	      
    	      XX[xuhao] = XX[8];
    	      YY[xuhao] = YY[8];
    	      XX[8] = tempX;
    	      YY[8] = tempY;
	    	  
    	  repaint();
	 }
	
	

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	private class MouseHandler extends MouseAdapter
	   {
	      @Override
		  public void mousePressed(MouseEvent event)
	      {
	         // add a new square if the cursor isn't inside a square
	    	  int ii = dedao(event.getPoint());
	    	  yidong(event.getPoint(),ii);
	      
	      }

	      @Override
		  public void mouseClicked(MouseEvent event)
	      {
	         // remove the imgBox square if double clicked
	    	  /*if(event.getClickCount()==4){
	    	  int ii = dedao(event.getPoint());
	    	  swap(event.getPoint(),ii);
	    	  }*/
	      }
	   }

	private class recoverAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			int [][] jieshu = {{1,4,7},{2,5,8},{3,6,9}};
			startModel = new SudokuExt(jieshu, null, null);
			for(int i=0; i<9; i++)
			{
				if(i<9) XX[i] = 300;
				if(i<6) XX[i] = 200;
				if(i<3) XX[i] = 100;
				
				if(i%3==0) YY[i] = 100;
				if(i%3==1) YY[i] = 200;
				if(i%3==2) YY[i] = 300;		
			}
			repaint();
		}
	}
	
	// 自动拼图提示
	private class aotuAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			Stack<String> stack = new Stack<>();
			
			int [][] jieshu = {{1,4,7},{2,5,8},{3,6,9}};
			SudokuExt end = new SudokuExt(jieshu, null, null);
			SudokuService ss = new SudokuService();
			SudokuExt jie = ss.jieda(new SudokuExt(startModel.cloneGezi(), null, null), end);
			int bushu=0; //需要的步数
			while(jie.hasOldGezi()){
				stack.push(jie.getTishi());
				bushu++;
				System.out.println(jie.toString()+jie.getTishi());
				jie = jie.getOldGezi();
			}
			System.out.println(jie.toString());
			System.out.println("一共需要"+bushu+"步");
			
			//自动解答
			MyTask task = new MyTask(stack);
			Timer timer = new Timer();
	        timer.schedule(task, 2000, 2000);
		}
	}
	
	
	public static void recover(){
		for(int i=0; i<9; i++)
		{
			if(i<9) XX[i] = 300;
			if(i<6) XX[i] = 200;
			if(i<3) XX[i] = 100;
			
			if(i%3==0) YY[i] = 100;
			if(i%3==1) YY[i] = 200;
			if(i%3==2) YY[i] = 300;	
		}
	}
	
	private class MyTask extends TimerTask {
		private Stack<String> stack;
		public MyTask(Stack<String> stack) {
			this.stack = stack;
		}
	    @Override  
	    public void run() {
	    	System.out.println("33333333");
	    	if(stack.isEmpty()){
	    		cancel();
	    	}else {
	    		yidong(null, stack.pop());
			}
	    } 
	}
}

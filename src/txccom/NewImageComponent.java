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
public class NewImageComponent extends JComponent {

    private class ImgGrid {
        public int x;				//x坐标
        public int y;				//y坐标
        public Image img;			//图片
        public Rectangle2D imgBox;  //图片盒子模型
    }

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private SudokuExt startModel;

    private ImgGrid[] imgObjArray;       //9图片对象集合

    private JPanel panel;
    private JButton recoverButton;		//复位按钮
    private JButton helpButton;			//帮助按钮



    public NewImageComponent(){
        setLayout(new BorderLayout());

        /*int [][] jieshu = {
                {1,4,7},
                {2,5,8},
                {3,6,9}
        };
        startModel = new SudokuExt(jieshu, null, null);*/

        panel = new JPanel();
        recoverButton = new JButton("recover");
        recoverButton.setVisible(true);
        helpButton = new JButton("help");
        helpButton.setVisible(true);
        panel.add(helpButton);
        panel.add(recoverButton);
        add(panel, BorderLayout.SOUTH);
        recoverAction action = new recoverAction();
        recoverButton.addActionListener(action);
        helpButton.addActionListener(new aotuAction());
        addMouseListener(new MouseHandler());

        //初始化
       initImgObjArray();

        repaint();

        //随机打乱图片模型
        daluan();
        repaint();
    }

    private void initImgObjArray(){
        imgObjArray = new ImgGrid[9];
        String osName = System.getProperty("os.name");
        for(int i=0; i<9; i++) {
            imgObjArray[i] = new ImgGrid();
            ImgGrid imageO = imgObjArray[i];
            imageO.x = i/3;
            imageO.y = i%3;
            if(i<8) {
                if ("Linux".equals(osName)) {
                    //linux 系统处理
                    imageO.img = new ImageIcon("image/n-pintu/" + (i) + ".gif").getImage();
                } else {
                    //windos 系统处理
                    imageO.img = new ImageIcon("image\\n-pintu\\" + (i) + ".gif").getImage();
                }
            }
        }
    }




    /**

     * @Title: daluan
     * @Description: 随机打乱图片位置
     * @param:    设定文件
     * @return void    返回类型
     * @throws
     */
    private void daluan() {
        String[] fangxiang = {"上移","下移","右移","左移"};
        for(int i=0;i<999;i++){
            int x = (int)(1+Math.random()*4);
            x = x%4;
            yidong(fangxiang[x]);
            System.out.print(x);
        }
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int width = 100;
        for(int i=0;i<9;i++){
            ImgGrid imgO = imgObjArray[i];
            if(imgO.img != null){
                g.drawImage(imgO.img, (imgO.x+1)*width, (imgO.y+1)*width, width, width, null);
            }
            imgO.imgBox = new Rectangle2D.Double((imgO.x+1)*width, (imgO.y+1)*width,width,width);
            g2.draw(imgO.imgBox);
        }
    }

    public int dedao(Point2D p){
        for (int i=0;i<9;i++){
            if(imgObjArray[i].imgBox.contains(p)) return i;
        }
        return 8;
    }


    /**
     * 移动某个格子到空白位置
     * @param index:需要移动的格子的index
     */
    public void yidong(int index){
        if (index==8) return;
        //int xuhao = index;

        ImgGrid nullGrid = imgObjArray[8];
        ImgGrid moveGrid = imgObjArray[index];

        //判断位置是否合法-移动的盒子需要在空白格子的周围
        if(isNearby(nullGrid, moveGrid)){
            //待理解
            //startModel.ChangeModel(xuhao+1);
            //交换ImgGrid的xy坐标
            int tempX = nullGrid.x;
            int tempY = nullGrid.y;
            nullGrid.x = moveGrid.x;
            nullGrid.y = moveGrid.y;
            moveGrid.x = tempX;
            moveGrid.y = tempY;
        }
        repaint();
    }

    //判断位置是否合法-移动的盒子需要在空白格子的周围
    private boolean isNearby(ImgGrid nullGrid, ImgGrid moveGrid){
        //x和y坐标都相等
        if(nullGrid.x == moveGrid.x && nullGrid.y == moveGrid.y){
            //这是同一个格子
            return false;
        }
        if(nullGrid.x == moveGrid.x || nullGrid.y == moveGrid.y){
            //x和y坐标存在一个相等相等
            if((nullGrid.x+1)==moveGrid.x || (nullGrid.x-1)==moveGrid.x
                 || (nullGrid.y+1)==moveGrid.y || (nullGrid.y-1)==moveGrid.y ){
                return true;
            }
        }
        return false;
    }

    public void yidong(String fangxiang){
        ImgGrid nullGrid = imgObjArray[8];

        //通过方向找到需要交换位置的盒子坐标
        int moveIndexX = nullGrid.x;
        int moveIndexY = nullGrid.y;
        if(fangxiang.equals("上移")){
          if(nullGrid.y==0){
              //如果y坐标为0则不存在上移动的操作
              return;
          }
          moveIndexY = nullGrid.y - 1;
        }else if(fangxiang.equals("下移")){
            if(nullGrid.y==2){
                //如果y坐标为2则不存在下移动的操作
                return;
            }
            moveIndexY = nullGrid.y + 1;
        }else if(fangxiang.equals("左移")){
            if(nullGrid.x==0){
                //如果x坐标为0则不存在左移动的操作
                return;
            }
            moveIndexX = nullGrid.x - 1;
        }else if(fangxiang.equals("右移")){
            if(nullGrid.x==2){
                //如果x坐标为2则不存在右移动的操作
                return;
            }
            moveIndexX = nullGrid.x + 1;
        }else {
            return;
        }
        //遍历imgObjArray找到目标
        ImgGrid moveGrid = null;
        for(int i=0;i<imgObjArray.length;i++){
           if(imgObjArray[i].x==moveIndexX && imgObjArray[i].y==moveIndexY){
               moveGrid = imgObjArray[i];
               break;
           }
        }
        if(isNearby(nullGrid, moveGrid)){
            //待理解 todo
            //startModel.ChangeModel(xuhao+1);
            //交换ImgGrid的xy坐标
            int tempX = nullGrid.x;
            int tempY = nullGrid.y;
            nullGrid.x = moveGrid.x;
            nullGrid.y = moveGrid.y;
            moveGrid.x = tempX;
            moveGrid.y = tempY;
        }
        repaint();

    }




    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    //鼠标事件
    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            int ii = dedao(event.getPoint());
            yidong(ii);
        }
        public void mouseClicked(MouseEvent event) {
	    	  /*if(event.getClickCount()==4){
	    	  int ii = dedao(event.getPoint());
	    	  swap(event.getPoint(),ii);
	    	  }*/
        }
    }

    //复位按钮事件
    private class recoverAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
           /* int [][] jieshu = {{1,4,7},{2,5,8},{3,6,9}};
            startModel = new SudokuExt(jieshu, null, null);*/
           initImgObjArray();
           repaint();
        }
    }

    // 自动拼图提示--帮助按钮
    private class aotuAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            /*Stack<String> stack = new Stack<>();
            int [][] jieshu = {{1,4,7},{2,5,8},{3,6,9}};
            SudokuExt end = new SudokuExt(jieshu, null, null);
            SudokuService ss = new SudokuService();
            SudokuExt jie = ss.jieda(new SudokuExt(startModel.getGezi(), null, null), end);
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
            timer.schedule(task, 2000, 2000);*/
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
                yidong(stack.pop());
            }
        }
    }
}

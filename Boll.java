package swing;
import javax.swing.JFrame; // для создания окна
import javax.swing.JPanel; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class Boll extends JFrame { 
	final int COUNT1=10;
	final String TITLE ="Главное окно";// делам переменную для названия заглавнго окна
	final int WIDTH1 =650; //делаем переменнуб для ширины окта
	final int HEIGHT1=650;// делаем переменнуб для высоты окна
	final Color[] COLORS= {Color.black, Color.blue, Color.red, Color.green}; // делаем массив цветоа из библиотеки КОЛОР
	Random ram; // создаем переменну рандом
	ArrayList <Ball> balls;
	int counter=0;
	int showDelay = 1000;
public Boll (){// конструтор класса
	ram= new Random();
	balls=new ArrayList<>();	 	 
    setTitle(TITLE);// название окна
	setDefaultCloseOperation(EXIT_ON_CLOSE);// закрытие окна
	 		// setSize(500,500);//размера окна
	 		// Ball ball = new Ball(0, 0, 0, null);// создали обьект ball и вызвали метод addBall();, по другому вообще не получаетя(
	 		//	 for (int i=0; i<COUNT1; i++) {
		 	//	      	ball.addBall();
	 		//	 System.out.println("This code work");
	 		// }
	Canvas canvas=new Canvas(); // создаем новый обьект класса конвас (хослт)
	canvas.setBackground(Color.white);// цвет фона
	canvas.setPreferredSize( new Dimension (WIDTH1,HEIGHT1));// размеры окна 
	canvas.addMouseListener( new MouseAdapter() {//Вызов метода addMouseListener() создаёт обработчик событий от мыши.
	@Override
	public void mouseReleased (MouseEvent ee) { // нажатие кнопки мыши
			 canvas.repaint();// перерисовка холста
			 //balls.clear(); 
			 // ball.addBall(); 
			 // можно добавлять по шарику
			 deleteBall(ee.getX(), ee.getY());/// метод для удаления шарика 
		 		}
		 	 });
	 	 add(canvas);// добавляем обьект конвас в наше окно
	 	 pack(); // скорректирует размер окна в соответствии с конвас 
	 	 setResizable(false);
	 	 setVisible(true); // видимое
	 	 setLocationRelativeTo(null);// начальное положение окна
	}
class Ball {
	 int x,y,d;
	 Color color;
	 	 Ball (int x, int y, int d, Color color){
		 this.x=x;
		 this.y=y;
		 this.d=d;
		 this.color=color;
		 for (int i=0; i<COUNT1; i++) {
		//      	addBall();
		//	 System.out.println("This code work");
		}
		}
void paint (Graphics gg ){
	// super.paint(gg);
	gg.setColor(color); // установить цвет для рисовальщика gg
	gg.fillOval(x, y, d, d);// нарисовать круг по координатам 
	gg.setColor(Color.black);
	gg.drawOval(x, y, d, d);
	 }
void addBall (){
	int d = (ram.nextInt(30))+30;
	int x =ram.nextInt(WIDTH1-d);
	int y = ram.nextInt(HEIGHT1-d);
	Color color = COLORS[ram.nextInt(COLORS.length)];
	balls.add(new Ball(x,y,d,color));
	//System.out.println("This code work4");
	}
  }
 class Canvas extends JPanel{
	 @Override
	 public void paint(Graphics gg) { //paint встроенный метод из свинг
		 super.paint(gg);
		// System.out.println("This code work");
		 for (Ball ball:balls) {
		// int d = (ram.nextInt(30))+30;
		// int x =ram.nextInt(WIDTH1-d);
		// int y = ram.nextInt(HEIGHT1-d);
		//	 Color color = COLORS[ram.nextInt(COLORS.length)];
		//	 gg.setColor(color); // установить цвет для рисовальщика gg
		//	 gg.fillOval(x, y, d, d);// нарисовать круг по координатам 
		//	 gg.setColor(Color.black);
		//	 gg.drawOval(x, y, d, d);
		ball.paint(gg);	
		}
	 }
 }
void deleteBall(int x, int y) { // метод для удаления шарика, при нажатии кнопки мыги  предаются координаты Х и Y 
    for (int i = balls.size() - 1; i > -1; i--) {// проверкака кажого обьекта, если совпал -1
        double dx = balls.get(i).x + balls.get(i).d/2 - x;// координата  по х 
        double dy = balls.get(i).y  + balls.get(i).d/2 - y;// координата  по y 
        double d = Math.sqrt(dx * dx + dy * dy);
        if (d < balls.get(i).d/2) {
            balls.remove(i);
            break;
        }
    }
}
void game() {
    while (true) {
    	Ball ball= new Ball(0, 0, 0, null);
        ball.addBall();
        //canvas.repaint();
        if (balls.size() >= 5) {
            System.out.println("Game Over: " + counter);
            break;
        }
        counter++;
        if (counter % 10 == 0 && showDelay > 100) {
            showDelay -= 100;
        }
        try {
            Thread.sleep(showDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    
public static void main(String[] args) {
		// TODO Auto-generated method stub
    new Boll().game();;
  	}
 }

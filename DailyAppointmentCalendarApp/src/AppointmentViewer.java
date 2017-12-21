import javax.swing.JFrame;

/*
 * Name: Tony Misic
 */
public class AppointmentViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new AppointmentFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Appointments");
      frame.setVisible(true);
   }
}

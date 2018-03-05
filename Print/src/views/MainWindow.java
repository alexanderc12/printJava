package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Ventana para demostrar el uso de servicio de impresión en PDF de un componente gráfico
 * @author Alexander
 *
 */
public class MainWindow extends JFrame{

	private static final String ICON_PATH = "/img/icon.png";
	private static final String TITLE = "My print";
	private static final long serialVersionUID = 1L;

	public MainWindow() {

		setTitle(TITLE);
		setIconImage(new ImageIcon(getClass().getResource(ICON_PATH)).getImage());
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JLabel lb = new JLabel("Hola mundo :v");
		lb.setFont(new Font("Arial", Font.BOLD, 40));
		add(lb, BorderLayout.CENTER);

		setVisible(true);
		print();
	}

	/**
	 * Metodo que permite imprimir en formato PDF un componente grafico de Java
	 * para este caso se toma el panel contenedor de la ventana en la linea 57.
	 * Tomado de la documentación oficial de JDK8
	 */
	public void print(){
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setPrintable (new Printable() {    
			public int print(Graphics pg, PageFormat pf, int pageNum){
				if (pageNum > 0){
					return Printable.NO_SUCH_PAGE;
				}
				Graphics2D g2 = (Graphics2D) pg;
				g2.translate(pf.getImageableX(), pf.getImageableY());
				getContentPane().paintAll(g2);
				return Printable.PAGE_EXISTS;
			}
		});
		if (!printerJob.printDialog())
			return;
		try {
			printerJob.print();
		} catch (PrinterException ex) {
			JOptionPane.showMessageDialog(this, "Error en la impresión", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new MainWindow();
	}
}
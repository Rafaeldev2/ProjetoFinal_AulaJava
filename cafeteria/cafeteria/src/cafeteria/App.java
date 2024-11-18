package cafeteria;

import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class App {

	public static void main(String[] args) {

		Locale.setDefault(Locale.forLanguageTag("pt-BR"));

        try {
			
			// Temas específicos de plataforma
        	//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        	//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        	//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

			// Temas multiplataforma
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

			// Caso queira usar, pode importar um tema de terceiro (similar como usamos para incluir o driver do JDBC)
			// https://github.com/JFormDesigner/FlatLaf

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Make sure we have nice window decorations.
				JFrame.setDefaultLookAndFeelDecorated(true);

				// Create and set up the window.
				TelaPrincipal frame = new TelaPrincipal();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// Display the window.
				frame.setVisible(true);
			}
		});
	}

}

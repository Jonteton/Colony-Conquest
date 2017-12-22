
package colonyconquest;


import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame {
    
    private final int WIDTH = 1280, HEIGHT = 720;
    JPanel mainPanel, subPanel;
    
    public Menu() {
        super("Menu");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        subPanel = new JPanel(new GridBagLayout());
        mainPanel = new JPanel(new BorderLayout());
        
        
        mainPanel.add(subPanel, NORTH);
        
        createButtons();
        createLayout();
        
        this.add(mainPanel);
        
    }
    
    private void createButtons()
    {
        JButton exitButton = new JButton("Exit");
        JButton serverButton = new JButton("Initiate server");
        JButton connectButton = new JButton("Connect to server");
        JButton optionsButton = new JButton("Options");
        
        subPanel.add(exitButton);
        subPanel.add(serverButton);
        subPanel.add(connectButton);
        subPanel.add(optionsButton);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        serverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            }

        });
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            }

        });
        optionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            }

        });
    }

    private void createLayout() {
        
    }

}

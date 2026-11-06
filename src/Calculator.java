import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener{

    private JTextField display;
    JButton ac;
    JButton backspaceButton;
    JButton percentButton;
    JButton divideButton;
    JButton number7; 
    JButton number8;
    JButton number9;
    JButton multiplyButton;  
    JButton number4;
    JButton number5;
    JButton number6;
    JButton subtractButton;   
    JButton number1; 
    JButton number2; 
    JButton number3;   
    JButton addButton;   
    JButton number0;   
    JButton decimalButton;   
    JButton signButton;   
    JButton equalsButton;   
    


    // ==== Constructor ====
    public Calculator(){

        // Configure the main window
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
    
        // Create the calculator display
        JPanel textFieldPanel = new JPanel(new BorderLayout());
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setEditable(false);

        textFieldPanel.add(display, BorderLayout.CENTER);

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 5,5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ac = new JButton("AC");
        backspaceButton  = new JButton(new ImageIcon("resources\\delete.png"));
        percentButton = new JButton("%");
        divideButton = new JButton("/");

        number7 = new JButton("7");
        number8 = new JButton("8");
        number9 = new JButton("9");
        multiplyButton = new JButton("x");

        number4 = new JButton("4");
        number5 = new JButton("5");
        number6 = new JButton("6");
        subtractButton = new JButton("-");

        number1 = new JButton("1");
        number2 = new JButton("2");
        number3 = new JButton("3");
        addButton = new JButton("+");

        number0 = new JButton("0");
        decimalButton = new JButton(".");
        signButton = new JButton("+/-");
        equalsButton = new JButton("=");

        equalsButton.setBackground(Color.ORANGE); 
        ac.setBackground(new Color(211, 47, 47)); 
        ac.setForeground(Color.WHITE);

        // Add buttons to the panel
        buttonsPanel.add(ac);
        buttonsPanel.add(backspaceButton );
        buttonsPanel.add(percentButton);
        buttonsPanel.add(divideButton);
        buttonsPanel.add(number7);
        buttonsPanel.add(number8);
        buttonsPanel.add(number9);
        buttonsPanel.add(multiplyButton);
        buttonsPanel.add(number4);
        buttonsPanel.add(number5);
        buttonsPanel.add(number6);
        buttonsPanel.add(subtractButton);
        buttonsPanel.add(number1);
        buttonsPanel.add(number2);
        buttonsPanel.add(number3);
        buttonsPanel.add(addButton);
        buttonsPanel.add(number0);
        buttonsPanel.add(decimalButton);
        buttonsPanel.add(signButton);
        buttonsPanel.add(equalsButton);

        ac.addActionListener(this);
        backspaceButton .addActionListener(this);
        signButton.addActionListener(this);
        percentButton.addActionListener(this);
        divideButton.addActionListener(this);
        number7.addActionListener(this);
        number8.addActionListener(this);
        number9.addActionListener(this);
        multiplyButton.addActionListener(this);
        number4.addActionListener(this);
        number5.addActionListener(this);
        number6.addActionListener(this);
        subtractButton.addActionListener(this);
        number1.addActionListener(this);
        number2.addActionListener(this);
        number3.addActionListener(this);
        addButton.addActionListener(this);
        number0.addActionListener(this);
        decimalButton.addActionListener(this);
        equalsButton.addActionListener(this);

        // Add components to the window
        this.add(textFieldPanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);

        // Display the window
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // ==== Main Method ====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new Calculator());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        // Handle number button clicks
        if (command.matches("[0-9]")) {

            if (display.getText().equals("0")) {
                display.setText(command);  
            }else{
                display.setText(display.getText()+command);
            }
        }
        
    }

}

